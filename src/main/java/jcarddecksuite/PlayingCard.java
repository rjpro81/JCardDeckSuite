package jcarddecksuite;

public class PlayingCard {
    private FrenchSuitDeck.suits suit;
    private String rank;

    public PlayingCard(FrenchSuitDeck.suits suit, String rank){
        this.suit = suit;
        this.rank = rank;
    }

    FrenchSuitDeck.suits getSuit(){
        return suit;
    }

    String getRank(){
        return rank;
    }

    @Override
    public String toString(){
       return getRank()+" of "+getSuit();
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof PlayingCard)){
          return false;
        }
        return this.rank == ((PlayingCard) obj).rank;
    }

    @Override
    public int hashCode(){
        return rank.hashCode() * 7;
    }
}
