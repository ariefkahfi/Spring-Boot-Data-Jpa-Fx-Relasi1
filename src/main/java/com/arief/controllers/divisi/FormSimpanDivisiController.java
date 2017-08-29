package com.arief.controllers.divisi;

import com.arief.config.AbstractFxController;
import com.arief.entity.Divisi;
import com.arief.services.DivisiServices.DivisiServiceDAO;
import com.arief.services.FxServices.FxServiceDatabaseTransactionForDivisi;
import com.arief.services.KaryawanServices.KaryawanServiceDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Arief on 8/28/2017.
 */
@Component
public class FormSimpanDivisiController extends AbstractFxController{

    @FXML
    private Label labelTanggalDivisi;



    @FXML
    private TextField fieldNamaDivisi,fieldKodeDivisi;


    @FXML
    private Button bSimpanDivisi,bMainMenu,bLihatDataDivisi;

    @Autowired
    private DivisiServiceDAO divisiServiceDAO;
    @Autowired
    private EntityManager em;
    @Autowired
    private KaryawanServiceDAO karyawanServiceDAO;
    @Autowired
    private FxServiceDatabaseTransactionForDivisi list;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //coba


    }


    private Date setUpForLabelTanggal(){
        Date d = new Date();
        labelTanggalDivisi.setText(d.toString());

        return d;
    }

    public void doSimpanDivisi(ActionEvent ev){
        try{
            if(fieldNamaDivisi.getText().equals("") || fieldKodeDivisi.getText().equals("")){
                buatDialog("Masih ada field yang kosong", Alert.AlertType.INFORMATION);
            }else{
                Date d = setUpForLabelTanggal();
                Divisi divisi = new Divisi(fieldKodeDivisi.getText().trim(),fieldNamaDivisi.getText().trim(),d,new ArrayList<>());

                divisiServiceDAO.simpan(divisi);

                buatDialog("Data Divisi berhasil disimpan", Alert.AlertType.CONFIRMATION);
                refreshFields();
            }
        } catch (DuplicateKeyException dup){
            buatDialog("Kode divisi sudah ada", Alert.AlertType.ERROR);
            refreshFields();
        } catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void refreshFields(){
        fieldKodeDivisi.setText("");
        fieldNamaDivisi.setText("");
        labelTanggalDivisi.setText("");
    }

    public void buatDialog(String contentText, Alert.AlertType a){
        Alert alert = new Alert(a);
        alert.setTitle("Spring boot Dialog");
        alert.setContentText(contentText);

        alert.show();
    }

    public void doMainMenu(ActionEvent ev){
        sceneReloadToMainMenu(ev);
    }

    public void sceneReloadToMainMenu(ActionEvent ev){
        Stage st = (Stage) ((Node)ev.getSource()).getScene().getWindow();
        sceneReloadToMainMenu(st);
    }

    public void changeToAnotherScene(ActionEvent ev , Class<? extends  AbstractFxController> c , String fxml){
        Stage st= (Stage)((Node)ev.getSource()).getScene().getWindow();
        changeScene(st,c,fxml);
    }

    public void goDataDivisi(ActionEvent ev){
        changeToAnotherScene(ev,ListDataDivisiController.class,"/scene-divisi/list-divisi.fxml");
    }

}
