package taejoong.chapter8.item50;

import java.util.Date;

public final class DatePeriodV1{
    private final Date start;
    private final Date end;

    public DatePeriodV1(Date start, Date end) {
        if(start.compareTo(end)>0) throw new IllegalArgumentException();
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
