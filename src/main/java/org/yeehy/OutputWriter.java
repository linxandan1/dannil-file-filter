package org.yeehy;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumMap;
import java.util.Map;

import org.yeehy.TypeDetector.DataType;

public class OutputWriter implements AutoCloseable {
    private final Path outputDir;
    private final String prefix;
    private final boolean append;

    private final Map<DataType, BufferedWriter> writers = new EnumMap<>(DataType.class);

    public OutputWriter(Path outputDir, String prefix, boolean append) {
        this.outputDir = outputDir;
        this.prefix = prefix;
        this.append = append;
    }

    public void write(DataType type, String line) throws IOException {
        BufferedWriter writer = writers.get(type);
        if (writer == null) {
            Path file = resolveFile(type);
            Files.createDirectories(file.getParent());

            StandardOpenOption mode = append
                    ? StandardOpenOption.APPEND
                    : StandardOpenOption.TRUNCATE_EXISTING;

            writer = Files.newBufferedWriter(file,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    mode);

            writers.put(type, writer);
        }

        writer.write(line);
        writer.newLine();
    }

    private Path resolveFile(DataType type) {
        String filename = switch (type) {
            case INTEGER -> prefix + "integers.txt";
            case FLOAT   -> prefix + "floats.txt";
            case STRING  -> prefix + "strings.txt";
        };
        return outputDir.resolve(filename);
    }

    @Override
    public void close() throws IOException {
        for (BufferedWriter writer : writers.values()) {
            writer.close();
        }
    }
}
