Feature: Play/Stop background music
  As app user, I would like to be able to choose if the background music is active or not for my personal confort
  Scenario: Use of the app
    Given I want to be able to choose if the music is playing
    When I choose Play or stop background music option
    Then The Background music must start or stop
