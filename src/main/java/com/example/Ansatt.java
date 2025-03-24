package com.example;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ansatt", schema="dat107oblig3")
public class Ansatt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ansatt_id")
    private Integer id;

    @Column(name = "brukernavn")
    private String brukernavn;

    @Column(name = "fornavn")
    private String fornavn;

    @Column(name = "etternavn")
    private String etternavn;

    @Column(name = "dato_for_ansettelse")
    private Date ansettelsesdato;

    @Column(name = "stilling")
    private String stilling;

    @Column(name = "manedslonn")
    private Double lonn;

    @ManyToOne
    @JoinColumn(name = "avdeling_id")
    private Avdeling avdeling;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public Date getAnsettelsesdato() {
        return ansettelsesdato;
    }

    public void setAnsettelsesdato(Date ansettelsesdato) {
        this.ansettelsesdato = ansettelsesdato;
    }

    public String getStilling() {
        return stilling;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }

    public Double getLonn() {
        return lonn;
    }

    public void setLonn(Double lonn) {
        this.lonn = lonn;
    }

    public Avdeling getAvdeling() { return avdeling; }
    public void setAvdeling(Avdeling avdeling) { this.avdeling = avdeling; }

    public void printAnsattDetaljer() {
        System.out.println("Ansatt ID: " + id);
        System.out.println("Brukernavn: " + brukernavn);
        System.out.println("Navn: " + fornavn + " " + etternavn);
        System.out.println("Ansettelsesdato: " + ansettelsesdato);
        System.out.println("Stilling: " + stilling);
        System.out.println("Lønn: " + lonn);
        if(avdeling != null) {
            System.out.println("Avdeling: " + avdeling.getNavn());
        } else {
            System.out.println("Avdeling: Ikke satt");
        }
    }
}
