package com.arief.controllers.jabatan;

import com.arief.config.AbstractFxController;
import com.arief.entity.Jabatan;
import com.arief.entity.Karyawan;
import com.arief.services.DivisiServices.DivisiServiceDAO;
import com.arief.services.FxServices.FxServiceDatabaseTransactionForDivisi;
import com.arief.services.FxServices.FxServiceDatabaseTransactionForJabatan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private FxServiceDatabaseTransactionForJabatan fxJabatan;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void doSimpanJabatan(ActionEvent ev){
        Jabatan jab = new Jabatan(fieldKodeJabatan.getText().trim(),fieldNamaJabatan.getText().trim(),new ArrayList<>());
        fxJabatan.ActionDoSimpanJabatan(jab,fieldKodeJabatan,fieldNamaJabatan);
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
