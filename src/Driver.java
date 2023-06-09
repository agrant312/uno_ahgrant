import java.awt.event.WindowEvent;
import java.util.Scanner;
public class Driver {
    public static Deck deck = new Deck();
    public static Player[] players = new Player[4];
    public static Card activeCard = null;
    public static Deck discard = new Deck();
    public static int place = 0;
    static Card draw() {
        if (deck.getDeckArray().length == 0) {
            System.out.println("The deck has been reshuffled");
            deck.setDeckArray(deck.shuffle(discard.getDeckArray()));
            discard.setDeckArray(new Card[0]);
        }
        return deck.draw(deck.deckArray);
    }
    public static void main(String[] args) {
        StringBuffer outPutText = new StringBuffer();
        Scanner scan = new Scanner(System.in);
        HomePage frame = new HomePage();
        frame.setVisible(true);
        discard.setDeckArray(new Card[0]);
        while(frame.isActive == true) {
            System.out.println("waiting");
        }
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player("Player " + (i + 1));
        }
        deck.deckArray = deck.shuffle(deck.deckArray);
        deck.deal(players);
        activeCard = draw();
        System.out.println("The first card is a " + activeCard.toString());
        outPutText.append("The first card is a " + activeCard.toString());
        while (activeCard.getValue() == 14) {
            System.out.println("Redrawing card . . .");
            outPutText.append("\nRedrawing card . . .");
            discard.take(activeCard);
            activeCard = draw();
            System.out.println("The new first card is a " + activeCard.toString());
            outPutText.append("\nThe new first card is a " + activeCard.toString());
        }
        GamePage game = new GamePage(outPutText.toString());
        game.setVisible(true);
        while(game.isActive == true) {
            System.out.println("waiting");
        }
        boolean playing = true;
        while (playing) {
            for (int i = 0; i < players.length; i++) {
                outPutText = new StringBuffer();
                ActionCard placeHolder = new ActionCard();
                boolean endEarly = false;
                if (activeCard.getClass().equals(placeHolder.getClass())) {
                    if (((ActionCard) activeCard).actionComplete == false) {
                        if (activeCard.getValue() == 10) {
                            System.out.println(players[i].getName() + " has been skipped");
                            outPutText.append("\n" + players[i].getName() + " has been skipped");
                            endEarly = true;
                        }
                        if (activeCard.getValue() == 11) {
                            System.out.println("The order has been reversed");
                            outPutText.append("\n" + "The order has been reversed");
                            Player[] parallel = new Player[players.length];
                            int k = players.length - 1;
                            int indexOfPrevious = i - 1;
                            for (int j = 0; j < players.length; j++) {
                                parallel[j] = players[k];
                                if (k == indexOfPrevious) {
                                    i = j;
                                }
                                k--;
                            }
                            players = parallel;
                            endEarly = true;
                        }
                        if (activeCard.getValue() == 12) {
                            System.out.println(players[i].getName() + " drew two cards");
                            outPutText.append("\n" + players[i].getName() + " drew two cards");
                            players[i].hand.take(draw());
                            players[i].hand.take(draw());
                            endEarly = true;
                        }
                        if (activeCard.getValue() == 14) {
                            System.out.println(players[i].getName() + " drew four cards");
                            outPutText.append("\n" + players[i].getName() + " drew four cards");
                            players[i].hand.take(draw());
                            players[i].hand.take(draw());
                            players[i].hand.take(draw());
                            players[i].hand.take(draw());
                            endEarly = true;
                        }
                        ((ActionCard) activeCard).setActionComplete(true);
                    }
                }
                if (!endEarly) {
                    Card hold = null;
                    try {
                        hold = activeCard;
                        activeCard = players[i].hand.pick(hold);
                        discard.take(hold);
                        System.out.println(players[i].getName() + " played a " + activeCard.toString());
                        outPutText.append("\n" + players[i].getName() + " played a " + activeCard.toString());
                    } catch (Exception e) {
                        players[i].hand.take(draw());
                        System.out.println(players[i].getName() + " drew a card");
                        outPutText.append("\n" + players[i].getName() + " drew a card");
                        try {
                            activeCard = players[i].hand.pick(hold);
                            discard.take(hold);
                            System.out.println(players[i].getName() + " played a " + activeCard.toString());
                            outPutText.append("\n" + players[i].getName() + " played a " + activeCard.toString());
                        } catch (Exception x) {
                            activeCard = hold;
                            System.out.println(players[i].getName() + " has no cards to play");
                            outPutText.append("\n" + players[i].getName() + " has no cards to play");
                        }
                    }
                    if (players[i].hand.getDeckArray().length == 1) {
                        System.out.println(players[i].getName() + " shouts: 'UNO!'");
                        outPutText.append("\n" + players[i].getName() + " shouts: 'UNO!'");
                    }
                    if (players[i].hand.getDeckArray().length == 0) {
                        System.out.println(players[i].getName() + " played their last card and won the game!");
                        outPutText.append("\n" + players[i].getName() + " played their last card and won the game!");
                        playing = false;
                    }
                }
                game.setBlurb(outPutText.toString());
                while(game.isActive == true) {

                }
                if (playing == false) {
                    break;
                }
            }
        }
        for (int i = 0; i < players.length; i++) {
            outPutText = new StringBuffer();
            if (players[i].getHand().getDeckArray().length > 0) {
                System.out.println(players[i].getName() + " finished with these " + players[i].getHand().getDeckArray().length + " cards");
                outPutText.append("\n" + players[i].getName() + " finished with these " + players[i].getHand().getDeckArray().length + " cards");
                int points = 0;
                for (int j = 0; j < players[i].getHand().getDeckArray().length; j++) {
                    System.out.println(players[i].getHand().getDeckArray()[j].toString());
                    outPutText.append("\n" + players[i].getHand().getDeckArray()[j].toString());
                    points += players[i].getHand().getDeckArray()[j].getValue();
                }
                System.out.println("and finished with " + points + " points");
                outPutText.append("\n" + "and finished with " + points + " points");
            } else {
                System.out.println(players[i].getName() + " played all their cards and won the game!");
                outPutText.append("\n" + players[i].getName() + " played all their cards and won the game!");
            }
            game.setBlurb(outPutText.toString());
            while(game.isActive == true) {
                System.out.println("waiting");
            }
        }
    }
}
