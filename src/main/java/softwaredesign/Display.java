package softwaredesign;

import java.util.ArrayList;

public abstract class Display {
    static void welcome(){
        System.out.println("WELCOME TO EXPLODING KITTENS GAME\n");
    }

    static void botCountQn(String userName){
        System.out.println("Hi "+userName+ "! Against how many bots do you want to compete?");
        System.out.println("You can pick a number between 1 and 4 \n");
    }
        static void start(int opps) {
        System.out.println("You will play against "+ opps+ " opponents. Good luck!");
        }

    static void nameQn(){
        System.out.println("What is your name: \n");
    }

    static void turnMess(String playerName){
        System.out.println("Now it is " + playerName + "'s turn \n");
    }

    static void userCards(ArrayList<Card> cardList){
        System.out.println("Your cards are: \n");
        for(int i = 0; i < cardList.size(); i++){
            System.out.println( (i +  1) + " " + cardList.get(i).getCardName());
        }
        System.out.println();
    }

    static void time(Integer timeLeft){
        System.out.println("Time left = " + timeLeft +"sec \n");
    }

    static void commands(){
        System.out.println("Commands are: \n" +
        "'play n' (n is a number)\n" +
        "'draw' (the turn will end) \n" +
        "'quit' (Exit game)\n" +
        "You have 60 sec to play the turn\n\n");
    }

    static void playedCard(String playerName, Card card){
        System.out.println(playerName + " played " + card.getCardName() + "\n");
    }

    static void cardDrawnMess(String playerName){
        System.out.println(playerName + " draws a card from the deck\n");
    }

    static void cardNotDrawn(String playerName){
        System.out.println(playerName + " did not draw a card\n");
    }

    static void kittenDrawn(String playerName){
        System.out.println("!!!!!! " + playerName + " Draw Exploding Kitten !!!!!!!!\n");
    }

    static void insertKitten(){
        System.out.println("At what position would you like to insert the exploding kitten card?");
        System.out.println("You can pick a number between 0 (bottom of the deck) and "+Game.gameState.getDrawDeckSize()+" (top of the deck)");
    }

    static void insertSuccessful(int location){
        System.out.println("You have inserted the exploding kitten card at place "+location+" of the deck \n");

    }

    static void botInsertSuccesful() {
        System.out.println("The bot has inserted the exploding kitten card in the deck \n");
    }

    static void hasDefuseCard(String playerName){
        System.out.println("++++ " +playerName + " Has a Defuse Card. Playing Defuse Card to neutralize the Exploding Kitten ++++\n");
    }

    static void noDefuseCard(String playerName){
        System.out.println("!-!-!-!-! " + playerName + "  Does NOT Have a Defuse Card and is eliminated from of the game. !-!-!-!-!\n");
    }

    static void endTurn(String playerName, Boolean timeUp){
        if (timeUp) System.out.println("You did not enter any input.\n" +
                "----------Time's up! " + playerName + "'s turn has ended ------------\n");
        else System.out.println("------------ " + playerName + "'s turn has ended ---------------\n");
    }

    static void results(String winner){
        System.out.println("***** Game Over *****\n Winner is: " + winner + "\n");
    }

    static void invalidInput(){ System.out.println("I'm sorry, that input is invalid, please try again\n");}

    static void quitGameMess(){ System.out.println("Thank you for playing Exploding Kittens,the game has quit"); }

    // prints related to card action

    static void seeTheFutureCards(ArrayList<Card> futureCards, PlayerType playerType){
        if(playerType.equals(PlayerType.HUMAN)){
            System.out.println("The top 3 cards in the deck are: \n");
            for(int i = 0; i < 3 && i<=futureCards.size()-1; i++){
                System.out.println( (i + 1) + " " + futureCards.get(i).getCardName() + "\n");
            }
        }
        else{
            System.out.println("The current bot player has player a SeeTheFuture card! You should be careful!");
        }
    }

    static void favorSelectPlayer(int playerNum) {
        if (playerNum>2) {
            System.out.println("There are " + (playerNum - 1) + " other players, " +
                    "from which player do you wish to steal a card?");
            System.out.println("Please input a number between 1 and " + (playerNum - 1));
        }
        else if (playerNum==2){
            System.out.println("There is only one other player from who you can steal a card");
        }
    }


    static void inputCheckMess(String checkType, int size) {
        if (checkType.equals("playerCheck")) {
            System.out.println("The input is out of range!");
            System.out.println("Please try again and input a number between 1 and " +
                    (size - 1) + " to indicate the target player");

        } else if (checkType.equals("cardCheck")) {
            System.out.println("The input is out of range!");
            System.out.println("Please try again and input a  number between 1 and " +
                    size + " to indicate the card to steal");

        }
    }

    static void favorSelectCard(String targetPlayerName, int targetHandSize){
        System.out.println(targetPlayerName+" has " + targetHandSize + " cards.");
        System.out.println("Please input a number between 1 and "+ targetHandSize +
                " to indicate the card you want to steal");
    }

    static void favorStolenCard(CardName targetCardName, PlayerType type){
        if(type == PlayerType.HUMAN){
            System.out.println("The card which you have stolen is a "+ targetCardName +
                    " card. It has been added to your hand");
        }
        else{
            System.out.println("This opponent has stolen a "+targetCardName+" from your hand!");
        }

    }


    static void cardPlayedInfo(CardName cardName) {
        switch (cardName){
            case DEFUSE:
                System.out.println("A Defuse card has been played!");
                break;
            case SHUFFLE:
                System.out.println("The draw deck has been shuffled!");
                break;
            case ATTACK:
                System.out.println("An Attack card was played, the turn was moved to next player, " +
                        "the number of turns for the next player is increased by 1.");
                break;
            case SKIP:
                System.out.println("A Skip card has been played!");
                break;
            case NORMAL:
                System.out.println("A Normal card has been played, there is no action for this card.");
                break;
            case NOPE:
                System.out.println("A Nope card has been played!");
                break;
            case SEE_THE_FUTURE:
                System.out.println("A See The Future card has been played!");
                break;
            case FAVOR:
                System.out.println("A Favor card has been played!");
                break;
        }
    }

    static void nopeQn(Card playCard){
        System.out.println("The bot player played a "+ playCard.getCardName() + " card!");
        System.out.println("Will you play your Nope Card to prevent this? Please input y for yes or n for no.");
    }

    static void invalidCard(){
        System.out.println("Cannot play this card right now. Input another card");
    }

    static void emptyHand(){
        System.out.println("No cards in hand. Can draw only.");
    }

}

