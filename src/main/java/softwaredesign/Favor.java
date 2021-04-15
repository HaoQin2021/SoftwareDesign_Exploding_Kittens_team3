package softwaredesign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Favor extends Card{

    private final CardName cardName;

    public Favor() {
        this.cardName = CardName.FAVOR;
    }

    public CardName getCardName() {
        return this.cardName;
    }

    public void cardAction() {

        int indexOfCurrentPlayer = Game.gameState.getCurrentPlayerNr();
        Player currentPlayer = Game.gameState.getPlayer(indexOfCurrentPlayer);
        PlayerType currentPlayerType = currentPlayer.getPlayerType();
        int playerNum = Game.gameState.getNrPlayers();
        int indexOfTargetPlayer = 6;
        Display.cardPlayedInfo(CardName.FAVOR);

        if (currentPlayerType.equals(PlayerType.HUMAN)) {
            if(playerNum>2) {
                Display.favorSelectPlayer(playerNum);
                Scanner playerInput = new Scanner(System.in);
                indexOfTargetPlayer = playerInput.nextInt();
                while (indexOfTargetPlayer>(playerNum-1) || indexOfTargetPlayer<1) {
                    Display.inputCheckMess("playerCheck", playerNum);
                    playerInput = new Scanner(System.in);
                    indexOfTargetPlayer = playerInput.nextInt();
                }
            }
            else if(playerNum==2){
                Display.favorSelectPlayer(playerNum);
                indexOfTargetPlayer=1;
            }

            Player targetPlayer = Game.gameState.getPlayer(indexOfTargetPlayer);
            ArrayList<Card> targetHand = Game.gameState.getCards(indexOfTargetPlayer);
            int targetHandSize = targetHand.size();
            String targetPlayName = targetPlayer.getName();
            Display.favorSelectCard(targetPlayName, targetHandSize);
            Scanner cardInput = new Scanner(System.in);
            int indexOfTargetCard = cardInput.nextInt()-1;
            while(!(indexOfTargetCard>=0&&indexOfTargetCard<=targetHandSize-1)){
                Display.inputCheckMess("cardCheck",targetHandSize);
                cardInput = new Scanner(System.in);
                indexOfTargetCard = cardInput.nextInt()-1;
            }

            Card targetCard = targetHand.get(indexOfTargetCard);
            CardName targetCardName = targetCard.getCardName();
            Display.favorStolenCard(targetCardName, currentPlayerType);
            Game.gameState.addCard(indexOfCurrentPlayer, targetCard);
            Game.gameState.removeCard(indexOfTargetPlayer, indexOfTargetCard);


        }
        else if (currentPlayerType.equals(PlayerType.BOT)) {
            indexOfTargetPlayer = 0;

            Player targetPlayer = Game.gameState.getPlayer(indexOfTargetPlayer);

            ArrayList<Card> targetHand = Game.gameState.getCards(indexOfTargetPlayer);
            int handCardSize = targetHand.size();
            Random rand = new Random();
            int indexOfTargetCard = rand.nextInt(handCardSize);

            Card targetCard = targetHand.get(indexOfTargetCard);
            Game.gameState.addCard(indexOfCurrentPlayer, targetCard);
            Game.gameState.removeCard(indexOfTargetPlayer, indexOfTargetCard);
            CardName targetCardName = targetCard.getCardName();
            Display.favorStolenCard(targetCardName,currentPlayerType);
        }
    }
}
