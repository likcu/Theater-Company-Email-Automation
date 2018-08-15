package edu.neu.ccs.cs5004;

import org.junit.Before;
import org.junit.Test;

public class ReaderTest {
  private Reader reader;

  @Before
  public void setUp() throws Exception {
    reader = new Reader();
  }

  @Test
  public void readInfo() {
    Members members = new Members();
    reader.readInfo("a.txt",members);
  }

  @Test
  public void readTemplate() {
    reader.readTemplate("a.txt");
  }
}