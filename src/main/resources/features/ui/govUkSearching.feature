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
        | firefox |