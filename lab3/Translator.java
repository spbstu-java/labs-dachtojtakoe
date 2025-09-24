package lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translator {
    private final Dictionary dictionary;

    public Translator(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String translate(String text) {
        if (text == null || text.trim().isEmpty()) {
            return text;
        }

        List<String> tokens = tokenize(text);
        List<String> result = new ArrayList<>();

        int i = 0;
        while (i < tokens.size()) {
            boolean translated = false;

            for (int j = tokens.size(); j > i; j--) {
                List<String> sequence = tokens.subList(i, j);
                String phrase = String.join("", sequence);

                if (!phrase.isEmpty()) {
                    String translation = dictionary.findTranslation(phrase);

                    if (translation != null) {
                        String translatedPhrase = restoreCase(phrase, translation);
                        result.add(translatedPhrase);
                        i = j;
                        translated = true;
                        break;
                    }
                }
            }

            if (!translated) {
                result.add(tokens.get(i));
                i++;
            }
        }

        return String.join("", result);
    }

    private List<String> tokenize(String text) {
        List<String> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\w+|[^\\w\\s]|\\s+");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        return tokens;
    }

    private String restoreCase(String original, String translation) {
        if (original.isEmpty() || translation.isEmpty()) {
            return translation;
        }

        if (Character.isUpperCase(original.charAt(0))) {
            if (translation.length() == 1) {
                return translation.toUpperCase();
            } else {
                return Character.toUpperCase(translation.charAt(0)) + translation.substring(1);
            }
        }

        return translation;
    }
}
