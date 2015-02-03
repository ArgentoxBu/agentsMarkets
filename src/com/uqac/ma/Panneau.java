package com.uqac.ma;

import javax.swing.*;
import java.awt.*;

public class Panneau extends JFrame {
    private JPanel titres;
    private JTextArea annonces;

    private JLabel AppleLabel = new JLabel();
    private JLabel FacebookLabel = new JLabel();
    private JLabel YahooLabel = new JLabel();
    private JLabel MicrosoftLabel = new JLabel();
    private JLabel GoogleLabel = new JLabel();

    public Panneau() {
        super("La bourse");

        titres = new JPanel();
        titres.add(AppleLabel);
        titres.add(MicrosoftLabel);
        titres.add(YahooLabel);
        titres.add(FacebookLabel);
        titres.add(GoogleLabel);

        annonces = new JTextArea("", 15, 50);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pan = new JPanel();
        pan.setLayout(new BorderLayout());
        pan.add(titres, BorderLayout.NORTH);
        pan.add(annonces, BorderLayout.SOUTH);
        pan.add(new JPanel(), BorderLayout.CENTER);

        add(pan);

        pack();
        setVisible(true);
    }

    public void print(String s) {
        annonces.append(s);
    }

    public void setAnnonce(String key, String s) {
        if(key.equals("Apple"))
            AppleLabel.setText("Apple : "+s);
        else if (key.equals("Microsoft"))
            MicrosoftLabel.setText("Microsoft : "+s);
        else if(key.equals("Google"))
            GoogleLabel.setText("Google : "+s);
        else if(key.equals("Yahoo"))
            YahooLabel.setText("Yahoo : "+s);
        else
            FacebookLabel.setText("Facebook : "+s);
    }
}
