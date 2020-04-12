package com.example.tetrisg8;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.Preference;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.support.test.*;

import androidx.preference.PreferenceManager;
import androidx.test.InstrumentationRegistry;
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
import org.mockito.internal.matchers.Null;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.anyListOf;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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


    @Given("^i want to be able to go to main screen from end game screen$")
    public void iWantToBeAbleToGoToMainScreenFromEndGameScreen() {
    }

    @When("^I click main screen button$")
    public void iClickMainScreenButton() {
    }

    @Then("^I will be redirect to the main screen$")
    public void iWillBeRedirectToTheMainScreen() {
    }
    //endregion

    //region start/stop music
    @Given("^I want to be able to choose if the music is playing$")
    public void iWantToBeAbleToChooseIfTheMusicIsPlaying() {
    }

    @When("^I choose Play or stop background music option$")
    public void iChoosePlayOrStopBackgroundMusicOption() {
    }

    @Then("^The Background music must start or stop$")
    public void theBackgroundMusicMustStartOrStop() {
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