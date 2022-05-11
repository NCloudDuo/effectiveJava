package taejoong.chapter7.item45;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class AnagramsV2 {
    public static void main(String[] args) throws IOException {
        Path dictionary = Paths.get("src/main/java/taejoong/chapter7/item45/dictionary.txt");
        int minGroupSize = 2;

        try (Stream<String> words = Files.lines(dictionary)) {
            words.collect(groupingBy(word -> word.chars().sorted()
                            .collect(StringBuilder::new, (sb, c) -> sb.append((char) c), StringBuilder::append)
                            .toString()))
                    .values()
                    .stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .map(group -> group.size() + ": " + group)
                    .forEach(System.out::println);
        }
    }
}
