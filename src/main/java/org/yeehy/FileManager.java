package org.yeehy;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    public static void processFile(Path path, LineHandler handler) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                handler.handle(line);
            }
        }
    }

    @FunctionalInterface
    public interface LineHandler {
        void handle(String line);
    }
}