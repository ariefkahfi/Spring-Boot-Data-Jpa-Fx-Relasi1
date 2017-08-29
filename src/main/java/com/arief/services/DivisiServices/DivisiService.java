package com.arief.services.DivisiServices;

import com.arief.entity.Divisi;
import com.arief.entity.Karyawan;
import com.arief.services.repositories.DivisiRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arief on 8/28/2017.
 */
@Service
public class DivisiService implements DivisiServiceDAO {

    @Autowired
    private DivisiRepo divRepo;

    /*@Transactional
    public void test(String kode){
            for(Karyawan k : findOneByKodeDivisi(kode).getKaryawanList()){
                System.err.println(k.getNamaKaryawan());
            }
    }*/

    @Transactional
    @Override
    public void simpan(Divisi divisi) {
        divRepo.save(divisi);
    }

    @Transactional
    @Override
    public void hapus(String s) {
        divRepo.delete(s);
    }

    @Transactional
    @Override
    public List<String> getNamaKaryawanByDivisiKode(String kode) {
        List<String> names = new ArrayList<>();
        for(Karyawan k : findOneByKodeDivisi(kode).getKaryawanList()){
            names.add(k.getNamaKaryawan());
        }
        return names;
    }

    @Transactional
    @Override
    public List<Karyawan> getKaryawansByDivisiKode(String kode) {
        List<Karyawan> data = new ArrayList<>();
        data.addAll(findOneByKodeDivisi(kode).getKaryawanList());
        return data;
    }

    @Transactional
    @Override
    public List<String> getAllOnlyKodeDivisi() {
        List<String> data = new ArrayList<>();
        data.addAll(divRepo.getAllKodeDivisi());
        return data;
    }

    @Transactional
    @Override
    public List<Divisi> getAll() {
        return (List<Divisi>) divRepo.findAll();
    }

    @Transactional
    @Override
    public Divisi findOneByKodeDivisi(String kode) {
      return  divRepo.findByKodeDivisi(kode);
    }

    @Transactional
    @Override
    public Divisi getOne(String s) {
        return divRepo.findOne(s);
    }


}
