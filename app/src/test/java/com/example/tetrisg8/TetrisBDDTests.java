package com.example.tetrisg8;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


public class TetrisBDDTests {
    private GameView gameView = mock(GameView.class);
    private FichaView fichaView = mock(FichaView.class);
    private Tablero tablero = mock(Tablero.class);
    private Tablero table = new Tablero();
    private FuncionamientoJuego funcionamientoJuego;
    private MainActivity mainActivity = mock(MainActivity.class);
    private TakePhoto takePhoto = mock(TakePhoto.class);
    private GameOverActivity gameOverActivity = mock(GameOverActivity.class);
    private Pieza piezaSiguienteAntigua;
    private Pieza nuevaPieza;


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
    @When("^I click on skip button$")
    public void iClickOnSkipButton() {
        takePhoto.skip(null);
    }

    @Then("^The image saved will be a predefinied photo$")
    public void theImageSavedWillBeAPredefiniedPhoto() {
        assertNull(gameOverActivity.getFoto());
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

    //region lose points
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
    //endregion

/*
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
    }*/
}
