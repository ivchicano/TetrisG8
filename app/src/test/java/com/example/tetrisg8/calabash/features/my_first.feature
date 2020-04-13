Feature: Start Game

  Scenario: Starting game
    When I see "START GAME"
	Then I press "START GAME"
	Then I enter now "Pedro" as "nombreUsuario"
	Then I press "AceptarBtn"
	Then I check if exists "scoreBox"
