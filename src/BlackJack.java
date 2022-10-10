//10/10/22 test!!
// There's a ton wrong with this program.
// Will re-make at a later date
//

import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //sets up possible cards
        String[] cards = {"ace", "2", "3", "4", "5", "6",
                "7", "8", "9", "10",
                "jack", "queen", "king"};
        ArrayList<Card> deck = new ArrayList<>();
        int count = 0;
        int type = 4;

        //for loop for filling new deck
        for (int i = 0; i < 52; i++) {

            deck.add(new Card(cards[count]));
            type--;
            if (type == 0) {
                type = 4;
                count++;
            }
        }

        // uses collection to shuffle deck
        Collections.shuffle(deck);

        while (true) { // start overall game
            int value;
            int aceCheck = 0;

            //create hand and dealer hand
            ArrayList<Card> hand = new ArrayList<>();
            ArrayList<Card> dealerHand = new ArrayList<>();

            //draw two cards for dealer
            dealerHand.add(drawCard(deck));
            dealerHand.add(drawCard(deck));
            System.out.println("Dealer hand value is: " + getValue(dealerHand));

            //draw two cards for player
            hand.add(drawCard(deck));
            hand.add(drawCard(deck));
            value = getValue(hand); //puts value of hand in a variable, makes it easier to work with aces. will fix later
            System.out.println("Hand Value is: " + value);

            while (true) { //drawing Starts
                //loops until player enters anything other than 1
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

                //set of conditions to check if drawing can continue
                if (value > 21) {
                    if (aceCheck == 0) {
                        if (checkAce(hand)) {
                            //checks if hand has an ace, where 11 will turn into 1
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
            //logic for dealer
            if (getValue(dealerHand) < 17){
                dealerHand.add(drawCard(deck));
                System.out.println("Dealer hand value is: " + getValue(dealerHand));
            }

            //checks winner
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
        //returns first entry in array list and deletes it

        Card rtrn = c.get(0);
        System.out.println(rtrn);
        c.remove(0);
        return rtrn;
    }

    public static int getValue(ArrayList<Card> c) {
        //returns value of given arrayList
        int sum = 0;
        for (Card out : c) {
            sum += out.returnValue();
        }
        return sum;
    }

    public static boolean checkAce(ArrayList<Card> c) {
        //checks if hand has ace or not.
        for (Card out : c) {
            if (out.returnName().equals("ace")) {
                return true;
            }
        }
        return false;
    }


}
