package inventory;

import static inventory.Menu.fizzies;
import static java.util.Map.entry;

import accounting.CashRegister;
import exceptions.NegativeQuantityException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import utilities.StockUtils;

public final class WareHouse
{
//A map with all the ABVDrinks and their quantities
  public Map<ABVDrinks, Integer> alcohols = new TreeMap<>(Map.ofEntries(
      entry(ABVDrinks.WHISKEY, 400),
      entry(ABVDrinks.VODKA, 400),
      entry(ABVDrinks.COINTREAU, 400),
      entry(ABVDrinks.RUM, 400),
      entry(ABVDrinks.WHITE_RUM, 400),
      entry(ABVDrinks.BOURBON, 400),
      entry(ABVDrinks.BEER, 2000),
      entry(ABVDrinks.GINGER_BEER, 2000),
      entry(ABVDrinks.TEQUILA, 400),
      entry(ABVDrinks.VERMOUTH, 400),
      entry(ABVDrinks.CAMPARI, 400),
      entry(ABVDrinks.GIN, 400),
      entry(ABVDrinks.COGNAC, 400),
      entry(ABVDrinks.LIQUEUR, 1400),
      entry(ABVDrinks.CHAMPAGNE, 1400),
      entry(ABVDrinks.RED_WINE, 1400),
      entry(ABVDrinks.WHITE_WINE, 1400)));
//A map with all the Fizzy drinks and their quantities
  public Map<Fizzies, Integer> fizzies = new TreeMap<>(Map.ofEntries(
      entry(Fizzies.SPRITE, 400),
      entry(Fizzies.SODA, 400),
      entry(Fizzies.LEMON_JUICE, 400),
      entry(Fizzies.CRANBERRY_JUICE, 400),
      entry(Fizzies.COLA, 400),
      entry(Fizzies.FANTA, 400),
      entry(Fizzies.ORANGE_JUICE, 400),
      entry(Fizzies.APPLE_JUICE, 400),
      entry(Fizzies.ZERO_BEER, 400)));
//A map with all the Ingridients and their quantities
  public Map<Ingredients, Integer> ingredients = new HashMap<>(Map.ofEntries(
      entry(Ingredients.SUGAR, 40),
      entry(Ingredients.SALT, 40),
      entry(Ingredients.MILK, 40),
      entry(Ingredients.ICE, 40),
      entry(Ingredients.FRUITS, 40),
      entry(Ingredients.BITTERS, 40),
      entry(Ingredients.OLIVE, 40),
      entry(Ingredients.MINT_LEAVES, 40),
      entry(Ingredients.TOMATO_JUICE, 40),
      entry(Ingredients.COFFEE, 40),
      entry(Ingredients.LIME_JUICE, 40)));


  public void increaseStock(Object drink, int quantity)
  {
    if (drink instanceof ABVDrinks) {

      alcohols.merge((ABVDrinks) drink, quantity, Integer::sum);
    }
    else if (drink instanceof Fizzies) {
      fizzies.merge((Fizzies) drink, quantity, Integer::sum);
    }
    else {
      ingredients.merge((Ingredients) drink, quantity, Integer::sum);
    }
  }

