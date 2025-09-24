package lab3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = null;
        String dictionaryFile = "dictionary.txt";
        Dictionary dictionary = new Dictionary();

        try {
            dictionary.loadFromFile(dictionaryFile);

            Translator translator = new Translator(dictionary);
            scanner = new Scanner(System.in);

            System.out.println("Введите текст для перевода (или 'exit' для выхода):");

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                if (input.isEmpty()) {
                    continue;
                }

                try {
                    String translated = translator.translate(input);
                    System.out.println("Перевод: " + translated);
                } catch (Exception e) {
                    System.out.println("Ошибка при переводе: " + e.getMessage());
                }
            }

        } catch (FileReadException e) {
            System.out.println(e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Причина: " + e.getCause().getMessage());
            }
        } catch (InvalidFileFormatException e) {
            System.out.println("Ошибка формата файла: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Неожиданная ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
