package exceptions;

import static utilities.ColorUtils.*;

public class NegativeQuantityException extends Exception
{

  @Override
  public String getMessage()
  {
    return COLOUR_RED + "Negative quantity! Out of stock!" + COLOUR_RESET;
  }
}
