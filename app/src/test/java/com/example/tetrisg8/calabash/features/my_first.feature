Feature: End to end

  Scenario: Starting game
    When I see "START GAME"
	Then I press "START GAME"
	Then I enter now "Pedro" as "nombreUsuario"
	Then I press "AceptarBtn"
	Then I check if exists "scoreBox"

  Scenario: See ranking
    When I see "START GAME"
	Then I press "START GAME"
	Then I enter now "Pedro" as "nombreUsuario"
	Then I press "AceptarBtn"
	Then I check if exists "buttonDown"
	Then I press "buttonDown" while exists
    Then I see "TAKE PHOTO"
    And I see "SKIP"
	Then I press "button02"
    Then I see "PLAY AGAIN"
    And I see "RANKING"
    And I see "MAIN SCREEN"
    And I see "EXIT"
	Then I press "button_viewAll"
    Then I see "TOP SCORES"

  Scenario: Restart game
    When I see "START GAME"
	Then I press "START GAME"
	Then I enter now "Pedro" as "nombreUsuario"
	Then I press "AceptarBtn"
	Then I check if exists "buttonDown"
	Then I press "buttonDown" while exists
    Then I see "TAKE PHOTO"
    And I see "SKIP"
	Then I press "button02"
    Then I see "PLAY AGAIN"
    And I see "RANKING"
    And I see "MAIN SCREEN"
    And I see "EXIT"
	Then I press "botPantallaPrincipal"
    Then I see "START GAME"
