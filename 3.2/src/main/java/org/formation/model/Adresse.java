package org.formation.model;

import lombok.Data;

@Data
public class Adresse {

	private int numero;
	private String rue;
	private int codePostal;
	private String ville;
}
