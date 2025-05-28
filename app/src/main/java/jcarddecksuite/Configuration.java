package jcarddecksuite;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Configuration {
  private static final int MAX_PLAYERS = 4;
  private final List<WarPlayer> listOfWarPlayers = new CopyOnWriteArrayList<>();
  private int gameId;

  public int getGameId() {
    return gameId;
  }

  public void setGameId(int gameId) {
    this.gameId = gameId;
  }
  private static Configuration instance;

  private Configuration(){
    initialize();
  }

  private void initialize() {
    listOfWarPlayers.clear();
    gameId = new java.util.Random().nextInt(1000000);
  }

  public static Configuration getInstance(){
    if (instance == null) {
      synchronized (Configuration.class) {
        if (instance == null) {
          instance = new Configuration();
        }
      }
    }
    return instance;
  }

  public List<WarPlayer> getPlayers() {
    return Collections.unmodifiableList(listOfWarPlayers);
  }

  public void addPlayer(WarPlayer player) {
    if (listOfWarPlayers.size() < MAX_PLAYERS) {
      listOfWarPlayers.add(player);
    }
  }

  public void removePlayer(WarPlayer player) {
    listOfWarPlayers.remove(player);
  }
}
