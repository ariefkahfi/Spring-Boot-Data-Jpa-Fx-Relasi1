package com.arief.controllers.dialogsController;

import com.arief.config.AbstractFxController;
import com.arief.entity.Jabatan;
import com.arief.entity.Karyawan;
import com.arief.services.FxServices.FxServiceDatabaseTransactionForJabatan;
import com.arief.services.KaryawanServices.KaryawanServiceDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Arief on 8/29/2017.
 */
@Component
public class DialogUbahJabatanController extends AbstractFxController {

    @FXML
    private TextField tampilKodeKaryawan,tampilNamaKaryawan,tampilKodeJabatan,tampilNamaJabatan;

    @FXML
    private Button bUbahJabatan ;

    @Autowired
    private FxServiceDatabaseTransactionForJabatan fxJabatan;
    @Autowired
    private KaryawanServiceDAO karyawanServiceDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void refreshTexts(){}

    public void setTextForKaryawanAndJabatan(Karyawan k , Jabatan j){
        if(j==null){

            tampilKodeKaryawan.setText(k.getKodeKaryawan());
            tampilNamaKaryawan.setText(k.getNamaKaryawan());

            tampilKodeJabatan.setText("-");
            tampilNamaJabatan.setText("-");
        }else{
            tampilKodeKaryawan.setText(k.getKodeKaryawan());
            tampilNamaKaryawan.setText(k.getNamaKaryawan());

            tampilKodeJabatan.setText(j.getKodeJabatan());
            tampilNamaJabatan.setText(j.getNamaJabatan());
        }
    }

    private void buatChoiceDialogUntukPindahJabatan(){
        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>();
        choiceDialog.setContentText("Pilih Kode jabatan yang baru : ");

        fxJabatan.setChoiceDialogItemsForKodeJabatan(choiceDialog);

        String kodeJabatan = null;

        Optional<String> opt = choiceDialog.showAndWait();

        if(opt.isPresent()){
            kodeJabatan = opt.get();
            System.err.println(kodeJabatan);
        }

        try{

            if (kodeJabatan != null && !kodeJabatan.equals("")) {
                Karyawan k = karyawanServiceDAO.findByKodeKaryawan(tampilKodeKaryawan.getText().trim());
                fxJabatan.actionPindahJabatan(k, kodeJabatan);
                setTextForKaryawanAndJabatan(k, k.getJabatan());
            }
        } catch (NullPointerException ex){
            fxJabatan.buatAlertDialog("Maaf Jabatan belum dipilih", Alert.AlertType.ERROR,null);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void doUbahJabatan(ActionEvent ev){
        buatChoiceDialogUntukPindahJabatan();
    }
}
