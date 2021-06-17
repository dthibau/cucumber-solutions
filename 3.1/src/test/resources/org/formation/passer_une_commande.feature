# language: fr

Fonctionnalité: Passer une commande
  Lorsque le prospect a terminé sa séléction de produits, il lui reste à passer la commande.
  Cette fonctionnalité doit être la plus fluide possible. Elle doit permettre de déterminer le mode de réception souhaité et 
  de valider ses informations personelles. Une fois validé, le client peut passer au règlement.
  Eric est un client Lillois connu. Tous les modes de livraison lui sont possibles.
  Valérie est une nouvelle cliente habitante d'une zone non déservie.


  Scénario: Le système reconnait Eric

  Etant donné que Eric a activé le bouton "Passer une commande"
  Quand Eric fournit un email reconnu
  Alors le système affiche les informations de Eric

  Scénario: Les 2 modes de livraison sont possibles pour Eric

  Etant donné que Eric a activé le bouton "Passer une commande"
  Et que Eric fournit un email reconnu
  Quand Eric valide ses informations personelles
  Alors les modes de réception proposés sont :  
  	| magasin | 
  	| livraison | 

  Scénario: Le système ne reconnait pas Valérie
  
  Etant donné que Valérie a activé le bouton "Passer une commande"
  Quand Valérie fournit un email non-connu
  Alors le système propose le formulaire d'inscription
  
  Scénario: Seul le mode de livraison magasin est possible pour Valérie

  Etant donné que Valérie a activé le bouton "Passer une commande"
  Et que Valérie a saisi ses informations personelles avec un code postal de 8170
  Quand Valérie valide ses informations personelles
  Alors les modes de réception proposés sont :
  	| magasin |
  
  
  
  
  