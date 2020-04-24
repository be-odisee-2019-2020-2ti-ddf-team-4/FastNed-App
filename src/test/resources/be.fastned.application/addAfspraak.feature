  Feature: Maak een Afspraak

  Het moet mogelijk zijn om een afspraak aan te maken (knop)
  Na het toevoegen van een afspraak moet je de details ervan zien
  Een knop "Ga terug" moet beschikbaar zijn (Einde scenario)

  Scenario: Maak een Afspraak
  Given Ik bevind me op de Home-pagina
  When Ik klik op de "Maak Afspraak" knop
  When Ik een installateur selecteer
    And ik een laadpaal selecteer
    And ik een contract selecteer
  Then Klik ik op de "Verwerk Nieuwe Afspraak" knop
  Then Word ik teruggeleid naar de Home-Pagina

