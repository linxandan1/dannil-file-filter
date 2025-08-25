package org.yeehy;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.ArrayList;
import java.util.List;

@Parameters(separators = "=", commandDescription = "File filter utility")
public class ArgsConfig {

    @Parameter(names = {"-o", "--output"},
            description = "Путь к директории для результатов (по умолчанию текущая)")
    private String outputDir = ".";

    @Parameter(names = {"-p", "--prefix"},
            description = "Префикс имён выходных файлов (например: result_)")
    private String prefix = "";

    @Parameter(names = {"-a", "--append"},
            description = "Добавлять в существующие файлы вместо перезаписи")
    private boolean append = false;

    @Parameter(names = {"-s", "--short"},
            description = "Выводить только краткую статистику")
    private boolean shortStats = false;

    @Parameter(names = {"-f", "--full"},
            description = "Выводить полную статистику")
    private boolean fullStats = false;

    @Parameter(description = "Входные файлы")
    private List<String> inputFiles = new ArrayList<>();

    @Parameter(names = {"-h", "--help"}, help = true,
            description = "Показать помощь")
    private boolean help;

    public String getOutputDir() { return outputDir; }
    public String getPrefix() { return prefix; }
    public boolean isAppend() { return append; }
    public boolean isShortStats() { return shortStats; }
    public boolean isFullStats() { return fullStats; }
    public List<String> getInputFiles() { return inputFiles; }
    public boolean isHelp() { return help; }
}