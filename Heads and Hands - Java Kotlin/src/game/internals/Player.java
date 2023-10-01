package game.internals;
public final class Player extends Creature implements Healable {
    private final String name;
    private int healsCount = 4;
    private final int initialHealth;
    public Player(final int attack, final int defence, final String name) {
        super(attack, defence);
        this.name = name;
        this.health = 200 + random.nextInt(801);
        this.minDamage = random.nextInt(21);
        this.maxDamage = minDamage + random.nextInt(81);
        this.initialHealth = health;
    }
    @Override
    public void printInitialStats() {
        System.out.format("%nИгрок %s начинает битву со следующими характеристиками:%n", getName());
        System.out.format("Атака: %d Защита: %d Здоровье: %d Урон: %d-%d Модификатор атаки: %d%n", attack, defence, health, minDamage, maxDamage, attackModifier);
    }
    @Override
    public String getName() {
        return name;
    }
    //Игрок может себя исцелить до 4-х раз на 30% от максимального Здоровья.
    @Override
    public void heal() {
        int restoredHealth = ((initialHealth * 3) / 10);
         health += restoredHealth;
         healsCount--;
         System.out.format("%s восстанавливает %d очков здоровья и теперь его здоровье равно %d%n", getName(), restoredHealth, health);
    }
    //Лечимся если здоровье падает меньше 50% от изначального и еще есть красные бутыльки :).
    public void checkHealPossibility() {
        if ((health < initialHealth / 2) && healsCount > 0)
            heal();
    }
}

