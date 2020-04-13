package com.example.tetrisg8;

import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;


public class TetrisBDDTests {
    GameOverActivity gameOverActivity;
    private GameView gameView = mock(GameView.class);
    private FichaView fichaView = mock(FichaView.class);
    private Tablero tablero = mock(Tablero.class);
    private FuncionamientoJuego funcionamientoJuego;
    private MainActivity mainActivity = mock(MainActivity.class);
    Pieza piezaSiguienteAntigua;
    Pieza nuevaPieza;

    //region main screen
    @When("^I click main screen button$")
    public void iClickMainScreenButton() {
    }

    @Then("^I will be redirect to the main screen$")
    public void iWillBeRedirectToTheMainScreen() {
        assertEquals(1, 2);
    }
    //endregion

    //region music
    @When("^I choose play background music option$")
    public void iChoosePlayBackgroundMusicOption() {
        funcionamientoJuego = new FuncionamientoJuego(gameView, fichaView, tablero, "test", null);
        funcionamientoJuego.setMainActivity(mainActivity);
        funcionamientoJuego.setMusicEnable(true);
    }

    @Then("^The Background music must start$")
    public void theBackgroundMusicMustStart() {
        assertTrue(funcionamientoJuego.isMusicEnable());
    }

    @When("^I choose stop background music option$")
    public void iChooseStopBackgroundMusicOption() {
        funcionamientoJuego = new FuncionamientoJuego(gameView, fichaView, tablero, "test", null);
        funcionamientoJuego.setMainActivity(mainActivity);
        funcionamientoJuego.setMusicEnable(false);
    }

    @Then("^The Background music must stop$")
    public void theBackgroundMusicMustStop() {
        assertFalse(funcionamientoJuego.isMusicEnable());
    }
    //endregion

    //region skip photo
    @Given("^I want to skip take photo step$")
    public void iWantToSkipTakePhotoStep() {
    }

    @When("^I click on skip button$")
    public void iClickOnSkipButton() {
    }

    @Then("^The image saved will be a predefinied photo$")
    public void theImageSavedWillBeAPredefiniedPhoto() {
        assertEquals(1, 2);
    }
    //endregion

    //region Add a new piece
    @When("^Appears a new piece on the board$")
    public void appears_a_new_piece_on_the_board() throws Throwable {
        nuevaPieza = new Pieza(8);
    }

    @Then("^The user have to see a new different piece that not exists until now$")
    public void the_user_have_to_see_a_new_different_piece_that_not_exists_until_nowtype_() throws Throwable {
        assertNotNull(nuevaPieza);
    }
    //endregion

    //region change next piece
    @Given("^The player is playing the game$")
    public void the_player_is_playing_the_game() throws Throwable {
        funcionamientoJuego = new FuncionamientoJuego(gameView, fichaView, tablero, "test", null);
        funcionamientoJuego.setMainActivity(mainActivity);
        mainActivity.setStart(funcionamientoJuego);
    }

    @When("^I click in the change next piece button$")
    public void iClickInTheChangeNextPieceButton() {
        piezaSiguienteAntigua = funcionamientoJuego.generarPiezaSiguiente();
        Pieza p = funcionamientoJuego.generarPiezaSiguiente();
        funcionamientoJuego.setPiezaSiguiente(p);
    }

    @Then("^The user have to see how the next piece changes$")
    public void theUserHaveToSeeHowTheNextPieceChanges() {
        Pieza p = funcionamientoJuego.getPiezaSiguiente();
        assertNotEquals(piezaSiguienteAntigua, p);
    }
    //endregion

}