
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    Horse mockHorse = Mockito.spy(new Horse("Maria", 2.5, 1.0));

    @Test
    void constructorFirstParameterIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 1.0);
        });
    }

    @Test
    void constructorFirstParameterIsNullMessage() {
        Throwable message = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 1.0);
        });
        assertEquals("Name cannot be null.", message.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", ".", ","})
    void constructorFirstParameterIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("", 1.0);
        });
    }

    @Test
    void constructorSecondParameterIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Maria", -1.0);
        });
    }

    @Test
    void constructorSecondParameterIsNegativeMessage() {
        Throwable message = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Maria", -1.0);
        });
        assertEquals("Speed cannot be negative.", message.getMessage());
    }

    @Test
    void constructorThirdParameterIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Maria", 1.0, -1.0);
        });
    }

    @Test
    void constructorThirdParameterIsNegativeMessage() {
        Throwable message = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Maria", 1.0, -1.0);
        });
        assertEquals("Distance cannot be negative.", message.getMessage());
    }

    @Test
    void getName() {
        assertEquals("Maria", mockHorse.getName());
    }

    @Test
    void getSpeed() {
        assertEquals(2.5, mockHorse.getSpeed());
    }

    @Test
    void getDistance() {
        assertEquals(1.0, mockHorse.getDistance());

        if (mockHorse.getDistance() == 0) {
            assertEquals(0, mockHorse.getDistance());
        }
    }

    @Test
    void checkGetRandom() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockHorse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }

    }

    @ParameterizedTest
    @ValueSource (doubles = {0.1, 0.2, 0.5, 1.0})
    void move(double random) {
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("lala", 2.0, 1.0);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();
            assertEquals(1.0 + 2.0 * random, horse.getDistance());
        }
    }
}