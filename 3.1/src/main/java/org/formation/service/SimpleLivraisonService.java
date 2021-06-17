package org.formation.service;

import java.util.Optional;

import org.formation.model.Adresse;
import org.formation.model.Client;
import org.formation.model.ModeReception;

public class SimpleLivraisonService implements LivraisonServiceIF {

	@Override
	public Optional<Client> getClientFromEmail(String email) {
		Optional<Client> ret = Optional.empty();
		
		if ( email.endsWith("known.com") ) {
			Adresse lilleAdresse = new Adresse();
			lilleAdresse.setCodePostal(59000);
			lilleAdresse.setNumero(11);
			lilleAdresse.setRue("Rue poincaré");
			lilleAdresse.setVille("Lille");
			Client client = new Client();
			client.setEmail(email);
			client.setPrenom("Dupont");
			client.setNom("Eric");
			client.setAdresse(lilleAdresse);
			ret = Optional.of(client);
		}
		return ret;
	}

	@Override
	public boolean isPossible(int codePostal, ModeReception modeReception) {
		
		if ( modeReception.equals(ModeReception.MAGASIN)) {
			return true;
		}
		if ( modeReception.equals(ModeReception.LIVRAISON)) {
			return codePostal > 50000 && codePostal < 96000;
		}
		if ( modeReception.equals(ModeReception.LIVRAISON_EXPRESS)) {
			return codePostal > 75000 && codePostal < 76000;
		}
		return false;
	}

	@Override
	public Client registerNewClient(int codePostal) {
		Adresse lilleAdresse = new Adresse();
		lilleAdresse.setCodePostal(codePostal);
		lilleAdresse.setNumero(11);
		lilleAdresse.setRue("Rue poincaré");
		lilleAdresse.setVille("Lille");
		Client client = new Client();
		client.setEmail("valerie@knownnot.com");
		client.setPrenom("Dupont");
		client.setNom("Valérie");
		client.setAdresse(lilleAdresse);
		
		return client;
	}

}
