package com.uqac.ma;

import java.util.Map;

public class AgentPigeon extends Agent {
    public AgentPigeon() {
        name = "Pigeon";
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
        // Le pigeon revend son titre moins cher (-5)
        Bourse bourse = Bourse.getInstance();
        bourse.vendreTitre(this, titrePossede, -5);
        possedeTitre = false;
    }

    @Override
    protected void AcheterTitre() {
        // Le pigeon choisit le titre ayant la valeur maximum
        Map<String, Integer> titres = Bourse.getInstance().getTitres();
        Map.Entry<String, Integer> max = null;
        for (Map.Entry<String, Integer> entry : titres.entrySet()) {
            if (max == null || max.getValue() < entry.getValue()) max = entry;
        }
        assert max != null;
        String titreAAcheter = max.getKey();

        Bourse bourse = Bourse.getInstance();
        if (bourse.acheterTitre(this, titreAAcheter)) {
            titrePossede = titreAAcheter;
            possedeTitre = true;
        }
    }
}
