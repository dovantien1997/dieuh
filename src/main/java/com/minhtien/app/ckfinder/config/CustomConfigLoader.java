package com.minhtien.app.ckfinder.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.inject.Named;

import com.cksource.ckfinder.config.Config;
import com.cksource.ckfinder.config.loader.ConfigLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

@Named
public class CustomConfigLoader implements ConfigLoader {
    @Override
    public Config loadConfig() throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Path configPath = Paths.get(System.getProperty("user.dir"), "ckfinder.yml");

        return mapper.readValue(Files.newInputStream(configPath), CustomConfig.class);
    }
}
