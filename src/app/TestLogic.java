package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * TestLogic
 * 
 * Source for statements.txt:
 * https://www.slader.com/textbook/9780534359454-discrete-mathematics-with-applications-3rd-edition/15/exercise-set/10/
 */
public class TestLogic {

    static ArrayList<String> testStatements = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(System.getProperty("user.dir") + "/src/app/statements.txt"))) {
            lines.forEach(line -> {
                testStatements.add(line);
            });
        }

        testStatements.forEach(line -> new ProcessStatement(line, 3));
    }
}


// p                              | 1 | 1 | 1 | 1 | 0 | 0 | 0 | 0
// q                              | 1 | 1 | 0 | 0 | 1 | 1 | 0 | 0
// r                              | 1 | 0 | 1 | 0 | 1 | 0 | 1 | 0

// ~q                             | 0 | 0 | 1 | 1 | 0 | 0 | 1 | 1
// p||~q                          | 1 | 1 | 1 | 1 | 0 | 0 | 1 | 1

// q||r                           | 1 | 1 | 1 | 0 | 1 | 1 | 1 | 0
// (q||r)&&p                      | 1 | 1 | 1 | 0 | 0 | 0 | 0 | 0
// ~((q||r)&&p)                   | 0 | 0 | 0 | 1 | 1 | 1 | 1 | 1
// ~((q||r)&&p)||r                | 0 | 0 | 0 | 1 | 1 | 1 | 1 | 1

// (p||~q)&&(~((q||r)&&p)||r)     | 0 | 0
