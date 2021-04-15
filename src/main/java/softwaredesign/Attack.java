package softwaredesign;

import java.sql.SQLOutput;

public class Attack extends Card {

    private final CardName cardName;

    public Attack() {
        this.cardName = CardName.ATTACK;
    }

    public CardName getCardName() {
        return this.cardName;
    }

    public void cardAction() {
        Integer playerNr = Game.gameState.getCurrentPlayerNr();
        String playerName = Game.gameState.getPlayer(playerNr).name;
        GameLogic.changeTurnType();
        GameLogic.increaseNrTurns();
        Display.cardPlayedInfo(CardName.ATTACK);
        Display.endTurn(playerName, false);
        GameLogic.moveTurn();

    }

}
