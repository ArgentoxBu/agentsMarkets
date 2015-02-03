package com.uqac.ma;

import java.util.Map;

public abstract class Agent extends Thread {
    protected String name;
    protected String decision;
    protected boolean possedeTitre = false;
    protected String titrePossede;
    protected Map<String, Integer> fluctuations;

    protected void EntrerBourse() {
        Bourse bourse = Bourse.getInstance();
        bourse.entrer(this);
    }

    protected void SortirBourse() {
        Bourse bourse = Bourse.getInstance();
        bourse.sortir(this);
    }

    protected void ObserverBourse() {
        Bourse bourse = Bourse.getInstance();
        fluctuations = bourse.getFluctuations();
    }

    abstract protected void Decider();
    abstract protected void Executer();
    abstract protected void AcheterTitre();
    abstract protected void VendreTitre();

    public String getAgentName() {return name;}
}
