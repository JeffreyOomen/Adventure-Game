//package nl.avans.ivh11.a2b;
//
//import nl.avans.ivh11.a2b.domain.character.Character;
//import nl.avans.ivh11.a2b.domain.character.Elf;
//import nl.avans.ivh11.a2b.domain.util.EquipmentEnum;
//import nl.avans.ivh11.a2b.domain.util.Stats;
//import nl.avans.ivh11.a2b.domain.util.observer.Observer;
//import nl.avans.ivh11.a2b.domain.util.observer.TextBasedLogger;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//
///**
// * Created by matthijs on 8-3-17.
// */
//public class ObserverTests {
//    private Character character;
//    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
//
//    @Before
//    public void setup() {
//        this.character = new Elf("Elf", new Stats());
//        this.character.setAttackStyle(EquipmentEnum.SWORD);
//        System.setOut(new PrintStream(outContent));
//        System.setErr(new PrintStream(errContent));
//    }
//
//    @Test
//    public void TextBasedLoggerTest() {
//        //Arrange
//        Observer observer = new TextBasedLogger(character);
//        int currentHitpoints = character.getCurrentHitpoints();
//
//        //Act
//        character.takeDamage(10);
//
//        //Assert
//        assertEquals("New state: " + Integer.toString(currentHitpoints - 10) + "\n", outContent.toString());
//    }
//
//    @After
//    public void tearDown() {
//        this.character = null;
//        System.setOut(null);
//        System.setErr(null);
//    }
//}
