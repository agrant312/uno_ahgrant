import java.util.Random;
public class Deck {
    public Card[] deckArray;
    public Deck() {
        deckArray = newDeck();
    }
    public Card[] discard = new Card[0];

    public Card[] getDeckArray() {
        return this.deckArray;
    }

    public void setDeckArray(Card[] deckArray) {
        this.deckArray = deckArray;
    }

    public Card[] shuffle(Card[] inputDeck) {
        int[] marker = new int[inputDeck.length];
        for (int i = 0; i < marker.length; i++) {
            marker[i] = 1;
        }
        Random ran = new Random();
        Card[] result = new Card[marker.length];
        for (int i = 0; i < marker.length; i++) {
            boolean placed = false;
            while (!placed) {
                int index = ran.nextInt(marker.length);
                if (marker[index] > 0) {
                    result[i] = inputDeck[index];
                    try {
                        if (((ActionCard) inputDeck[index]).actionComplete == true) {
                            ((ActionCard) inputDeck[index]).actionComplete = false;
                        }
                    } catch (Exception e) {

                    }
                    marker[index] = 0;
                    placed = true;
                }
            }
        }
        return result;
    }
    public static Card[] newDeck() {
        Card[] output = new Card[112];
        int index = 0;
        while (index < 112) {
            for (int i = 1; i < 5; i++) {
                for (int j = 0; j < 13; j++) {
                    if (j < 10) {
                        output[index] = new Card(i,j);
                    } else {
                        output[index] = new ActionCard(i,j);
                    }
                    index++;
                }
            }
            for (int i = 0; i < 2; i++) {
                output[index] = new Card(0,13);
                index++;
                output[index] = new ActionCard(0,14);
                index++;
            }
        }
        return output;
    }
    public Card draw(Card[] deckArray) {
        Card output = deckArray[0];
        Card[] parallel = new Card[deckArray.length - 1];
        for (int i = 0; i < parallel.length; i++) {
            parallel[i] = deckArray[i + 1];
        }
        this.deckArray = parallel;
        return output;
    }

    public void take(Card card)  {
        Card[] parallel = new Card[deckArray.length + 1];
        for (int i = 0; i < deckArray.length; i++) {
            parallel[i] = deckArray[i];
        }
        parallel[parallel.length - 1] = card;
        deckArray = parallel;
    }
    public void deal(Player[] players) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < players.length; j++) {
                Card[] refDeck = players[j].getHand().getDeckArray();
                refDeck[i] = draw(this.deckArray);
            }
        }
    }
    public void playAction(Card actionCard) {

    }
}
