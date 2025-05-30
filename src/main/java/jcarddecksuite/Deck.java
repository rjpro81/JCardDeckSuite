package jcarddecksuite;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Deck {
    protected PlayingCard[] cardDeck;
    protected abstract void createCardDeck();
    public abstract PlayingCard[] getPlayingCardDeck();

    public void shuffleDeck(){
        Collections.shuffle(Arrays.asList(getPlayingCardDeck()));
    }

    public abstract void divideDeck(List<? extends CardGamePlayer> listOfPlayers);
}
