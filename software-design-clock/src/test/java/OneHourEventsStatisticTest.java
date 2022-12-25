import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OneHourEventsStatisticTest {
    private static final double DELTA = 1e-6;
    private static final double MINUTES = 60.0;
    private static final long SECONDS = 3600L;
    SetClock clock;
    EventsStatistic eventsStatistic;

    @BeforeEach
    public void setUp() {
        clock = new SetClock(Instant.now());
        eventsStatistic = new OneHourEventsStatistic(clock);
    }

    @Test
    public void testEmpty() {
        assertEquals(0, eventsStatistic.getEventStatisticByName("kek"));
    }

    @Test
    public void testSingleEvent() {
        eventsStatistic.incEvent("lol");
        assertEquals(1.0 / MINUTES, eventsStatistic.getEventStatisticByName("lol"), DELTA);
        assertEquals(0.0 / MINUTES, eventsStatistic.getEventStatisticByName("kek"), DELTA);

        clock.incTime(SECONDS);
        assertEquals(0.0 / MINUTES, eventsStatistic.getEventStatisticByName("lol"), DELTA);
    }

    @Test
    public void testMultipleEvents() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 0; j < i; j++) {
                eventsStatistic.incEvent(String.valueOf(i));
            }
        }
        for (int i = 1; i <= 10; i++) {
            assertEquals((long) i / MINUTES, eventsStatistic.getEventStatisticByName(String.valueOf(i)), DELTA);
        }

        clock.incTime(SECONDS * 2);
        for (int i = 1; i <= 10; i++) {
            eventsStatistic.incEvent(String.valueOf(i));
        }
        for (int i = 1; i <= 10; i++) {
            assertEquals(1.0 / MINUTES, eventsStatistic.getEventStatisticByName(String.valueOf(i)), DELTA);
        }
    }

    @Test
    public void testAllEventsStatistic() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 0; j < i; j++) {
                eventsStatistic.incEvent(String.valueOf(i));
            }
        }

        Map<String, Double> stats = eventsStatistic.getAllEventStatistic();

        for (int i = 1; i <= 10; i++) {
            assertEquals((long) i / MINUTES, stats.get(String.valueOf(i)), DELTA);
        }
    }
}
