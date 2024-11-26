package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ConfigurationReader {
        private static final String PATH = "src".concat(File.separator).concat("main").concat(File.separator)
                        .concat("resources").concat(File.separator).concat("config.yml");

        public static Configuration GetConfiguration() {
                try {
                        final List<String> configList;
                        configList = Files.readAllLines(Path.of(PATH));
                        Configuration.Builder b = new Configuration.Builder();
                        return b.setMin(Integer.parseInt(configList.get(0).split(":")[1].trim()))
                                        .setMax(Integer.parseInt(configList.get(1).split(":")[1].trim()))
                                        .setAttempts(Integer.parseInt(configList.get(2).split(":")[1].trim()))
                                        .build();
                } catch (IOException ioException) {
                        return new Configuration.Builder().build();
                }

        }
}
