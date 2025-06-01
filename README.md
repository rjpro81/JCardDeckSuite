# JCardDeckSuite

A lightweight Java library for implementing card games, providing essential functionality for managing decks and playing cards.

## Features

- Standard playing card implementation with suits and ranks
- French deck implementation with standard 52 cards
- Card game player management
- Deck manipulation operations (shuffling, drawing, etc.)
- Modern Java 21 implementation with best practices

## Project Structure

- `src/main/java/jcarddecksuite/`
  - [PlayingCard.java](cci:7://file:///Users/rjulsaint/JCardDeckSuite/src/main/java/jcarddecksuite/PlayingCard.java:0:0-0:0): Represents individual playing cards with suit and rank
  - [FrenchSuitDeck.java](cci:7://file:///Users/rjulsaint/JCardDeckSuite/src/main/java/jcarddecksuite/FrenchSuitDeck.java:0:0-0:0): Implements a standard 52-card French deck
  - [Deck.java](cci:7://file:///Users/rjulsaint/JCardDeckSuite/src/main/java/jcarddecksuite/Deck.java:0:0-0:0): Provides deck manipulation operations
  - [CardGamePlayer.java](cci:7://file:///Users/rjulsaint/JCardDeckSuite/src/main/java/jcarddecksuite/CardGamePlayer.java:0:0-0:0): Manages player-related operations

## Requirements

- Java 21 or higher
- Maven 3.8.x or higher

## Building the Project

```bash
mvn clean install
```

## Usage

The library can be used to create various card games by utilizing the provided classes:

```java
// Example usage
FrenchSuitDeck deck = new FrenchSuitDeck();
deck.shuffle();
PlayingCard card = deck.draw();
```

## License

GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## Contact

Elimu LLC
rjulsaint@elimullc.com
