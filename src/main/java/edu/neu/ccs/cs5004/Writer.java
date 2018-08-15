package edu.neu.ccs.cs5004;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class Writer {

  /**
   * Write the normal message into the file.
   * @param file The file name with its directory.
   * @param content The contents list of strings.
   */
  public void writeIntoDir(String file, List<String> content) {
    try (BufferedWriter outputFile = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(file), "UTF-8"))) {
      for (String line : content) {
        outputFile.write(line);
        outputFile.newLine();
      }
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }
  }

}
