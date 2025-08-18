package org.yeehy;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Application {
    public static void main(String[] args) {
        ArgsConfig config = new ArgsConfig();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(config)
                .programName("file-filter")
                .build();

        try {
            jCommander.parse(args);

            if (config.isHelp() || args.length == 0) {
                jCommander.usage();
                return;
            }

            if (config.isShortStats() && config.isFullStats()) {
                System.err.println("Ошибка: нельзя одновременно использовать -s и -f");
                return;
            }

            if (config.getInputFiles().isEmpty()) {
                System.err.println("Ошибка: не указаны входные файлы");
                return;
            }

            System.out.println("Output dir: " + config.getOutputDir());
            System.out.println("Prefix: " + config.getPrefix());
            System.out.println("Append: " + config.isAppend());
            System.out.println("Short stats: " + config.isShortStats());
            System.out.println("Full stats: " + config.isFullStats());
            System.out.println("Input files: " + config.getInputFiles());

            System.out.println("---------------------------------------------------");

            Path outputPath = Path.of(config.getOutputDir());

            try (OutputWriter writer = new OutputWriter(outputPath, config.getPrefix(), config.isAppend())) {
                for (String filename : config.getInputFiles()) {
                    Path path = Path.of(filename);
                    if (!Files.exists(path)) {
                        System.err.println("Файл не найден: " + filename);
                        continue;
                    }

                    System.out.println("=== Обработка файла: " + filename + " ===");
                    FileManager.processFile(path, line -> {
                        TypeDetector.DataType type = TypeDetector.determineType(line);
                        try {
                            writer.write(type, line);
                        } catch (IOException e) {
                            System.err.println("Ошибка записи в файл: " + e.getMessage());
                        }
                    });
                }
            } catch (IOException e) {
                System.err.println("Ошибка при работе с выходными файлами: " + e.getMessage());
            }


        } catch (ParameterException e) {
            System.err.println("Ошибка аргументов: " + e.getMessage());
            jCommander.usage();
        }
    }
}
