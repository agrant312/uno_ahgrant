public class ActionCard extends Card{
    public boolean actionComplete = false;
    public ActionCard() {
        this.color = 0;
        this.value = 0;
    }
    public ActionCard(int color, int value) {
        this.color = color;
        this.value = value;
    }
    public boolean getActionComplete() {
        return actionComplete;
    }
    public void setActionComplete(boolean actionComplete) {
        this.actionComplete = actionComplete;
    }
}
