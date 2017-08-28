package com.arief.services.JabatanServices;

import com.arief.entity.Jabatan;
import com.arief.services.repositories.JabatanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Arief on 8/28/2017.
 */
@Service
public class JabatanService implements JabatanServiceDAO{


    @Autowired
    private JabatanRepo jabRepo;


    @Override
    public void simpan(Jabatan jabatan) {
        jabRepo.save(jabatan);
    }

    @Override
    public Jabatan getOneByKodeJabatan(String kodeJabatan) {
        return jabRepo.findByKodeJabatan(kodeJabatan);
    }

    @Override
    public void hapus(String s) {
        jabRepo.delete(s);
    }

    @Override
    public List<Jabatan> getAll() {
        return (List<Jabatan>) jabRepo.findAll();
    }

    @Override
    public Jabatan getOne(String s) {
        return jabRepo.findOne(s);
    }
}
