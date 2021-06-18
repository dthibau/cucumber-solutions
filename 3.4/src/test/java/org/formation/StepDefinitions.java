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
import org.formation.service.Database;
import org.formation.service.LivraisonServiceIF;
import org.formation.service.SimpleLivraisonService;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Scenario;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Quand;

public class StepDefinitions {
	
	LivraisonServiceIF livraisonService;
	Optional<Client> currentClient;

	@Before("@required-data")
	public void initDataBase(Scenario scenario) {
		System.out.println("INITIALISATION pour "+scenario.getName());
		_initBD();
	}
	@After("@required-data")
	public void cleanDataBase(Scenario scenario) {
		System.out.println("Cleaning for "+scenario.getName());
	}
	
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

	@Quand("l'utilisateur fournit l'email {string}")
	public void l_utilisateur_fournit_l_email(String email) {
		currentClient = livraisonService.getClientFromEmail(email);
	}


	@Alors("le système affiche les informations de (\\w+)")
	public void le_système_affiche_les_informations_de() {
	    assertTrue(currentClient.isPresent());
	}
	@Alors("le système propose le formulaire d'inscription")
	public void le_système_propose_le_formulaire_d_inscription() {
		assertFalse(currentClient.isPresent());
	}
	
	@Etantdonnéque("^l'utilisateur est identifié:$")
	public void l_utilisateur_est_identifié(Client client) {
	  currentClient = Optional.of(client);
	}

	@Quand("(.+) valide ses informations personelles avec un code postal de {int}")
	public void valide_ses_informations_personelles(int codePostal) {
		currentClient.orElseThrow(() -> new IllegalStateException("Client non défini")).getAdresse().setCodePostal(codePostal);
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

	
	
	private void _initBD() {
		Adresse paris = new Adresse();
		paris.setCodePostal(75000);
		paris.setVille("Paris");
		Adresse lille = new Adresse();
		lille.setCodePostal(59000);
		lille.setVille("Lille");
		Adresse haybes = new Adresse();
		haybes.setCodePostal(8170);
		haybes.setVille("Haybes");
		
		Client ericParis = new Client();
		ericParis.setNom("Eric");
		ericParis.setEmail("eric@known.com");
		ericParis.setAdresse(paris);
		Client pierreLille = new Client();
		pierreLille.setNom("Pierre");
		pierreLille.setEmail("pierre@known.com");
		pierreLille.setAdresse(lille);
		Client rachidHaybes = new Client();
		rachidHaybes.setNom("Rachid");
		rachidHaybes.setEmail("haybes@known.com");
		rachidHaybes.setAdresse(haybes);
		Database.getInstance().addClient(ericParis);
		Database.getInstance().addClient(pierreLille);
		Database.getInstance().addClient(rachidHaybes);
	}

}
