package softwaredesign;


import java.util.ArrayList;
import java.util.Collections;

    public class CardCollection {

    private ArrayList<Card> content = new ArrayList<>();

    public CardCollection() {}

    public Integer getSize() {
        return this.content.size();
    }

    public ArrayList<Card> getCards() {
        ArrayList<Card> contentCopy = new ArrayList<Card>();

        for (Card card : this.content) {
            switch (card.getCardName()) {
                case NORMAL:
                    contentCopy.add(new Normal());
                    break;
                case EXPLODING_KITTEN:
                    contentCopy.add(new ExplodingKitten());
                    break;
                case DEFUSE:
                    contentCopy.add(new Defuse());
                    break;
                case ATTACK:
                    contentCopy.add(new Attack());
                    break;
                case SKIP:
                    contentCopy.add(new Skip());
                    break;
                case FAVOR:
                    contentCopy.add(new Favor());
                    break;
                case NOPE:
                    contentCopy.add(new Nope());
                    break;
                case SHUFFLE:
                    contentCopy.add(new Shuffle());
                    break;
                case SEE_THE_FUTURE:
                    contentCopy.add(new SeeTheFuture());
                    break;
            }
        }
        return contentCopy;
        }


        public void addCard(Card newCard) {
            this.content.add(newCard);
        }

        public void insertCard(int location, Card newCard) {
        this.content.add(location, newCard);
        }

        public Card removeCard(int num) {
            return this.content.remove(num);
            }

        }




