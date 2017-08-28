package com.arief.controllers;

import com.arief.config.AbstractFxController;
import com.arief.controllers.divisi.FormSimpanDivisiController;
import com.arief.controllers.jabatan.FormSimpanJabatanController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //ActionEventHandler untuk Hyperlinks
    public void simpanKaryawan(ActionEvent ev){

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



    //ActionEvent untuk MenuBar Item
    public void showListKaryawan(){

    }
    public void showListJabatan(){

    }
    public void showListDivisi(){

    }
    //ActionEvent untuk MenuBar Item

}
