package com.arief.services.repositories;

import com.arief.entity.Sertifikat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Arief on 8/31/2017.
 */
@Repository
public interface SertifikatRepo extends CrudRepository<Sertifikat,String>{

    @Query(
            "select s.id from Sertifikat  s"
    )
    List<String> getAllIdSertifikat();
}
