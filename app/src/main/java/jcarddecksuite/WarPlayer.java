package jcarddecksuite;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class WarPlayer extends CardGamePlayer implements Serializable, Comparable<WarPlayer> {

    public WarPlayer(String username) {
        setUserId();
        setUsername(username);
        Configuration.getInstance().addPlayer(this);
    }

    @Override
    public PlayingCard playHand(){
        return super.playHand();
    }

    @Override
    public void removePlayerFromGame() {
        // Remove player from game configuration
        Configuration.getInstance().removePlayer(this);
        
        // Clear player's state
        setPlayingCard(null);
        setGamesWon(0);
        setGamesLost(0);
        setBattlesWon(0);
        setBattlesLost(0);
        
        // Clear hand using the public method
        List<PlayingCard> hand = getPlayerHand();
        hand.clear();
    }

   @Override
   public int compareTo(WarPlayer otherPlayer) {
     PlayingCard thisCard = getPlayingCard();
     PlayingCard otherCard = otherPlayer.getPlayingCard();
     
     if (thisCard == null && otherCard == null) {
         return 0;
     }
     if (thisCard == null) {
         return 1; // null card loses
     }
     if (otherCard == null) {
         return -1; // other null card loses
     }
     
     // Check if cards have same rank
     if (thisCard.getRank().equals(otherCard.getRank())) {
         // Add to duplicate list if not already there
         synchronized (CardGamePlayer.duplicateCardPlayed) {
             if (!CardGamePlayer.duplicateCardPlayed.contains(this)) {
                 CardGamePlayer.duplicateCardPlayed.add(this);
             }
         }
         return 0;
     }
     
     // Compare ranks
     int thisIndex = Arrays.asList(FrenchSuitDeck.getRanks()).indexOf(thisCard.getRank());
     int otherIndex = Arrays.asList(FrenchSuitDeck.getRanks()).indexOf(otherCard.getRank());
     
     return Integer.compare(otherIndex, thisIndex); // Compare in reverse order for sorting
   }

    @Override
    public String toString(){
        PlayingCard currentCard = getPlayingCard();
        String cardStr = currentCard != null ? currentCard.toString() : "No card played";
        return String.format("userid: %s%nusername: %s%ncard played: %s%ngames won: %s%ngames lost: %s%nnumber of cards left: %s%nbattles won: %s%nbattles lost: %s%nhand%s%n%n", 
            getUserId(), getUsername(), cardStr, getGamesWon(), getGamesLost(), 
            getNumOfCardsInHand(), getBattlesWon(), getBattlesLost(), getPlayerHand());
    }
}
