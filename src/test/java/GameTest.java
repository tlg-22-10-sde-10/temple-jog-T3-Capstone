import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by dev0 on 2/4/23.
 */
class GameTest {

    HashMap<String, Item> itemsMap;
    Game game;
    @BeforeEach
    void setUp() {
        itemsMap = new HashMap<>();
        itemsMap.put("alpha",new Item("alpha"));
        itemsMap.put("bravo",new Item("bravo"));
        itemsMap.put("charlie",new Item("charlie"));
        game = new Game(new Player(), new HashMap<>(), new HashMap<>(), itemsMap);
    }

//CONTROLLERS


    //processLooking


    //processQuitting
    @Test
    void test_processQuitting_Given_InputStringToQuit_ShouldReturnSameInputString(){}
    void test_processQuitting_Given_BadInputStringToQuit_ShouldReturnInvalidInputString(){}


    //HELPER METHODS
    @Test
    void test_popItemFromMap_GivenValidNounString_ShouldReturnValidItem(){
        Item expected = new Item("alpha");
        Item actual = game.popItemFromMap("alpha");
        assertEquals( expected.getName(), actual.getName() );
    }
    @Test
    void test_popItemFromMap_GivenInvalidNoun_ShouldReturnEmptyItemObject(){
        Item expected = new Item();
        String testKey = "Zulu";
        Item actual = game.popItemFromMap(testKey);
        assertTrue(actual.equals(expected));
    }
    @Test
    void test_popItemFromMap_GivenEmptyNoun_ShouldReturnEmptyItemObject() {
        Item expected = new Item();
        String testKey = "";
        Item actual = game.popItemFromMap(testKey);
        assertTrue(actual.equals(expected));
    }
    @Test
    void test_popItemFromMap_GivenNull(){
        Item expected = new Item();
        Item actual = game.popItemFromMap(null);
        assertTrue(actual.equals(expected));
    }
}