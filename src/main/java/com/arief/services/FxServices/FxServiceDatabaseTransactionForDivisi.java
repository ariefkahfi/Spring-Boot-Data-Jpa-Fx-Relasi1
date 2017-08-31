package com.arief.services.FxServices;

import com.arief.entity.Divisi;
import com.arief.entity.Karyawan;
import com.arief.services.DivisiServices.DivisiServiceDAO;
import com.arief.services.KaryawanServices.KaryawanServiceDAO;
import com.arief.services.repositories.DivisiRepo;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Arief on 8/28/2017.
 */
@Component
public class FxServiceDatabaseTransactionForDivisi {

    @Autowired
    private DivisiServiceDAO divDAO;
    @Autowired
    private KaryawanServiceDAO karyawanServiceDAO;
    @Autowired
    private DivisiRepo divRepo;


    public void setListViewValue(ListView<String> namaKaryawans,String kodeDivisi){
        List<String> karList = divDAO.getNamaKaryawanByDivisiKode(kodeDivisi);
        namaKaryawans.setItems(FXCollections.observableList(karList));
    }

    public void actionPindahDivisi(Karyawan k,String kodeDivisi){
        try {
            if (k != null && kodeDivisi != null) {
                Divisi divisiBaru = divRepo.findByKodeDivisi(kodeDivisi);
                k.setDivisi(divisiBaru);

                if(!divRepo.exists(divisiBaru.getId())){
                    buatAlertDialog("Data divisi dengan kode tersebut tidak ada", Alert.AlertType.ERROR);
                }else{
                    karyawanServiceDAO.simpan(k);
                    buatAlertDialog("Karyawan pindah divisi sukses", Alert.AlertType.CONFIRMATION);
                }

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void buatAlertDialog(String s, Alert.AlertType error) {
        Alert alert = new Alert(error);

        alert.setTitle("Spring Boot jpa Fx Dialog");
        alert.setContentText(s);

        alert.show();
    }

    public void setChoiceDialogItems(ChoiceDialog<String> choice){
        choice.getItems().clear();
        choice.getItems().addAll(divDAO.getAllOnlyKodeDivisi());
    }

}
