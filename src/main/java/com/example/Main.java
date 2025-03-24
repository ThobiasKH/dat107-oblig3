package com.example;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AnsattDAO ansattDao = new AnsattDAO();
        AvdelingDAO avdelingDao = new AvdelingDAO();
        Scanner scanner = new Scanner(System.in);
        int valg = -1;
        
        while(valg != 0) {
            System.out.println("\n---------- Meny ----------");
            System.out.println("1: Søk etter ansatt på ansatt-id");
            System.out.println("2: Søk etter ansatt på brukernavn");
            System.out.println("3: Utlisting av alle ansatte");
            System.out.println("4: Oppdater en ansatts stilling og/eller lønn");
            System.out.println("5: Legg inn en ny ansatt (med avdelingsvalg)");
            System.out.println("6: Søk etter avdeling på avdeling-id");
            System.out.println("7: Utlisting av alle ansatte på en avdeling (marker sjef)");
            System.out.println("8: Oppdater avdeling for en ansatt (kan ikke bytte hvis sjef)");
            System.out.println("9: Legg inn en ny avdeling (velg sjef blant eksisterende ansatte)");
            System.out.println("0: Avslutt");
            System.out.print("Velg et alternativ: ");
            
            try {
                valg = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Ugyldig valg, prøv igjen.");
                continue;
            }
            
            switch(valg) {
                case 1:
                    System.out.print("Oppgi ansatt-id: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Ansatt ans1 = ansattDao.finnAnsattMedId(id);
                    if(ans1 != null) {
                        ans1.printAnsattDetaljer();
                    } else {
                        System.out.println("Ingen ansatt funnet med id: " + id);
                    }
                    break;
                case 2:
                    System.out.print("Oppgi brukernavn: ");
                    String brukernavn = scanner.nextLine();
                    try {
                        Ansatt ans2 = ansattDao.finnAnsattMedBrukernavn(brukernavn);
                        if(ans2 != null) {
                            ans2.printAnsattDetaljer();
                        }
                    } catch(Exception e) {
                        System.out.println("Ingen ansatt funnet med brukernavn: " + brukernavn);
                    }
                    break;
                case 3:
                    List<Ansatt> alleAnsatte = ansattDao.hentAlleAnsatte();
                    for(Ansatt a : alleAnsatte) {
                        a.printAnsattDetaljer();
                        System.out.println("--------------------");
                    }
                    break;
                case 4:
                    System.out.print("Oppgi ansatt-id for oppdatering: ");
                    int oppdaterId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Oppgi ny stilling: ");
                    String nyStilling = scanner.nextLine();
                    System.out.print("Oppgi ny lønn: ");
                    Double nyLonn = Double.parseDouble(scanner.nextLine());
                    ansattDao.oppdaterAnsatt(oppdaterId, nyStilling, nyLonn);
                    System.out.println("Ansatt oppdatert.");
                    break;
                case 5:
                    Ansatt nyAnsatt = new Ansatt();
                    System.out.print("Oppgi brukernavn: ");
                    nyAnsatt.setBrukernavn(scanner.nextLine());
                    System.out.print("Oppgi fornavn: ");
                    nyAnsatt.setFornavn(scanner.nextLine());
                    System.out.print("Oppgi etternavn: ");
                    nyAnsatt.setEtternavn(scanner.nextLine());
                    System.out.print("Oppgi dato for ansettelse (yyyy-MM-dd): ");
                    String datoStr = scanner.nextLine();
                    try {
                        Date ansettelsesdato = Date.valueOf(datoStr);
                        nyAnsatt.setAnsettelsesdato(ansettelsesdato);
                    } catch(IllegalArgumentException e) {
                        System.out.println("Ugyldig datoformat!");
                        break;
                    }
                    System.out.print("Oppgi stilling: ");
                    nyAnsatt.setStilling(scanner.nextLine());
                    System.out.print("Oppgi lønn: ");
                    nyAnsatt.setLonn(Double.parseDouble(scanner.nextLine()));
                    System.out.print("Oppgi avdeling-id hvor ansatt skal jobbe: ");
                    int avdId = Integer.parseInt(scanner.nextLine());
                    Avdeling avd = avdelingDao.finnAvdelingMedId(avdId);
                    if(avd == null) {
                        System.out.println("Fant ikke avdeling med id: " + avdId);
                        break;
                    }
                    nyAnsatt.setAvdeling(avd);
                    ansattDao.leggTilAnsatt(nyAnsatt);
                    System.out.println("Ny ansatt lagt til.");
                    break;
                case 6:
                    System.out.print("Oppgi avdeling-id: ");
                    int avdelingId = Integer.parseInt(scanner.nextLine());
                    Avdeling avdeling = avdelingDao.finnAvdelingMedId(avdelingId);
                    if(avdeling != null){
                        avdeling.printAvdelingDetaljer();
                    } else {
                        System.out.println("Ingen avdeling funnet med id: " + avdelingId);
                    }
                    break;
                case 7:
                    System.out.print("Oppgi avdeling-id: ");
                    int avdForListe = Integer.parseInt(scanner.nextLine());
                    Avdeling listeAvd = avdelingDao.finnAvdelingMedId(avdForListe);
                    if(listeAvd != null) {
                        System.out.println("Avdeling: " + listeAvd.getNavn());
                        if(listeAvd.getSjef() != null) {
                            System.out.println("Sjef: " + listeAvd.getSjef().getFornavn() + " " + listeAvd.getSjef().getEtternavn());
                        }
                        List<Ansatt> ansatte = listeAvd.getAnsatte();
                        if(ansatte != null && !ansatte.isEmpty()){
                            for(Ansatt a : ansatte) {
                                System.out.print(a.getFornavn() + " " + a.getEtternavn());
                                if(listeAvd.getSjef() != null && listeAvd.getSjef().getId().equals(a.getId())){
                                    System.out.print(" (SJEF)");
                                }
                                System.out.println();
                            }
                        } else {
                            System.out.println("Ingen ansatte registrert i denne avdelingen.");
                        }
                    } else {
                        System.out.println("Ingen avdeling funnet med id: " + avdForListe);
                    }
                    break;
                case 8:
                    System.out.print("Oppgi ansatt-id som skal bytte avdeling: ");
                    int ansattId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Oppgi ny avdeling-id: ");
                    int nyAvdId = Integer.parseInt(scanner.nextLine());
                    boolean byttet = ansattDao.oppdaterAnsattAvdeling(ansattId, nyAvdId);
                    if(byttet) {
                        System.out.println("Avdelingsbytte gjennomført.");
                    }
                    break;
                case 9:
                    Avdeling nyAvdeling = new Avdeling();
                    System.out.print("Oppgi navn på ny avdeling: ");
                    nyAvdeling.setNavn(scanner.nextLine());
                    System.out.print("Oppgi ansatt-id for sjef (existing employee): ");
                    int sjefId = Integer.parseInt(scanner.nextLine());
                    Ansatt sjef = ansattDao.finnAnsattMedId(sjefId);
                    if(sjef == null) {
                        System.out.println("Finner ikke ansatt med id: " + sjefId);
                        break;
                    }
                    nyAvdeling.setSjef(sjef);
                    sjef.setAvdeling(nyAvdeling);
                    avdelingDao.leggTilAvdeling(nyAvdeling);
                    System.out.println("Ny avdeling lagt til med sjef: " + sjef.getFornavn() + " " + sjef.getEtternavn());
                    break;
                case 0:
                    System.out.println("Avslutter programmet.");
                    break;
                default:
                    System.out.println("Ugyldig valg, prøv igjen.");
            }
        }
        
        ansattDao.close();
        avdelingDao.close();
        scanner.close();
    }
}