package inventory;

import static utilities.ColorUtils.*;

import java.math.*;
import java.util.*;

public class Menu
{

  //A map with all ABVDrinks and their prices
  public static LinkedHashMap<ABVDrinks, BigDecimal> aDrinks = fillADrinks();
  public static LinkedHashMap<Fizzies, BigDecimal>   fizzies =fillFizzies();

  public static LinkedHashMap<ABVDrinks, BigDecimal> fillADrinks()
  {
    aDrinks = new LinkedHashMap<>();
    aDrinks.put(ABVDrinks.WHISKEY, new BigDecimal(40));
    aDrinks.put(ABVDrinks.VODKA, new BigDecimal(30));
    aDrinks.put(ABVDrinks.COINTREAU, new BigDecimal(30));
    aDrinks.put(ABVDrinks.RUM, new BigDecimal(35));
    aDrinks.put(ABVDrinks.WHITE_RUM, new BigDecimal(30));
    aDrinks.put(ABVDrinks.BOURBON, new BigDecimal(40));
    aDrinks.put(ABVDrinks.BEER, new BigDecimal(10));
    aDrinks.put(ABVDrinks.GINGER_BEER, new BigDecimal(15));
    aDrinks.put(ABVDrinks.TEQUILA, new BigDecimal(30));
    aDrinks.put(ABVDrinks.VERMOUTH, new BigDecimal(30));
    aDrinks.put(ABVDrinks.CAMPARI, new BigDecimal(20));
    aDrinks.put(ABVDrinks.GIN, new BigDecimal(40));
    aDrinks.put(ABVDrinks.COGNAC, new BigDecimal(35));
    aDrinks.put(ABVDrinks.LIQUEUR, new BigDecimal(20));
    aDrinks.put(ABVDrinks.CHAMPAGNE, new BigDecimal(60));
    aDrinks.put(ABVDrinks.RED_WINE, new BigDecimal(25));
    aDrinks.put(ABVDrinks.WHITE_WINE, new BigDecimal(25));
    return aDrinks;
  }

  //A map with all Fizzy drinks and their prices
  public static LinkedHashMap<Fizzies, BigDecimal> fillFizzies()
  {
    fizzies = new LinkedHashMap<>();

    fizzies.put(Fizzies.SPRITE, new BigDecimal(15));
    fizzies.put(Fizzies.COLA, new BigDecimal(15));
    fizzies.put(Fizzies.FANTA, new BigDecimal(15));
    fizzies.put(Fizzies.SODA, new BigDecimal(10));
    fizzies.put(Fizzies.LEMON_JUICE, new BigDecimal(20));
    fizzies.put(Fizzies.ORANGE_JUICE, new BigDecimal(20));
    fizzies.put(Fizzies.APPLE_JUICE, new BigDecimal(20));
    fizzies.put(Fizzies.CRANBERRY_JUICE, new BigDecimal(20));
    fizzies.put(Fizzies.ZERO_BEER, new BigDecimal(15));
    return fizzies;
  }
//A list with all the cocktails and their prices

