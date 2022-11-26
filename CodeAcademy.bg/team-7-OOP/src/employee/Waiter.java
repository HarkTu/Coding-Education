package employee;

import java.math.BigDecimal;
import java.util.*;

public class Waiter extends Employee
{

  private BigDecimal   tips = BigDecimal.ZERO;
  private boolean isBusy;
  private List<String> orders;

  public boolean isBusy()
  {
    return isBusy;
  }

  public void setBusy(boolean busy)
  {
    isBusy = busy;
  }

  public Waiter(String name, EmployeeType employeeType, long egn)
  {
    super(name, employeeType, egn);
    orders = new ArrayList<>();
    isBusy=false;
  }

  public BigDecimal getTips()
  {
    return this.tips;
  }

  public void addTips(BigDecimal tips)
  {
    this.tips = this.tips.add(tips);
  }


  @Override
  public String toString()
  {
    return super.toString() + ". Get tips: " + getTips() + "BGN";
  }
}