  public void decreaseStock(Object o, WareHouse wareHouse, CashRegister cashRegister, int quantity)
      throws NegativeQuantityException
  {
    if (o instanceof ABVDrinks) {

      if (((ABVDrinks) o).getMilliliters() > alcohols.get(o)) {
        StockUtils.addStock(wareHouse, cashRegister, o, Menu.aDrinks.get(o));
        throw new NegativeQuantityException();
      }
      alcohols.merge((ABVDrinks) o, -quantity, Integer::sum);
    }
    else if (o instanceof Fizzies) {
      if (((Fizzies) o).getMilliliters() > wareHouse.fizzies.get(o)) {
        StockUtils.addStock(wareHouse, cashRegister, o, Menu.fizzies.get(o));
        throw new NegativeQuantityException();
      }
      fizzies.merge((Fizzies) o, -quantity, Integer::sum);
    }
    else if (o instanceof Ingredients) {
      if (((Ingredients) o).getMilligrams() > wareHouse.ingredients.get(o)) {
        StockUtils.addStock(wareHouse, cashRegister, o, BigDecimal.valueOf(2));
        throw new NegativeQuantityException();
      }
      ingredients.merge((Ingredients) o, -quantity, Integer::sum);
    }
    else {
      Cocktail cocktail = (Cocktail) o;
      boolean allIngredients = true;
      for (Map.Entry<ABVDrinks, Integer> alcEntry : cocktail.aDrinks.entrySet()) {
        if (alcEntry.getValue() > alcohols.get(alcEntry.getKey())) {
          decreaseStock(alcEntry.getKey(), wareHouse, cashRegister, alcEntry.getValue());
          allIngredients = false;
          break;
        }
      }
      for (Map.Entry<Fizzies, Integer> alcEntry : cocktail.fizzies.entrySet()) {
        decreaseStock(alcEntry.getKey(), wareHouse, cashRegister, alcEntry.getValue());
        allIngredients = false;
        break;
      }
      for (Map.Entry<Ingredients, Integer> alcEntry : cocktail.ingredients.entrySet()) {
        decreaseStock(alcEntry.getKey(), wareHouse, cashRegister, alcEntry.getValue());
        allIngredients = false;
        break;
      }
      if (allIngredients) {
        for (Map.Entry<ABVDrinks, Integer> alcEntry : cocktail.aDrinks.entrySet()) {
          decreaseStock(alcEntry.getKey(), wareHouse, cashRegister, alcEntry.getValue());
        }
        for (Map.Entry<Fizzies, Integer> alcEntry : cocktail.fizzies.entrySet()) {
          decreaseStock(alcEntry.getKey(), wareHouse, cashRegister, alcEntry.getValue());
        }
        for (Map.Entry<Ingredients, Integer> alcEntry : cocktail.ingredients.entrySet()) {
          decreaseStock(alcEntry.getKey(), wareHouse, cashRegister, alcEntry.getValue());
        }
      }
    }
  }
  //Prints the stock receipt
  public void printWareHouseStock()
  {
    LocalDateTime today = LocalDateTime.now();
    List<LocalDateTime> workingDays = new ArrayList<>();
    workingDays.add(today);

    try {
      DateTimeFormatter formatFileName = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH_mm_ss_SSS");
      String formattedDateTime = today.format(formatFileName);
      String directoryName = "C:\\Club_cash_book_reports\\";
      File directory = new File(directoryName);
      if (!directory.exists()) {
        directory.mkdirs(); // create directory, a method of File class
      }
      FileWriter stockAvailabilityInTheEndOfTheDay;
      try {
        stockAvailabilityInTheEndOfTheDay = new FileWriter("C:\\Club_cash_book_reports\\" + formattedDateTime + "_quantities.txt");
      }
      catch (IOException e) {
        throw new RuntimeException(e);
      }
      stockAvailabilityInTheEndOfTheDay.write("+" + "-".repeat(43) + "+"+"\n");
      stockAvailabilityInTheEndOfTheDay.write("| Stock quantities for : "+formattedDateTime+"|\n");
      stockAvailabilityInTheEndOfTheDay.write("+" + "-".repeat(43) + "+"+"\n");
      for (Map.Entry<ABVDrinks, Integer> entry : alcohols.entrySet()) {
        String name = "| "+entry.getKey();
        String value =" /"+ entry.getValue()+" ml |";
        String spaces = " ".repeat(45 - ((name.length())+value.length()));
        stockAvailabilityInTheEndOfTheDay.write(name+spaces+value+"\n");
      }
      for (Map.Entry<Fizzies, Integer> entry : fizzies.entrySet()) {
        String name = "| "+entry.getKey();
        String value =" /"+ entry.getValue()+" ml |";
        String spaces = " ".repeat(45 - ((name.length())+value.length()));
        stockAvailabilityInTheEndOfTheDay.write(name+spaces+value+"\n");
      }
      for (Map.Entry<Ingredients, Integer>  entry : ingredients.entrySet()) {
        String name = "| "+entry.getKey();
        String value =" /"+ entry.getValue()+" ml |";
        String spaces = " ".repeat(45 - ((name.length())+value.length()));
        stockAvailabilityInTheEndOfTheDay.write(name+spaces+value+"\n");
      }
      stockAvailabilityInTheEndOfTheDay.write("+" + "-".repeat(43) + "+");
      stockAvailabilityInTheEndOfTheDay.flush();
      /*with this method e clean the buffer(a linear, finite sequence of elements
      of a specific primitive type. Aside from its content, the essential properties
      of a buffer are its capacity, limit, and position: A buffer's capacity is the number
      of elements it contains. The capacity of a buffer is never negative and never changes)
      and writes everything to the file
      */
      stockAvailabilityInTheEndOfTheDay.close();
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
  }
}
