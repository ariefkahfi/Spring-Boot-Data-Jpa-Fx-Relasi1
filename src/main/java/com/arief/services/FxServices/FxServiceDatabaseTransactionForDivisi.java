package com.arief.services.FxServices;

import com.arief.entity.Karyawan;
import com.arief.services.DivisiServices.DivisiServiceDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arief on 8/28/2017.
 */
@Component
public class FxServiceDatabaseTransactionForDivisi {

    @Autowired
    private DivisiServiceDAO dao;


    public void setListViewValue(ListView<String> namaKaryawans,String kodeDivisi){
        List<String> karList = dao.getNamaKaryawanByDivisiKode(kodeDivisi);
        namaKaryawans.setItems(FXCollections.observableList(karList));
    }

}
