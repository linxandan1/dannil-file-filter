package org.yeehy;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

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

        } catch (ParameterException e) {
            System.err.println("Ошибка аргументов: " + e.getMessage());
            jCommander.usage();
        }
    }
}
