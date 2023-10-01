package game;

import game.internals.Monster;
import game.internals.Player;
import java.util.Scanner;

public class Game {
    private static String playerName;
    private static int playerAttack;
    private static int playerDefence;
    private static int monsterAttack;
    private static int monsterDefence;
    private static final Scanner scanner;
    static {
        scanner = new Scanner(System.in);
    }
    public boolean getUserInput() {
        boolean validInput = true;

        System.out.println("Введите имя игрока:");
        playerName = scanner.next();
        System.out.println("""
                            Введите параметры атаки и защиты для игрока и монстра - натуральные числа от 1 до 30 через пробел
                            Первое число - атака игрока, второе - зашита игрока, третье - атака монстра, четвертое - защита монстра
                            Пример ввода: 2 2 1 1
                            """);
        /*
         * Пытаемся распарсить пользовательский ввод в 4 int. Если не получилось, то исключение обрабатывается вторым обработчиком, который ловит Exception
         * Далее если у нас есть четыре int, проверяем что они соответствуют условиям задачи "У Существа есть параметры Атака и Защита. Это целые числа от 1 до 30."
         * Для этого запускаем метод checkInputValidity(intsToCheck) и если хоть одно из чисел не соответствует условиям то получаем кастомное исключение IncorrectInputException
         */
        try {
            playerAttack = scanner.nextInt();
            playerDefence = scanner.nextInt();
            monsterAttack = scanner.nextInt();
            monsterDefence = scanner.nextInt();

            int[] intsToCheck = new int[4];
            intsToCheck[0] = playerAttack;
            intsToCheck[1] = playerDefence;
            intsToCheck[2] = monsterAttack;
            intsToCheck[3] = monsterDefence;

            checkInputValidity(intsToCheck);
        }
        catch (IncorrectInputException e) {
            System.out.println(e.getMessage());
            validInput = false;
        }
        catch (Exception e) {
            System.out.println("Ошибка ввода");
            validInput = false;
        }
        finally {
            scanner.close();
        }
        return validInput;
    }
    private static void checkInputValidity(final int... intsToCheck) throws IncorrectInputException {
        for (int intToCheck : intsToCheck)
            if ((intToCheck > 30) || (intToCheck < 1))
                throw new IncorrectInputException("Введенное число/числа меньше 1 либо больше 30");
    }

    /*
     * Создаем экземпляры классов Player и Monster, высчитываем модификаторы атаки, отображаем начальные характеристики и запускаем бесконечный цикл для симуляции боя
     * Можно было бы и не делать бесконечный цикл, но при текущей реализации здоровье рано или поздно у кого-нибудь закончится и он остановится
     */
    public void startGame() {
        Player player = new Player(playerAttack, playerDefence, playerName);
        Monster monster = new Monster(monsterAttack, monsterDefence);

        player.setAttackModifier(monster);
        monster.setAttackModifier(player);
        player.printInitialStats();
        monster.printInitialStats();

        while (true) {
            player.attack(monster);
            if (monster.checkHealth())
                break;
            monster.attack(player);
            if (player.checkHealth())
                break;
            player.checkHealPossibility();
        }
    }
}
