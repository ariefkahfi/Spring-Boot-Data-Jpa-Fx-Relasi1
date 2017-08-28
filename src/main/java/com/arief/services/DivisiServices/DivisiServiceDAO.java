package com.arief.services.DivisiServices;

import com.arief.entity.Divisi;
import com.arief.entity.Karyawan;
import com.arief.services.GenericServiceDAO;

import java.util.List;

/**
 * Created by Arief on 8/28/2017.
 */
public interface DivisiServiceDAO extends GenericServiceDAO<Divisi,String> {
    Divisi findOneByKodeDivisi(String kode);
    //void test(String kdoe);
    List<String> getNamaKaryawanByDivisiKode(String kode);

}
