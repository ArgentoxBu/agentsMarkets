package com.uqac.ma;

import java.util.Map;

public class AgentSpeculateur extends Agent {
    public AgentSpeculateur() {
        name = "Speculateur";
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
        // Le spéculateur revend son titre plus cher (+10)
        Bourse bourse = Bourse.getInstance();
        bourse.vendreTitre(this, titrePossede, 10);
        possedeTitre = false;
    }

    @Override
    protected void AcheterTitre() {
        // Le spéculateur choisit le titre ayant la valeur minimum
        Map<String, Integer> titres = Bourse.getInstance().getTitres();
        Map.Entry<String, Integer> min = null;
        for (Map.Entry<String, Integer> entry : titres.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) min = entry;
        }
        assert min != null;
        String titreAAcheter = min.getKey();

        Bourse bourse = Bourse.getInstance();
        if (bourse.acheterTitre(this, titreAAcheter)) {
            titrePossede = titreAAcheter;
            possedeTitre = true;
        }
    }
}
