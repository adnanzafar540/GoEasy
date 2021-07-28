package com.example.newawareness;

public class Dataholder {

    String Description;
    String PatientName;
    String DoctorName;
    public  Dataholder( String DoctorName, String Description, String patientName){
        this.Description=Description;
        this.DoctorName=DoctorName;
        this.PatientName=patientName;
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
