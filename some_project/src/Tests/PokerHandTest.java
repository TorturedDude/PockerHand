package Tests;

import global_defs.Ranking;
import models.PokerHand;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PokerHandTest {

    @Test
    public void testHandComparison() {
        PokerHand hand1 = new PokerHand("KS 2H 5C JD TD");
        PokerHand hand2 = new PokerHand("2C 3C AC 4C 5C");
        PokerHand hand3 = new PokerHand("KS AS QS JS TS");

        assertTrue(hand1.compareTo(hand2) > 0); // hand1 сильнее hand2
        assertTrue(hand2.compareTo(hand1) < 0); // hand2 слабее hand1
        assertEquals(0, hand1.compareTo(hand1)); // hand1 равно hand1

        assertTrue(hand1.compareTo(hand3) > 0); // hand1 слабее hand3
        assertTrue(hand3.compareTo(hand1) < 0); // hand3 сильнее hand1

        assertTrue(hand2.compareTo(hand3) > 0); // hand2 слабее hand3
        assertTrue(hand3.compareTo(hand2) < 0); // hand3 сильнее hand2
    }

    @Test
    public void testHandSorting() {
        PokerHand hand1 = new PokerHand("KS 2H 5C JD TD");
        PokerHand hand2 = new PokerHand("2C 3C AC 4C 5C");
        PokerHand hand3 = new PokerHand("KS AS QS JS TS");

        List<PokerHand> hands = new ArrayList<>();
        hands.add(hand2);
        hands.add(hand1);
        hands.add(hand3);

        Collections.sort(hands);

        assertEquals(hand3, hands.get(0)); // hand1 на первом месте после сортировки
        assertEquals(hand2, hands.get(1)); // hand2 на втором месте после сортировки
        assertEquals(hand1, hands.get(2)); // hand3 на третьем месте после сортировки
    }

    @Test
    void getRanking() {
        PokerHand hand1 = new PokerHand("KS 2H 5C JD TD");
        PokerHand hand2 = new PokerHand("2C 3C AC 4C 5C");
        PokerHand hand3 = new PokerHand("KS AS QS JS TS");

        assertEquals(hand1.getRanking(), Ranking.HIGH_CARD);
        assertEquals(hand2.getRanking(), Ranking.FLUSH);
        assertEquals(hand3.getRanking(), Ranking.STRAIGHT_FLUSH);
    }
}