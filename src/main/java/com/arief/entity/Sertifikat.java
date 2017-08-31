package com.arief.entity;

import com.arief.entity.Karyawan;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Arief on 8/31/2017.
 */
@Entity
@Table(name="t_sertifikat")
public class Sertifikat {

    @Id
    @Column(name="id")
    @GenericGenerator(name = "uuid",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String id;


    @Column(name="nama_sertifikat")
    private String namaSertifikat;

    @Column(name="tgl_beri")
    @Temporal(TemporalType.DATE)
    private Date tglBeri;

    @ManyToMany(mappedBy = "sertifikatList")
    private List<Karyawan> karyawanList;

    public Sertifikat() {
    }

    public Sertifikat(String namaSertifikat, Date tglBeri, List<Karyawan> karyawanList) {
        this.namaSertifikat = namaSertifikat;
        this.tglBeri = tglBeri;
        this.karyawanList = karyawanList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaSertifikat() {
        return namaSertifikat;
    }

    public void setNamaSertifikat(String namaSertifikat) {
        this.namaSertifikat = namaSertifikat;
    }

    public Date getTglBeri() {
        return tglBeri;
    }

    public void setTglBeri(Date tglBeri) {
        this.tglBeri = tglBeri;
    }

    public List<Karyawan> getKaryawanList() {
        return karyawanList;
    }

    public void setKaryawanList(List<Karyawan> karyawanList) {
        this.karyawanList = karyawanList;
    }
}
