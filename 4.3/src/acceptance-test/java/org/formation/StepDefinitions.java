package org.formation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;

public class StepDefinitions {

	private final WebDriver driver = new ChromeDriver();

	@Etantdonnéque("j'accède à la liste des produits")
	public void j_accède_à_la_liste_des_produits() {
		driver.get("http://localhost:8080/produits");
	}

	@Etantdonnéque("je sélectionner le fournisseur {int}")
	public void je_sélectionner_le_fournisseur(Integer idFournisseur) {
		Select fournisseurs = new Select(driver.findElement(By.name("fournisseurId")));
		fournisseurs.selectByValue("" + idFournisseur);
	}

	@Alors("la page contient {int}")
	public void la_page_contient(Integer nbProduits) {
		new WebDriverWait(driver, 10L).until(d -> {
			WebElement tableProduits = d.findElement(By.tagName("table"));
			List<WebElement> rows = tableProduits.findElements(By.tagName("tr"));
			return rows.size() == nbProduits + 1;
		});
	}

	@Alors("la page ne contient que des produits du fournisseur {string}")
	public void la_page_ne_contient_que_des_produits_du_fournisseur(String fournisseur) {
		new WebDriverWait(driver, 10L).until(d -> {
			WebElement tableProduits = d.findElement(By.tagName("table"));
			List<WebElement> rows = tableProduits.findElements(By.tagName("tr"));
			for (int i = 1; i < rows.size(); i++) {
				WebElement td = rows.get(i).findElements(By.tagName("td")).get(2);
				if (!td.getText().equals(fournisseur)) {
					return false;
				}
			}
			return true;
		});
	}

	@After()
	public void closeBrowser() {
		driver.quit();
	}

}
