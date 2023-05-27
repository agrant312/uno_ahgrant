public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Player[] players = new Player[4];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player("Player " + (i + 1));
        }
        deck.deckArray = deck.shuffle(deck.deckArray);
        deck.deal(players);
        for (int i = 0; i < players.length; i++) {
            System.out.println("\n" + players[i].toString());
            for (int j = 0; j < players[i].getHand().getDeckArray().length; j++) {
                System.out.println(players[i].getHand().getDeckArray()[j].toString());
            }
        }
        System.out.println("\nDeck Remaining:");
        for (int i = 0; i < deck.deckArray.length; i++) {
            System.out.println(deck.deckArray[i].toString());
        }
    }
}
