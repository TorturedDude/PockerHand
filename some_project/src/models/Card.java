package models;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Card implements Comparable<Card> {
    private final String cardValue;
    private final String suit;

    public Integer getCardValue() {
        List<String> valuesOrder = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");
        return valuesOrder.indexOf(cardValue) + 2;
    }

    public String getSuit() {
        return suit;
    }

    public Card(String card) {
        this.cardValue = String.valueOf(card.charAt(0));
        suit = String.valueOf(card.charAt(1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return Objects.equals(getCardValue(), card.getCardValue()) && Objects.equals(getSuit(), card.getSuit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardValue(), getSuit());
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardValue='" + cardValue + '\'' +
                ", suit='" + suit + '\'' +
                '}';
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(getCardValue(), other.getCardValue());
    }
}
