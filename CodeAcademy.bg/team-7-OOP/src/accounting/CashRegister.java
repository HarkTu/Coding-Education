package accounting;

import static java.math.BigDecimal.valueOf;

import employee.*;
import java.math.*;

public class CashRegister
{

  private final BigDecimal startMoney = new BigDecimal (1000);
  private       BigDecimal balance    = new BigDecimal(0);
  private       BigDecimal dailyWage;

  public CashRegister()
  {
    this.balance = this.balance.add(this.startMoney);
  }

  public BigDecimal getStartMoney()
  {
    return startMoney;
  }

  public BigDecimal getProfit()
  {
    return getBalance().subtract(getStartMoney().add(getDailyWage().add(getTax())));
  }

  public BigDecimal getTax()
  {
    return (getBalance().subtract(getDailyWage().add(getStartMoney()))).divide(BigDecimal.TEN, RoundingMode.HALF_UP);
  }

  //calculates the total amount for the employee daily wages
  public void setDailyWage(Waiter waiter, int waiterCount, Bartender bartender, Security security)
  {
    BigDecimal waiterSalary = waiter.getWage();

    this.dailyWage = waiterSalary
        .multiply(new BigDecimal(waiterCount))
        .add(bartender
            .getWage()
            .add(security.getWage()));
  }

  public BigDecimal getDailyWage()
  {
    return dailyWage;
  }

  public BigDecimal getIncome()
  {
    return balance.subtract(startMoney);
  }

  public BigDecimal getBalance()
  {
    return balance;
  }

  public void addMoney(BigDecimal money)
  {
    this.balance = this.balance.add(money);
  }

  public void removeMoney(BigDecimal money)
  {
    this.balance = this.balance.subtract(money);
  }

  @Override
  public String toString()
  {
    String format = String.format("+" + "-".repeat(40) + "+");
    String frame = "||";
    String spaces = " ".repeat(8);
    String spaces5 = " ".repeat(39 - (" Start balance: ".length() + String
        .valueOf(getStartMoney())
        .length() + " BGN ".length()));
    String spaces1 = " ".repeat(39 - ((" Income from sales: ".length() + String
        .valueOf(getIncome())
        .length() + " BGN ".length())));
    String spaces2 = " ".repeat(39 - ((" Total daily wages: ".length() + String
        .valueOf(getDailyWage())
        .length() + " BGN ".length())));
    String spaces3 = " ".repeat(39 - ((" Taxes: ".length() + String
        .valueOf(getTax())
        .length() + " BGN ".length())));
    String spaces4 = " ".repeat(39 - ((" Profit: ".length() + String
        .valueOf(getProfit())
        .length() + " BGN ".length())));

    return format + "\n" + frame + spaces + " Cash Register report:" + spaces + frame + "\n" + format + "\n" +
        frame + " Start balance: " + spaces5 + getStartMoney() + " BGN" + frame + "\n" +
        frame + " Income from sales: " + spaces1 + getIncome() + " BGN" + frame + "\n" +
        frame + " Total daily wages: " + spaces2 + getDailyWage() + " BGN" + frame + "\n" +
        frame + " Taxes: " + spaces3 + getTax() + " BGN" + frame + "\n" +
        frame + " Profit: " + spaces4 + getProfit() + " BGN" + frame + "\n" + format;
  }
}
