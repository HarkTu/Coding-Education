package employee;


import java.math.*;

public abstract class Employee
{

  private static BigDecimal   minWage = new BigDecimal(120);
  private        String       name;
  private        EmployeeType employeeType;
  private        BigDecimal   wage;
  private        long         egn;

  public Employee(String name, EmployeeType employeeType, long egn)
  {
    this.name = name;
    this.setEmployeeType(employeeType);
    this.setEgn(egn);
    this.setWage(employeeType.percentWage);
  }

  public String getName()
  {
    return name;
  }

  public void setEmployeeType(EmployeeType employeeType)
  {
    this.employeeType = employeeType;
  }

  public BigDecimal getWage()
  {
    return this.wage;
  }

  public void setWage(int bonus)
  {
    BigDecimal wage = minWage.multiply(new BigDecimal(bonus / 100d));
    this.wage = wage.setScale(2, RoundingMode.FLOOR);
  }

  public void setEgn(long egn)
  {
    this.egn = egn;
  }

  @Override
  public String toString()
  {
    return "Employee " + employeeType
        .toString()
        .toLowerCase() +
        ", with daily wage: " + wage +
        ", and unique personal number: " + egn;
  }
}



