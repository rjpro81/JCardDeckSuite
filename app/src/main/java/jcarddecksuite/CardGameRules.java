package jcarddecksuite;

import java.util.*;

public abstract class CardGameRules {
    static int MAX_PLAYERS;
    public static List<WarPlayer> listOfWarPlayers = new ArrayList<>();
    public static Map<PlayingCard, String> mapOfBattleCards = new HashMap<>();

    public static void divideDeck(List<PlayingCard> deck){
        if(deck.size() == 52) {
            if (listOfWarPlayers.size() >= 2) {
                for (int player = 0; player < listOfWarPlayers.size(); player++) {
                    int card = player;
                    for (; card < deck.size(); card = card + listOfWarPlayers.size()) {
                        listOfWarPlayers.get(player).addCardToTopOfDeck(deck.get(card));
                    }
                }
            }
        }
    }
}
