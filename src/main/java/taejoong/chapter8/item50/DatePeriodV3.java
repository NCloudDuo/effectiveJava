package taejoong.chapter8.item50;

import java.util.Date;

public final class DatePeriodV3{
    private final Date start;
    private final Date end;

    public DatePeriodV3(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());

        if(this.start.compareTo(this.end)>0) throw new IllegalArgumentException();
    }

    // 접근자에 방어적 복사
    public Date getStart() {
        return new Date(start.getTime());
    }

    public Date getEnd() {
        return new Date(end.getTime());
    }
}