  public static List<Cocktail> cocktails = new ArrayList<>(Arrays.asList(
      new Cocktail("Margarita", new BigDecimal(40), 90,
          new LinkedHashMap<>()
          {{
            put(ABVDrinks.TEQUILA, 60);
            put(ABVDrinks.COINTREAU, 30);
          }},
          new LinkedHashMap<>(),
          new LinkedHashMap<>()
          {{
            put(Ingredients.FRUITS, 1);
            put(Ingredients.LIME_JUICE, 1);
            put(Ingredients.SALT, 1);
            put(Ingredients.ICE, 1);
          }}
      ),
      new Cocktail("Martini", new BigDecimal(40), 90,
          new LinkedHashMap<>()
          {{
            put(ABVDrinks.GIN, 60);
            put(ABVDrinks.VERMOUTH, 30);
          }},
          new LinkedHashMap<>(),
          new LinkedHashMap<>()
          {{
            put(Ingredients.FRUITS, 1);
            put(Ingredients.OLIVE, 1);
          }}
      ),
      new Cocktail("Cosmopolitan", new BigDecimal(45), 100,
          new LinkedHashMap<>()
          {{
            put(ABVDrinks.VODKA, 60);
            put(ABVDrinks.COINTREAU, 30);
          }},
          new LinkedHashMap<>()
          {{
            put(Fizzies.CRANBERRY_JUICE, 1);
            put(Fizzies.SPRITE, 1);
          }},
          new LinkedHashMap<>()
          {{
            put(Ingredients.FRUITS, 1);
            put(Ingredients.MILK, 1);
          }}
      ),
      new Cocktail("Mojito", new BigDecimal(45), 60,
          new LinkedHashMap<>()
          {{
            put(ABVDrinks.RUM, 60);
          }},
          new LinkedHashMap<>()
          {{
            put(Fizzies.SODA, 10);
          }},
          new LinkedHashMap<>()
          {{
            put(Ingredients.FRUITS, 1);
            put(Ingredients.ICE, 1);
            put(Ingredients.MINT_LEAVES, 1);
            put(Ingredients.SUGAR, 1);
            put(Ingredients.LIME_JUICE, 1);
          }}
      ),
      new Cocktail("Whiskey Sour", new BigDecimal(45), 60,
          new LinkedHashMap<>()
          {{
            put(ABVDrinks.BOURBON, 60);
          }},
          new LinkedHashMap<>()
          {{
            put(Fizzies.LEMON_JUICE, 10);
          }},
          new LinkedHashMap<>()
          {{
            put(Ingredients.FRUITS, 1);
            put(Ingredients.MILK, 1);
            put(Ingredients.SUGAR, 1);
          }}
      ),
      new Cocktail("Old Fashioned", new BigDecimal(40), 60,
          new LinkedHashMap<>()
          {{
            put(ABVDrinks.BOURBON, 60);
          }},
          new LinkedHashMap<>(),
          new LinkedHashMap<>()
          {{
            put(Ingredients.SUGAR, 1);
            put(Ingredients.BITTERS, 2);
            put(Ingredients.FRUITS, 1);
            put(Ingredients.ICE, 3);
          }}
      ),
      new Cocktail("Moscow Mule", new BigDecimal(40), 150,
          new LinkedHashMap<>()
          {{
            put(ABVDrinks.VODKA, 60);
            put(ABVDrinks.GINGER_BEER, 90);
          }},
          new LinkedHashMap<>(),
          new LinkedHashMap<>()
          {{
            put(Ingredients.FRUITS, 1);
            put(Ingredients.LIME_JUICE, 1);
            put(Ingredients.ICE, 1);
          }}
      ),
      new Cocktail("Negroni", new BigDecimal(40), 90,
          new LinkedHashMap<>()
          {{

            put(ABVDrinks.GIN, 30);

            put(ABVDrinks.CAMPARI, 30);

            put(ABVDrinks.VERMOUTH, 30);
          }},
          new LinkedHashMap<>(),
          new LinkedHashMap<>()
          {{

            put(Ingredients.FRUITS, 1);
          }}
      ),
      new Cocktail("Daiquiri", new BigDecimal(45), 15,
          new LinkedHashMap<>()
          {{

            put(ABVDrinks.WHITE_RUM, 15);
          }},
          new LinkedHashMap<>(),
          new LinkedHashMap<>()
          {{

            put(Ingredients.FRUITS, 1);

            put(Ingredients.ICE, 3);

            put(Ingredients.LIME_JUICE, 2);

            put(Ingredients.SUGAR, 2);
          }}
      ),
      new Cocktail("Mai Tai", new BigDecimal(55), 75,
          new LinkedHashMap<>()
          {{

            put(ABVDrinks.RUM, 30);

            put(ABVDrinks.WHITE_RUM, 30);

            put(ABVDrinks.COINTREAU, 15);
          }},
          new LinkedHashMap<>()
          {{

            put(Fizzies.ORANGE_JUICE, 1);
          }},
          new LinkedHashMap<>()
          {{

            put(Ingredients.FRUITS, 1);

            put(Ingredients.SUGAR, 1);

            put(Ingredients.MILK, 1);

            put(Ingredients.LIME_JUICE, 1);

            put(Ingredients.ICE, 1);
          }}
      ),
      new Cocktail("Bloody Mary", new BigDecimal(45), 60,
          new LinkedHashMap<>()
          {{

            put(ABVDrinks.VODKA, 60);
          }},
          new LinkedHashMap<>(),
          new LinkedHashMap<>()
          {{

            put(Ingredients.TOMATO_JUICE, 1);

            put(Ingredients.MILK, 1);

            put(Ingredients.MINT_LEAVES, 1);

            put(Ingredients.LIME_JUICE, 1);

            put(Ingredients.ICE, 1);

            put(Ingredients.OLIVE, 1);

            put(Ingredients.FRUITS, 1);
          }}
      ),
      new Cocktail("Manhattan", new BigDecimal(45), 90,
          new LinkedHashMap<>()
          {{

            put(ABVDrinks.WHISKEY, 60);

            put(ABVDrinks.VERMOUTH, 30);
          }},
          new LinkedHashMap<>(),
          new LinkedHashMap<>()
          {{

            put(Ingredients.FRUITS, 1);

            put(Ingredients.BITTERS, 1);

            put(Ingredients.ICE, 1);
          }}
      ),
      new Cocktail("Pina Colada", new BigDecimal(40), 60,
          new LinkedHashMap<>()
          {{

            put(ABVDrinks.RUM, 60);
          }},
          new LinkedHashMap<>(),
          new LinkedHashMap<>()

          {
            {
              put(Ingredients.FRUITS, 5);
              put(Ingredients.MILK, 5);
            }
          })));

