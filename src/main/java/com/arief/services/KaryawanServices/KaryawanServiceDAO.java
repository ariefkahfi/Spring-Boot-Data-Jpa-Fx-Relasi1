package com.arief.services.KaryawanServices;

import com.arief.entity.Divisi;
import com.arief.entity.Jabatan;
import com.arief.entity.Karyawan;
import com.arief.entity.enums.Gender;
import com.arief.services.GenericServiceDAO;

import java.util.List;

/**
 * Created by Arief on 8/28/2017.
 */

public interface KaryawanServiceDAO extends GenericServiceDAO<Karyawan,String> {
    Karyawan findByKodeKaryawan(String kode);
    List<String> getAllKodeJabatan();
    List<String> getAllKodeDivisi();
    boolean sudahAdaKaryawanIniByKodeKaryawan(String kodeKaryawan);
    List<String> getAllOnlyKodeKaryawan();
    void testSave(Karyawan baru);
}
