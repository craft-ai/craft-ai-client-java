package com.craft_ai.interpreter.tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Resources {
  public static Path getResourcePath(String path) throws URISyntaxException {
    ClassLoader classLoader = Stream
        .of(Thread.currentThread().getContextClassLoader(), Resources.class.getClassLoader()).filter(cl -> cl != null)
        .findFirst().get();

    return Paths.get(classLoader.getResource(path).toURI());
  }

  public static String getResource(String file) throws IOException, URISyntaxException {
    Path path = getResourcePath(file);
    return new String(Files.readAllBytes(path));
  }

  public static List<String> listResources(String dir) throws IOException, URISyntaxException {
    Path dirpath = getResourcePath(dir);

    List<String> filenames = null;

    try (Stream<Path> walk = Files.walk(dirpath)) {
      filenames = walk.filter(Files::isRegularFile).map(filepath -> dir + dirpath.relativize(filepath).toString())
          .collect(Collectors.toList());
    } catch (IOException e) {
      throw e;
    }

    return filenames;
  }
}
