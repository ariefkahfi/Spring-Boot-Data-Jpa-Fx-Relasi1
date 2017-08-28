package com.arief.controllers.jabatan;

import com.arief.config.AbstractFxController;
import com.arief.entity.Jabatan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void doMainMenu(ActionEvent ev){
        sceneReloadToMainMenu((Stage)((Node)ev.getSource()).getScene().getWindow());
    }
    public void showJabatanKaryawan(ActionEvent ev){
        //Belum Implementasi..
    }
    public void goFormDataJabatan(ActionEvent ev){
        changeScene((Stage)((Node)ev.getSource()).getScene().getWindow(),FormSimpanJabatanController.class,
                "/scene-jabatan/form-jabatan.fxml");
    }
}
