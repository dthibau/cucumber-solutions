package org.formation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.formation.model.Adresse;
import org.formation.model.Client;
import org.formation.model.ModeReception;
import org.formation.service.LivraisonServiceIF;
import org.formation.service.SimpleLivraisonService;

import io.cucumber.java.DataTableType;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Quand;

public class StepDefinitions {
	
	LivraisonServiceIF livraisonService;
	Optional<Client> currentClient;

	@DataTableType
	public Client clientEntry(Map<String, String> entry) {
        Client client = new Client();
        client.setEmail(entry.get("email"));
        client.setNom(entry.get("nom"));
        Adresse adresse = new Adresse();
        adresse.setCodePostal(Integer.parseInt(entry.get("codePostal")));
        client.setAdresse(adresse);
        return client;
	}
	
	@Etantdonnéque("(.+) a activé le bouton \"Passer une commande\"")
	public void a_activé_le_bouton() {
	    livraisonService = new SimpleLivraisonService();
	}


	@Etantdonnéque("^l'utilisateur est identifié:$")
	public void l_utilisateur_est_identifié(Client client) {
	  currentClient = Optional.of(client);
	}


	@Quand("(\\w+) fournit un email reconnu")
	public void fournit_un_email_reconnu() {
		currentClient = livraisonService.getClientFromEmail("toto@known.com");
	}

	@Alors("le système affiche les informations de (\\w+)")
	public void le_système_affiche_les_informations_de() {
	    assertTrue(currentClient.isPresent());
	}


	@Quand("(.+) valide ses informations personelles avec un code postal de {int}")
	public void valide_ses_informations_personelles(int codePostal) {
		currentClient.orElseThrow(() -> new IllegalStateException("Client non défini")).getAdresse().setCodePostal(codePostal);
	}


	@Quand("(\\w+) fournit un email non-connu")
	public void fournit_un_email_non_connu() {
		currentClient = livraisonService.getClientFromEmail("toto@knownnot.com");
	}

	@Alors("le système propose le formulaire d'inscription")
	public void le_système_propose_le_formulaire_d_inscription() {
		assertFalse(currentClient.isPresent());
	}


	@Etantdonnéque("(.+) a saisi ses informations personelles avec un code postal de {int}")
	public void a_saisi_ses_informations_personelles(int codePostal) {
		currentClient = Optional.of(livraisonService.registerNewClient(codePostal));
	}

	@Alors("le mode de réception {string} est {string}")
	public void le_mode_de_réception_est_possible(String mode, String outcome) {
		Client client = currentClient.orElseThrow(() -> new IllegalStateException("Client is not defined"));
		ModeReception modeReception = ModeReception.valueOf(mode.toUpperCase());
	    if ( outcome.equalsIgnoreCase("possible") ) {
    		assertTrue(livraisonService.isPossible( client.getAdresse().getCodePostal(), modeReception));
	    } else {
	    	assertFalse(livraisonService.isPossible( client.getAdresse().getCodePostal(), modeReception));
	    }
	}

	
	@Alors("^les modes de réception proposés sont :")
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
