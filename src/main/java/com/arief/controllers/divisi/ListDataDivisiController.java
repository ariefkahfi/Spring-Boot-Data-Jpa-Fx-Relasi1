package com.arief.controllers.divisi;

import com.arief.config.AbstractFxController;
import com.arief.entity.Divisi;
import com.arief.services.DivisiServices.DivisiServiceDAO;
import com.arief.services.FxServices.ListViewForKaryawanList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.*;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Arief on 8/28/2017.
 */
@Component
public class ListDataDivisiController extends AbstractFxController{

    @FXML
    private TextField tampilNamaDivisi,tampilTanggalDivisi,tampilKodeDivisi;
    @FXML
    private TableView<Divisi> tabelDivisi;
    @FXML
    private Button bMainMenu,bFormDivisi;
    @FXML
    private TableColumn<Divisi,String> colKodeDivisi;
    @FXML
    private TableColumn<Divisi,String> colNamaDivisi;
    @FXML
    private TableColumn<Divisi,Date> colTglDivisi;
    @FXML
    private ListView<String> namaKaryawans;

    @Autowired
    private DivisiServiceDAO divisiServiceDAO;
    @Autowired
    private ListViewForKaryawanList listDAO;


    private ContextMenu contextMenuForTableView(TableView tv){
        ContextMenu cm = new ContextMenu();

        MenuItem itemOne = new MenuItem("Clear Selection");

        itemOne.setOnAction(e->{
            tv.getSelectionModel().clearSelection();
            refreshFields();
        });


        cm.getItems().add(itemOne);

        tv.setContextMenu(cm);

        return cm;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpTableColums();
        refreshTableView();
        contextMenuForTableView(tabelDivisi);

        tabelDivisi.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Divisi>() {
            @Override
            public void changed(ObservableValue<? extends Divisi> observable, Divisi oldValue, Divisi newValue) {
                if(newValue!=null){
                    setUpDivisiValueForLabelsWhenTableViewItemSelected(newValue);
                    String kodeDivisi = newValue.getKodeDivisi();
                    setUpListViewForNamaKaryawan(kodeDivisi);
                }
            }
        });
    }


    private void setUpListViewForNamaKaryawan(String kodeDivisi){
        listDAO.setListViewValue(namaKaryawans,kodeDivisi);
    }

    private void refreshFields(){
        tampilKodeDivisi.setText("");
        tampilNamaDivisi.setText("");
        tampilTanggalDivisi.setText("");
        namaKaryawans.setItems(null);
    }

    private void setUpDivisiValueForLabelsWhenTableViewItemSelected(Divisi d){
        tampilKodeDivisi.setText(d.getKodeDivisi());
        tampilNamaDivisi.setText(d.getNamaDivisi());
        tampilTanggalDivisi.setText(d.getTglBuat().toString());
    }

    private void refreshTableView(){
        List<Divisi> divs = divisiServiceDAO.getAll();
        tabelDivisi.getItems().clear();
        tabelDivisi.getItems().addAll(divs);
    }

    private void setUpTableColums(){
        colKodeDivisi.setCellValueFactory(new PropertyValueFactory<>("kodeDivisi"));
        colNamaDivisi.setCellValueFactory(new PropertyValueFactory<>("namaDivisi"));
        colTglDivisi.setCellValueFactory(new PropertyValueFactory<>("tglBuat"));
    }

    public void showMainMenu(ActionEvent ev){
        Stage st = (Stage)((Node)ev.getSource()).getScene().getWindow();
        sceneReloadToMainMenu(st);
    }

    public void showFormDivisi(ActionEvent ev){
        changeScene(
                (Stage)((Node)ev.getSource()).getScene().getWindow(),
                FormSimpanDivisiController.class,
                "/scene-divisi/form-divisi.fxml");
    }
}
