package com.arief.controllers.jabatan;

import com.arief.config.AbstractFxController;
import com.arief.entity.Jabatan;
import com.arief.services.FxServices.FxServiceDatabaseTransactionForJabatan;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Arief on 8/28/2017.
 */
@Component
public class ListDataJabatanController  extends AbstractFxController{

    @FXML
    private TextField tampilNamaJabatan,tampilKodeJabatan;
    @FXML
    private TableView<Jabatan> tabelJabatan;
    @FXML
    private TableColumn<Jabatan,String> colKodeJabatan,colNamaJabatan;

    @FXML
    private Button bMainMenu,bLihatJabatanKaryawan,bFormDataJabatan;

    @Autowired
    private FxServiceDatabaseTransactionForJabatan fxJabatan;


    private void createContextMenu(){
        ContextMenu cm = new ContextMenu();

        MenuItem itemOne = new MenuItem("Clear select table");

        itemOne.setOnAction(e->{
            tabelJabatan.getSelectionModel().clearSelection();
            clearFields();
        });

        MenuItem itemTwo = new MenuItem("Refresh tabel jabatan");

        itemTwo.setOnAction(e->{
            refreshTable();
            clearFields();
        });

        cm.getItems().addAll(itemOne,itemTwo);

        tabelJabatan.setContextMenu(cm);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpColumns();
        refreshTable();
        createContextMenu();

        tabelJabatan.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Jabatan>() {
            @Override
            public void changed(ObservableValue<? extends Jabatan> observable, Jabatan oldValue, Jabatan newValue) {
                if(newValue!=null){
                    setTextForSelectedTableRow(newValue);
                }
            }
        });
    }


    private void setTextForSelectedTableRow(Jabatan j){
        tampilKodeJabatan.setText(j.getKodeJabatan());
        tampilNamaJabatan.setText(j.getNamaJabatan());
    }

    private void clearFields(){
        tampilKodeJabatan.setText("");
        tampilNamaJabatan.setText("");
    }

    private void refreshTable(){
        fxJabatan.loadDataJabatanIntoTableView(tabelJabatan);
    }

    private void setUpColumns(){
        colKodeJabatan.setCellValueFactory(new PropertyValueFactory<>("kodeJabatan"));
        colNamaJabatan.setCellValueFactory(new PropertyValueFactory<>("namaJabatan"));
    }

    public void doMainMenu(ActionEvent ev){
        sceneReloadToMainMenu((Stage)((Node)ev.getSource()).getScene().getWindow());
    }
    public void showJabatanKaryawan(ActionEvent ev){
        if(!tampilKodeJabatan.getText().trim().equals("") || tampilKodeJabatan!=null){
            String kodeJabatan = tampilKodeJabatan.getText().trim();
            fxJabatan.showListViewForNamaKaryawanYangAdaDiJabatanIni(kodeJabatan);
        }
    }
    public void goFormDataJabatan(ActionEvent ev){
        changeScene((Stage)((Node)ev.getSource()).getScene().getWindow(),FormSimpanJabatanController.class,
                "/scene-jabatan/form-jabatan.fxml");
    }
}
