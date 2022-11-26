package utilities;
import static utilities.ColorUtils.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public final class FinanceUtils
{

  public static void printDailyReport(String report)
  {
    //This class provides the main application entry point for printing and parsing and provides common implementations of DateTimeFormatter:
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern(
        "dd-MM-yyyy HH:mm:ss_SSS");
    LocalDateTime today = LocalDateTime.now(); //takes today
    List<LocalDateTime> dates = new ArrayList<>();
    dates.add(today);

    try {
      DateTimeFormatter formatFileName = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH_mm_ss_SSS");
      String formattedDateTime = today.format(formatFileName);
      String directoryName = "C:\\Club_cash_book_reports\\";

      File directory = new File(directoryName);
      if (!directory.exists()) { //if the directory does not exist, it is created
        directory.mkdirs();// create directory, a method of File class
      }
      FileWriter dailyReport = new FileWriter("C:\\Club_cash_book_reports\\"+formattedDateTime + ".txt");
      dailyReport.write("Daily turnover for date: ");
      dailyReport.write(formatDate.format(today) + "\n");
      dailyReport.write(report);
      dailyReport.flush();
      /*with this method e clean the buffer(a linear, finite sequence of elements
      of a specific primitive type. Aside from its content, the essential properties
      of a buffer are its capacity, limit, and position: A buffer's capacity is the number
      of elements it contains. The capacity of a buffer is never negative and never changes)
      and writes everything to the file
      */
      dailyReport.close();// close the file in order to be used by other users

    }
    catch (FileNotFoundException fnfe) {
      System.err.println("File not found: " + fnfe.getMessage());
    }
    catch (FileAlreadyExistsException faee) {
      System.err.println("File already exists: " + faee.getMessage());
    }
    catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
    System.out.println(COLOUR_BLUE + LocalDateTime
        .now()
        .format(formatDate) + COLOUR_RESET);
  }
}
