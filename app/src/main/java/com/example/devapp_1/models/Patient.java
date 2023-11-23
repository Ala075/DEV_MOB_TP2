package com.example.devapp_1.models;

public class Patient {
    private double vm;
    private int age;
    private boolean isFasting;

    private String consultation;

    public Patient(double vm, int age, boolean isFasting) {
        this.vm = vm;
        this.age = age;
        this.isFasting = isFasting;
        calculer();
    }

    public double getVm() {
        return vm;
    }

    public String getConsultation() {
        return consultation;
    }

    public int getAge() {
        return age;
    }

    public boolean isFasting() {
        return isFasting;
    }

    public void calculer() {
        if(isFasting){
            if (age >= 13){
                if(vm < 5.0){
                    consultation="Niveau de glycemie est bas";
                }else if (vm >= 5.0 && vm <= 7.2){
                    consultation="Niveau de glycemie est normale";
                }else {
                    consultation="Niveau de glycemie est trop elevée";
                }
            } else if (age >= 6 && age <= 12) {
                if (vm < 5.0){
                    consultation="Niveau de glycemie est trop bas";
                } else if (vm >= 5.0 && vm<=10.0) {
                    consultation="Niveau de glycemie est normale";
                }else {
                    consultation="Niveau de glycemie est trop elevée";
                }
            }else{
                if (vm < 5.5){
                    consultation="Niveau de glycemie est trop bas";
                } else if (vm >= 5.5 && vm <= 10.0) {
                    consultation="Niveau de glycemie est normale";
                }else {
                    consultation="Niveau de glycemie est trop elevée";
                }
            }
        } else if (vm < 10.5) {
            consultation="Niveau de glycemmie est normale";
        }else {
            consultation="Niveau de glycemie est elevée";
        }
    }
}