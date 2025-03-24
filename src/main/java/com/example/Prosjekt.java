package com.example;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prosjekt", schema="dat107oblig3")
public class Prosjekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prosjekt_id")
    private Integer id;

    @Column(name = "navn", nullable = false)
    private String navn;
    
    @Column(name = "beskrivelse")
    private String beskrivelse;
    
    @OneToMany(mappedBy = "prosjekt", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Prosjektdeltagelse> deltagelser;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }
    
    public String getBeskrivelse() { return beskrivelse; }
    public void setBeskrivelse(String beskrivelse) { this.beskrivelse = beskrivelse; }
    
    public List<Prosjektdeltagelse> getDeltagelser() { return deltagelser; }
    public void setDeltagelser(List<Prosjektdeltagelse> deltagelser) { this.deltagelser = deltagelser; }
    
    public void printProsjektDetaljer() {
        System.out.println("Prosjekt ID: " + id);
        System.out.println("Navn: " + navn);
        System.out.println("Beskrivelse: " + beskrivelse);
        if(deltagelser != null && !deltagelser.isEmpty()){
            int totalTimer = 0;
            System.out.println("Deltagelser:");
            for(Prosjektdeltagelse pd : deltagelser) {
                System.out.println("  Ansatt: " + pd.getAnsatt().getFornavn() + " " + pd.getAnsatt().getEtternavn() +
                                   ", Rolle: " + pd.getRolle() + ", Timer: " + pd.getTimer());
                totalTimer += pd.getTimer();
            }
            System.out.println("Totalt antall timer: " + totalTimer);
        } else {
            System.out.println("Ingen registrerte deltagelser.");
        }
    }
}
