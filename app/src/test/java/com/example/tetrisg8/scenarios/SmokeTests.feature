Feature: Smoke tests
   As a Developer, I would like that the functionality of the application has not been destroyed

   Scenario: Create the thread of game
   		Given the tetris in pause
   		When the game is starting
   		Then the thread is created
   Scenario: Pause the game
	   Given The player is playing the game
        When the game is paused
        Then the music is disable
   Scenario: Close the game
	   Given The player is playing the game
		When the game is stop
		Then the music is disable
   Scenario: Create piece
		Given the piece
		When the piece is created
		Then the piece is created correctly
   Scenario: Init correctly table
		Given the game
		When the table is created
		Then the table is created correctly



