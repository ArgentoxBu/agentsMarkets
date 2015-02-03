package com.uqac.ma;

import java.util.HashMap;
import java.util.Map;

public class Bourse {
    private static Bourse instance = null;
    private Panneau panneau;
    private Map<String, Integer> titres;
    private Map<String, Boolean> titresPossedes;
    private Map<String, Integer> titresAvant;

    private Bourse() {
        panneau = new Panneau();

        titres = new HashMap<String, Integer>();
        titres.put("Apple", 25);
        titres.put("Google", 20);
        titres.put("Microsoft", 15);
        titres.put("Yahoo", 10);
        titres.put("Facebook", 5);

        titresPossedes = new HashMap<String, Boolean>();
        titresPossedes.put("Apple", false);
        titresPossedes.put("Google", false);
        titresPossedes.put("Microsoft", false);
        titresPossedes.put("Yahoo", false);
        titresPossedes.put("Facebook", false);

        titresAvant = new HashMap<String, Integer>();
        titresAvant.put("Apple", 25);
        titresAvant.put("Google", 20);
        titresAvant.put("Microsoft", 15);
        titresAvant.put("Yahoo", 10);
        titresAvant.put("Facebook", 5);
    }

    public static Bourse getInstance() {
        if(instance == null) {
            instance = new Bourse();
        }
        return instance;
    }

    public void entrer(Agent agent) {
        panneau.print(agent.getAgentName() + " entre sur le marché.\n");
    }

    public void sortir(Agent agent) {
        panneau.print(agent.getAgentName() + " sort du marché.\n");
    }

    public Map<String, Integer> getTitres() {
        return titres;
    }

    public Map<String, Integer> getFluctuations() {
        Map<String, Integer> fluctuations = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : titres.entrySet()) {
            fluctuations.put(entry.getKey(), titres.get(entry.getKey()) - titresAvant.get(entry.getKey()));
            panneau.setAnnonce(entry.getKey(), fluctuations.get(entry.getKey()) <= 0 ? "Diminue" : "Augmente");
        }

        return fluctuations;
    }

    public boolean acheterTitre(Agent agent, String titreAAcheter) {
        if(!titresPossedes.get(titreAAcheter)) {
            titresPossedes.put(titreAAcheter, true);
            panneau.print(agent.getAgentName()+" achète "+titreAAcheter+" pour "+titres.get(titreAAcheter)+"$.\n");
            return true;
        } else
            return false;
    }

    public void vendreTitre(Agent agent, String titreAVendre, int ecart) {
        Integer prixActuel = titres.get(titreAVendre);
        Integer prixNouveau = (prixActuel + ecart) < 0 ? 0 : (prixActuel + ecart);
        titresPossedes.put(titreAVendre, false);
        titresAvant.put(titreAVendre, prixActuel);
        titres.put(titreAVendre, prixNouveau);
        panneau.print(agent.getAgentName()+" vends "+titreAVendre+" pour "+prixNouveau+"$.\n");
        if(prixNouveau > 1000) {
            panneau.print("Crack boursier !! On recommence du début .\n");
            titres.put(titreAVendre, 10);
            titresAvant.put(titreAVendre, 10);
        }
    }
}
