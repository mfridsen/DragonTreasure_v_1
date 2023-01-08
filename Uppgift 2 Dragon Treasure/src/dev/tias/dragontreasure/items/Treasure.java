package dev.tias.dragontreasure.items;

public class Treasure extends Item {
    public static final String TREASURE_IMAGE =
            "                  _.--.\n"+
            "              _.-'_:-'||\n"+
            "          _.-'_.-::::'||\n"+
            "     _.-:'_.-::::::'  ||\n"+
            "   .'`-.-:::::::'     ||\n"+
            "  /.'`;|:::::::'      ||_\n"+
            " ||   ||::::::'      _.;._'-._\n"+
            " ||   ||:::::'   _.-!oo @.!-._'-.\n"+
            " \'.  ||:::::.-!() oo @!()@.-'_.||\n"+
            "   '.'-;|:.-'.&$@.& ()$%-'o.'\\U||\n"+
            "     `>'-.!@%()@'@_%-'_.-o _.|'||\n"+
            "      ||-._'-.@.-'_.-' _.-o  |'||\n"+
            "      ||=[ '-._.-\\U/.-'    o |'||\n"+
            "      || '-.]=|| |'|      o  |'||\n"+
            "      ||      || |'|        _| ';\n"+
            "      ||      || |'|    _.-'_.-'\n"+
            "      |'-._   || |'|_.-'_.-'\n"+
            "      '-._'-.|| |' `_.-'\n"+
            "           '-.||_/.-'\n";

    public Treasure() {
        this.description = "a chest full of gold and jewelry";
        this.noun = "Treasure Chest";
        this.indefiniteNoun = "a Treasure Chest";
    }
}
