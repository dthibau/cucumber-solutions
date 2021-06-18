package org.formation.service;

import java.util.Optional;

import org.formation.model.Client;
import org.formation.model.ModeReception;

public interface LivraisonServiceIF {

	public boolean isPossible(int codePostal, ModeReception modeReception);
	
	public Optional<Client> getClientFromEmail(String email);
	
	public Client registerNewClient(int codePostal);
}
