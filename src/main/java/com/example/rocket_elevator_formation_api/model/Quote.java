package com.example.rocket_elevator_formation_api.model;

public class Quote {
    private int ID;
    private String buildingType;
    private String priceType;
    private int numElevator;
    private double totalPrice;
    private double installationPrice;
    private double finalPrice;

    public Quote(int ID, String buildingType, String priceType, int numElevator, double totalPrice, double installationPrice, double finalPrice) {
        this.ID = ID;
        this.buildingType = buildingType;
        this.priceType = priceType;
        this.numElevator = numElevator;
        this.totalPrice = totalPrice;
        this.installationPrice = installationPrice;
        this.finalPrice = finalPrice;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public int getNumElevator() {
        return numElevator;
    }

    public void setNumElevator(int numElevator) {
        this.numElevator = numElevator;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getInstallationPrice() {
        return installationPrice;
    }

    public void setInstallationPrice(double installationPrice) {
        this.installationPrice = installationPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
