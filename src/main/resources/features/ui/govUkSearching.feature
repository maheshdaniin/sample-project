@ui
Feature: UI Test
  Rule: Example Rule 1
    Scenario Outline: Searching for Citizen in gov.uk home page
      Given I use the <browser> browser
      And I open Gov.uk home page
      When I search for "Citizen"
      Then I am on search result page
      And I should find "Citizen" related results
      And I run accessibility check

      Examples:
        | browser |
        | chrome |



    Scenario Outline: Searching for Citizen in google home page
      Given I use the <browser> browser
      And I open google search page
      When I search for "Citizen" in google search page
      Then I am on google result page
      And I run accessibility check

      Examples:
        | browser |
        | chrome |