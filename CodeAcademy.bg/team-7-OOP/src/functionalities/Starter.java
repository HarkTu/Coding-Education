package functionalities;

import static inventory.Menu.*;
import static java.lang.String.valueOf;
import static utilities.ColorUtils.*;

import accounting.CashRegister;
import employee.*;
import exceptions.NegativeQuantityException;
import functionalities.Club.Customer;
import inventory.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import utilities.FinanceUtils;

public class Starter
{

  static Random            random          = new Random();
  static ArrayList<String> takenEgnNumbers = new ArrayList<>();

  public static void start()
  {
    Club club = new Club();
    WareHouse wareHouse = new WareHouse();
    CashRegister cashRegister = new CashRegister();
    Menu.printMenu();
    Bartender bartender = new Bartender(valueOf(1), EmployeeType.BARMAN, generateEGN());
    Security security = new Security(valueOf(1), EmployeeType.SECURITY, generateEGN());

    // random waiters count btw 6-9
    int waitersCount = random.nextInt(3) + 6;
    //filling waiters list
    List<Waiter> waiters = new ArrayList<>(waitersCount);
    for (int i = 0; i < waitersCount; i++) {
      waiters.add(new Waiter(valueOf(i), EmployeeType.WAITER, generateEGN()));
    }
    System.out.printf(COLOUR_GREEN + "There is total of %d waiters." + COLOUR_RESET, waiters.size());

    // daily working waitress
    int sickWaitress = random.nextInt(2);
    int workingWaitress = waitersCount - sickWaitress;
    if (sickWaitress == 1) {
      System.out.printf(COLOUR_RED + " %d of them are sick today." + COLOUR_RESET, sickWaitress);
      int sickWaiterIndex = random.nextInt(waitersCount);
      waiters.remove(sickWaiterIndex);
    }
    System.out.println("\n");

    // calculatıng daıly workıng personel total wages
    cashRegister.setDailyWage(waiters.get(0), workingWaitress, bartender, security);

    //Waiter busyWaiter = null; // keeps working waiter busy from serving next customer

    // random customer count btw 60-80
    int numberOfCustomers = random.nextInt(20) + 60;
    Queue<Integer> customersGroups = new LinkedList<>();
    while (numberOfCustomers > 0) { // filling the queue with customers in groups for max 4 people in group
      int groupSize = random.nextInt(4) + 1;

      if (groupSize > numberOfCustomers) {
        groupSize = numberOfCustomers;
      }
      customersGroups.add(groupSize);

      numberOfCustomers -= groupSize;
    }
    int sizeOfQueue=customersGroups.size();
    while (customersGroups.size() > 0) {

      //Loop through the tables array
      int sum = 0;
      for (Integer integer : club.getTables()) {
        sum = sum + integer;
      }
      //If the sum of all the values in the array is equal to zero(0),
      // then there are no free tables at the moment.
      if (sum == 0) {
        System.out.println(
            COLOUR_RED + "All tables are taken at the moment. Will you wait?" + COLOUR_RESET);

        int groupsLeftOutside = customersGroups.size();
        for (int i = 0; i < groupsLeftOutside; i++) {

          //Random choice if the customers want to wait
          int chooseWaiting = random.nextInt(2);

          if (chooseWaiting == 1) {

            System.out.println("Ok we will wait");
            customersGroups.add(customersGroups.poll());

          }
          else {
            System.out.println("No. Have a nice evening");
            customersGroups.poll();
          }
        }
        int releaseOfTables = random.nextInt(15) + 1;

        //make tables empty
        club.setTables(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        for (int i = releaseOfTables; i < club.getTables().length; i++) {
          club.getTables()[i] = 0;
        }
      }
      if (customersGroups.size() == 0) {
        break;
      }
      int currentGroupSize = customersGroups.poll();

      boolean isThereAGrownPerson = false;
      // Loop in number of customers in current group
      for (int i = 0; i < currentGroupSize; i++) {

        Club.Customer customer = new Club.Customer(random.nextInt(2),
            random.nextInt(49) + 16); // (title, age)

        if (customer.getAge() >= 18) {
          isThereAGrownPerson = true;
        }
      }

      // checks if customer is at legal age to enter nightclub. If not doesn't let him in
      if (!isThereAGrownPerson) {

        if (currentGroupSize == 1) {
          System.err.println("You are under 18.\nYou must go home child!");
          continue;
        }
        else {
          System.err.println("You are under 18.\nYou must go home kids!");
          continue;
        }
      }

      if (currentGroupSize == 1) {
        System.out.println("One customer arrived!");
      }
      else {
        System.out.println(currentGroupSize + " customers arrived!");
      }

      club.getTableNumber();

      // Random choose which waiter to serve
      Waiter waiter;
      while (true) {
        int chooseWaiterIndex = random.nextInt(workingWaitress);
        waiter = waiters.get(chooseWaiterIndex);
        if (!waiter.isBusy()) { //!= busyWaiter) {
          if (customersGroups.size() <sizeOfQueue- 1) {
            Waiter workingTillNow = waiters
                .stream()
                .filter(o -> o.isBusy())
                .collect(Collectors.toList())
                .get(0);
            workingTillNow.setBusy(false);
          }

          break;
        }
      }

      // busyWaiter = waiter;
      // Chosen waiter is busy for next customer
      waiter.setBusy(true);
      System.out.println(
          "Waitress #" + waiter.getName() + ": 'Welcome! "
              + "What would you like to "
              + "drink?'");

      while (true) {

        int randomDrinksCount = random.nextInt(currentGroupSize) + 1;

        while (randomDrinksCount > 0) {
          // Generates random drink index
          int orderPageIndex = random.nextInt(3);
          int orderIndex = generateOrderIndex(orderPageIndex);
          Orderable drink = generateDrink(orderPageIndex, orderIndex);
          System.out.println("One " + drink.getName() + " please!");

          // Try to create the order if there is no missing component. If not success asks customer if wants to order
          // another with %50 chance
          Club.Order order;
          try {
            wareHouse.decreaseStock(drink, wareHouse, cashRegister, drink.getMilliliters());
            order = new Club.Order(drink);
            cashRegister.addMoney(order.price);
            System.out.println("One " + drink.getName() + " ordered!");
          }
          catch (NegativeQuantityException nqe) {
            System.err.println(
                nqe.getMessage() + " " + drink.getName()
                    + "\nWe are out of this product. Would you like give "
                    + "another order?");
            int choice = random.nextInt(2);
            if (choice == 0) {
              break;
            }
            continue;
          }

          // If order created successfully adding to statistics
          addOrderToStatistics(orderPageIndex, orderIndex);
          randomDrinksCount--;
        }

        // %50 chance customer to order another drink
        System.out.println("Would you like another drink?");
        int nextDrink = random.nextInt(2);
        if (nextDrink == 1) {
          continue;
        }
        System.out.println("- No thanks, just the bill, please.");
        break;
      }
      // %50 chance customer to give tip
      int tipChance = random.nextInt(2);
      if (tipChance == 1) {
        BigDecimal tip = new BigDecimal(random.nextInt(10) + 5); // Random tip 5-15 lv.
        waiter.addTips(tip);
        System.out.printf("Waiter%s got %slv. tip.\n", waiter.getName(), tip);
      }
    }
    System.out.println(COLOUR_RED + "Night Club 007 is closed!" + COLOUR_RESET);
    try {
      for (int i = 0; i < waiters.size(); i++) {
        System.out.println("Waiter" + i + " has " + waiters
            .get(i)
            .getTips() + "lv. tips.");
      }
      FinanceUtils.printDailyReport(cashRegister.toString());
      wareHouse.printWareHouseStock();
      System.out.println(COLOUR_BLUE + "Daily Reports saved" + COLOUR_RESET);
    }
    catch (Exception e) {
      System.err.println("Daily report couldn't be saved");
    }
    sortedStatistics();
    removeWorstSeller();
    Menu.printMenu();

  }

  public static void removeWorstSeller()
  {
    System.out.println(COLOUR_GREEN + "Less sold drinks removing from the menu:" + COLOUR_RESET);
    int min = 0;
    boolean found = false;
    while (true) { //dgdfhfgj
      for (int i = 0; i < statistics.size(); i++) {
        for (int j = statistics.get(i).size() - 1; j >= 0; j--) {
          if (statistics.get(i).get(j) == min) {
            found = true;
            if (i == 0) {
              ABVDrinks drink = (ABVDrinks) Menu.aDrinks
                  .keySet()
                  .toArray()[j];
              System.out.println(drink.getName() + " is removed.");
              Menu.aDrinks.remove(drink);
            }
            else if (i == 1) {
              Cocktail drink = Menu.cocktails.get(j);
              System.out.println(drink.getName() + " is removed.");
              Menu.cocktails.remove(drink);
            }
            else {
              Fizzies drink = (Fizzies) Menu.fizzies
                  .keySet()
                  .toArray()[j];
              System.out.println(drink.getName() + " is removed.");
              Menu.fizzies.remove(drink);
            }
          }
        }
      }
      if (!found) {
        min++;
      }
      else {
        break;
      }
    }

  }

  public static void sortedStatistics()
  {
    System.out.println();
    System.out.println(COLOUR_GREEN + "Most ordered drinks from the menu are:" + COLOUR_RESET);
    int maxValue = Math.max(Collections.max(statistics.get(0)), Math.max(Collections.max(statistics.get(1)),
        Collections.max(statistics.get(2))));
    int indexSt;
    if (maxValue == Collections.max(statistics.get(0))) {
      indexSt = 0;
    }
    else if (maxValue == Collections.max(statistics.get(1))) {
      indexSt = 1;
    }
    else {
      indexSt = 2;
    }
    ArrayList<Integer> indexes = new ArrayList<>();
    for (int j = 0; j < statistics
        .get(indexSt)
        .size(); j++) {
      if (statistics
          .get(indexSt)
          .get(j) == maxValue) {
        indexes.add(j);
      }
    }
    if (indexSt == 0) {
      for (Integer i : indexes) {
        ABVDrinks drink = (ABVDrinks) Menu.aDrinks
            .keySet()
            .toArray()[i];
        System.out.println(drink.getName() + " is most ordered.");

      }
    }
    else if (indexSt == 1) {
      for (Integer i : indexes) {
        Cocktail drink = Menu.cocktails.get(i);
        System.out.println(drink.getName() + " is most ordered.");
      }
    }
    else {
      for (Integer i : indexes) {
        Fizzies drink = (Fizzies) Menu.fizzies
            .keySet()
            .toArray()[i];
        System.out.println(drink.getName() + " is most ordered.");
      }
    }
    System.out.println();
  }

  private static void addOrderToStatistics(int orderPageIndex, int orderIndex)
  {
    int value = Menu.statistics
        .get(orderPageIndex)
        .get(orderIndex) + 1;
    Menu.statistics
        .get(orderPageIndex)
        .set(orderIndex, value);
  }

  private static Orderable generateDrink(int orderPageIndex, int orderIndex)
  {
    Orderable drink;
    if (orderPageIndex == 0) {
      drink = (ABVDrinks) aDrinks
          .keySet()
          .toArray()[orderIndex];
    }
    else if (orderPageIndex == 1) {
      drink = cocktails.get(orderIndex);
    }
    else {
      drink = (Fizzies) fizzies
          .keySet()
          .toArray()[orderIndex];
    }
    return drink;
  }

  private static int generateOrderIndex(int pageIndex)
  {
    int orderIndex;
    if (pageIndex == 0) {
      orderIndex = random.nextInt(Menu.aDrinks.size());
    }
    else if (pageIndex == 1) {
      orderIndex = random.nextInt(Menu.cocktails.size());
    }
    else {
      orderIndex = random.nextInt(fizzies.size());
    }
    return orderIndex;
  }

  static long generateEGN() // generates random long number
  {
    //return 1_000_000_000L + (long) (random.nextDouble() * (9_000_000_000L));
    GregorianCalendar gc = new GregorianCalendar();
    //every position in EGN number has own weight.
    Integer[] weight = {2, 4, 8, 5, 10, 9, 7, 3, 6};
    StringBuilder egn;
    while (true) {
      int year = randBetween(1960, 2003);
      gc.set(gc.YEAR, year);
      int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
      gc.set(gc.DAY_OF_YEAR, dayOfYear);

      egn = new StringBuilder();
      int bYear = gc.get(gc.YEAR);
      int bMonth = gc.get(gc.MONTH) + 1;
      int bDay = gc.get(gc.DAY_OF_MONTH);
      int region = random.nextInt(999);
      int control = 0;
      if (bYear % 100 < 10) {
        egn.append(String.valueOf("0" + (bYear % 100)));
      }
      else {
        egn.append(String.valueOf(bYear % 100));
      }
      if (bYear > 2000) {
        bMonth += 40;
        egn.append(String.valueOf(bMonth));
      }
      else {
        if (bMonth < 10) {
          egn.append("0" + String.valueOf(bMonth));
        }
        else {
          egn.append(String.valueOf(bMonth));
        }
      }
      if (bDay < 10) {
        egn.append("0" + String.valueOf(bDay));
      }
      else {
        egn.append(String.valueOf(bDay));
      }
      if (region < 10) {
        egn.append("00" + String.valueOf(region));
      }
      else if (region < 100) {
        egn.append("0" + String.valueOf(region));
      }
      else {
        egn.append(String.valueOf(region));
      }
      //calculate the last number in EGN
      for (int i = 0; i < egn.length(); i++) {
        control += Integer.parseInt(String.valueOf(egn.charAt(i))) * weight[i];
      }
      control = control % 11;

      if (control == 10) {
        egn.append(String.valueOf(0));
      }
      else {
        egn.append(String.valueOf(control));
      }
      if (takenEgnNumbers.contains(String.valueOf(egn))) {
        continue;
      }
      else {
        break;
      }
    }
    takenEgnNumbers.add(String.valueOf(egn));
    return Long.parseLong(String.valueOf(egn));
  }

  public static int randBetween(int start, int end)
  {
    return start + (int) Math.round(Math.random() * (end - start));
  }
}

