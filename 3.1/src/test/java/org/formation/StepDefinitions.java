package org.formation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.formation.model.Client;
import org.formation.model.ModeReception;
import org.formation.service.LivraisonServiceIF;
import org.formation.service.SimpleLivraisonService;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Etque;
import io.cucumber.java.fr.Quand;

public class StepDefinitions {
	
	LivraisonServiceIF livraisonService;
	Optional<Client> currentClient;

	@Etantdonnéque("Eric/Valérie a activé le bouton \"Passer une commande\"")
	public void a_activé_le_bouton() {
	    livraisonService = new SimpleLivraisonService();
	}

	@Quand("^(\\w+) fournit un email reconnu$")
	public void fournit_un_email_reconnu(String nom) {
		currentClient = livraisonService.getClientFromEmail("toto@known.com");
	}

	@Alors("^le système affiche les informations de (\\w+)$")
	public void le_système_affiche_les_informations_de(String nom) {
	    assertTrue(currentClient.isPresent());
	}


	@Quand("^(\\w+) valide ses informations personelles$")
	public void valide_ses_informations_personelles(String nom) {
	    // Write code here that turns the phrase above into concrete actions
	    // Nothing to do
	}


	@Quand("^Valérie fournit un email non-connu$")
	public void fournit_un_email_non_connu() {
		currentClient = livraisonService.getClientFromEmail("toto@knownnot.com");
	}

	@Alors("le système propose le formulaire d'inscription")
	public void le_système_propose_le_formulaire_d_inscription() {
		assertFalse(currentClient.isPresent());
	}


	@Etantdonnéque("{word} a saisi ses informations personelles avec un code postal de {int}")
	public void a_saisi_ses_informations_personelles(String nom, int codePostal) {
		currentClient = Optional.of(livraisonService.registerNewClient(codePostal));
	}


	@Alors("^les modes de réception proposés sont :$")
	public void les_modes_de_réception_proposés_sont(List<String> modes) {
		Client client = currentClient.orElseThrow(() -> new IllegalStateException("Client is not defined"));
		List<ModeReception> modeReceptions = Arrays.asList(ModeReception.values());
	    for ( ModeReception modeReception : modeReceptions) {
	    	Optional<String> found = modes.stream().filter(s -> modeReception.toString().equalsIgnoreCase(s)).findFirst();
    		
	    	if ( found.isPresent() )
	    		assertTrue(livraisonService.isPossible( client.getAdresse().getCodePostal(), modeReception));
	    	else 
	    		assertFalse(livraisonService.isPossible( client.getAdresse().getCodePostal(), modeReception));
	    	
	    }
	}

}
