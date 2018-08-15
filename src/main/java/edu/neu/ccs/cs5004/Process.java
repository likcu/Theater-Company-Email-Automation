package edu.neu.ccs.cs5004;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Process {
  private Members member;
  private List<String> template;

  /**
   * The constructor.
   * @param member The member
   * @param template The template file string list
   */
  public Process(Members member, List<String> template) {
    this.member = member;
    this.template = template;
  }

  /**
   * Generate the normal file with a person info.
   * @param onePerson One person info from the member
   * @return The normal file list of strings
   */
  public List<String> generate(String[] onePerson) {
    List<String> result = new ArrayList<>();
    Map<String, Integer> map = member.getMap();
    for (String str : template) {
      int index = str.indexOf("[[");
      while (index != -1) {
        int end = str.indexOf("]]", index);
        String header = str.substring(index + 2, end);
        str = str.replaceAll("\\[\\[" + header + "\\]\\]", onePerson[map.get(header)]);
        index = str.indexOf("[[");
      }
      result.add(str);
    }
    return result;
  }

}
