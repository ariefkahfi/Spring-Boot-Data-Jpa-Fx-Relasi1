package com.arief.services.KaryawanServices;

import com.arief.entity.Divisi;
import com.arief.entity.Jabatan;
import com.arief.entity.Karyawan;
import com.arief.entity.enums.Gender;
import com.arief.services.JabatanServices.JabatanServiceDAO;
import com.arief.services.repositories.DivisiRepo;
import com.arief.services.repositories.JabatanRepo;
import com.arief.services.repositories.KaryawanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arief on 8/28/2017.
 */
@Service
public class KaryawanService implements KaryawanServiceDAO {


    @Autowired
    private KaryawanRepo karRepo;
    @Autowired
    private JabatanRepo jabRepo;
    @Autowired
    private DivisiRepo divRepo;


    @Transactional
    @Override
    public boolean sudahAdaKaryawanIniByKodeKaryawan(String kodeKaryawan) {
        return karRepo.findByKodeKaryawan(kodeKaryawan) != null;
    }

    @Transactional
    @Override
    public List<String> getAllOnlyKodeKaryawan() {
        List<String> data = new ArrayList<>();
        data.addAll(karRepo.getAllOnlyKodeKaryawan());
        return data;
    }

    @Transactional
    @Override
    public void testSave(Karyawan baru) {
        Karyawan inner = baru;
        karRepo.save(inner);
    }


    @Transactional
    @Override
    public void simpan(Karyawan k) {
        karRepo.save(k);
    }

    @Transactional
    @Override
    public Karyawan findByKodeKaryawan(String kode) {
        return karRepo.findByKodeKaryawan(kode);
    }

    @Transactional
    @Override
    public List<String> getAllKodeJabatan() {
        List<String> data = new ArrayList<>();
        data.addAll(jabRepo.getAllKodeJabatan());
        return data;
    }

    @Transactional
    @Override
    public List<String> getAllKodeDivisi() {
        List<String> data =new ArrayList<>();
        data.addAll(divRepo.getAllKodeDivisi());
        return data;
    }


    @Transactional
    @Override
    public void hapus(String s) {
        karRepo.delete(s);
    }

    @Transactional
    @Override
    public List<Karyawan> getAll() {
        return (List<Karyawan>) karRepo.findAll();
    }

    @Transactional
    @Override
    public Karyawan getOne(String s) {
        return karRepo.findOne(s);
    }

}
