package com.example.newawareness;

public class Dataholder {
    String Date;
    String Description;
    String PatientName;
    String DoctorName;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
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

    public  Dataholder(String Date, String DoctorName, String Description, String patientName){
        this.Date=Date;
        this.Description=Description;
        this.DoctorName=DoctorName;
        this.PatientName=patientName;
    }

}
