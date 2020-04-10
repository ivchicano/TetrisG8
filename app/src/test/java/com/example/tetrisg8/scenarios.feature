Feature: Play/Stop background music
As app user, I would like to be able to choose if the background music is active or not for my personal confort
  Scenario: Use of the app
    Given A config menu
     When I choose Play or stop background music option
     Then The Background music must start or stop
  
  Feature: Skip take a photo step
  As app user, I would like to be able to skip take a photo step and put a predefinied photo in ranking for my personal confort
  Scenario: Take photo screen
    Given A skip button
     When I click on it
     Then I will be redirect to end game screen 
      And the displayed photo must be a predefinied photo
  
  Feature: Go to Main Screen
  As app user, I would like to go back to the main screen from end game screen for better navigation through the app
  Scenario: End game screen
    Given A button
     When I click on it
     Then I will be redirect to the main screen
  
  Feature: Add a new piece
  As a Product Owner, I would like to have a new different piece for differentiate itself from the competitors 
  Scenario: A screen game
    Given The player is playing the game
     When Appears a new piece on the board
     Then The user have to see a new different piece that not exists until now 
  Feature: Change next piece in runtime
  As a Product Owner, I would like that the user can change the next piece when I he click on a button for a better game experience
  Scenario: A screen game
    Given The player is playing the game
     When I click in the change next piece button
     Then The user have to see how the next piece changes
  
  
