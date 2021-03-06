package com.arief.services.repositories;

import com.arief.entity.Divisi;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Arief on 8/28/2017.
 */
@Repository
public interface DivisiRepo extends CrudRepository<Divisi,String>{
    Divisi findByKodeDivisi(String kode);

    @Query("select d.kodeDivisi from Divisi d")
    List<String> getAllKodeDivisi();
}
