package softwaredesign;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public abstract class GameLogic {
    private static TurnType turnType = TurnType.NORMAL;
    private static int numTurns = 1;

    // The following 2 methods are called by the Attack Card when that is played
    public static void increaseNrTurns() {
        numTurns += 1;
    }
    public static void changeTurnType() {
        turnType = TurnType.ATTACK_WAS_PLAYED;
    }

    public static void moveTurn() {
        if (kittenDrawn()) checkDefuse();

        if (turnType == TurnType.NORMAL || turnType == TurnType.ATTACK_WAS_PLAYED) {
            if (Game.gameState.getCurrentPlayerNr() + 1 < Game.gameState.getNrPlayers()) {
                Game.gameState.setCurrentPlayer(Game.gameState.getCurrentPlayerNr() + 1);
            } else {
                Game.gameState.setCurrentPlayer(0);
            }
            if (turnType == TurnType.ATTACK_WAS_PLAYED) {
                turnType = TurnType.ATTACKED_TURN;
            }
            Game.delay(1);
            Game.gameState.getPlayer(Game.gameState.getCurrentPlayerNr()).playTurn(Game.gameState.getCurrentPlayerNr());
        } else if (turnType == TurnType.ATTACKED_TURN) {
            while (numTurns > 1) {
                numTurns -= 1;
                if (numTurns == 1) {
                    turnType = TurnType.NORMAL;
                }
                Game.gameState.getPlayer(Game.gameState.getCurrentPlayerNr()).playTurn(Game.gameState.getCurrentPlayerNr());
                if (turnType == TurnType.ATTACK_WAS_PLAYED) {
                    break;
                }
            }
            turnType = TurnType.NORMAL;
        }
    }

    public static boolean kittenDrawn() {
       return Game.gameState.getPlayer(Game.gameState.getCurrentPlayerNr()).handCards.getCards().get(Game.gameState.getPlayer(Game.gameState.getCurrentPlayerNr()).handCards.getSize()-1).getCardName() == CardName.EXPLODING_KITTEN;
    }

    public static void checkDefuse(){
        String playerName = Game.gameState.getPlayer(Game.gameState.getCurrentPlayerNr()).getName();
        Display.kittenDrawn(playerName);
       boolean defusePresent = false;
       ArrayList<Card> res = Game.gameState.getPlayer(Game.gameState.getCurrentPlayerNr()).handCards.getCards();
       for (int i=0;i<res.size(); i++) {
            if (res.get(i).getCardName() == CardName.DEFUSE) {
                Display.hasDefuseCard(playerName);
                defusePresent = true;
                Game.gameState.removeCard(Game.gameState.getCurrentPlayerNr(), res.size()-1);

                Game.gameState.removeCard(Game.gameState.getCurrentPlayerNr(), i);
                Game.gameState.addToDiscardPile(new Defuse());
               // defusePresent = false;
                if (Game.gameState.getPlayer(Game.gameState.getCurrentPlayerNr()).playerType == PlayerType.HUMAN) {
                    Display.insertKitten();
                    Scanner scan = new Scanner(System.in);
                    Integer location = scan.nextInt();
                    while (location > Game.gameState.getDrawDeckSize()) {
                        Display.invalidInput();
                        location = scan.nextInt();
                    }
                    Game.gameState.insertInDrawDeck(location);
                    Display.insertSuccessful(location);
                    Game.delay(1);

                } else{
                    int randomNum = ThreadLocalRandom.current().nextInt(0, Game.gameState.getDrawDeckSize());
                    Game.gameState.insertInDrawDeck(randomNum);
                    Display.botInsertSuccesful();
                    Game.delay(1);

                }
                break;
            }
       }
       if (!defusePresent) {
           Game.delay(1);
           Display.noDefuseCard(playerName);
           eliminatePlayer(Game.gameState.getCurrentPlayerNr());
        }
    }

    private static void eliminatePlayer(int playerNum) {
        Game.gameState.removePlayer(playerNum);
        if (Game.gameState.getCurrentPlayerNr() > 0) {
            Game.gameState.setCurrentPlayer(Game.gameState.getCurrentPlayerNr()-1);
        }
        else {
            Game.gameState.setCurrentPlayer(Game.gameState.getNrPlayers()-1);
        }
        checkWinner();
    }
    private static void checkWinner(){
        if (Game.gameState.getNrPlayers() == 1) {
            Game.delay(1);
            Display.results(Game.gameState.getPlayer(0).name);            System.exit(0);
        }
    }


    public static void checkNopes(Card playCard){
        int indexOfCurrentPlayer = Game.gameState.getCurrentPlayerNr();
        Player currentPlayer = Game.gameState.getPlayer(indexOfCurrentPlayer);
        PlayerType currentPlayerType = currentPlayer.getPlayerType();
        int playerNum = Game.gameState.getNrPlayers();

        if (currentPlayerType.equals(PlayerType.HUMAN)){
            for(int i=1; i< playerNum; i++) {
                ArrayList<Card> botHandCard = Game.gameState.getCards(indexOfCurrentPlayer+i);
                int indexOfNope = indexOfNope(botHandCard);
                if (indexOfNope>=0){
                    Game.gameState.removeCard(indexOfCurrentPlayer+i, indexOfNope);
                    Display.cardPlayedInfo(CardName.NOPE);
                    Game.gameState.getPlayer(indexOfCurrentPlayer).playTurn(indexOfCurrentPlayer);
                    break;
                }
            }
        }
        else if (currentPlayerType.equals(PlayerType.BOT)){
            ArrayList<Card> humanHandCard = Game.gameState.getCards(0);
            int indexOfNope = indexOfNope(humanHandCard);

            if(indexOfNope>=0){
                Scanner YorN = new Scanner(System.in);
                Display.nopeQn(playCard);
                String userInput = YorN.nextLine();

                switch (userInput) {
                    case "y":
                        Display.cardPlayedInfo(CardName.NOPE);
                        Game.gameState.removeCard(0,indexOfNope);
                        Game.gameState.getPlayer(indexOfCurrentPlayer).playTurn(indexOfCurrentPlayer);
                        break;
                    case "n":
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static int indexOfNope (ArrayList<Card> hand) {
        for(int j=0; j<hand.size(); j++) {
            Card card = hand.get(j);
            CardName cardName = card.getCardName();
            if (cardName.equals(CardName.NOPE)) {
                return j;
            }
        }
        return -1;
    }





}
