package jcarddecksuite;

import java.util.List;
import java.util.Arrays;

public class FrenchSuitDeck extends Deck {
    private static final String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    public enum suits { DIAMOND, HEART, SPADE, CLOVES }
    private final PlayingCard[] cardDeck = new PlayingCard[52];

    public FrenchSuitDeck(){
        createCardDeck();
    }
    protected void createCardDeck(){
        int i = 0;
        for(suits suit : suits.values()){
            for (String rank : ranks){
                cardDeck[i] = new PlayingCard(suit, rank);
                i++;
            }
        }
    }

    public void shuffleDeck() {
        PlayingCard[] tempDeck = Arrays.copyOf(cardDeck, cardDeck.length);
        for (int i = 0; i < cardDeck.length; i++) {
            int randomIndex = (int) (Math.random() * cardDeck.length);
            cardDeck[i] = tempDeck[randomIndex];
        }
    }

    public void divideDeck(List<? extends CardGamePlayer> listOfWarPlayers){
      int numberOfCardsPerPlayer = 0;
      if(cardDeck.length == 52) {
        numberOfCardsPerPlayer = (cardDeck.length / listOfWarPlayers.size());

        // Shuffle the deck before dividing
        shuffleDeck();

        for (int player = 0; player < listOfWarPlayers.size(); player++) {
          int card = player;
          for (; card < cardDeck.length; card = card + listOfWarPlayers.size()) {
            listOfWarPlayers.get(player).addCardToTopOfDeck(cardDeck[card]);
          }
        }
      }
    }

    public static String[] getRanks(){
      return ranks;
    }

    public PlayingCard[] getPlayingCardDeck(){
        return cardDeck;
    }
}
