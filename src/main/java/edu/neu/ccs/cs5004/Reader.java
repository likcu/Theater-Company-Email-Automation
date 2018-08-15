package edu.neu.ccs.cs5004;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Reader {
  /**
   * Read the member info file then put the info into the member.
   *
   * @param fileName The string of file name.
   * @param member   The member which store the member info.
   */
  public void readInfo(String fileName, Members member) {
    try (BufferedReader breader = new BufferedReader(new InputStreamReader(
        new FileInputStream(fileName), "UTF-8"))) {

      //read the first file line(header) and put it into map
      String line;
      if ((line = breader.readLine()) != null) {
        String[] header = line.split(",");
        for (int i = 0; i < header.length; i++) {
          header[i] = header[i].replaceAll("\"", "");
          member.getMap().put(header[i], i);
        }
      }

      while ((line = breader.readLine()) != null) {
        if (line.equals("")) {
          break;
        }
        String[] memberInfo = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        for (int i = 0; i < memberInfo.length; i++) {
          memberInfo[i] = memberInfo[i].replace("\"", "");
        }
        member.getInfoList().add(memberInfo);
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OUPS! A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }
  }

  /**
   * Read the template file then return the strings of template.
   *
   * @param template The template file name.
   * @return The list of strings.
   */
  public List<String> readTemplate(String template) {
    String csvFile = "./" + template;
    String line;
    List<String> result = new ArrayList<>();
    try (BufferedReader breader = new BufferedReader(new InputStreamReader(
        new FileInputStream(csvFile), "UTF-8"))) {
      while ((line = breader.readLine()) != null) {
        result.add(line);
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OUPS! A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
    }
    return result;
  }
}
