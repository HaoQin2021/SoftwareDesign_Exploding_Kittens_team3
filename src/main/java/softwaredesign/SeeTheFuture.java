package softwaredesign;

import java.util.ArrayList;

public class SeeTheFuture extends Card {

    private final CardName cardName;

    public SeeTheFuture() {
        this.cardName = CardName.SEE_THE_FUTURE;
    }

    public CardName getCardName() {
        return this.cardName;
    }

    public void cardAction() {

        int indexOfCurrentPlayer = Game.gameState.getCurrentPlayerNr();
        Player currentPlayer = Game.gameState.getPlayer(indexOfCurrentPlayer);
        PlayerType currentPlayerType = currentPlayer.getPlayerType();
        ArrayList<Card> drawPile = Game.gameState.getDrawDeck();
        Display.cardPlayedInfo(CardName.SEE_THE_FUTURE);
        Display.seeTheFutureCards(drawPile, currentPlayerType);
    }

}
