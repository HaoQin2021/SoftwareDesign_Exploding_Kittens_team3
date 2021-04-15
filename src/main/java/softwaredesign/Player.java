package softwaredesign;

import java.util.ArrayList;

abstract class Player {
    final PlayerType playerType;
    final String name;
    CardCollection handCards;
    Boolean exploded;

    public abstract  void playTurn(Integer playerNr);

    Player(String name, CardCollection cardList, PlayerType type){
        this.name = name;
        this.handCards = cardList;
        this.playerType = type;
        this.exploded = false;
    }

    public String getName() { return this.name; }

    public ArrayList<Card> getCards(){
        return handCards.getCards();
    }

    public PlayerType getPlayerType() { return playerType; }

    public void explodePlayer(){ this.exploded = true; }

    public Boolean isExploded(){
        return exploded;
    }

    public void addCard(Card newCard){
        handCards.addCard(newCard);
    }

    public void removeCard(Integer deleteCard){
        handCards.removeCard(deleteCard);
    }

}
