package softwaredesign;

public class Skip extends Card{

    private final CardName cardName;

    public Skip(){
        this.cardName = CardName.SKIP;
    }

    public CardName getCardName() {
        return this.cardName;
    }

    public void cardAction() {
        Display.cardPlayedInfo(CardName.SKIP);

    }

}

