import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextAnalyzer {

    public static void main(String[] args) {
        String fileName = "input.txt";
        Map<String, Integer> wordCount = new HashMap<>();
        int totalWords = 0;
        String longestWord = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(",");
                for (String word : words) {
                    totalWords++;
                    if (!word.isEmpty()) {
                        word = word.replaceAll("[^a-zA-Zа-яА-Я\\s]", "").toLowerCase();
                        word = word.trim();
                        if (!word.isEmpty()) {
                            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                            if (word.length() > longestWord.length()) {
                                longestWord = word;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        System.out.println("Количество слов в файле: " + totalWords);
        System.out.println("Самое длинное слово в файле: " + longestWord);
        System.out.println("Частота слов в файле:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
