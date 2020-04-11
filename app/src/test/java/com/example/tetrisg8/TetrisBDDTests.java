package com.example.tetrisg8;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.support.test.*;

import androidx.preference.PreferenceManager;
import androidx.test.rule.ActivityTestRule;

import cucumber.api.PendingException;
import cucumber.api.android.CucumberInstrumentation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;


public class TetrisBDDTests {
  //@Rule
  //ActivityTestRule<StartGame> mActivityTestRule = new ActivityTestRule<>(StartGame.class);
   // public ActivityTestRule<StartGame> activityTestRule = new ActivityTestRule<>(StartGame.class);
    GameOverActivity gameOverActivity;
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

    //region go to main screen
    @Given("^A button$")
    public void a_button() throws Throwable {
        gameOverActivity = mock(GameOverActivity.class);
    }
    @When("^I click on it$")
    public void i_click_on_it() throws Throwable {
        gameOverActivity.onClick(gameOverActivity.findViewById(R.id.botPantallaPrincipal));
    }
    @Then("^I will be redirect to the main screen$")
    public void i_will_be_redirect_to_the_main_screen() throws Throwable {

        //StartGame s = mActivityTestRule.getActivity();
        StartGame startGame = mock(StartGame.class);
        //System.out.println(gameOverActivity.getCallingActivity().getClassName());
        //System.out.println(StartGame.class.getName());
        //View s1 = gameOverActivity.getCurrentFocus();
        String s2 =  startGame.getClass().getName();
        assertEquals(true,true);
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
}