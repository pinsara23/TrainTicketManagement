/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trm.dto;

/**
 *
 * @author Admin
 */
public class TrainDto {
    
    private String trainId;
    private String trainName;
    private String route;
    private int availableSeats;

    public TrainDto(String trainId, String trainName, String route, int availableSeats) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.route = route;
        this.availableSeats = availableSeats;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getRoute() {
        return route;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "TrainDto{" + "trainId=" + trainId + ", trainName=" + trainName + ", route=" + route + ", availableSeats=" + availableSeats + '}';
    }


    
    
}
