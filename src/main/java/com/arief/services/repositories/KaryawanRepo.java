package com.arief.services.repositories;

import com.arief.entity.Karyawan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Arief on 8/28/2017.
 */
@Repository
public interface KaryawanRepo extends CrudRepository<Karyawan,String>{

    Karyawan findByKodeKaryawan(String kode);
}