package com.uqac.ma;


import java.util.Map;

public class AgentArbitre extends Agent {
    public AgentArbitre() {
        name = "Arbitre";
    }

    @Override
    public void run() {
        EntrerBourse();

        boolean finish = false;
        while(!finish) {
            Decider();
            Executer();
        }

        SortirBourse();
    }

    @Override
    protected void Decider() {
        ObserverBourse();
        if(possedeTitre)
            decision = "VendreTitre";
        else
            decision = "AcheterTitre";
    }

    @Override
    protected void Executer() {
        if(decision.equals("VendreTitre"))
            VendreTitre();
        else
            AcheterTitre();
    }

    @Override
    protected void VendreTitre() {
        // L'arbitre revend son titre pour réguler le marché
        Bourse bourse = Bourse.getInstance();
        bourse.vendreTitre(this, titrePossede, fluctuations.get(titrePossede));
        possedeTitre = false;
    }

    @Override
    protected void AcheterTitre() {
        // L'arbitre choisit le titre ayant le plus fluctué
        Map.Entry<String, Integer> max = null;
        for (Map.Entry<String, Integer> entry : fluctuations.entrySet()) {
            if (max == null || max.getValue() < entry.getValue()) max = entry;
        }

        String titreAAcheter = max.getKey();

        Bourse bourse = Bourse.getInstance();
        if (bourse.acheterTitre(this, titreAAcheter)) {
            titrePossede = titreAAcheter;
            possedeTitre = true;
        }
    }
}
