package mage.constants;

/**
 *
 * @author North
 */
public enum SpellAbilityType {
    BASE("Basic SpellAbility"),
    BASE_ALTERNATE("Basic SpellAbility Alternate"), // used for Overload, Flashback to know they must be handled as Alternate casting costs
    LAND_ALTERNATE("Basic SpellAbility Alternate Land"), // used for Lands with Morph to cast as Face Down creature
    SPLIT("Split SpellAbility"),
    SPLIT_FUSED("Split SpellAbility"),
    SPLIT_LEFT("LeftSplit SpellAbility"),
    SPLIT_RIGHT("RightSplit SpellAbility"),
    MODE("Mode SpellAbility"),
    SPLICE("Spliced SpellAbility");

    private final String text;

    SpellAbilityType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
