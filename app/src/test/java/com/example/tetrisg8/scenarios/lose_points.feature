Feature: Lose points
  As a Product Owner, I would like to user lose 20 points when he click on change next piece button to make the game more difficult

  Scenario: Game screen
    Given I want to the user loses 20 points
    When I click on the change next piece button
    Then The points has to decrease 20 units