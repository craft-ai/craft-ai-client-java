package com.craft_ai.interpreter.tools;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Resources {

  public static String getResource(String filename) throws IOException {
    Path path;
    String content = null;

    ClassLoader classLoader = Stream
        .of(Thread.currentThread().getContextClassLoader(), Resources.class.getClassLoader()).filter(cl -> cl != null)
        .findFirst().get();

    URL url = classLoader.getResource(filename);

    if (url != null) {
      try {
        path = Paths.get(url.toURI());
        content = new String(Files.readAllBytes(path));
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }
    }

    return content;
  }

}
