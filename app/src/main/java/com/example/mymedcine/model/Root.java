package com.example.mymedcine.model;

import java.util.ArrayList;

public class Root {
    public ArrayList<Prescription> prescriptions;
    public ArrayList<String> drugs;
    public ArrayList<User> users;

    public Root() {
    }

    public Root(ArrayList<Prescription> prescriptions, ArrayList<String> drugs, ArrayList<User> users) {
        this.prescriptions = prescriptions;
        this.drugs = drugs;
        this.users = users;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(ArrayList<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public ArrayList<String> getDrugs() {
        return drugs;
    }

    public void setDrugs(ArrayList<String> drugs) {
        this.drugs = drugs;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
