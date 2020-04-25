Feature: End to end

  Scenario: Starting game
	Given the app starts
    When I see "START GAME"
	Then I press "START GAME"
	Then I enter now "Pedro" as "nombreUsuario"
	Then I press "AceptarBtn"
	Then I check if exists "scoreBox"
	Then I check if exists "fichaView"


  Scenario: Die
	Given the game has started
	Then I check if exists "buttonDown"
	Then I press "buttonDown" while exists
	Then I see "TAKE PHOTO"
    And I see "SKIP"

  Scenario: See ranking
    Given the game has started
	And I die
	Then I press "button02"
    Then I see "PLAY AGAIN"
    And I see "RANKING"
    And I see "MAIN SCREEN"
    And I see "EXIT"
	Then I press "button_viewAll"
    Then I see "TOP SCORES"
	Then I see "Pedro"
	

  Scenario: Restart game
    Given the game has started
	And I die
	Then I press "button02"
    Then I see "PLAY AGAIN"
    And I see "RANKING"
    And I see "MAIN SCREEN"
    And I see "EXIT"
	Then I press "botPantallaPrincipal"
    Then I see "START GAME"
	
  
  Scenario: Exit app when finish
	Given the game has started
	And I die
	Then I press "button01"
	Then I take a picture
	Then I wait 5 seconds
	