  // creating List of lists with counters for each product
  public static List<List<Integer>> statistics = new ArrayList<>(Arrays.asList(
      new ArrayList<>(Collections.nCopies(aDrinks.size(), 0)),
      new ArrayList<>(Collections.nCopies(cocktails.size(), 0)),
      new ArrayList<>(Collections.nCopies(fizzies.size(), 0))
  ));

  //prints the Menu
  public static void printMenu()
  {
    System.out.printf("%-14sWelcome to Night Club 007!%s\n", COLOUR_CYAN, COLOUR_RESET);
    System.out.println(COLOUR_YELLOW + "+" + "-".repeat(40) + "+" + COLOUR_RESET);
    System.out.printf("%s||%-12sOur alcohol drinks are:%12s||%s\n", COLOUR_YELLOW, COLOUR_RESET,
        COLOUR_YELLOW, COLOUR_RESET);
    System.out.println(COLOUR_YELLOW + "+" + "-".repeat(40) + "+" + COLOUR_RESET);
    int index = 0;

    for (Map.Entry<ABVDrinks, BigDecimal> entry : aDrinks.entrySet()) {
      index++;
      String printName = String.format(COLOUR_BLACK + "%02d" + ". " + entry
          .getKey()
          .getName() + COLOUR_RESET, index);
      String printPrice = COLOUR_BLACK + entry.getValue() + " BGN" + " / " + COLOUR_BLACK + entry
          .getKey()
          .getMilliliters() + " ml" + COLOUR_RESET;
      String spaces = " ".repeat(61 - (printName.length() + printPrice.length()));
      String frame = COLOUR_YELLOW + "||" + COLOUR_RESET;
      System.out.println(
          frame + BACKGROUND_WHITE + printName + BACKGROUND_WHITE + spaces + BACKGROUND_WHITE + printPrice + frame);
    }
    System.out.println(COLOUR_YELLOW + "+" + "-".repeat(40) + "+" + COLOUR_RESET);
    System.out.printf("%s||%-13sOur cocktails are:%16s||%s\n", COLOUR_YELLOW, COLOUR_RESET,
        COLOUR_YELLOW, COLOUR_RESET);
    System.out.println(COLOUR_YELLOW + "+" + "-".repeat(40) + "+" + COLOUR_RESET);
    int index2 = 0;

    for (Cocktail cocktail : cocktails) {
      index2++;
      String frame = COLOUR_YELLOW + "||" + COLOUR_RESET;
      String cocktailName = String.format(COLOUR_BLACK + "%02d" + ". " + cocktail.getName() + " " + COLOUR_RESET,
          index2);
      String cocktailPrice =
          COLOUR_BLACK + cocktail.getPrice() + " BGN / " + cocktail.getMilliliters() + " ml" + COLOUR_RESET;
      String spaces = " ".repeat(56 - (cocktailName.length() + cocktailPrice.length()));
      System.out.println(
          frame + BACKGROUND_PURPLE + cocktailName + BACKGROUND_PURPLE + spaces + BACKGROUND_PURPLE + cocktailPrice
              + frame);
    }
    System.out.println(COLOUR_YELLOW + "+" + "-".repeat(40) + "+" + COLOUR_RESET);
    System.out.printf("%s||%-13sOur fizzy drinks are:%13s||%s\n", COLOUR_YELLOW, COLOUR_RESET,
        COLOUR_YELLOW, COLOUR_RESET);
    System.out.println(COLOUR_YELLOW + "+" + "-".repeat(40) + "+" + COLOUR_RESET);
    int index3 = 0;

    for (Map.Entry<Fizzies, BigDecimal> entry : fizzies.entrySet()) {
      index3++;
      String printName = String.format(COLOUR_BLACK + "%02d" + ". " + entry
          .getKey()
          .getName() + COLOUR_RESET, index3);
      String printPrice = COLOUR_BLACK + entry.getValue() + " BGN" + " / " + COLOUR_BLACK + entry
          .getKey()
          .getMilliliters() + " ml" + COLOUR_RESET;
      String spaces = " ".repeat(61 - (printName.length() + printPrice.length()));
      String frame = COLOUR_YELLOW + "||" + COLOUR_RESET;
      System.out.println(
          frame + BACKGROUND_GREEN + printName + BACKGROUND_GREEN + spaces + BACKGROUND_GREEN + printPrice + frame);
    }

    System.out.println(COLOUR_YELLOW + "+" + "-".repeat(40) + "+" + COLOUR_RESET);
    System.out.printf("%s||%-14sEnjoy your drink!!!%14s||%s\n", COLOUR_YELLOW, COLOUR_RESET,
        COLOUR_YELLOW, COLOUR_RESET);
    System.out.println(COLOUR_YELLOW + "+" + "-".repeat(40) + "+" + COLOUR_RESET);

  }
}

