package softwaredesign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Bot extends Player {
    Bot(String name, CardCollection cardList, PlayerType type) {
        super(name, cardList, type);
    }

    private final Random rand = new Random();

    @Override
    public void playTurn(Integer playerNr) {
        String playerName = Game.gameState.getPlayer(playerNr).name;
        ArrayList<Card> handCards = Game.gameState.getCards(playerNr);
        if(handCards.size() == 0){
            Display.emptyHand();
            Command.draw(playerNr);
            Display.endTurn(playerName, false);
            GameLogic.moveTurn();
        }
        int cardIndex = 0;
        Card playCard = null;
        boolean continueLoop = true;

        Display.turnMess(playerName);
        Game.delay(2);

        while(continueLoop){
            cardIndex = rand.nextInt(handCards.size());
            playCard = handCards.get(cardIndex);
            if(playCard.getCardName() != CardName.DEFUSE && playCard.getCardName() != CardName.NOPE) continueLoop = false;
        }
        Game.gameState.removeCard(playerNr, cardIndex);
        GameLogic.checkNopes(playCard);
        Display.playedCard(playerName, playCard);
        playCard.cardAction();
        if(playCard.getCardName() != CardName.SKIP){
            Display.cardDrawnMess(playerName);
            Command.draw(playerNr);
        }
        else Display.cardNotDrawn(playerName);
        Display.endTurn(playerName, false);
        Game.delay(2);

        GameLogic.moveTurn();
    }

    Boolean playNope(Integer playerNr){
        ArrayList<Card> handCards = Game.gameState.getCards(playerNr);
        Card nopeCard = new Nope();
        boolean nopeInHand = Arrays.asList(handCards).contains(new Nope());
        boolean playNope = rand.nextBoolean();
        if(nopeInHand && playNope){
            nopeCard.cardAction();
            return true;
        }
        return false;
    }

}
