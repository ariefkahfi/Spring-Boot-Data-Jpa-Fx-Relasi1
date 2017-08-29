package com.arief.services.repositories;

import com.arief.entity.Jabatan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Arief on 8/28/2017.
 */
@Repository
public interface JabatanRepo extends CrudRepository<Jabatan,String> {
    Jabatan findByKodeJabatan(String kode);

    @Query("select j.kodeJabatan from Jabatan j")
    List<String> getAllKodeJabatan();

}
