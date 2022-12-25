import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class OneHourEventsStatistic implements EventsStatistic {
    private final Clock clock;
    private final Map<String, List<Instant>> eventsLog = new HashMap<>();
    private final double MINUTES = 60.0;
    private final long SECONDS = 3600L;

    public OneHourEventsStatistic(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void incEvent(String name) {
        eventsLog.computeIfAbsent(name, e -> new ArrayList<>()).add(clock.now());
    }

    @Override
    public double getEventStatisticByName(String name) {
        if (eventsLog.containsKey(name)) {
            return deleteOldEvents(eventsLog.get(name)).size() / MINUTES;
        } else {
            return 0;
        }
    }

    private List<Instant> deleteOldEvents(List<Instant> events) {
        while (!events.isEmpty() && !clock.now().minusSeconds(SECONDS).isBefore(events.get(0))) {
            events.remove(0);
        }
        return events;
    }

    @Override
    public Map<String, Double> getAllEventStatistic() {
        return eventsLog.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> getEventStatisticByName(e.getKey())));
    }

    @Override
    public void printStatistic() {
        getAllEventStatistic().forEach((event, rpm) -> System.out.format("Event: %s, RPM: %f \n", event, rpm));
    }
}
