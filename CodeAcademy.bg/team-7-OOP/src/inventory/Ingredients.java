package inventory;

public enum Ingredients
{
  SALT(5),
  SUGAR(5),
  MILK(5),
  ICE(5),
  LIME_JUICE(5),
  OLIVE(5),
  MINT_LEAVES(5),
  BITTERS(5),
  FRUITS(5),
  COFFEE(5),
  TOMATO_JUICE(5);

  int milligrams;

  Ingredients(int milligrams)
  {
    this.milligrams = milligrams;
  }

  public int getMilligrams()
  {
    return milligrams;
  }

}
