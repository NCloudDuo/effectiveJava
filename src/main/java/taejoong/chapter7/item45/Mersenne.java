package taejoong.chapter7.item45;

import java.math.BigInteger;
import java.util.stream.Stream;

public class Mersenne {
    public static void main(String[] args) {
        primes().map(p -> BigInteger.TWO.pow(p.intValue()).subtract(BigInteger.ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .forEach(mp -> System.out.println(mp.bitLength() + ": " + mp));
    }

    static Stream<BigInteger> primes() {
        return Stream.iterate(BigInteger.TWO, BigInteger::nextProbablePrime);
    }
}
