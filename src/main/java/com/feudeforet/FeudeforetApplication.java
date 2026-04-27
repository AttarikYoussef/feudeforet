package com.feudeforet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.feudeforet.application.CasUsageSimulation;
import com.feudeforet.configuration.SimulationConfig;
import com.feudeforet.domaine.modele.EtatCase;
import com.feudeforet.domaine.modele.Foret;
import com.feudeforet.domaine.service.ServicePropagationFeu;
import com.feudeforet.infrastructure.affichage.AfficheurConsole;
import com.feudeforet.infrastructure.aleatoire.GenerateurAleatoireJava;

@SpringBootApplication
@EnableConfigurationProperties(SimulationConfig.class)
public class FeudeforetApplication implements CommandLineRunner{
	
	private final SimulationConfig config;

    public FeudeforetApplication(SimulationConfig config) {
        this.config = config;
    }

	public static void main(String[] args) {
		SpringApplication.run(FeudeforetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Foret foret = new Foret(config.getLargeur(), config.getHauteur());

		for (var pos : config.getFeuxInitiaux()) {
            int x = pos.get(0);
            int y = pos.get(1);
            foret.set(x, y, EtatCase.EN_FEU);
        }

        ServicePropagationFeu service =
            new ServicePropagationFeu(config.getProbabilite(), new GenerateurAleatoireJava());

        AfficheurConsole afficheur = new AfficheurConsole();

        CasUsageSimulation simulation =
            new CasUsageSimulation(service, afficheur);

        simulation.executer(foret);
	}

}
