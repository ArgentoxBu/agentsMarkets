package com.uqac.ma;

public class Main {
    public static void main(String[] args) {
        // Initialisation
        Bourse bourse = Bourse.getInstance();
        Agent[] agents = new Agent[3];
        AgentSpeculateur speculateur = new AgentSpeculateur();
        AgentPigeon pigeon = new AgentPigeon();
        AgentArbitre arbitre = new AgentArbitre();
        agents[0] = speculateur;
        agents[1] = pigeon;
        agents[2] = arbitre;

        // On lance les agents
        for(Agent agent : agents)
            agent.start();

        // On attends que les agents aient fini
        for(Agent agent : agents) {
            try {
                agent.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
