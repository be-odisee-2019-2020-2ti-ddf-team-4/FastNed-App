  Feature: Maak een Afspraak

  Het moet mogelijk zijn om een afspraak aan te maken (knop)
  Na het toevoegen van een afspraak moet je de details ervan zien
  Een knop "Ga terug" moet beschikbaar zijn

  Scenario: Een afspraak maken (geen invoerfouten)
  Given Ik bevind me op de Home-pagina
    And De database installateurs, contracten en laadpalen bevat
  When Ik klik op de "Maak Afspraak" knop
    And Ik een installateur selecteer
    And ik een laadpaal selecteer
    And ik een contract selecteer
    And ik een status ingeef
    And ik afsluit door op de "Verwerk Nieuwe Afspraak" knop te klikken
  Then Zie ik de nieuwe afspraak
    And Kan ik op "Terug naar Home" klikken
    And Word ik teruggeleid naar de Home-Pagina

  Scenario: Een afspraak maken (wel invoerfouten)
  Given Ik bevind me op de Home-pagina
    And De database installateurs, contracten en laadpalen bevat
  When Ik klik op de "Maak Afspraak" knop
    And Ik een installateur selecteer
    And ik een laadpaal selecteer
    And ik een contract selecteer
    And ik GEEN status ingeef
    And ik afsluit door op de "Verwerk Nieuwe Afspraak" knop te klikken
  Then Zie ik een error ivm de niet ingevulde status
    And Wordt de afspraak correct verwerkt wanneer ik op de "Verwerk Nieuwe Afspraak" knop klik
    And Kan ik op "Terug naar Home" klikken
    And Word ik teruggeleid naar de Home-Pagina

