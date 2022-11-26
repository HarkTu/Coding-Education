package employee;

public enum EmployeeType
{
  WAITER(110),
  BARMAN(150),
  SECURITY(120);
  final int percentWage;

  EmployeeType(int percent)
  {
    this.percentWage = percent;
  }

}
