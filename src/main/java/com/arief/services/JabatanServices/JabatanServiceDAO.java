package com.arief.services.JabatanServices;

import com.arief.entity.Jabatan;
import com.arief.entity.Karyawan;
import com.arief.services.GenericServiceDAO;

import java.util.List;

/**
 * Created by Arief on 8/28/2017.
 */

public interface JabatanServiceDAO extends GenericServiceDAO<Jabatan,String> {
    Jabatan getOneByKodeJabatan(String kodeJabatan);
    List<Karyawan> getJabatanKaryawanByKodeJabatan(String kodeJabatan);
    List<String> getNamaKaryawanDiJabatanIni(String kodeJabatan);
    List<String> getAllOnlyKodeJabatan();
}
