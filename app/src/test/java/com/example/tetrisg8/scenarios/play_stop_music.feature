Feature: Play/Stop background music
  As app user, I would like to be able to choose if the background music is active or not for my personal confort
  Scenario: Use of the app
    When I choose play background music option
    Then The Background music must start
  Scenario: Use of the app
    When I choose stop background music option
    Then The Background music must stop
