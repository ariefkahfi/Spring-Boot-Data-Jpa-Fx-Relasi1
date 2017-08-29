package com.arief.services.JabatanServices;

import com.arief.entity.Jabatan;
import com.arief.entity.Karyawan;
import com.arief.services.repositories.JabatanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arief on 8/28/2017.
 */
@Service
public class JabatanService implements JabatanServiceDAO{


    @Autowired
    private JabatanRepo jabRepo;


    @Transactional
    @Override
    public void simpan(Jabatan jabatan) {
        jabRepo.save(jabatan);
    }

    @Transactional
    @Override
    public Jabatan getOneByKodeJabatan(String kodeJabatan) {
        return jabRepo.findByKodeJabatan(kodeJabatan);
    }

    @Transactional
    @Override
    public List<Karyawan> getJabatanKaryawanByKodeJabatan(String kodeJabatan) {
        List<Karyawan> data = new ArrayList<>();
        data.addAll(getOneByKodeJabatan(kodeJabatan).getKaryawans());
        return data;
    }

    @Transactional
    @Override
    public List<String> getNamaKaryawanDiJabatanIni(String kodeJabatan) {
        List<String> names = new ArrayList<>();
        getJabatanKaryawanByKodeJabatan(kodeJabatan).forEach(karyawan -> {
            names.add(karyawan.getNamaKaryawan());
        });
        return names;
    }

    @Transactional
    @Override
    public void hapus(String s) {
        jabRepo.delete(s);
    }

    @Transactional
    @Override
    public List<Jabatan> getAll() {
        return (List<Jabatan>) jabRepo.findAll();
    }

    @Transactional
    @Override
    public Jabatan getOne(String s) {
        return jabRepo.findOne(s);
    }
}
