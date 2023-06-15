import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Test
    void nullIsArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
    }

    @Test
    void nullIsArgumentMessage() {
        Throwable message = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", message.getMessage());
    }

    @Test
    void argumentIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());
        });
    }

    @Test
    void argumentIsEmptyMessage() {
        Throwable message = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());
        });
        assertEquals("Horses cannot be empty.", message.getMessage());
    }


    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("" + i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for( int i = 1; i <= 5; i++) {
            horses.add(Mockito.spy(new Horse("" + i, i)));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        for (Horse horse : hippodrome.getHorses()) {
            horse.move();
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        Horse horse1 = new Horse("mama", 2.0, 3.0);
        Horse horse2 = new Horse("papa", 1.0, 1.0);
        Horse horse3 = new Horse("baba", 3.0, 2.0);
        Horse horse4 = new Horse("deda", 2.2, 4.0);
        Horse horse5 = new Horse("sina", 2.3, 22.0);
        Horse horse6 = new Horse("docha", 2.5, 3.0);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4, horse5, horse6));
        assertEquals(horse5, hippodrome.getWinner());
    }
}