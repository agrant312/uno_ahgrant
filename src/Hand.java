public class Hand extends Deck{
    public Hand() {
        deckArray = new Card[7];
    }
    public static void deal() {

    }
    public static Card draw() {
        return null;
    }

    public Card pick(Card card) {
        int topVal = card.getValue();
        int topCol = card.getColor();
        Card mostValuable = null;
        int indexOf = 0;
        for (int i = 0; i < deckArray.length; i++) {
            if (deckArray[i].getValue() == topVal || deckArray[i].getColor() == topCol) {
                if (mostValuable == null || deckArray[i].getValue() > mostValuable.getValue()) {
                    mostValuable = deckArray[i];
                    indexOf = i;
                }
            }
        }
        if (mostValuable == null) {
            for (int i = 0; i < deckArray.length; i++) {
                if ( deckArray[i].getColor() == 0) {
                    if (mostValuable == null || deckArray[i].getValue() > mostValuable.getValue()) {
                        mostValuable = deckArray[i];
                        indexOf = i;
                    }
                }
            }
        }
        if (mostValuable.getColor() == 0) {
            int numRed = 0;
            int numBlue = 0;
            int numYellow = 0;
            int numGreen = 0;
            for (int i = 0; i < deckArray.length; i++) {
                if (deckArray[i].getColor() == 1) {
                    numRed++;
                } else if (deckArray[i].getColor() == 2) {
                    numBlue++;
                } else if (deckArray[i].getColor() == 3) {
                    numYellow++;
                } else if (deckArray[i].getColor() == 4) {
                    numGreen++;
                }
            }
            int[] colorArray = {numRed, numBlue, numYellow, numGreen};
            int indexOfColor = 0;
            for (int i = 0; i < 4; i++) {
                if (colorArray[i] > colorArray[indexOfColor]) {
                    indexOfColor = i;
                }
            }
            while (mostValuable.getColor() == 0) {
                mostValuable.setColor(indexOfColor + 1);
            }
        }
        if (mostValuable != null) {
            Card[] parallel = new Card[deckArray.length - 1];
            int indexMod = 0;
            for (int i = 0; i < parallel.length; i++) {
                if (i + indexMod != indexOf) {
                    parallel[i] = deckArray[i + indexMod];
                } else {
                    indexMod++;
                    i--;
                }
            }
            deckArray = parallel;
        }
        return mostValuable;
    }
}
