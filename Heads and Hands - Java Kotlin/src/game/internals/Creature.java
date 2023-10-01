package game.internals;

import java.util.Random;

abstract class Creature {
    protected final int attack;
    protected final int defence;
    protected int health;
    protected int minDamage;
    protected int maxDamage;
    protected int attackModifier;
    static final Random random;
    static {
        random = new Random();
    }
    protected Creature(final int attack, final int defence) {
        this.attack = attack;
        this.defence = defence;
    }
    abstract void printInitialStats();
    //attackModifier по условиям задачи это количество попыток нанести удар. Это значение всегда минимум 1, поэтому используем цикл с постусловием.
    public void attack(final Creature enemy) {
        boolean hit = false;
        int itterator = attackModifier;

        do {
            itterator--;
            if (isCreatureHit()) {
                hit = true;
                break;
            }
        } while (itterator > 0);

        if (hit) {
            int damage = minDamage + random.nextInt(maxDamage - minDamage);
            enemy.health = enemy.health - damage;
            System.out.format("%s атакует %s и наносит %d урона%n", getName(), enemy.getName(), damage);
        } else
            System.out.format("%s атакует %s и промахивается%n", getName(), enemy.getName());
    }
    //Удар считается нанесенным если на кубике выпадает 5 или 6.
    private boolean isCreatureHit() {
        return (random.nextInt(6) + 1) > 4;
    }
    abstract String getName();
    public int getHealth() {
        return health;
    }
    public boolean checkHealth() {
        boolean healthZeroOrBelow = this.health < 1;

        if (healthZeroOrBelow)
            System.out.format("%s умер%n%n",getName());
        else
            System.out.format("Здоровье %s равно %d%n%n", getName(), this.health);
        return healthZeroOrBelow;
    }
    //Рассчитываем Модификатор атаки. Он равен разности Атаки атакующего и Защиты защищающегося плюс 1.
    public void setAttackModifier(final Creature enemy) {
        int attackModifier = attack - enemy.defence + 1;
        this.attackModifier = Math.max(attackModifier, 1);
    }
}
