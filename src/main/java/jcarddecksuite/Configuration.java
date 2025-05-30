package jcarddecksuite;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;



public class Configuration {
  private static final int MAX_PLAYERS = 4;
  private final List<CardGamePlayer> listOfCardGamePlayers = new CopyOnWriteArrayList<>();
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
    listOfCardGamePlayers.clear();
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

  public List<CardGamePlayer> getPlayers() {
    return Collections.unmodifiableList(listOfCardGamePlayers);
  }

  public void addPlayer(CardGamePlayer player) {
    if (listOfCardGamePlayers.size() < MAX_PLAYERS) {
      listOfCardGamePlayers.add(player);
    }
  }

  public void removePlayer(CardGamePlayer player) {
    listOfCardGamePlayers.remove(player);
  }
}
