package utilities;

import accounting.CashRegister;
import inventory.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public final class StockUtils
{

  public static void addStock(WareHouse inventory, CashRegister cashRegister, Object o, BigDecimal price)
  {
    int quantity;
    BigDecimal priceBuy;
    if (o instanceof ABVDrinks) {
      priceBuy = price
          .divide(BigDecimal.valueOf(2), RoundingMode.HALF_DOWN);
      quantity = (500 - inventory.alcohols.get(o)) / ((ABVDrinks) o).getMilliliters();
    }
    else if (o instanceof Fizzies) {
      priceBuy = price
          .divide(BigDecimal.valueOf(2), RoundingMode.HALF_DOWN);
      quantity = (500 - inventory.fizzies.get(o)) / ((Fizzies) o).getMilliliters();
    }
    else {
      priceBuy = price;
      quantity = 50;
    }

    cashRegister.removeMoney(priceBuy.multiply(BigDecimal.valueOf(quantity)));

    inventory.increaseStock(o, quantity);
  }

}



