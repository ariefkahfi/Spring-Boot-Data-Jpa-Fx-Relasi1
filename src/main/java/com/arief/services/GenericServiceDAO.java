package com.arief.services;

import com.arief.entity.Karyawan;
import com.arief.services.repositories.KaryawanRepo;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arief on 8/28/2017.
 */

public interface GenericServiceDAO<T,ID extends Serializable> {
    void simpan(T t);
    void hapus(ID id);
    List<T> getAll();
    T getOne(ID id);
}
