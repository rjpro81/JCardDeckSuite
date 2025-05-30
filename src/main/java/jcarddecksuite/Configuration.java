package jcarddecksuite;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;



/**
 * Manages game configuration and player state.
 */
public class Configuration {
    private static final int MAX_PLAYERS = 4;
    private final List<CardGamePlayer> listOfCardGamePlayers = new CopyOnWriteArrayList<>();
    private int gameId;
    private static final Object lock = new Object();
    private static volatile Configuration instance;

    /**
     * Gets the maximum number of players allowed in a game.
     * @return Maximum number of players
     */
    public static int getMaxPlayers() {
        return MAX_PLAYERS;
    }

    /**
     * Gets the current game ID.
     * @return Current game ID
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * Sets the game ID.
     * @param gameId The new game ID
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private Configuration() {
        initialize();
    }

    /**
     * Initializes the configuration with default values.
     */
    private void initialize() {
        synchronized (lock) {
            listOfCardGamePlayers.clear();
            gameId = ThreadLocalRandom.current().nextInt(1000000);
        }
    }

    /**
     * Gets the singleton instance of Configuration.
     * @return The Configuration instance
     */
    public static Configuration getInstance() {
        if (instance == null) {
            synchronized (Configuration.class) {
                if (instance == null) {
                    instance = new Configuration();
                }
            }
        }
        return instance;
    }

    /**
     * Gets an unmodifiable list of players in the game.
     * @return List of players
     */
    public List<CardGamePlayer> getPlayers() {
        return Collections.unmodifiableList(listOfCardGamePlayers);
    }

    /**
     * Adds a player to the game if space is available.
     * @param player The player to add
     * @return true if player was added, false if game is full
     */
    public boolean addPlayer(CardGamePlayer player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        synchronized (lock) {
            if (listOfCardGamePlayers.size() < MAX_PLAYERS) {
                listOfCardGamePlayers.add(player);
                return true;
            }
            return false;
        }
    }

    /**
     * Removes a player from the game.
     * @param player The player to remove
     * @return true if player was removed, false if not found
     */
    public boolean removePlayer(CardGamePlayer player) {
        if (player == null) {
            return false;
        }
        synchronized (lock) {
            return listOfCardGamePlayers.remove(player);
        }
    }

    /**
     * Clears all players from the game.
     */
    public void clearPlayers() {
        synchronized (lock) {
            listOfCardGamePlayers.clear();
        }
    }
}
