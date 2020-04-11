Feature: Skip take a photo step
  As app user, I would like to be able to skip take a photo step and put a predefinied photo in ranking for my personal confort
  Scenario: Take photo screen
    Given A skip button
    When I click on it
    Then I will be redirect to end game screen
    And the displayed photo must be a predefinied photo

