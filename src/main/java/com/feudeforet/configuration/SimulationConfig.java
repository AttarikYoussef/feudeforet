package com.feudeforet.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "simulation")
@Data
public class SimulationConfig {

    private int largeur;
    private int hauteur;
    private double probabilite;
    private List<List<Integer>> feuxInitiaux;

}
