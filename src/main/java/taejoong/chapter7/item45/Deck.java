package taejoong.chapter7.item45;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    public static void main(String[] args) {
        List<Card> deckV1 = newDeckV1();
        List<Card> deckV2 = newDeckV2();

        System.out.println(deckV1);
        System.out.println(deckV2);
    }

    private static List<Card> newDeckV1() {
        List<Card> result = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                result.add(new Card(suit, rank));
            }
        }

        return result;
    }

    private static List<Card> newDeckV2() {
        return Stream.of(Suit.values())
                .flatMap(suit -> Stream.of(Rank.values()).map(rank -> new Card(suit, rank)))
                .collect(Collectors.toList());
    }

    @ToString
    static class Card{
        private Suit suit;
        private Rank rank;

        Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }

        Card(Rank rank) {
            this.rank = rank;
        }
    }

    enum Suit{
        SPACE, DIAMOND, CLOVA
    }

    enum Rank{
        ONE, TWO
    }
}
