package com.arief.controllers.karyawan;

import com.arief.config.AbstractFxController;
import com.arief.entity.Divisi;
import com.arief.entity.Jabatan;
import com.arief.entity.Karyawan;
import com.arief.entity.enums.Gender;
import com.arief.services.DivisiServices.DivisiServiceDAO;
import com.arief.services.FxServices.FxServiceDatabaseTransactionForKaryawan;
import com.arief.services.JabatanServices.JabatanServiceDAO;
import com.arief.services.KaryawanServices.KaryawanServiceDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Arief on 8/28/2017.
 */
@Component
public class FormSimpanKaryawanController extends AbstractFxController{

    @FXML
    private RadioButton rButtonMale,rButtonFemale;
    @FXML
    private TextField fieldKodeKaryawan,fieldNamaKaryawan;
    @FXML
    private ChoiceBox<String> cBoxJabatan,cBoxDivisi;

    @FXML
    private Button bSimpanDataKaryawan,bMainMenu,bDataKaryawan;

    @Autowired
    private FxServiceDatabaseTransactionForKaryawan fxKaryawan;

    @Autowired
    private JabatanServiceDAO jabatanServiceDAO;
    @Autowired
    private DivisiServiceDAO divisiServiceDAO;
    @Autowired
    private KaryawanServiceDAO karyawanServiceDAO;


    private ToggleGroup tg;
    private Gender gender;

    private Jabatan j;
    private Divisi d;


    private void setUpForToggleGroup(){
        tg = new ToggleGroup();
        rButtonMale.setToggleGroup(tg);
        rButtonFemale.setToggleGroup(tg);
    }




    private void setUpChoiceBoxes(){
        fxKaryawan.setUpForChoiceBoxKodeDivisi(cBoxDivisi);
        fxKaryawan.setUpForChoiceBoxKodeJabatan(cBoxJabatan);
    }

   private String kodeDivisi ;
   private String kodeJabatan;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpForToggleGroup();
        setUpChoiceBoxes();
        gender = null;
    }

    private Divisi returnForSelectedItemCBoxDivisi(){
        String kodeDivisi = cBoxDivisi.getSelectionModel().getSelectedItem();

        return divisiServiceDAO.findOneByKodeDivisi(kodeDivisi);

    }

    private Jabatan returnForSelectedItemCBoxJabatan(){
        String kodeJabatan = cBoxJabatan.getSelectionModel().getSelectedItem();
        return jabatanServiceDAO.getOneByKodeJabatan(kodeJabatan);
    }

    private Gender getGenderForSelectedToggle(){
        RadioButton rb = (RadioButton)tg.getSelectedToggle();


        if(rb!=null && rb.getText().equals("Male")){
           return gender = Gender.Male;
        }else if(rb!=null && rb.getText().equals("Female")){
           return gender = Gender.Female;
        }
        return null;
    }


    public void testSimpanLagi(){


        if(getGenderForSelectedToggle()==null
                ||returnForSelectedItemCBoxDivisi()==null
                ||returnForSelectedItemCBoxJabatan()==null
                ||fieldKodeKaryawan.getText().equals("")
                ||fieldNamaKaryawan.getText().equals("")){
            System.err.println("masih ada form yang kosong");
        }else{
            Karyawan k = new Karyawan(
                    fieldKodeKaryawan.getText().trim(),
                    fieldNamaKaryawan.getText().trim(),
                    getGenderForSelectedToggle(),
                    returnForSelectedItemCBoxDivisi(),
                    returnForSelectedItemCBoxJabatan()
            );
            fxKaryawan.saveKaryawanTestInner(k);
        }
    }





    public void doSimpanKaryawan(ActionEvent ev){
        /*if(gender==null || fieldKodeKaryawan.getText().trim().equals("") || fieldNamaKaryawan.getText().trim().equals("")){
        }*/
        //Karyawan k = new Karyawan(fieldKodeKaryawan.getText().trim(),fieldNamaKaryawan.getText().trim(),gender,d,j);

        //Jabatan j = returnForSelectedItemCBoxJabatan();
        //Divisi d = returnForSelectedItemCBoxDivisi();

        Gender g = getGenderForSelectedToggle();

        if(g==null  || fieldKodeKaryawan.getText().trim().equals("")
                    || fieldNamaKaryawan.getText().trim().equals("")
                    || cBoxJabatan.getValue() == null
                    || cBoxDivisi.getValue() == null){
            fxKaryawan.buatAlertDialog("Masih ada bagian form yang kosong", Alert.AlertType.ERROR,null);
        }else{
            String kodeJabatan =cBoxJabatan.getValue();
            String kodeDivisi = cBoxDivisi.getValue();

            System.err.println(kodeJabatan);
            System.err.println(kodeDivisi);

                fxKaryawan.ActionSaveKaryawan(fieldKodeKaryawan,
                        fieldNamaKaryawan,g,kodeDivisi,kodeJabatan);
                refreshAll();

            //fxKaryawan.ActionSaveKaryawan(fieldKodeKaryawan,fieldNamaKaryawan,g,d.getKodeDivisi()
            //        ,j.getKodeJabatan());
            //refreshAll();
        }



    }



    private void refreshAllFields(){
        fieldNamaKaryawan.setText("");
        fieldKodeKaryawan.setText("");
    }

    private void refreshAll(){
        refreshAllFields();
        refreshToggleAndChoiceBox();
    }

    private void refreshToggleAndChoiceBox(){
        gender = null;
        cBoxDivisi.getSelectionModel().clearSelection();
        cBoxJabatan.getSelectionModel().clearSelection();
        tg.selectToggle(null);
    }

    public void goMainMenu(ActionEvent ev){
        sceneReloadToMainMenu((Stage)((Node)ev.getSource()).getScene().getWindow());
    }

    public void goDataKaryawan(ActionEvent ev){
        changeScene((Stage)((Node)ev.getSource()).getScene().getWindow(),ListDataKaryawanController.class,
                "/scene-karyawan/list-karyawan.fxml");
    }

}
