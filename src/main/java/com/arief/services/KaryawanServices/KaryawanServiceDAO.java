package com.arief.services.KaryawanServices;

import com.arief.entity.Karyawan;
import com.arief.services.GenericServiceDAO;

/**
 * Created by Arief on 8/28/2017.
 */

public interface KaryawanServiceDAO extends GenericServiceDAO<Karyawan,String> {
    Karyawan findByKodeKaryawan(String kode);
}
