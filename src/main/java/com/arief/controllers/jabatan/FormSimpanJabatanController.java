package com.arief.controllers.jabatan;

import com.arief.config.AbstractFxController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
public class FormSimpanJabatanController extends AbstractFxController{

    @FXML
    private TextField fieldKodeJabatan,fieldNamaJabatan;
    @FXML
    private Button bSimpanDataJabatan,bMainMenu,bListDataJabatan;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void doSimpanJabatan(ActionEvent ev){
        //Belum Implementasi
    }

    public void doMainMenu(ActionEvent ev){
        Stage st = (Stage) ((Node)ev.getSource()).getScene().getWindow();
        sceneReloadToMainMenu(st);
    }

    public void goDataJabatan(ActionEvent ev){
        changeScene((Stage)((Node)ev.getSource()).getScene().getWindow(),ListDataJabatanController.class,
                "/scene-jabatan/list-jabatan.fxml");
    }
}
