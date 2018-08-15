package edu.neu.ccs.cs5004;

import java.io.File;

/**
 * The run class.
 */
public class Run {
  private Reader reader;
  private Writer writer;
  private ParseArgument parseArgument;

  /**
   * The constructor.
   * @param reader The reader
   * @param writer The writer
   * @param parseArgument The parse arguments
   */
  public Run(Reader reader, Writer writer, ParseArgument parseArgument) {
    this.reader = reader;
    this.writer = writer;
    this.parseArgument = parseArgument;
  }

  /**
   * Run the system.
   */
  public void runSystem() {
    String error = parseArgument.checkArguments();
    if (!error.equals("")) {
      throw new IllegalArgumentException(error);
    } else {
      generateMessages();
    }
  }

  /**
   * Generate the message.
   */
  public void generateMessages() {
    Members members = new Members();
    reader.readInfo(parseArgument.getInputFile(), members);
    Process process = new Process(members, reader.readTemplate(parseArgument.getTemplate()));
    String path = "./src/main/java/edu/neu/ccs/cs5004/" + parseArgument.getDirPath();
    File dir = new File(path);
    boolean successful = dir.mkdir();
    if (!successful) {
      System.out.println("Could not find the directory.");
    }
    int index = 0;
    for (String[] member : members.getInfoList()) {
      writer.writeIntoDir(path + "/" + index++ + ".txt",
          process.generate(member));
    }
  }

  /**
   * The main function.
   * @param args Given the filename and the directory.
   */
  public static void main(String[] args) {
    Reader reader = new Reader();
    Writer writer = new Writer();
    ParseArgument parseArgument = new ParseArgument(args);
    Run run = new Run(reader, writer, parseArgument);
    run.runSystem();
  }
}
