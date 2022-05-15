package taejoong.chapter8.item50;

import java.time.Instant;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        useDatePeriodV1();    // 불변식이 깨진다
        useInstantPeriod();   // setter가 없어서 불변식이 안 깨진다(?)
        useDatePeriodV2();    // 방어적 복사로 인해 불변식이 안 깨진다.
    }

    private static void useInstantPeriod() {
        Instant start = Instant.now();
        Instant end = Instant.now();
        InstantPeriod instantPeriod = new InstantPeriod(start, end);
        start = start.plusMillis(24 * 60 * 60 * 60);    // Instant를 살펴보니 setter가 없는 듯 하다. 이 함수의 경우 copy 반ㅊ
        boolean isIllegalState = instantPeriod.getStart().compareTo(instantPeriod.getEnd()) > 0;
        System.out.println("isIllegalState when use Instant = " + isIllegalState);
    }

    private static void useDatePeriodV1() {
        Date start = new Date();
        Date end = new Date();
        DatePeriodV1 datePeriod = new DatePeriodV1(start, end);
        start.setYear(125);
        boolean isIllegalState = datePeriod.getStart().compareTo(datePeriod.getEnd()) > 0;
        System.out.println("isIllegalState when use DateV1 = " + isIllegalState);
    }

    private static void useDatePeriodV2() {
        Date start = new Date();
        Date end = new Date();
        DatePeriodV2 datePeriod = new DatePeriodV2(start, end);
        start.setYear(125);
        boolean isIllegalState = datePeriod.getStart().compareTo(datePeriod.getEnd()) > 0;
        System.out.println("isIllegalState when use DateV2 = " + isIllegalState);
    }
}

