package properties;

import java.util.List;

public enum SmokeProperties {

    CASINO_BLOCK_NAMES(List.of("Popular", "Video Slots", "Table Games", "Live Casino", "Video Poker", "Games")),
    LIVE_CASINO_BLOCK_NAMES(List.of("Roulette", "Blackjack", "Holdem", "Baccarat", "Games")),
    BETGAMES_SPORTS_NAME(List.of("Lucky5", "Lucky7", "Dice", "Bet on Poker", "Bet on Baccarat", "Wheel of Fortune", "War of Bets")),
    VIRTUAL_SPORTS_GAME_NAME(List.of("Bundesliga", "Virtual NBA", "Champions Cup", "Baseball", "Tennis",
            "Dogs", "Horses", "Asian Cup", "Nations Cup", "World Cup", "Football League", "Euro Cup")),
    PROMO_SECTIONS(List.of("Sports", "Casino")),
    NO_PROMO_MESSAGE(List.of("Unfortunately there are no promotions available at this time"));

    public final List<String> values;

    SmokeProperties(List<String> values) {
        this.values = values;
    }

}
