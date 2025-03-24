package com.example;

public class MainTestAvdeling {
    public static void main(String[] args) {
        AvdelingDAO avdelingDAO = new AvdelingDAO();
        int testId = 1;
        
        Avdeling avd = avdelingDAO.finnAvdelingMedId(testId);
        if(avd != null) {
            avd.printAvdelingDetaljer();
        } else {
            System.out.println("Ingen avdeling funnet med id: " + testId);
        }
        avdelingDAO.close();
    }
}