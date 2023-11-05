package models;

import global_defs.Ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PokerHand implements Comparable<PokerHand> {
    private List<Card> cards;
    private Ranking ranking;

    public PokerHand(String hand) {
        parseHand(hand);
        ranking = getRanking();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PokerHand pokerHand)) return false;
        return Objects.equals(cards, pokerHand.cards) && getRanking() == pokerHand.getRanking();
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards, getRanking());
    }

    @Override
    public String toString() {
        return "PokerHand{" +
                "cards=" + cards +
                ", ranking=" + ranking +
                '}';
    }

    @Override
    public int compareTo(PokerHand other) {
        if (ranking != other.ranking) {
            return ranking.compareTo(other.ranking);
        } else {
            for (int i = 0; i < cards.size(); i++) {
                int compareResult = cards.get(i).compareTo(other.cards.get(i));
                if (compareResult != 0) {
                    return compareResult;
                }
            }
        }
        return 0;
    }

    private void parseHand(String hand) {
        cards = new ArrayList<Card>();
        for(String card : hand.split(" ")) {
            cards.add(new Card(card));
        }
    }

    public Ranking getRanking() {
        if (isStraightFlush()) {
            return Ranking.STRAIGHT_FLUSH;
        } else if (isFourOfAKind()) {
            return Ranking.FOUR_OF_A_KIND;
        } else if (isFullHouse()) {
            return Ranking.FULL_HOUSE;
        } else if (isFlush()) {
            return Ranking.FLUSH;
        } else if (isStraight()) {
            return Ranking.STRAIGHT;
        } else if (isThreeOfAKind()) {
            return Ranking.THREE_OF_A_KIND;
        } else if (isTwoPair()) {
            return Ranking.TWO_PAIR;
        } else if (isOnePair()) {
            return Ranking.ONE_PAIR;
        } else {
            return Ranking.HIGH_CARD;
        }
    }

    private boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    private boolean isFourOfAKind() {
        List<Integer> values = getSortedCardValues();
        return hasNCardsWithSameValue(values, 4);
    }

    private boolean isFullHouse() {
        List<Integer> values = getSortedCardValues();
        return hasNCardsWithSameValue(values, 3) && hasNCardsWithSameValue(values, 2);
    }

    private boolean isFlush() {
        String suit = cards.get(0).getSuit();
        for (int i = 1; i < cards.size(); i++) {
            if (!cards.get(i).getSuit().equals(suit)) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight() {
        List<Integer> values = getSortedCardValues();
        int prevValue = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) != prevValue + 1) {
                return false;
            }
            prevValue = values.get(i);
        }
        return true;
    }

    private boolean isThreeOfAKind() {
        List<Integer> values = getSortedCardValues();
        return hasNCardsWithSameValue(values, 3);
    }

    private boolean isTwoPair() {
        List<Integer> values = getSortedCardValues();
        int countPairs = 0;
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i).equals(values.get(i + 1))) {
                countPairs++;
                i++;
            }
        }
        return countPairs == 2;
    }

    private boolean isOnePair() {
        List<Integer> values = getSortedCardValues();
        return hasNCardsWithSameValue(values, 2);
    }

    private List<Integer> getSortedCardValues() {
        List<Integer> values = new ArrayList<>();
        for (Card card : cards) {
            values.add(card.getCardValue());
        }
        Collections.sort(values);
        return values;
    }

    private boolean hasNCardsWithSameValue(List<Integer> values, int n) {
        int count = 1;
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i).equals(values.get(i + 1))) {
                count++;
                if (count == n) {
                    return true;
                }
            } else {
                count = 1;
            }
        }
        return false;
    }
}
