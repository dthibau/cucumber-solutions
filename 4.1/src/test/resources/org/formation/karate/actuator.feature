# language: fr


Fonctionnalité: Ressources actuator
  

  Scénario: Health endpoint
    Etant donné url 'http://localhost:8080/actuator/health'
    Lorsque method GET
    Alors status 200


