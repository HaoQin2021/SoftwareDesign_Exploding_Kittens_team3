package softwaredesign;

public class Nope extends Card {

    private final CardName cardName;

    public Nope() {
        this.cardName = CardName.NOPE;
    }

    public CardName getCardName() {
        return this.cardName;
    }

    public void cardAction() {
        Display.cardPlayedInfo(CardName.NOPE);
    }
}
