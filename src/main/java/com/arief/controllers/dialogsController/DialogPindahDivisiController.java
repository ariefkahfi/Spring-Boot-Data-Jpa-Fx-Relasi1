package com.arief.controllers.dialogsController;

import com.arief.config.AbstractFxController;
import com.arief.entity.Divisi;
import com.arief.entity.Karyawan;
import com.arief.services.DivisiServices.DivisiServiceDAO;
import com.arief.services.FxServices.FxServiceDatabaseTransactionForDivisi;
import com.arief.services.FxServices.FxServiceDatabaseTransactionForKaryawan;
import com.arief.services.KaryawanServices.KaryawanServiceDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Arief on 8/29/2017.
 */
@Component
public class DialogPindahDivisiController extends AbstractFxController {

    @FXML
    private TextField tampilKodeKaryawan,tampilNamaKaryawan,tampilKodeDivisi,tampilNamaDivisi;

    @FXML
    private Button bPindahDivisi;


    @Autowired
    private FxServiceDatabaseTransactionForDivisi fxDivisi;
    @Autowired
    private KaryawanServiceDAO karyawanServiceDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void refreshText(){

    }

    public void setTextForKaryawanAndDivisiDetailsInfo(Karyawan k , Divisi d){

      if(d==null){

          tampilKodeKaryawan.setText(k.getKodeKaryawan());
          tampilNamaKaryawan.setText(k.getNamaKaryawan());

          tampilKodeDivisi.setText("-");
          tampilNamaDivisi.setText("-");
      }else{
          tampilKodeKaryawan.setText(k.getKodeKaryawan());
          tampilNamaKaryawan.setText(k.getNamaKaryawan());

          tampilKodeDivisi.setText(d.getKodeDivisi());
          tampilNamaDivisi.setText(d.getNamaDivisi());
      }
    }



    private void buatChoiceDialogForKodeDivisi(){
        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>();
        fxDivisi.setChoiceDialogItems(choiceDialog);

        choiceDialog.setContentText("Pilih Kode Divisi baru : ");
        String kodeDivisi = null;

        Optional<String> opt = choiceDialog.showAndWait();

        if(opt.isPresent()){
            kodeDivisi = opt.get();
        }

        try {
            if (kodeDivisi != null && !kodeDivisi.equals("")) {
                Karyawan k = karyawanServiceDAO.findByKodeKaryawan(tampilKodeKaryawan.getText().trim());
                fxDivisi.actionPindahDivisi(k, kodeDivisi);
                setTextForKaryawanAndDivisiDetailsInfo(k, k.getDivisi());
            }
        } catch (NullPointerException ex){
            System.err.println(ex.getMessage() + "NULL");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void doPindahDivisi(ActionEvent ev){
        buatChoiceDialogForKodeDivisi();
    }
}
