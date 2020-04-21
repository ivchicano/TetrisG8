package com.example.tetrisg8;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


public class SmokeTests {
    private GameView gameView = mock(GameView.class);
    private FichaView fichaView = mock(FichaView.class);
    private Tablero tablero = mock(Tablero.class);
    private Tablero table = new Tablero();
    private FuncionamientoJuego funcionamientoJuego = new FuncionamientoJuego(gameView, fichaView, tablero, "test", null);
    private MainActivity mainActivity = mock(MainActivity.class);
    private Pieza nuevaPieza = new Pieza(8);

	//Smoke test 1

    @Given("^the tetris in pause$")
    public void the_tetris_in_pause() {
        funcionamientoJuego = new FuncionamientoJuego(gameView, fichaView, tablero, "test", null);
    }

    @When("^the game is starting$")
    public void the_game_is_starting() throws Throwable {
        funcionamientoJuego.setMainActivity(mainActivity);
        mainActivity.setStart(funcionamientoJuego);
    }

    @Then("^the thread is created$")
    public void the_thread_is_created() {
        assertNotNull(funcionamientoJuego.getMainActivity());
    }

    //end smoke test 1
    //Smoke test 2
    @When("^the game is paused$")
    public void the_game_is_paused() throws Throwable {
        mainActivity.onPause();
    }

    @Then("^the music is disable$")
    public void the_music_is_disable() {
        assertFalse(funcionamientoJuego.isMusicEnable());
    }

    //end smoke test 2
    //Smoke test 3
    @When("^the game is stop$")
    public void the_game_is_stop() throws Throwable {
        mainActivity.onDestroy();
    }

    //end smoke test 3
    //Smoke test 4

    @Given("^the piece$")
    public void the_piece() throws Throwable {
    }

    @When("^the piece is created$")
    public void the_piece_is_created() throws Throwable {
        nuevaPieza = new Pieza(5);
    }

    @Then("^the piece is created correctly$")
    public void the_piece_is_created_correctly() throws Throwable {

        assertEquals(5, nuevaPieza.getCuadrados()[0]);
        assertEquals(1, nuevaPieza.getCuadrados()[1]);
        assertEquals(4, nuevaPieza.getCuadrados()[2]);
        assertEquals(2, nuevaPieza.getCuadrados()[3]);
        assertEquals(5, nuevaPieza.getCuadrados()[4]);
        assertEquals(2, nuevaPieza.getCuadrados()[5]);
        assertEquals(4, nuevaPieza.getCuadrados()[6]);
        assertEquals(3, nuevaPieza.getCuadrados()[7]);
    }

    @When("^the table is created$")
    public void the_table_is_created() throws Throwable{
        table.inicializarTablero();
    }

    @Then("^the table is created correctly$")
    public void the_table_is_created_correctly() throws Throwable {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(0,table.getTablero()[i][j]);
            }
        }
    }

    @Given("^the game$")
    public void theGame() {
        funcionamientoJuego = new FuncionamientoJuego(gameView, fichaView, tablero, "test", null);
        funcionamientoJuego.setMainActivity(mainActivity);
        mainActivity.setStart(funcionamientoJuego);
    }
}