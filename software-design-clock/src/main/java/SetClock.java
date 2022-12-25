import java.time.Instant;

public class SetClock implements Clock {
    private Instant now;

    public SetClock(Instant now) {
        this.now = now;
    }

    public void incTime(long seconds) {
        now = now.plusSeconds(seconds);
    }
    @Override
    public Instant now() {
        return now;
    }
}
