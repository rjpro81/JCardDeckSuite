package jcarddecksuite;

import java.util.List;

/**
 * Represents a standard French-suited deck of 52 playing cards.
 */
public class FrenchSuitDeck extends Deck {
    /**
     * Standard ranks for a French-suited deck.
     */
    public static final String[] RANKS = {
        "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"
    };

    /**
     * Standard suits for a French-suited deck.
     */
    public enum Suit {
        DIAMOND, HEART, SPADE, CLUB
    }

    /**
     * Creates a new French-suited deck.
     */
    public FrenchSuitDeck() {
        super(52); // Call superclass constructor with number of cards
        createCardDeck();
    }

    @Override
    protected void createCardDeck() {
        int index = 0;
        for (Suit suit : Suit.values()) {
            for (String rank : RANKS) {
                super.getPlayingCardDeck()[index++] = new PlayingCard(suit, rank);
            }
        }
    }

    @Override
    public PlayingCard[] getPlayingCardDeck() {
        return super.getPlayingCardDeck();
    }

    @Override
    public void divideDeck(List<? extends CardGamePlayer> players) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("Player list cannot be null or empty");
        }
        
        if (super.getPlayingCardDeck() == null || super.getPlayingCardDeck().length != 52) {
            throw new IllegalStateException("Deck must contain exactly 52 cards");
        }

        super.divideDeck(players);
    }

    /**
     * Gets the standard ranks for a French-suited deck.
     * @return Array of rank strings
     */
    public static String[] getRanks() {
        return RANKS.clone(); // Return a defensive copy
    }

    /**
     * Gets the standard suits for a French-suited deck.
     * @return Array of suit values
     */
    public static Suit[] getSuits() {
        return Suit.values().clone(); // Return a defensive copy
    }
}
