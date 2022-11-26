package functionalities;

import static utilities.ColorUtils.COLOUR_RESET;
import static utilities.ColorUtils.COLOUR_YELLOW;

import inventory.*;
import java.math.BigDecimal;
import java.util.*;

public class Club
{
  private int[] tables = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

  public Club()
  {
  }

  public int[] getTables()
  {
    return tables;
  }

  public void setTables(int[] tables)
  {
    this.tables = tables;
  }

  public void getTableNumber()
  {
    Random random = new Random();
    int table = 0;
    for (;;) {
      //Gets a random table number
      table = random.nextInt(15);
//Checks if it is a zero in the tables array. If it is the table is taken
      if (tables[table] == 0) {
        continue;
      }
//If it is not a zero, we give the customer the table,then we change that value to zero. Zero = Taken
      if (tables[table] != 0) {
        System.out.println(COLOUR_YELLOW + "You can sit at table #" + tables[table] + COLOUR_RESET);
        tables[table] = 0;
        break;
      }
    }
  }


  public static class Customer
  {

    private String title;
    private int    age;

    //Randomise the gender of the customer
    public Customer(int title, int age)
    {
      this.age = age;
      if (title == 1) {
        this.title = "Miss";
      }
      else {
        this.title = "Sir";
      }
    }

    //Checks if the Customer is of the appropriate age
    public boolean isEligibleToDrink(int age)
    {
      return age > 17;
    }

    public String getTitle()
    {
      return title;
    }

    public int getAge()
    {
      return age;
    }
  }

  public static class Order
  {

    Orderable  drink;
    BigDecimal price;
    private List<String> orders = new ArrayList<>();

    public Order(Orderable drink)
    {
      this.drink = drink;
      if (drink instanceof ABVDrinks) {
        this.price = Menu.aDrinks.get(drink);
      }
      else if (drink instanceof Fizzies) {
        this.price = Menu.fizzies.get(drink);
      }
      else {
        this.price = ((Cocktail) drink).getPrice();
      }
      this.orders.add(drink.getName());
    }
  }
}
