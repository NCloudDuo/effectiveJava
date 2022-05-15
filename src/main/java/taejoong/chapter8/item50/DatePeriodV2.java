package taejoong.chapter8.item50;

import java.util.Date;

public final class DatePeriodV2{
    private final Date start;
    private final Date end;

    public DatePeriodV2(Date start, Date end) {
        // 유효성 검사 전에 방어적 복사본을 만들고, 복사본에 대해서 유효성 검사를 수행하자.
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());

        if(this.start.compareTo(this.end)>0) throw new IllegalArgumentException();
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
