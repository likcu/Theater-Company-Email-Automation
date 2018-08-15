package edu.neu.ccs.cs5004;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParseArgumentTest {
  private ParseArgument parseTest1;
  private ParseArgument parseTest2;
  private ParseArgument sameRefParseTest1;
  private ParseArgument sameStateParseTest1;
  private ParseArgument anotherParseTest1;
  private ParseArgument noDir;
  private ParseArgument noInput;

  @Before
  public void setUp() throws Exception {
    String[] arg1 = {"--email", "--email-template", "email-template.txt", "--output-dir", "emails",
        "--csv-file", "customer.csv"};
    String[] arg2 = {"--letter-template", "letter-template.txt", "--letter", "--output-dir",
        "letters", "--csv-file", "customer.csv"};
    parseTest1 = new ParseArgument(arg1);
    parseTest2 = new ParseArgument(arg2);
    sameRefParseTest1 = parseTest1;
    sameStateParseTest1 = new ParseArgument(arg1);
    anotherParseTest1 = new ParseArgument(arg1);

    String[] arg3 = {"--email", "--email-template", "email-template.txt", "--output-dir",
        "--csv-file", "customer.csv"};
    String[] arg4 = {"--email", "--email-template", "email-template.txt", "--csv-file",
        "--output-dir", "emails", "customer.csv"};
    noDir = new ParseArgument(arg3);
    noInput = new ParseArgument(arg4);

  }

  @Test
  public void checkArguments() {
    String example = "Usage:\n\n"
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
    String actionError = "Error: no action provided.\n";
    String templateError = "Error: --email provided but no --email-template was given.\n";
    String dirError = "Error: output directory was not given.\n";
    String inputError = "Error: member information file was not given.\n";
    String[] noAction = {"--email-template", "email-template.txt", "--output-dir", "emails",
        "--csv-file", "customer.csv"};
    String[] noTemplate1 = {"--email", "--email-template", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    ParseArgument parseTest3 = new ParseArgument(noAction);
    ParseArgument parseTest4 = new ParseArgument(noTemplate1);
    Assert.assertEquals("", parseTest1.checkArguments());
    Assert.assertEquals("", parseTest2.checkArguments());
    Assert.assertEquals(actionError + example, parseTest3.checkArguments());
    Assert.assertEquals(templateError + example, parseTest4.checkArguments());
  }

  @Test
  public void getType() {
    Assert.assertEquals("--email",parseTest1.getType());
    Assert.assertEquals("--letter",parseTest2.getType());
  }

  @Test
  public void getTemplate() {
    Assert.assertEquals("email-template.txt",parseTest1.getTemplate());
    Assert.assertEquals("letter-template.txt",parseTest2.getTemplate());
  }

  @Test
  public void getDirPath() {
    Assert.assertEquals("emails",parseTest1.getDirPath());
    Assert.assertEquals("letters",parseTest2.getDirPath());
    Assert.assertEquals(null,noDir.getDirPath());
  }

  @Test
  public void getInputFile() {
    Assert.assertEquals("customer.csv",parseTest1.getInputFile());
    Assert.assertEquals(null,noInput.getInputFile());
  }

  @Test
  public void equals() {
    ParseArgument nullParseTest1 = null;
    Assert.assertTrue(parseTest1.equals(sameRefParseTest1));
    Assert.assertTrue(parseTest1.equals(sameStateParseTest1));
    Assert.assertEquals(parseTest1.equals(sameStateParseTest1),
        sameStateParseTest1.equals(parseTest1));
    Assert.assertEquals(parseTest1.equals(sameStateParseTest1)
        && sameStateParseTest1.equals(anotherParseTest1), parseTest1.equals(anotherParseTest1));
    Assert.assertFalse(parseTest1.equals(nullParseTest1));
    Assert.assertFalse(parseTest1.equals(new Integer(9)));
    Assert.assertFalse(parseTest1.equals(parseTest2));
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(parseTest1.equals(sameStateParseTest1),
        parseTest1.hashCode() == sameStateParseTest1.hashCode());
    Assert.assertEquals(parseTest1.equals(sameRefParseTest1),
        parseTest1.hashCode() == sameRefParseTest1.hashCode());
    Assert.assertEquals(!parseTest1.equals(parseTest2),
        parseTest1.hashCode() != parseTest2.hashCode());
  }
}