public class Card {
    public int color;
    public int value;
    public Card() {
        this.color = 0;
        this.value = 0;
    }
    public Card(int color, int value) {
        this.color = color;
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuffer output = new StringBuffer();
        if (color == 1) {
            output.append("Red ");
        } else if (color == 2) {
            output.append("Blue ");
        } else if (color == 3) {
            output.append("Yellow ");
        } else if (color == 4) {
            output.append("Green ");
        }
        if (value < 10) {
            output.append(value + "");
        } else if (value == 10) {
            output.append("Skip");
        } else if (value == 11) {
            output.append("Reverse");
        } else if (value == 12) {
            output.append("Draw Two");
        } else if (value == 13) {
            output.append("Wild");
        } else if (value == 14) {
            output.append("Wild Draw Four");
        }
        return output.toString();
    }
}
