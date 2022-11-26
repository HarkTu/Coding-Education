package inventory;

public enum Fizzies implements Orderable
{

  SPRITE("Sprite", 50),
  COLA("Cola", 50),
  FANTA("Fanta", 50),
  SODA("Soda", 50),
  LEMON_JUICE("Lemon juice", 50),
  ORANGE_JUICE("Orange juice", 50),
  APPLE_JUICE("Apple juice", 50),
  CRANBERRY_JUICE("Cranberry juice", 50),
  ZERO_BEER("Zero beer", 50);

  String name;
  int    milliliters;

  Fizzies(String name, int milliliters)
  {
    this.name = name;
    this.milliliters = milliliters;

  }

  public String getName()
  {
    return name;
  }

  public int getMilliliters()
  {
    return milliliters;
  }
}
