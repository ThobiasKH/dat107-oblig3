package com.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prosjektdeltagelse", schema="dat107oblig3")
public class Prosjektdeltagelse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pd_id")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "prosjekt_id", nullable = false)
    private Prosjekt prosjekt;
    
    @ManyToOne
    @JoinColumn(name = "ansatt_id", nullable = false)
    private Ansatt ansatt;
    
    @Column(name = "rolle", nullable = false)
    private String rolle;
    
    @Column(name = "timer", nullable = false)
    private int timer;
    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Prosjekt getProsjekt() { return prosjekt; }
    public void setProsjekt(Prosjekt prosjekt) { this.prosjekt = prosjekt; }
    
    public Ansatt getAnsatt() { return ansatt; }
    public void setAnsatt(Ansatt ansatt) { this.ansatt = ansatt; }
    
    public String getRolle() { return rolle; }
    public void setRolle(String rolle) { this.rolle = rolle; }
    
    public int getTimer() { return timer; }
    public void setTimer(int timer) { this.timer = timer; }
}
