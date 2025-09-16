package lab1;

import java.util.Scanner;

import static java.lang.System.*;

public class Game {
    public static void main(String[] args) {
        Hero hero = new Hero("Герой", new FlyStrategy());

        hero.move("замок", "лес");

        hero.setNewStrategy(new HorseRideStrategy());
        hero.move("лес", "город");

        hero.setNewStrategy(new FlyStrategy());
        hero.move("город", "подземелье");

        hero.setNewStrategy(new WalkStrategy());
        hero.move("подземелье", "дом");

        Scanner scanner = new Scanner(in);
        out.println("\nПеремещение героя: " + hero.getName());
        out.println("Выберите способ перемещения:");
        out.println("1: Пешком");
        out.println("2: Верхом на лошади");
        out.println("3: Полёт");
        out.println("0: Выход из игры");

        int choice = 0;
        while (true) {
            out.print("\nВыбор: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }
            else{
                out.println("Некорретный ввод!");
            }

            if (choice == 0) break;

            switch (choice) {
                case 1 -> hero.setNewStrategy(new WalkStrategy());
                case 2 -> hero.setNewStrategy(new HorseRideStrategy());
                case 3 -> hero.setNewStrategy(new FlyStrategy());
                default -> {
                    out.println("Неверный ввод!");
                    continue;
                }
            }

            out.print("Начальная точка: ");
            String from = scanner.next();
            out.print("Конечная точка: ");
            String to = scanner.next();

            hero.move(from, to);
        }

        scanner.close();
        out.println("Игра завершена");
    }
}