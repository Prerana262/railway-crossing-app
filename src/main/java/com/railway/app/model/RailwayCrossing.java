package com.railway.app.model;

import java.time.LocalDateTime;
import java.util.Date;

public class RailwayCrossing
{
   private int id;
   private String name;
   private String address;
   private String landmark;
   private LocalDateTime trainSchedule;
   private String platformInCharge;
   private String status;

   public RailwayCrossing() {
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getLandmark() {
      return landmark;
   }

   public void setLandmark(String landmark) {
      this.landmark = landmark;
   }

   public LocalDateTime getTrainSchedule() {
      return trainSchedule;
   }

   public void setTrainSchedule(LocalDateTime trainSchedule) {
      this.trainSchedule = trainSchedule;
   }

   public String getPlatformInCharge() {
      return platformInCharge;
   }

   public void setPlatformInCharge(String platformInCharge) {
      this.platformInCharge = platformInCharge;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }
}