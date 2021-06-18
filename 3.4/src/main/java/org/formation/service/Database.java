package org.formation.service;

import java.util.ArrayList;
import java.util.List;

import org.formation.model.Client;

public class Database {

	private static Database instance = new Database();
	private List<Client> clients = new ArrayList<>();
	
	private Database() {
		
	}
	static public Database getInstance() {
		return instance;
	}
	
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	public void addClient(Client client) {
		clients.add(client);
	}
	
	
}
