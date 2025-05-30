package jcarddecksuite;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Manages game configuration and player state.
 * @param <T> The type of player that can be added to this configuration
 */
public class Configuration<T extends CardGamePlayer> {
    public static final int MAX_PLAYERS = 4;
    private final List<T> players = new CopyOnWriteArrayList<>();
    private int gameId;
    private static final Object lock = new Object();
    
    private static volatile Configuration<?> instance;

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
    protected Configuration() {
        initialize();
    }

    /**
     * Initializes the configuration with default values.
     */
    private void initialize() {
        synchronized (lock) {
            players.clear();
            gameId = ThreadLocalRandom.current().nextInt(1000000);
        }
    }

    /**
     * Gets the singleton instance of Configuration.
     * @param <T> The type of player for this configuration instance
     * @return The Configuration instance
     */
    @SuppressWarnings("unchecked")
    public static <T extends CardGamePlayer> Configuration<T> getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new Configuration<>();
                }
            }
        }
        return (Configuration<T>) instance;
    }

    /**
     * Gets an unmodifiable list of players in the game.
     * @return List of players
     */
    public List<T> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    /**
     * Adds a player to the game if space is available.
     * @param player The player to add
     * @return true if player was added, false if game is full
     */
    public boolean addPlayer(T player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        synchronized (lock) {
            if (players.size() >= MAX_PLAYERS) {
                return false;
            }
            players.add(player);
            return true;
        }
    }

    /**
     * Removes a player from the game.
     * @param player The player to remove
     * @return true if player was removed, false if player was not in game
     */
    public boolean removePlayer(T player) {
        synchronized (lock) {
            return players.remove(player);
        }
    }

    /**
     * Checks if the game is full.
     * @return true if game is full, false otherwise
     */
    public boolean isGameFull() {
        return players.size() >= MAX_PLAYERS;
    }

    /**
     * Gets the number of players currently in the game.
     * @return Number of players
     */
    public int getPlayerCount() {
        return players.size();
    }

    /**
     * Resets the configuration to its initial state.
     */
    public void reset() {
        synchronized (lock) {
            players.clear();
            gameId = ThreadLocalRandom.current().nextInt(1000000);
        }
    }
}
