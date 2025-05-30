package jcarddecksuite;

/**
 * Represents a playing card with a suit and rank.
 */
public class PlayingCard {
    private final FrenchSuitDeck.Suit suit;
    private final String rank;

    /**
     * Creates a new PlayingCard with the specified suit and rank.
     * @param suit The suit of the card
     * @param rank The rank of the card
     */
    public PlayingCard(FrenchSuitDeck.Suit suit, String rank) {
        if (suit == null) {
            throw new IllegalArgumentException("Suit cannot be null");
        }
        if (rank == null || rank.trim().isEmpty()) {
            throw new IllegalArgumentException("Rank cannot be null or empty");
        }
        this.suit = suit;
        this.rank = rank.trim();
    }

    /**
     * Gets the suit of this card.
     * @return The suit of the card
     */
    public FrenchSuitDeck.Suit getSuit() {
        return suit;
    }

    /**
     * Gets the rank of this card.
     * @return The rank of the card
     */
    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return getRank() + " of " + getSuit();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PlayingCard other = (PlayingCard) obj;
        return suit == other.suit && rank.equals(other.rank);
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }
}
