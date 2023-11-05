import models.PokerHand;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ArrayList<PokerHand> hands = new ArrayList<PokerHand>();
        hands.add(new PokerHand("KS 2H 5C JD TD"));
        hands.add(new PokerHand("2C 3C AC 4C 5C"));
        hands.add(new PokerHand("KS AS QS JS TS"));
        for (PokerHand hand : hands) {
            System.out.println(hand);
        }

        Collections.sort(hands);
        System.out.println();

        for (PokerHand hand : hands) {
            System.out.println(hand);
        }
    }
}