package com.arief.services.FxServices;

import com.arief.entity.Jabatan;
import com.arief.entity.Karyawan;
import com.arief.services.DivisiServices.DivisiServiceDAO;
import com.arief.services.JabatanServices.JabatanService;
import com.arief.services.JabatanServices.JabatanServiceDAO;
import com.arief.services.KaryawanServices.KaryawanServiceDAO;
import com.arief.services.repositories.JabatanRepo;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Arief on 8/29/2017.
 */
@Component
public class FxServiceDatabaseTransactionForJabatan  {

    @Autowired
    private JabatanServiceDAO jabServiceDAO;
    @Autowired
    private JabatanRepo jabRepo;
    @Autowired
    private KaryawanServiceDAO karyawanServiceDAO;

    @Transactional
    public void actionPindahJabatan(Karyawan k, String kodeJabatan){
        try{
            if(k!=null && kodeJabatan!=null){
                Jabatan jabatanBaru = jabRepo.findByKodeJabatan(kodeJabatan);
                k.setJabatan(jabatanBaru);

                if(!jabRepo.exists(jabatanBaru.getId())){
                    buatAlertDialog("Data Jabatan dengan kode tersebut tidak ada", Alert.AlertType.ERROR,null);
                }else {
                    karyawanServiceDAO.simpan(k);
                    buatAlertDialog("Karyawan pindah jabatan sukses", Alert.AlertType.CONFIRMATION, null);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void setChoiceDialogforKodeKaryawan(ChoiceDialog<String> choice){
        choice.getItems().clear();
        choice.getItems().addAll(karyawanServiceDAO.getAllOnlyKodeKaryawan());
    }

    public void setChoiceDialogItemsForKodeJabatan(ChoiceDialog<String> choice){
        choice.getItems().clear();
        choice.getItems().addAll(jabServiceDAO.getAllOnlyKodeJabatan());
    }

    public void ActionDoSimpanJabatan(Jabatan jab, TextField fieldKodeJabatan,TextField fieldNamaJabatan){
        try{
            if(fieldKodeJabatan.getText().trim().equals("") || fieldNamaJabatan.getText().trim().equals("")){
                buatAlertDialog("Masih ada field kosong", Alert.AlertType.WARNING,null);
            }else{
                jabServiceDAO.simpan(jab);
                buatAlertDialog("Data berhasil disimpan", Alert.AlertType.CONFIRMATION,null);
            }
        } catch (DuplicateKeyException ex){
            buatAlertDialog("Data Jabatan sudah ada di database", Alert.AlertType.ERROR,null);
        } catch (DataIntegrityViolationException ex){
            buatAlertDialog("Kemungkinan Data Jabatan sudah ada di database", Alert.AlertType.ERROR,null);
        } catch (Exception ex){
           System.err.println(ex.getMessage());
           ex.printStackTrace();
        }
    }

    public void loadDataJabatanIntoTableView(TableView<Jabatan> tabelJabatan){
       try{
           tabelJabatan.getItems().clear();
           tabelJabatan.getItems().addAll(jabServiceDAO.getAll());
       }catch (Exception ex){
            ex.printStackTrace();
       }
    }

    public void ActionForShowJabatanKaryawan(String kodeJabatan){
        showListViewForNamaKaryawanYangAdaDiJabatanIni(kodeJabatan);
    }

    public void showListViewForNamaKaryawanYangAdaDiJabatanIni(String kodeJabatan){
        try{
            if(!kodeJabatan.trim().equals("")){
                ListView<String> namaKaryawanDiJabatanIniList = new ListView<>();
                namaKaryawanDiJabatanIniList.setItems(FXCollections.observableList(jabServiceDAO.getNamaKaryawanDiJabatanIni(kodeJabatan)));
               buatAlertDialog(null, Alert.AlertType.INFORMATION,namaKaryawanDiJabatanIniList);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void buatAlertDialog(String contentText, Alert.AlertType a, Node node){
        Alert alert = new Alert(a);
        alert.setTitle("Spring boot Java Fx Dialog");

        if(node!=null && (contentText==null || contentText.trim().equals(""))){
            alert.getDialogPane().setContent(node);
        }else{
            alert.setContentText(contentText);
        }


        alert.show();
    }

}
