package softwaredesign;

import java.io.IOException;
import java.util.ArrayList;

public class Command {
    public static void play(Integer playerNr) {
        try {
            humanInput(playerNr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void humanInput(Integer playerNr) throws IOException {
        ArrayList<Card> handCards;
        String playerName = Game.gameState.getPlayer(playerNr).name;
        String userCommand;
        int cardNr;
        Card playCard;
        boolean continueLoop = true;

        Display.turnMess(playerName);
        Game.delay(1);
        while(continueLoop){
            handCards = Game.gameState.getCards(playerNr);
            if(handCards.size() == 0){
                Display.emptyHand();
                Command.draw(playerNr);
                Display.endTurn(playerName, false);
                GameLogic.moveTurn();
            }
            Display.userCards(handCards);
            Game.delay(1);
            Display.commands();
            userCommand = Time.timeInput();
            if(userCommand.isEmpty()){
                draw(playerNr);
                Display.cardDrawnMess(playerName);
                Display.endTurn(playerName, true);
                GameLogic.moveTurn();
            }
            else{
                if (invalidCommand(userCommand, handCards.size())){
                    Display.invalidInput();
                    continue;
                }
            }
            switch (userCommand.substring(0,4)){
                case "play":
                    cardNr =  Integer.parseInt(userCommand.substring(5)) - 1;
                    playCard = handCards.get(cardNr);
                    if(playCard.getCardName() == CardName.DEFUSE && playCard.getCardName() != CardName.NOPE){
                        Display.invalidCard();
                        continue;
                    }
                    // remove card form hand
                    Game.gameState.removeCard(playerNr, cardNr);
                    Display.playedCard(playerName, playCard);
                    if(playCard.getCardName() == CardName.SKIP){
                        Display.cardNotDrawn(playerName);
                        continueLoop = false;
                        continue;
                    }
                    GameLogic.checkNopes(playCard);
                    playCard.cardAction();
                    Game.delay(2);
                    break;
                case "draw":
                    draw(playerNr);
                    Display.cardDrawnMess(playerName);
                    Game.delay(1);
                    handCards = Game.gameState.getCards(playerNr);
                    continueLoop = false;
                    break;
                case "quit":
                    Display.quitGameMess();
                    System.exit(0);
                    break;
                default:
                    Display.invalidInput();
                    break;
            }
        }
        Display.endTurn(playerName, false);
        GameLogic.moveTurn();
    }

     public static void draw(Integer playerNr) {
        int drawDeckSize = Game.gameState.getDrawDeckSize();
        Card drawCard = Game.gameState.removeFromDrawDeck(drawDeckSize - 1);
        Game.gameState.addCard(playerNr, drawCard);
    }

    private static boolean invalidCommand(String userCommand, int handCardSize){
        boolean invalidInput = false;
        if (userCommand.length() < 4) invalidInput = true;
        else if (userCommand.startsWith("play")){
            char[] chars = userCommand.substring(5).toCharArray();
            StringBuilder sb = new StringBuilder();
            for(char c : chars){
                if(Character.isDigit(c)){
                    sb.append(c);
                }
            }
            if (Integer.parseInt(String.valueOf(sb)) > handCardSize) invalidInput = true;
        }
        return invalidInput;
    }
}

