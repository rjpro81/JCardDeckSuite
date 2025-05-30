package jcarddecksuite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class CardGamePlayer {
    private String username;
    private int userid;
    private int gamesWon;
    private int gamesLost;
    static List<CardGamePlayer> duplicateCardPlayed = new ArrayList<>();
    private int battlesWon;
    private int battlesLost;

    private PlayingCard playingCard;

    private final LinkedList<PlayingCard> playerHand = new LinkedList<>();

    public void setUserId(){
        this.userid = new Random().nextInt(10000000 - 100000) + 100000;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public void setGamesWon(int gamesWon){
        this.gamesWon = gamesWon;
    }

    public void setGamesLost(int gamesLost){
        this.gamesLost = gamesLost;
    }

    public void setBattlesWon(int battlesWon){
        this.battlesWon = battlesWon;
    }

    public void setBattlesLost(int battlesLost){
        this.battlesLost = battlesLost;
    }

    public void setPlayingCard(PlayingCard playingCard){
        this.playingCard = playingCard;
    }

    public static void clearDuplicateCards() {
        duplicateCardPlayed.clear();
    }

    public static List<CardGamePlayer> getDuplicateCardPlayed() {
        return duplicateCardPlayed;
    }

    public PlayingCard getPlayingCard() {
        return playingCard;
    }

    public void incrementBattlesWon(){
        battlesWon += 1;
    }

    public void incrementBattlesLost(){
        battlesLost += 1;
    }

    public int getUserId(){
        return userid;
    }

    public String getUsername(){
        return username;
    }

    public int getGamesWon(){
        return gamesWon;
    }

    public int getGamesLost(){
        return gamesLost;
    }

    public int getNumOfCardsInHand(){
        return playerHand.size();
    }

    public int getBattlesWon(){
        return battlesWon;
    }

    public int getBattlesLost(){
        return battlesLost;
    }

    public void addCardToTopOfDeck(PlayingCard card){
        this.playerHand.addFirst(card);
    }

    public void addToBottomOfDeck(PlayingCard card){ 
        this.playerHand.addLast(card);
    }

    public PlayingCard playHand() {
        if (playerHand.isEmpty()) {
            return null;
        }
        PlayingCard card = playerHand.removeFirst();
        setPlayingCard(card);
        return card;
    }

    public boolean hasCards() {
        return !playerHand.isEmpty();
    }

    public List<PlayingCard> getPlayerHand(){
        return playerHand;
    }



    public void removePlayerFromGame() {
        // Remove player from all game collections
        Configuration<CardGamePlayer> config = Configuration.getInstance();
        config.getPlayers().remove(this);
        duplicateCardPlayed.remove(this);
        
        // Clear player's hand and stats
        playerHand.clear();
        playingCard = null;
        gamesWon = 0;
        gamesLost = 0;
        battlesWon = 0;
        battlesLost = 0;
    }
}
