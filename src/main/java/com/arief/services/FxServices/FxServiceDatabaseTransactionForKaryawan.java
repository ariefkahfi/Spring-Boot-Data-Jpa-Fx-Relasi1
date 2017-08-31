package com.arief.services.FxServices;

import com.arief.entity.Divisi;
import com.arief.entity.Jabatan;
import com.arief.entity.Karyawan;
import com.arief.entity.enums.Gender;
import com.arief.services.KaryawanServices.KaryawanServiceDAO;
import com.arief.services.repositories.DivisiRepo;
import com.arief.services.repositories.JabatanRepo;
import com.arief.services.repositories.KaryawanRepo;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Arief on 8/29/2017.
 */
@Component
public class FxServiceDatabaseTransactionForKaryawan {

    @Autowired
    private KaryawanServiceDAO karyawanServiceDAO;
    @Autowired
    private DivisiRepo divRepo;
    @Autowired
    private JabatanRepo jabRepo;
    @Autowired
    private KaryawanRepo karRepo;


    private TaskExecutor task;

    @Transactional
    public void saveKaryawanTestInner(Karyawan k){
        Divisi d= divRepo.findByKodeDivisi(k.getDivisi().getKodeDivisi());
        Jabatan j = jabRepo.findByKodeJabatan(k.getJabatan().getKodeJabatan());

        k.setDivisi(d);
        k.setJabatan(j);

        karRepo.save(k);
    }



    public void loadDataintoTableView(TableView<Karyawan> tabelKaryawan){
        try{
            tabelKaryawan.getItems().clear();
            tabelKaryawan.getItems().addAll(karyawanServiceDAO.getAll());
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void setUpForChoiceBoxKodeJabatan(ChoiceBox<String> cBoxJabatan){
        cBoxJabatan.getItems().addAll(karyawanServiceDAO.getAllKodeJabatan());
        cBoxJabatan.getItems().add("-");
    }
    public void setUpForChoiceBoxKodeDivisi(ChoiceBox<String> cBoxDivisi){
        cBoxDivisi.getItems().addAll(karyawanServiceDAO.getAllKodeDivisi());
        cBoxDivisi.getItems().add("-");
    }



    public void buatDialog(Node node){
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle("Spring boot jpa fx");
        dialog.getDialogPane().setContent(node);

        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.APPLY);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);


        dialog.getDialogPane().getButtonTypes().setAll(ok,cancel);
        Optional<ButtonType> opt = dialog.showAndWait();

    }

    public void buatAlertDialog(String contentText, Alert.AlertType a, Node node){
        Alert alert = new Alert(a);
        if(node!=null && contentText==null){
            alert.getDialogPane().setContent(node);
        }else{
            alert.setTitle("Spring Boot Jpa Fx Dialog");
            alert.setContentText(contentText);
        }

        alert.show();
    }




    @Transactional
    public void ActionSaveKaryawan(TextField kodeKaryawan,
                                   TextField namaKaryawan ,
                                   Gender gender,
                                   String kodeDivisi ,String kodeJabatan){
        try{
            if(gender==null || namaKaryawan.getText().trim().equals("") || kodeKaryawan.getText().trim().equals("")){
                buatAlertDialog("Masih Ada form yang kosong", Alert.AlertType.WARNING,null);
            }else{
                Divisi d = null;
                Jabatan j = null;

                if(!kodeDivisi.equals("-")){
                    d= divRepo.findByKodeDivisi(kodeDivisi);
                }

                if(!kodeJabatan.equals("-")){
                    j = jabRepo.findByKodeJabatan(kodeJabatan);
                }


                boolean sudahAda = karyawanServiceDAO.sudahAdaKaryawanIniByKodeKaryawan(kodeKaryawan.getText().trim());

                if(sudahAda){
                    buatAlertDialog("Data Karyawan ini sudah ada", Alert.AlertType.ERROR,null);
                }else{
                   Karyawan baru = new Karyawan(kodeKaryawan.getText().trim(),namaKaryawan.getText().trim(),
                           gender,d,j) ;
                   karyawanServiceDAO.simpan(baru);
                   System.err.println("FxServiceKaryawan : " + Thread.currentThread().getName());
                   buatAlertDialog("Data Karyawan berhasil disimpan", Alert.AlertType.CONFIRMATION,null);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
