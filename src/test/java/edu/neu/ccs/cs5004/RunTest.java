package edu.neu.ccs.cs5004;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RunTest {
  private Run run1;
  private Run run2;
  private Run run3;
  private String[] args;
  private String[] args2;
  private String[] args3;

  @Before
  public void setUp() throws Exception {
    args = new String[7];
    args[0] = "--email";
    args[1] = "--email-template";
    args[2] = "email-template.txt";
    args[3] = "--output-dir";
    args[4] = "emails";
    args[5] = "--csv-file";
    args[6] = "theater-company-members.csv";
    args2 = new String[6];
    args2[0] = "--email";
    args2[1] = "--email-template";
    args2[2] = "email-template.txt";
    args2[3] = "--output-dir";
    args2[4] = "emails";
    args2[5] = "--csv-file";
    args3 = new String[7];
    run1 = new Run(new Reader(), new Writer(), new ParseArgument(args));
    run2 = new Run(new Reader(), new Writer(), new ParseArgument(args2));
    run3 = new Run(new Reader(), new Writer(), new ParseArgument(args3));
  }

  @Test(expected = RuntimeException.class)
  public void runSystem() {
    run1.runSystem();
    run2.runSystem();
    run3.runSystem();
  }


  @Test(expected = RuntimeException.class)
  public void testMain() {
    Run.main(args);
    Run.main(args2);
  }
}