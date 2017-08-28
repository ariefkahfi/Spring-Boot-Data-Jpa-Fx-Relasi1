package com.arief.services.JabatanServices;

import com.arief.entity.Jabatan;
import com.arief.services.GenericServiceDAO;

/**
 * Created by Arief on 8/28/2017.
 */

public interface JabatanServiceDAO extends GenericServiceDAO<Jabatan,String> {
    Jabatan getOneByKodeJabatan(String kodeJabatan);
}
