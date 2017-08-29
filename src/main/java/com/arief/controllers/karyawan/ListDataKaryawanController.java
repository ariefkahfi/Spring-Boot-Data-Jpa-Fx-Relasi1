package com.arief.controllers.karyawan;

import com.arief.config.AbstractFxController;
import com.arief.controllers.dialogsController.DialogPindahDivisiController;
import com.arief.controllers.dialogsController.DialogUbahJabatanController;
import com.arief.controllers.divisi.ListDataDivisiController;
import com.arief.controllers.jabatan.ListDataJabatanController;
import com.arief.entity.Divisi;
import com.arief.entity.Jabatan;
import com.arief.entity.Karyawan;
import com.arief.entity.enums.Gender;
import com.arief.services.FxServices.FxServiceDatabaseTransactionForKaryawan;
import com.arief.services.JabatanServices.JabatanServiceDAO;
import com.arief.services.KaryawanServices.KaryawanServiceDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Arief on 8/28/2017.
 */
@Component
public class ListDataKaryawanController extends AbstractFxController{


    @FXML
    private TextField tampilKodeKaryawan,tampilNamaKaryawan,tampilGenderKaryawan;

    @FXML
    private TableView<Karyawan> tabelKaryawan;


    @FXML
    private TableColumn<Karyawan,String> colKodeKaryawan,colNamaKaryawan;
    @FXML
    private TableColumn<Karyawan,Gender> colGenderKaryawan;

    @FXML
    private TableColumn colJabatan,colDivisi;


    @Autowired
    private FxServiceDatabaseTransactionForKaryawan fxKaryawan;
    @Autowired
    private KaryawanServiceDAO karyawanServiceDAO;
    @FXML
    private Button bDataJabatan,bMainMenu,bDataDivisi,bFormSimpanKaryawan;


    private void setUpColumnsKaryawanParameterizedTypes(){
        colKodeKaryawan.setCellValueFactory(new PropertyValueFactory<>("kodeKaryawan"));
        colNamaKaryawan.setCellValueFactory(new PropertyValueFactory<>("namaKaryawan"));
        colGenderKaryawan.setCellValueFactory(new PropertyValueFactory<>("gender"));

    }

    private void setUpColumForJabatanAndDivisi(){
        colJabatan.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new TableCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if(!empty){
                            Button infoJabatan = new Button("Jabatan");
                            infoJabatan.setOnAction(e->{
                                if(!tampilKodeKaryawan.getText().trim().equals("")){
                                    Karyawan k = karyawanServiceDAO.findByKodeKaryawan(tampilKodeKaryawan.getText().trim());
                                    Jabatan j = k.getJabatan();

                                    DialogUbahJabatanController dialogJabatan = context.getBean(DialogUbahJabatanController.class);
                                    Node dialogNode = dialogJabatan.initNodeForView("/scene-dialog/dialog-jabatan.fxml");

                                    dialogJabatan.setTextForKaryawanAndJabatan(k,j);

                                    fxKaryawan.buatDialog(dialogNode);

                                }
                            });
                            setAlignment(Pos.CENTER);
                            setGraphic(infoJabatan);
                        }
                    }
                };
            }
        });
        colDivisi.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {

                return new TableCell(){

                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if(!empty){
                            Button infoDivisi = new Button("Divisi");
                            infoDivisi.setOnAction(e->{
                                if(!tampilKodeKaryawan.getText().trim().equals("")){
                                    Karyawan k = karyawanServiceDAO.findByKodeKaryawan(tampilKodeKaryawan.getText().trim());
                                    Divisi d = k.getDivisi();


                                    DialogPindahDivisiController pindahDivisiController = context.getBean(DialogPindahDivisiController.class);
                                    Node nodeDialog = pindahDivisiController.initNodeForView("/scene-dialog/dialog-divisi.fxml");

                                    pindahDivisiController.setTextForKaryawanAndDivisiDetailsInfo(k,d);
                                    fxKaryawan.buatDialog(nodeDialog);

                                }
                            });
                            setAlignment(Pos.CENTER);
                            setGraphic(infoDivisi);
                        }
                    }
                };
            }
        });

    }

    private void setUpAllColumns(){
        setUpColumnsKaryawanParameterizedTypes();
        setUpColumForJabatanAndDivisi();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpAllColumns();
        refreshTabel();
        createContextMenu();
        tabelKaryawan.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Karyawan>() {
            @Override
            public void changed(ObservableValue<? extends Karyawan> observable, Karyawan oldValue, Karyawan newValue) {
                if(newValue!=null){
                    setLabelForSelectedTableView(newValue);
                }
            }
        });
    }

    private void setLabelForSelectedTableView(Karyawan k){
        tampilKodeKaryawan.setText(k.getKodeKaryawan());
        tampilNamaKaryawan.setText(k.getNamaKaryawan());
        tampilGenderKaryawan.setText(k.getGender().toString());
    }

    private void refreshFields(){
        tampilKodeKaryawan.setText("");
        tampilNamaKaryawan.setText("");
        tampilGenderKaryawan.setText("");
    }

    private void refreshAll(){
        refreshTabel();
        refreshFields();
    }

    private void createContextMenu(){
        ContextMenu cm = new ContextMenu();

        MenuItem itemOne = new MenuItem("Clear selection table");
        itemOne.setOnAction(e->{
            tabelKaryawan.getSelectionModel().clearSelection();
            refreshFields();
        });
        MenuItem itemTwo = new MenuItem("Refresh tabel karyawan");
        itemTwo.setOnAction(e->{
            refreshAll();
        });

        cm.getItems().addAll(itemOne,itemTwo);

        tabelKaryawan.setContextMenu(cm);
    }

    public void goDataDivisi(ActionEvent ev){
        changeScene((Stage)((Node)ev.getSource()).getScene().getWindow(), ListDataDivisiController.class,"/scene-divisi/list-divisi.fxml");
    }

    public void goDataJabatan(ActionEvent ev){
        changeScene((Stage)((Node)ev.getSource()).getScene().getWindow(), ListDataJabatanController.class,"/scene-jabatan/list-jabatan.fxml");
    }

    public void goFormKaryawan(ActionEvent ev){
        changeScene((Stage)((Node)ev.getSource()).getScene().getWindow(),FormSimpanKaryawanController.class,"/scene-karyawan/form-karyawan.fxml");
    }

    public void goMainMenu(ActionEvent ev){
        sceneReloadToMainMenu((Stage)((Node)ev.getSource()).getScene().getWindow());
    }

    private  void refreshTabel(){
        fxKaryawan.loadDataintoTableView(tabelKaryawan);
    }

}
