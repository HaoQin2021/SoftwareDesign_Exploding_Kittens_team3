package softwaredesign;

public class Normal extends Card {

    private final CardName cardName;

    public Normal() {
        this.cardName = CardName.NORMAL;
    }

    public CardName getCardName() {
        return this.cardName;
    }

    public void cardAction() {     // no action for normal card, may use for 2, 3, 5 card bundle
        Display.cardPlayedInfo(CardName.NORMAL);
    }

}
