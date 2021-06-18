# language : fr
@web
Fonctionnalité: Recherche de produits
 

  Plan du scénario: Recherche par fournisseurs
    Etant donné que j'accède à la liste des produits
    Et que je sélectionner le fournisseur <id_fournisseur>
    Alors la page contient <nb_produits>
    Et la page ne contient que des produits du fournisseur "<fournisseur>"
    

    Exemples: 
      | id_fournisseur  | nb_produits	|	fournisseur	|
      | 1 							| 1						| Belleville express |
      | 2								| 2						| BricBrac Import/Export |
      | 3								| 0						| Luxuary unlimited |
