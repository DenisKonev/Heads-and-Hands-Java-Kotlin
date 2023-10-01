package game.internals;
public final class Monster extends Creature {
    private final MonsterType monsterType;
    public Monster(final int attack, final int defence) {
        super(attack, defence);
        this.monsterType = MonsterType.randomMonsterType();
        this.health = 500 + random.nextInt(1501);
        this.minDamage = random.nextInt(11);
        this.maxDamage = minDamage + random.nextInt(91);
    }
    @Override
    public void printInitialStats() {
        System.out.format("%nМонстр %s начинает битву со следующими характеристиками:%n", getName());
        System.out.format("Атака: %d Защита: %d Здоровье: %d Урон: %d-%d Модификатор атаки: %d%n", attack, defence, health, minDamage, maxDamage, attackModifier);
        System.out.println();
    }
    @Override
    public String getName() {
        return monsterType.toString();
    }
}

