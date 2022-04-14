package junghyeok.duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class DurationTest {

    @Test
    void test1(){
        Duration duration = Duration.ofMinutes(100);
        long seconds = duration.getSeconds();
        Assertions.assertEquals(100*60, seconds);
        Duration minusHours = duration.minusHours(2).abs();
        long seconds2 = minusHours.get(ChronoUnit.SECONDS);
        Assertions.assertEquals(20 * 60, seconds2);
    }
}
