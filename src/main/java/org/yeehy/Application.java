package org.yeehy;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.yeehy.statistic.NumericStats;
import org.yeehy.statistic.StringStats;

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
            if (!Files.exists(outputPath)) {
                try {
                    Files.createDirectories(outputPath);
                } catch (IOException e) {
                    System.err.println("Ошибка: не удалось создать директорию вывода: " + e.getMessage());
                    return;
                }
            }

            try (OutputWriter writer = new OutputWriter(outputPath, config.getPrefix(), config.isAppend())) {
                NumericStats intStats = new NumericStats();
                NumericStats floatStats = new NumericStats();
                StringStats stringStats = new StringStats();

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

                            switch (type) {
                                case INTEGER -> intStats.addNumber(Long.parseLong(line));
                                case FLOAT   -> floatStats.addNumber(Double.parseDouble(line));
                                case STRING  -> stringStats.addString(line);
                            }
                        } catch (Exception e) {
                            System.err.println("Ошибка при обработке строки: " + e.getMessage());
                        }
                    });
                }

                printStats(config, intStats, floatStats, stringStats);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (ParameterException e) {
            System.err.println("Ошибка аргументов: " + e.getMessage());
            jCommander.usage();
        }
    }
    private static void printStats(ArgsConfig config, NumericStats intStats, NumericStats floatStats, StringStats stringStats) {
        System.out.println("================================");
        if (config.isShortStats()) {
            System.out.println("========= SHORT STATS =========");
            System.out.printf("Integers : %d%n", intStats.getCount());
            System.out.printf("Floats   : %d%n", floatStats.getCount());
            System.out.printf("Strings  : %d%n", stringStats.getCount());
        } else if (config.isFullStats()) {
            System.out.println("========= FULL STATS =========");
            System.out.printf("Integers : count=%d | min=%s | max=%s | sum=%.3f | avg=%.3f%n",
                    intStats.getCount(),
                    Double.isNaN(intStats.getMin()) ? "-" : intStats.getMin(),
                    Double.isNaN(intStats.getMax()) ? "-" : intStats.getMax(),
                    intStats.getSum(),
                    Double.isNaN(intStats.getAverage()) ? 0.0 : intStats.getAverage());
            System.out.printf("Floats   : count=%d | min=%s | max=%s | sum=%.3f | avg=%.3f%n",
                    floatStats.getCount(),
                    Double.isNaN(floatStats.getMin()) ? "-" : floatStats.getMin(),
                    Double.isNaN(floatStats.getMax()) ? "-" : floatStats.getMax(),
                    floatStats.getSum(),
                    Double.isNaN(floatStats.getAverage()) ? 0.0 : floatStats.getAverage());
            System.out.printf("Strings  : count=%d | minLen=%d | maxLen=%d%n",
                    stringStats.getCount(),
                    stringStats.getMinLength(),
                    stringStats.getMaxLength());
        }
        System.out.println("================================");
    }

}
