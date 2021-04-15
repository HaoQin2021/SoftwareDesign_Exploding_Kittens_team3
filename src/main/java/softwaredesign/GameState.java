package softwaredesign;

import java.util.ArrayList;

public class GameState {

    private Integer nrPlayers;
    private ArrayList<Player> players = new ArrayList<>();
    private Integer currentPlayer;

    private CardCollection drawDeck;
    private CardCollection discardPile;
    private Boolean gameOver;

    public GameState(ArrayList<String> playerNames) {
        makePlayers(playerNames);
        this.nrPlayers = players.size();
        this.currentPlayer = 0;
        this.gameOver = false;
        this.drawDeck = new CardCollection();
        this.discardPile = new CardCollection();

    }

    private void makePlayers(ArrayList<String> playerNames) {
        Player user = new Human(playerNames.get(0),
                new CardCollection(), PlayerType.HUMAN);
        this.players.add(user);

        for (int i = 1; i < playerNames.size(); i++) {
            Player bot = new Bot(playerNames.get(i),
                    new CardCollection(), PlayerType.BOT);
            this.players.add(bot);
        }
    }

    // Functions dealing with Players
    public int getNrPlayers() {
        int resNrPlayers = this.players.size();
        return resNrPlayers;
    }
    public void removePlayer(int playerNr) {
        this.players.remove(playerNr);
    }
    public void setCurrentPlayer(int playerNr) {
        this.currentPlayer = playerNr;
    }

    public int getCurrentPlayerNr() {
        int cur = this.currentPlayer;
                return cur;
    }
    public Player getPlayer(int playerNum) {
        Player res;
        if (this.players.get(playerNum).playerType == PlayerType.HUMAN) {
             res = new Human(players.get(playerNum).name, players.get(playerNum).handCards,
                    players.get(playerNum).playerType);
        }
        else {
             res = new Bot(players.get(playerNum).name, players.get(playerNum).handCards,
                    players.get(playerNum).playerType);
        }
        return res;
    }


    // Functions dealing with CardCollections
    // Functions dealing with handCards
    public void addCard(int playerNr, Card card) {
        this.players.get(playerNr).handCards.addCard(card);
    }
    public void removeCard(int playerNr, int card) {
        this.players.get(playerNr).handCards.removeCard(card);
    }
    public ArrayList<Card> getCards(int playerNum) {
        ArrayList<Card> res = players.get(playerNum).handCards.getCards();
        return res;
    }


    // Functions dealing with DrawDeck
    public Card removeFromDrawDeck(int num) {
        return drawDeck.removeCard(num);
    }

    public void addToDrawDeck(Card card) {
        drawDeck.addCard(card);
    }

    public void insertInDrawDeck(int location) {
        drawDeck.insertCard(location, new ExplodingKitten());
    }

    public void clearDrawDeck() {
        while (getDrawDeckSize() > 0)   {
            this.removeFromDrawDeck(0);
        }
    }

    public ArrayList<Card> getDrawDeck() {
        ArrayList<Card> res = drawDeck.getCards();
        return res;
    }

    public int getDrawDeckSize() {
        return drawDeck.getSize();
    }


    // Functions dealing with discardPile
    public ArrayList<Card> getDiscardPile() {
        ArrayList<Card> res = discardPile.getCards();
        return res;
    }

    public void addToDiscardPile(Card card) {
        discardPile.addCard(card);
    }

}