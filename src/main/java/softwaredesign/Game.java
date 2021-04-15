package softwaredesign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public abstract class Game {

    public static GameState gameState;

    public static void playGame() {
        setupGame();
        Game.gameState.getPlayer(0).playTurn(0);
    }

    private static GameState setupGame(){
        Display.welcome();
        setPlayers();
        setupBasicDeck();
        dealCards();
        return gameState;
    }

    public static void delay(int delayTime) {
        try {
            TimeUnit.SECONDS.sleep(delayTime);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    private static void dealCards() {
        for (int i = 0; i < gameState.getNrPlayers(); i++) {
            gameState.addCard(i, new Defuse());
        }

        ArrayList<Card> shuffledDeck = gameState.getDrawDeck();
        Collections.shuffle(shuffledDeck);

        for (int i = 0; i < gameState.getNrPlayers(); i++) {
            for (int j = 0; j < 4; j++) {
                gameState.addCard(i, shuffledDeck.get(i));
                shuffledDeck.remove(i);
            }
        }
        for (int k = 0; k< gameState.getNrPlayers()-1; k++) {
            shuffledDeck.add(new ExplodingKitten());
        }
        for (int k = 0; k<6- gameState.getNrPlayers(); k++) {
            shuffledDeck.add(new Defuse());
        }
        gameState.clearDrawDeck();
        Collections.shuffle(shuffledDeck);

        for (int k = 0; k<shuffledDeck.size(); k++) {
            gameState.addToDrawDeck(shuffledDeck.get(k));
        }


    }

    private static void setPlayers() {
        Scanner scan = new Scanner(System.in);
        Display.nameQn();
        String userName = scan.nextLine();

        // FIXME: Input should be CHECKED!
        Display.botCountQn(userName);
        Integer nrBots = scan.nextInt();
        while (nrBots == 0 || nrBots > 4 ) {
            Display.invalidInput();
            nrBots = scan.nextInt();
        }
        Display.start(nrBots);

        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add(userName);

        for (int i=1; i<=nrBots; i++) {
            String botName = "Bot" + i;
            playerNames.add(botName);
         }
         gameState = new GameState(playerNames);
    }

    private static void setupBasicDeck() {
        for (Integer i = 0; i < 4; i++) {
            gameState.addToDrawDeck(new Attack());
            gameState.addToDrawDeck(new Skip());
            gameState.addToDrawDeck(new Favor());
            gameState.addToDrawDeck(new Shuffle());
        }
        for (Integer i = 0; i < 5; i++) {
            gameState.addToDrawDeck(new SeeTheFuture());
            gameState.addToDrawDeck(new Nope());
        }
        for (Integer i = 0; i < 10; i++) {
            gameState.addToDrawDeck(new Normal());
        }
    }

}