package com.arief.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Arief on 8/28/2017.
 */
@Entity
@Table(name="t_jabatan")
public class Jabatan {


    @Id
    @Column(name="id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name="kode_jabatan",unique = true,nullable = false)
    private String kodeJabatan;

    @Column(name="nama_jabatan")
    private String namaJabatan;


    @OneToMany(mappedBy = "jabatan")
    private List<Karyawan> karyawans;

    public Jabatan() {
    }

    public Jabatan(String kodeJabatan, String namaJabatan, List<Karyawan> karyawans) {
        this.kodeJabatan = kodeJabatan;
        this.namaJabatan = namaJabatan;
        this.karyawans = karyawans;
    }

    public List<Karyawan> getKaryawans() {
        return karyawans;
    }

    public void setKaryawans(List<Karyawan> karyawans) {
        this.karyawans = karyawans;
    }

    public String getNamaJabatan() {
        return namaJabatan;
    }

    public void setNamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKodeJabatan() {
        return kodeJabatan;
    }

    public void setKodeJabatan(String kodeJabatan) {
        this.kodeJabatan = kodeJabatan;
    }

}
