package com.arief.controllers;

import com.arief.config.AbstractFxController;
import com.arief.controllers.divisi.FormSimpanDivisiController;
import com.arief.controllers.divisi.ListDataDivisiController;
import com.arief.controllers.jabatan.FormSimpanJabatanController;
import com.arief.controllers.jabatan.ListDataJabatanController;
import com.arief.controllers.karyawan.FormSimpanKaryawanController;
import com.arief.controllers.karyawan.ListDataKaryawanController;
import com.arief.entity.Divisi;
import com.arief.entity.Jabatan;
import com.arief.entity.Karyawan;
import com.arief.entity.enums.Gender;
import com.arief.services.DivisiServices.DivisiServiceDAO;
import com.arief.services.JabatanServices.JabatanServiceDAO;
import com.arief.services.KaryawanServices.KaryawanServiceDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Arief on 8/28/2017.
 */
@Component
public class HomeController extends AbstractFxController{

    @FXML
    private Hyperlink linkSimpanJabatan,linkSimpanDivisi,linkSimpanKaryawan;
    @FXML
    private MenuItem menuListKaryawan,menuListDivisi,menuListJabatan;
    @FXML
    private MenuBar menuBar;

    @Autowired
    private KaryawanServiceDAO karyawanServiceDAO;
    @Autowired
    private DivisiServiceDAO divisiServiceDAO;
    @Autowired
    private JabatanServiceDAO jabatanServiceDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     }

    //ActionEventHandler untuk Hyperlinks
    public void simpanKaryawan(ActionEvent ev){
        changeScene((Stage)((Node)ev.getSource()).getScene().getWindow(), FormSimpanKaryawanController.class,
                "/scene-karyawan/form-karyawan.fxml");
    }
    public void simpanJabatan(ActionEvent ev){
        changeScene((Stage)((Node)ev.getSource()).getScene().getWindow(), FormSimpanJabatanController.class,
                "/scene-jabatan/form-jabatan.fxml");
    }
    public void simpanDivisi(ActionEvent ev){
        Stage st = (Stage)((Node)ev.getSource()).getScene().getWindow();
        changeScene(st, FormSimpanDivisiController.class,"/scene-divisi/form-divisi.fxml");
    }
    //ActionEventHandler untuk Hyperlinks


    private Stage getStageInstance(){
        return (Stage)menuBar.getScene().getWindow();
    }

    //ActionEvent untuk MenuBar Item

    //Kylie c853affc-e9c2-46a8-b92f-0b6ef8eb8b4f

    public void testSaveKaryawanRepo(){

    }

    public void showListKaryawan(){
         if(getStageInstance()!=null)
            changeScene(getStageInstance(), ListDataKaryawanController.class,"/scene-karyawan/list-karyawan.fxml");

    }
    public void showListJabatan(){
        changeScene(getStageInstance(), ListDataJabatanController.class,"/scene-jabatan/list-jabatan.fxml");
    }

    public void showListDivisi(){
        changeScene(getStageInstance(), ListDataDivisiController.class,"/scene-divisi/list-divisi.fxml");
    }
    //ActionEvent untuk MenuBar Item

}
