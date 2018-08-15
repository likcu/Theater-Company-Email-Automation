package edu.neu.ccs.cs5004;

import java.util.Arrays;

/**
 * The parseArgument class.
 */
public class ParseArgument {
  private String[] args;
  public static final String LETTER = "--letter";
  public static final String EMAIL = "--email";
  public static final String TEMPLATE = "-template";
  public static final String DIRECTORY = "--output-dir";
  public static final String INPUTFILE = "--csv-file";


  /**
   * The constructor of the class.
   */
  public ParseArgument(String[] args) {
    this.args = new String[args.length];
    int index = 0;
    for (String str : args) {
      this.args[index] = str;
      index++;
    }
  }

  /**
   * Check if the arguments are correct.
   *
   * @return The check message.
   */
  public String checkArguments() {
    String res = "";
    if (getType() == null) {
      res += "Error: no action provided.\n";
    }

    if (getTemplate() == null && getType() != null) {
      res += "Error: " + getType().toString() + " provided but no " + getType().toString()
          + TEMPLATE + " was given.\n";
    }

    if (getDirPath() == null) {
      res += "Error: output directory was not given.\n";
    }
    if (getInputFile() == null) {
      res += "Error: member information file was not given.\n";
    }
    if (!res.equals("")) {
      res += "Usage:\n\n"
          + "        --email                        only generate email messages\n"
          + "        --email-template <file>  accepts a filename that holds the email "
          + "template.\n"
          + "          Required if --email is used\n\n"
          + "        --letter                        only generate letters\n"
          + "        --letter-template <file> accepts a filename that holds the email "
          + "template.\n"
          + "          Required if --letter is used\n\n"
          + "        --output-dir <path> accepts the name of a folder, all output is placed "
          + "in this folder\n"
          + "\n"
          + "        --csv-file <path> accepts the name of the csv file to process\n";
    }
    return res;
  }

  /**
   * Check the flag.
   *
   * @param arg the string.
   * @return True if it is a flag, otherwise return false.
   */
  public boolean isSwitch(String arg) {
    return arg.charAt(0) == '-' && arg.charAt(1) == '-';
  }

  /**
   * Get the type of the file.
   * @return The string
   */
  public String getType() {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals(LETTER)) {
        return LETTER;
      } else if (args[i].equals(EMAIL)) {
        return EMAIL;
      }
    }
    return null;
  }

  /**
   * Get the template of the arguments.
   *
   * @return String of template file
   */
  public String getTemplate() {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals(LETTER + TEMPLATE) && !isSwitch(args[i + 1])) {
        return args[i + 1].toString();
      } else if (args[i].equals(EMAIL + TEMPLATE) && !isSwitch(args[i + 1])) {
        return args[i + 1].toString();
      }
    }
    return null;
  }

  /**
   * Get the directory path.
   * @return The string of path
   */
  public String getDirPath() {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals(DIRECTORY) && !isSwitch(args[i + 1])) {
        return args[i + 1].toString();
      }
    }
    return null;
  }

  /**
   * Get the input file name.
   *
   * @return The input file name.
   */
  public String getInputFile() {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals(INPUTFILE) && !isSwitch(args[i + 1])) {
        return args[i + 1].toString();
      }
    }
    return null;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    ParseArgument that = (ParseArgument) object;
    return Arrays.equals(args, that.args);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(args);
  }
}
