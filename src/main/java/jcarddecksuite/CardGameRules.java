package jcarddecksuite;

import java.util.*;

public abstract class CardGameRules {
    static int MAX_PLAYERS;
    public static List<CardGamePlayer> listOfCardGamePlayers = new ArrayList<>();
    public static Map<PlayingCard, String> mapOfBattleCards = new HashMap<>();

    public static void divideDeck(List<PlayingCard> deck){
        if(deck.size() == 52) {
            if (listOfCardGamePlayers.size() >= 2) {
                for (int player = 0; player < listOfCardGamePlayers.size(); player++) {
                    int card = player;
                    for (; card < deck.size(); card = card + listOfCardGamePlayers.size()) {
                        listOfCardGamePlayers.get(player).addCardToTopOfDeck(deck.get(card));
                    }
                }
            }
        }
    }
}
