package softwaredesign;

import java.util.concurrent.TimeUnit;

public class Human extends Player {
    Human(String name, CardCollection cardList, PlayerType type) {
        super(name, cardList, type);
    }

    @Override
    public void playTurn(Integer playerNr) {
        Command.play(playerNr);
    }
}
