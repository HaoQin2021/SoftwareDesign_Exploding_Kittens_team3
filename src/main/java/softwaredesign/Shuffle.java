package softwaredesign;

import java.util.ArrayList;
import java.util.Collections;

public class Shuffle extends Card {

    private final CardName cardName;

    public Shuffle() {
        this.cardName = CardName.SHUFFLE;
    }

    public CardName getCardName() {
        return this.cardName;
    }

    public void cardAction() {
        ArrayList<Card> drawDeck = Game.gameState.getDrawDeck();
        Collections.shuffle(drawDeck);
        Display.cardPlayedInfo(CardName.SHUFFLE);
    }
}
