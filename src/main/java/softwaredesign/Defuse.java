package softwaredesign;

public class Defuse extends Card {

    private final CardName cardName;

    public Defuse() {
        this.cardName = CardName.DEFUSE;
    }

    public CardName getCardName() {
        return this.cardName;
    }

    public void cardAction() {
        Display.cardPlayedInfo(CardName.DEFUSE);
    }

}
