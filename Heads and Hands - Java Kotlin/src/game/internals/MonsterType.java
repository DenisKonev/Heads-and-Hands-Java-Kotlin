package game.internals;

import java.util.Random;

public enum MonsterType {
    DRAGON,
    DEMON,
    GIANT,
    WEREWOLF,
    VAMPIRE,
    MINOTAUR;

    private static final Random random = new Random();

    public static MonsterType randomMonsterType()  {
        MonsterType[] monsterTypes = values();
        return monsterTypes[random.nextInt(monsterTypes.length)];
    }
}
