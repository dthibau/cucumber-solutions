# language : fr

Fonctionnalité: API REST


  Scénario: Health endpoint
    Etant donné url 'http://localhost:8080/actuator/health'
    Lorsque method GET
    Alors status 200

  Plan du scénario: Effectuer un POST
    Lors d'une URL post avec un json, le statut doit être 201 et la réponse doit contenir un attribut .@id  
    
    Etant donné url '<url>'
    Et request <json> 
		Lorsque method POST
		Alors status 201
    Et match $ contains {id:"#notnull"} 

    Exemples: 
      | url  																		| json 																						|
      | http://localhost:8080/api/fournisseurs 	| {"nom": "FournisseurName", "reference": "FNA"}	|
      | http://localhost:8080/api/produits 			|	{"description": "string", "dimension": {"hauteur": 0,"largeur": 0, "longueur": 0 }, "fournisseur": {"id": 1},"nom": "string","prixUnitaire": 0,"reference": "string"}	|

