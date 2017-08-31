package com.arief.entity;

import com.arief.entity.enums.Gender;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Arief on 8/28/2017.
 */
@Entity
@Table(name="t_karyawan")
public class Karyawan {


    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;


    @Column(name = "kode_karyawan", length = 6, unique = true, nullable = false)
    private String kodeKaryawan;

    @Column(name = "nama_karyawan")
    private String namaKaryawan;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinTable(name = "kar_div", joinColumns = @JoinColumn(name = "id_kar"), inverseJoinColumns = @JoinColumn(name = "id_div"))
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST})
    private Divisi divisi;

    @ManyToOne
    @JoinTable(name="kar_jab",joinColumns = @JoinColumn(name="id_kar"),inverseJoinColumns = @JoinColumn(name="id_jab"))
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST})
    private Jabatan jabatan;


    @ManyToMany
    @JoinTable(name="kar_sertifikat",joinColumns = @JoinColumn(name="id_kar"),inverseJoinColumns = @JoinColumn(name="id_sertifikat"))
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    private List<Sertifikat> sertifikatList;

    public Karyawan() {
    }


    public Karyawan(String kodeKaryawan, String namaKaryawan, Gender gender, Divisi divisi, Jabatan jabatan) {
        this.kodeKaryawan = kodeKaryawan;
        this.namaKaryawan = namaKaryawan;
        this.gender = gender;
        this.divisi = divisi;
        this.jabatan = jabatan;
    }

    public Karyawan(String kodeKaryawan,
                    String namaKaryawan,
                    Gender gender,
                    Divisi divisi,
                    Jabatan jabatan,
                    List<Sertifikat> sertifikatList) {
        this.kodeKaryawan = kodeKaryawan;
        this.namaKaryawan = namaKaryawan;
        this.gender = gender;
        this.divisi = divisi;
        this.jabatan = jabatan;
        this.sertifikatList = sertifikatList;
    }

    public List<Sertifikat> getSertifikatList() {
        return sertifikatList;
    }

    public void setSertifikatList(List<Sertifikat> sertifikatList) {
        this.sertifikatList = sertifikatList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKodeKaryawan() {
        return kodeKaryawan;
    }

    public void setKodeKaryawan(String kodeKaryawan) {
        this.kodeKaryawan = kodeKaryawan;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public void setNamaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Divisi getDivisi() {
        return divisi;
    }

    public void setDivisi(Divisi divisi) {
        this.divisi = divisi;
    }

    public Jabatan getJabatan() {
        return jabatan;
    }

    public void setJabatan(Jabatan jabatan) {
        this.jabatan = jabatan;
    }
}