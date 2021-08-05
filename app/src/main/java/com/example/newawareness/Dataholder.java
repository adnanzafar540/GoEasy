package com.example.newawareness;

public class Dataholder {

    String Description;
    String PatientName;
    String DoctorName;
    String  date;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    String  userid;


    public String  getDate() {
        return date;
    }

    public void setDate(String  date) {
        this.date = date;
    }

    public Dataholder(){
        return;
    }
    public  Dataholder(String  date, String DoctorName, String Description, String patientName){
        this.Description=Description;
        this.DoctorName=DoctorName;
        this.PatientName=patientName;
        this.date=date;
        this.userid=userid;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }



}
