package com.example.devapp_1.models;

public class History {
  private int id;
  private String username;
  private String consultation;

  public History() {}

  public History (String username, String consult){
    this.username = username;
    this.consultation = consult; 
  }

  public int getId () { return id; }
  public void setId (int id) { this.id = id; }

  public String getUsername() { return username; }
  public void setUsername (String username) { this.username = username; }
  
  public String getConsultation () { 
    return consultation; 
  }
  public void setConsultation (String consult) { this.consultation = consult; }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Nom du patient = " + username + "\n" + "Consultation  = " + consultation);
    return sb.toString(); 
  }
}