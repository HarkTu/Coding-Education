package inventory;

import java.math.BigDecimal;
import java.util.Map;


public final class Cocktail implements Orderable
{

  private String                    name;
  private BigDecimal                price;
  private int                       milliliters;
  public  Map<ABVDrinks, Integer>   aDrinks;
  public  Map<Fizzies, Integer>     fizzies;
  public  Map<Ingredients, Integer> ingredients;

  public Cocktail(String name, BigDecimal price, int milliliters, Map<ABVDrinks, Integer> aDrinks,
      Map<Fizzies, Integer> fizzies, Map<Ingredients, Integer> ingredients)
  {
    this.name = name;
    this.price = price;
    this.milliliters = milliliters;
    this.aDrinks = aDrinks;
    this.fizzies = fizzies;
    this.ingredients = ingredients;
  }

  public String getName()
  {
    return name;
  }

  public BigDecimal getPrice()
  {
    return price;
  }

  public int getMilliliters()
  {
    return milliliters;
  }
}
