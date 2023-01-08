package dev.tias.dragontreasure.mobs.monsters;

import dev.tias.dragontreasure.mobs.Mob;

public class Dragon extends Mob {
    public static final int DRAGON_MAX_HEALTH = 10000;
    public static final int DRAGON_DAMAGE = 100;
    public static final String DRAGON_IMAGE =
                    "                                                  .~))>>\n"+
                    "                                                 .~)>>\n"+
                    "                                               .~))))>>>\n"+
                    "                                             .~))>>             ___\n"+
                    "                                           .~))>>)))>>      .-~))>>\n"+
                    "                                         .~)))))>>       .-~))>>)>\n"+
                    "                                       .~)))>>))))>>  .-~)>>)>\n"+
                    "                   )                 .~))>>))))>>  .-~)))))>>)>\n"+
                    "                ( )@@*)             //)>))))))  .-~))))>>)>\n"+
                    "              ).@(@@               //))>>))) .-~))>>)))))>>)>\n"+
                    "            (( @.@).              //))))) .-~)>>)))))>>)>\n"+
                    "          ))  )@@*.@@ )          //)>))) //))))))>>))))>>)>\n"+
                    "       ((  ((@@@.@@             |/))))) //)))))>>)))>>)>\n"+
                    "      )) @@*. )@@ )   (\\_(\\-\\b  |))>)) //)))>>)))))))>>)>\n"+
                    "    (( @@@(.@(@ .    _/`-`  ~|b |>))) //)>>)))))))>>)>\n"+
                    "     )* @@@ )@*     (@)  (@) /\\b|))) //))))))>>))))>>\n"+
                    "   (( @. )@( @ .   _/  /    /  \\b)) //))>>)))))>>>_._\n"+
                    "    )@@ (@@*)@@.  (6///6)- / ^  \\b)//))))))>>)))>>   ~~-.\n"+
                    " ( @jgs@@. @@@.*@_ VvvvvV//  ^  \\b/)>>))))>>      _.     `bb\n"+
                    " ((@@ @@@*.(@@ . - | o |' \\ (  ^   \\b)))>>        .'       b`,\n"+
                    "   ((@@).*@@ )@ )   \\^^^/  ((   ^  ~)_        \\  /           b `,\n"+
                    "     (@@. (@@ ).     `-'   (((   ^    `\\ \\ \\ \\ \\|             b  `.\n"+
                    "       (*.@*              / ((((        \\| | |  \\       .       b `.\n"+
                    "                         / / (((((  \\    \\ /  _.-~\\     Y,      b  ;\n"+
                    "                        / / / (((((( \\    \\.-~   _.`\" _.-~`,    b  ;\n"+
                    "                       /   /   `(((((()    )    (((((~      `,  b  ;\n"+
                    "                     _/  _/      `\"\"\"/   /'                  ; b   ;\n"+
                    "                 _.-~_.-~           /  /'                _.'~bb _.'\n"+
                    "               ((((~~              / /'              _.'~bb.--~\n"+
                    "                                  ((((          __.-~bb.-~\n"+
                    "                                              .'  b .~~\n"+
                    "                                              :bb ,' \n"+
                    "                                              ~~~~\n";

    public Dragon() {
        super();
        this.description = "a fearsome and might Dragon";
        this.noun = "Dragon";
        this.indefiniteNoun = "a Dragon";
        this.maxHealth = DRAGON_MAX_HEALTH;
        this.health = this.maxHealth;
        this.damage = DRAGON_DAMAGE;
    }
}
