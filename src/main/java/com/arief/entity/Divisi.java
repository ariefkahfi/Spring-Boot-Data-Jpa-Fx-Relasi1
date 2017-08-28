package com.arief.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Arief on 8/28/2017.
 */
@Entity
@Table(name="t_divisi")
public class Divisi {


    @Id
    @Column(name="id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;


    @Column(name="kode_divisi",nullable = false,unique = true)
    private String kodeDivisi;


    @Column(name="nama_divisi")
    private String namaDivisi;

    @Column(name="tgl_buat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglBuat;

    @OneToMany(mappedBy = "divisi")
    private List<Karyawan> karyawanList;

    public Divisi() {
    }

    public Divisi(String kodeDivisi, String namaDivisi, Date tglBuat, List<Karyawan> karyawanList) {
        this.kodeDivisi = kodeDivisi;
        this.namaDivisi = namaDivisi;
        this.tglBuat = tglBuat;
        this.karyawanList = karyawanList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKodeDivisi() {
        return kodeDivisi;
    }

    public void setKodeDivisi(String kodeDivisi) {
        this.kodeDivisi = kodeDivisi;
    }

    public String getNamaDivisi() {
        return namaDivisi;
    }

    public void setNamaDivisi(String namaDivisi) {
        this.namaDivisi = namaDivisi;
    }

    public Date getTglBuat() {
        return tglBuat;
    }

    public void setTglBuat(Date tglBuat) {
        this.tglBuat = tglBuat;
    }

    public List<Karyawan> getKaryawanList() {
        return karyawanList;
    }

    public void setKaryawanList(List<Karyawan> karyawanList) {
        this.karyawanList = karyawanList;
    }
}
