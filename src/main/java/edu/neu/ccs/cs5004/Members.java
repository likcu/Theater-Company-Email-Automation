package edu.neu.ccs.cs5004;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the all the mmebers information that included
 * the header by given the csv file.
 */
public class Members {
  private Map<String, Integer> map;
  private List<String[]> infoList;

  /**
   * The constructor.
   */
  public Members() {
    this.map = new HashMap<>();
    this.infoList = new ArrayList<>();
  }

  /**
   * Get the map of the member.
   * @return The member header map.
   */
  public Map<String, Integer> getMap() {
    return map;
  }

  /**
   * Get the info list of member.
   * @return The list of member info
   */
  public List<String[]> getInfoList() {
    return infoList;
  }

}
