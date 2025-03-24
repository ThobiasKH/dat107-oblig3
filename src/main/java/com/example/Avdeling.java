package com.example;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "avdeling", schema="dat107oblig3")
public class Avdeling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avdeling_id")
    private Integer id;

    @Column(name = "navn")
    private String navn;

    @OneToOne
    @JoinColumn(name = "sjef_id")
    private Ansatt sjef;

    @OneToMany(mappedBy = "avdeling", fetch = FetchType.EAGER)
    private List<Ansatt> ansatte;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }
    public Ansatt getSjef() { return sjef; }
    public void setSjef(Ansatt sjef) { this.sjef = sjef; }
    public List<Ansatt> getAnsatte() { return ansatte; }
    public void setAnsatte(List<Ansatt> ansatte) { this.ansatte = ansatte; }

    public void printAvdelingDetaljer() {
        System.out.println("Avdeling ID: " + id);
        System.out.println("Navn: " + navn);
        if(sjef != null) {
            System.out.println("Sjef: " + sjef.getFornavn() + " " + sjef.getEtternavn());
        } else {
            System.out.println("Sjef: Ikke satt");
        }
    }
}