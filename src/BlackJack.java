//10/10/22

import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] cards = {"ace", "2", "3", "4", "5", "6",
                "7", "8", "9", "10",
                "jack", "queen", "king"};
        ArrayList<Card> deck = new ArrayList<>();
        int count = 0;
        int type = 4;

        //make new deck and then shuffle it
        for (int i = 0; i < 52; i++) {

            deck.add(new Card(cards[count]));
            type--;
            if (type == 0) {
                type = 4;
                count++;
            }
        }
        Collections.shuffle(deck);

        while (true) {
            int value = 0;
            int aceCheck = 0;
            ArrayList<Card> hand = new ArrayList<>();
            ArrayList<Card> dealerHand = new ArrayList<>();

            dealerHand.add(drawCard(deck));
            dealerHand.add(drawCard(deck));
            System.out.println("Dealer hand value is: " + getValue(dealerHand));


            hand.add(drawCard(deck));
            hand.add(drawCard(deck));
            value = getValue(hand);
            System.out.println("Hand Value is: " + value);

            while (true) { //drawing Starts
                System.out.println("Do you want to draw a card? 1 for yes, 2 for no");
                int userIn = scan.nextInt();
                if (userIn == 1) {
                    hand.add(drawCard(deck));
                    value = getValue(hand);
                    if (aceCheck == 1){
                        value = value - 10;
                    }
                    System.out.println("Hand Value is: " + value);
                } else {
                    break;
                }

                if (value > 21) {
                    if (aceCheck == 0) {
                        if (checkAce(hand)) {
                            System.out.println("Ace Detected");
                            value = value - 10;
                            System.out.println("New value: " + value);
                            aceCheck++;
                            if (value > 21){
                                System.out.println("Busted!");
                                value = 0;
                                break;
                            }
                        } else{
                            System.out.println("Busted!");
                            value = 0;
                            break;
                        }
                    } else {
                        System.out.println("Busted!");
                        value = 0;
                        break;
                    }
                }
            }
            if (getValue(dealerHand) < 17){
                dealerHand.add(drawCard(deck));
                System.out.println("Dealer hand value is: " + getValue(dealerHand));
            }


            if (value > getValue(dealerHand)){
                System.out.println("You win, with a hand of " + value);
            } else{
                if (getValue(dealerHand)>21){
                    System.out.println("Dealer busted! you win!");
                } else {
                    System.out.println("You lose! the dealer had " + getValue(dealerHand));
                }

            }
            break;
        }

    }

    public static Card drawCard(ArrayList<Card> c) {

        Card rtrn = c.get(0);
        System.out.println(rtrn);
        c.remove(0);
        return rtrn;
    }

    public static int getValue(ArrayList<Card> c) {
        int sum = 0;
        for (Card out : c) {
            sum += out.returnValue();
        }
        return sum;
    }

    public static boolean checkAce(ArrayList<Card> c) {

        for (Card out : c) {
            if (out.returnName().equals("ace")) {
                return true;
            }
        }

        return false;
    }


}
