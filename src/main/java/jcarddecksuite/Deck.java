package jcarddecksuite;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a deck of playing cards.
 */
public abstract class Deck {
    private final PlayingCard[] cardDeck;
    private boolean isShuffled = false;

    /**
     * Creates a new deck with the specified number of cards.
     * @param numberOfCards Number of cards in the deck
     */
    protected Deck(int numberOfCards) {
        if (numberOfCards <= 0) {
            throw new IllegalArgumentException("Number of cards must be positive");
        }
        this.cardDeck = new PlayingCard[numberOfCards];
    }

    /**
     * Creates the initial card deck.
     * Must be implemented by subclasses.
     */
    protected abstract void createCardDeck();

    /**
     * Gets the playing cards in this deck.
     * @return Array of playing cards
     */
    public PlayingCard[] getPlayingCardDeck() {
        return cardDeck.clone(); // Return a defensive copy
    }

    /**
     * Shuffles the deck using Fisher-Yates algorithm.
     */
    public void shuffleDeck() {
        if (cardDeck == null || cardDeck.length == 0) {
            throw new IllegalStateException("Deck must be created before shuffling");
        }
        
        PlayingCard[] tempDeck = cardDeck.clone();
        for (int i = 0; i < cardDeck.length; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(i, cardDeck.length);
            cardDeck[i] = tempDeck[randomIndex];
        }
        isShuffled = true;
    }

    /**
     * Checks if the deck has been shuffled.
     * @return true if deck has been shuffled, false otherwise
     */
    public boolean isShuffled() {
        return isShuffled;
    }

    /**
     * Divides the deck among players.
     * @param players List of players to divide cards among
     * @throws IllegalArgumentException if number of players is invalid
     */
    public void divideDeck(List<? extends CardGamePlayer> players) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("Player list cannot be null or empty");
        }
        
        if (cardDeck == null || cardDeck.length == 0) {
            throw new IllegalStateException("Deck must be created before dividing");
        }

        int totalCards = cardDeck.length;
        int playersCount = players.size();
        int cardsPerPlayer = totalCards / playersCount;
        int remainingCards = totalCards % playersCount;

        // Shuffle before dividing
        shuffleDeck();

        // Distribute cards evenly
        int cardIndex = 0;
        for (CardGamePlayer player : players) {
            for (int i = 0; i < cardsPerPlayer; i++) {
                if (cardIndex < totalCards) {
                    player.addCardToTopOfDeck(cardDeck[cardIndex++]);
                }
            }
        }

        // Distribute remaining cards (if any)
        for (int i = 0; i < remainingCards; i++) {
            if (cardIndex < totalCards) {
                players.get(i).addCardToTopOfDeck(cardDeck[cardIndex++]);
            }
        }
    }

    /**
     * Gets the number of cards in the deck.
     * @return Number of cards
     */
    public int getNumberOfCards() {
        return cardDeck.length;
    }
}
