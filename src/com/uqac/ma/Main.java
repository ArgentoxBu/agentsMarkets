package com.uqac.ma;

public class Main {
    public static void main(String[] args) {
        // Initialisation
        Agent[] agents = new Agents[3];
        AgentSpeculateur speculateur = new AgentSpeculateur();
        AgentSentimental sentimental = new AgentSentimental();
        AgentArbitre arbitre = new AgentArbitre();
        agents[0] = speculateur;
        agents[1] = sentimental;
        agents[2] = arbitre;

        // On lance les agents
        for(Agent agent : agents)
            agent.start();

        // On attends que les agents aient fini
        for(Agent agent : agents)
            agent.join();
    }
}
