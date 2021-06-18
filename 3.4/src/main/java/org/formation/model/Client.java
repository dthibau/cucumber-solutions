package org.formation.model;

import lombok.Data;

@Data
public class Client {

	private String email;
	private String prenom;
	private String nom;
	private Adresse adresse;
}
