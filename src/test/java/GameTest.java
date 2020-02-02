import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    Game game;

    @Before
    public void before(){

    }

    @Test
    public void canAddPlayers(){
        game = new Game();
        //assertEquals(3, game.playerCount());
    }
}
