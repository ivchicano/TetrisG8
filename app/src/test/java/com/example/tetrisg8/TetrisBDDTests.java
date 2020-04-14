package com.example.tetrisg8;

import android.content.SharedPreferences;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;


public class TetrisBDDTests {
    private GameView gameView = mock(GameView.class);
    private FichaView fichaView = mock(FichaView.class);
    private Tablero tablero = mock(Tablero.class);
    private FuncionamientoJuego funcionamientoJuego;
    private MainActivity mainActivity = mock(MainActivity.class);
    private Configuracion configuracion;

    boolean musicEnable;
    Pieza piezaSiguienteAntigua;
    Pieza nuevaPieza;
    SharedPreferences pref;

    // region Play/Stop background music
    @Given("^A config menu$")
    public void a_config_menu() throws Throwable {
        funcionamientoJuego = new FuncionamientoJuego(gameView, fichaView, tablero, "test", null);
        funcionamientoJuego.setMainActivity(mainActivity);
        mainActivity.setStart(funcionamientoJuego);
        configuracion = mock(Configuracion.class);
    }
    @When("^I choose Play or stop background music option$")
    public void i_choose_playstop_background_music_option() throws Throwable {
        List<Integer> p = configuracion.findViewById(R.array.numList);
        assertNotNull(p);
        assertNotNull(pref);
        musicEnable = Integer.parseInt(pref.getString("musicList", "1"))==1;
    }
    @Then("^The Background music must start or stop$")
    public void the_background_music_must_startstop() throws Throwable {
        assertTrue(musicEnable);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(pref.getString("musicList","1"), 0);
        editor.commit();
        musicEnable = Integer.parseInt(pref.getString("musicList", "1"))==1;
        assertFalse(musicEnable);
    }
    //endregion

    //region skip take a photo step
    @Given("^A skip button$")
    public void a_skip_button() throws Throwable {
        throw new PendingException();
    }

    ///Faltaria el on click
    @Then("^I will be redirect to end game screen$")
    public void i_will_be_redirect_to_end_game_screen() throws Throwable {
        throw new PendingException();
    }
    @And("^the displayed photo must be a predefinied photo$")
    public void the_displayed_photo_must_be_a_predefinied_photo() throws Throwable {
        throw new PendingException();
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
        Pieza p  = funcionamientoJuego.generarPiezaSiguiente();
        funcionamientoJuego.setPiezaSiguiente(p);
    }

    @Then("^The user have to see how the next piece changes$")
    public void theUserHaveToSeeHowTheNextPieceChanges() {
        Pieza p  = funcionamientoJuego.getPiezaSiguiente();
        assertNotEquals(piezaSiguienteAntigua,p);
    }
    //endregion

    @Given("^I want to the user loses 20 points$")
    public void iWantToTheUserLosesPoints() {
        funcionamientoJuego = new FuncionamientoJuego(gameView, fichaView, tablero, "test", null);
        funcionamientoJuego.setMainActivity(mainActivity);
        assertEquals(0, funcionamientoJuego.getPuntuacion());
        funcionamientoJuego.setPuntuacion(1000);
    }

    @When("^I click on the change next piece button$")
    public void iClickOnTheChangeNextPieceButton() {
        assertEquals(1000, funcionamientoJuego.getPuntuacion());
        funcionamientoJuego.losePointsWhenClickOnNextPiece();
    }

    @Then("^The points has to decrease 20 units$")
    public void thePointsHasToDecreaseUnits() {
        assertEquals(980, funcionamientoJuego.getPuntuacion());
    }
}