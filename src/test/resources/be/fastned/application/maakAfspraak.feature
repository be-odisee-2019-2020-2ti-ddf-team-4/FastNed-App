#Feature: Add Person
#
#It should be possible to add a musician to the list
#Just after adding the musician, the details of the musician should be known
#After the list is shown again, the musician first name and last name should be visible
#
#Scenario: Add one musician
#Given I am on the page where I can introduce a new person
#When I enter "Sanne" in the voornaam field
#And I enter "Putzeys" in the familienaam field
#And I enter "selah@sue.be" in the emailadres field
#And I enter "raggamuffin" in the paswoord field
#And I press on the Submit button
#Then I should see the following on the screen
#When I click the Lijst van alle personen Link
#Then I should see a list containing "Sanne Putzeys"
#And I close the browser

Feature: Maak een Afspraak

  Het moet mogelijk zijn om een afspraak aan te maken (knop)
  Na het toevoegen van een afspraak moet je de details ervan zien
  Een knop "Ga terug" moet beschikbaar zijn

  Scenario: Een afspraak maken (geen invoerfouten)
    Given Ik ben ingelogd als planner
      And Ik bevind me op de Home pagina
    When Ik klik op de "Beheer Afspraken" knop
    And Ik een installateur selecteer
    And ik een laadpaal selecteer
    And ik een contract selecteer
    And ik een status ingeef
    And ik afsluit door op de "Verwerk Nieuwe Afspraak" knop te klikken
    And ik naar de nieuwe afspraak navigeer door op de "Bekijk Afspraak" knop te klikken
    Then Zie ik de nieuwe afspraak
    And Kan ik op "Terug naar Home" klikken
    And Word ik teruggeleid naar de Home Pagina
#
#  Scenario: Een afspraak maken (wel invoerfouten)
#    Given Ik bevind me op de Home pagina
#    And De database installateurs, contracten en laadpalen bevat
#    When Ik klik op de "Maak Afspraak" knop
#    And Ik een installateur selecteer
#    And ik een laadpaal selecteer
#    And ik een contract selecteer
#    And ik GEEN status ingeef
#    And ik afsluit door op de "Verwerk Nieuwe Afspraak" knop te klikken
#    Then Zie ik een error ivm de niet ingevulde status
#    And Wordt de afspraak correct verwerkt wanneer ik op de "Verwerk Nieuwe Afspraak" knop klik
#    And Kan ik op "Terug naar Home" klikken
#    And Word ik teruggeleid naar de Home-Pagina