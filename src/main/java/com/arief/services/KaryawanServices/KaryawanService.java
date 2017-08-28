package com.arief.services.KaryawanServices;

import com.arief.entity.Karyawan;
import com.arief.services.repositories.KaryawanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Arief on 8/28/2017.
 */
@Service
public class KaryawanService implements KaryawanServiceDAO {


    @Autowired
    private KaryawanRepo karRepo;

    @Override
    public Karyawan findByKodeKaryawan(String kode) {
        return karRepo.findByKodeKaryawan(kode);
    }

    @Transactional
    @Override
    public void simpan(Karyawan karyawan) {
        karRepo.save(karyawan);
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
