package com.arief.services.FxServices;

import com.arief.entity.Jabatan;
import com.arief.services.DivisiServices.DivisiServiceDAO;
import com.arief.services.JabatanServices.JabatanService;
import com.arief.services.JabatanServices.JabatanServiceDAO;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Arief on 8/29/2017.
 */
@Component
public class FxServiceDatabaseTransactionForJabatan  {

    @Autowired
    private JabatanServiceDAO jabServiceDAO;


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
