package com.uqac.ma;


public abstract class Agent implements Runnable {
    abstract protected void EntrerBourse();
    abstract protected void SortirBourse();
    abstract protected void ObserverBourse();
    abstract protected void AcheterTitres();
    abstract protected void VendreTitres();
}
