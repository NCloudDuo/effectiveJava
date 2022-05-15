package taejoong.chapter8.item50;

import java.time.Instant;

public final class InstantPeriod{
    private final Instant start;
    private final Instant end;

    public InstantPeriod(Instant start, Instant end) {
        if(start.compareTo(end)>0) throw new IllegalArgumentException();
        this.start = start;
        this.end = end;
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }
}
