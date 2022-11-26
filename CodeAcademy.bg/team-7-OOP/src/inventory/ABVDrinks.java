package inventory;


public enum ABVDrinks implements Orderable
{
  WHISKEY("Whiskey", 50),
  VODKA("Vodka", 100),
  RUM("Rum", 50),
  BOURBON("Bourbon", 100),
  BEER("Beer", 500),
  GINGER_BEER("Ginger beer", 500),
  TEQUILA("Tequila", 50),
  VERMOUTH("Vermouth", 50),
  CAMPARI("Campari", 200),
  GIN("Gin", 50),
  COGNAC("Cognac", 50),
  LIQUEUR("Liqueur", 50),
  COINTREAU("Cointreau", 100),
  WHITE_RUM("White Rum", 100),
  RED_WINE("Red wine", 100),
  WHITE_WINE("White wine", 100),
  CHAMPAGNE("Champagne", 500);

  String name;
  int    milliliters;

  ABVDrinks(String name, int milliliters)
  {
    this.name = name;
    this.milliliters = milliliters;

  }

  public String getName()
  {
    return this.name;
  }

  public int getMilliliters()
  {
    return this.milliliters;
  }
}
