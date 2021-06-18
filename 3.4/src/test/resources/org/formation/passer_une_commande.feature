# language: fr

Fonctionnalité: Passer une commande
  Lorsque le prospect a terminé sa séléction de produits, il lui reste à passer la commande.
  Cette fonctionnalité doit être la plus fluide possible. Elle doit permettre de déterminer le mode de réception souhaité et 
  de valider ses informations personelles. Une fois validé, le client peut passer au règlement.
  Eric est un client Lillois connu. Tous les modes de livraison lui sont possibles.
  Valérie est une nouvelle cliente habitante d'une zone non déservie.

  Contexte:
    Etant donné que l'utilisateur a activé le bouton "Passer une commande"
    
    
   
  Plan du scénario: Valider du code postal et des modes de livraison
   Les modes de livraisons dépendent exclusivement du code postal du client. La table
   des exemples liste de façon exhaustive les différents cas de figure.
    Etant donné que l'utilisateur est identifié:
    	| nom 	| email 					| codePostal 	|
    	| Eric 	| eric@known.com	|	00000				|	
    Quand Eric valide ses informations personelles avec un code postal de <code_postal>
    Alors le mode de réception "magasin" est "<statut_magasin>"
    Alors le mode de réception "livraison" est "<statut_livraison>"
    Alors le mode de réception "livraison_express" est "<statut_livraison_express>"
    
    Exemples: 
    |	code_postal |	statut_magasin	|	statut_livraison	|	statut_livraison_express	|
    | 01000				|	possible				|	impossible				|	impossible								|
    | 51000				|	possible				|	possible					|	impossible								|
    | 75000				|	possible				|	possible					|	impossible								|
    


	@required-data
  Scénario: Le système reconnait Eric

  Quand l'utilisateur fournit l'email "eric@known.com"
  Alors le système affiche les informations de Eric

	@required-data
  Scénario: Le système ne reconnait pas Valérie
  
  Quand l'utilisateur fournit l'email "valerie@notknown.com"
  Alors le système propose le formulaire d'inscription
  
 

  


  
  
  
  