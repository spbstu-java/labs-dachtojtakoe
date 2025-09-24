package lab3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private final List<DictionaryEntry> entries = new ArrayList<>();

    public void loadFromFile(String filename) throws FileReadException, InvalidFileFormatException {
        entries.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                processLine(line, lineNumber);
            }

        } catch (IOException e) {
            throw new FileReadException("Ошибка чтения файла: " + filename, e);
        }
    }

    private void processLine(String line, int lineNumber) throws InvalidFileFormatException {
        if (line.trim().isEmpty()) {
            return;
        }

        String[] parts = line.split("\\|", 2);

        if (parts.length != 2) {
            throw new InvalidFileFormatException(
                "Неверный формат строки " + lineNumber + ": ожидается формат 'слово | перевод'"
            );
        }

        String original = parts[0].trim().toLowerCase();
        String translation = parts[1].trim().toLowerCase();

        if (original.isEmpty() || translation.isEmpty()) {
            throw new InvalidFileFormatException(
                "Пустое слово или перевод в строке " + lineNumber
            );
        }

        entries.add(new DictionaryEntry(original, translation));
    }

    public String findTranslation(String text) {
        for (DictionaryEntry entry : entries) {
            if(text.equalsIgnoreCase(entry.original))
                return entry.translation;
        }

        return null;
    }

    private record DictionaryEntry(String original, String translation) { }
}