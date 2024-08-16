//Created by Brayden Distel
//Very simple Banking algorithim
//that takes names into a system where you can edit and change the amounts using the numerous methods within the program
import java.util.HashMap;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //Initializing map and variables
        //Known problems: Account names must be exact to work properly due to: HashMap hard, Money duplication if you transfer to your own acc (Fixed)
        //More issues: User is prompted twice sometimes???... :) (Fixed)
        HashMap<String, Double> Blist = new HashMap<>();
        int exit = 0;
        String person2;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to my BD's banking system!\n\n");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Prompts the user
        System.out.print("Input your First and Last name:");
        String person = scanner.nextLine();
        System.out.print("Great!\nNow input a balance:$");
        double balance = scanner.nextDouble();

        scanner.nextLine();

        while (balance < 0) {
            System.out.print("ERROR\nYou may only enter positive numbers.\nTry again:");
            balance = scanner.nextDouble();
            System.out.println(""); //Space
        }
        Blist.put(person, balance);
        printList(Blist);
        while (exit == 0) { //We stop inputting the user if they input "Exit"
            System.out.print("\nNow what would you like to do?\n(Deposit,Remove,Withdraw,GetBalance,AddAccount,ShowAccounts, Transfer| Exit)\n");
            String choice = scanner.nextLine().toLowerCase();
            switch (choice) {
                case "deposit":
                    System.out.print("What amount would you like to deposit? :$");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Which account would you like to send this Deposit to? :");
                    person = scanner.nextLine();
                    deposit(Blist, amount, person);
                    scanner.nextLine();
                    printList(Blist);
                    break;

                case "remove":
                    System.out.print("Which account would you like to remove? :");
                    person = scanner.nextLine();
                    remove(Blist, person);
                    printList(Blist);
                    break;
                case "withdraw":
                    System.out.print("What amount do you want to withdraw? :$");
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Which account would you like to withdraw from? :");
                    person = scanner.nextLine();
                    withdraw(Blist, amount, person);
                    break;
                case "getbalance", "balance":
                    System.out.print("What account would you like the balance of? :");
                    person = scanner.nextLine();
                    getBalance(Blist, person);
                    break;
                case "addaccount", "add":
                    System.out.print("What is the name of the account you would like to add? :");
                    person = scanner.nextLine();
                    System.out.print("Now what is the balance of this account? :$");
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    addPerson(Blist, person, amount);
                    break;
                case "showaccounts", "show":
                    System.out.print("Sure! Printing account list now...");
                    printList(Blist);
                    break;
                case "transfer":
                    System.out.print("What account would you like to take the money from? :");
                    person = scanner.nextLine();
                    System.out.print("Which account would you like to transfer the money into? :");
                    person2 = scanner.nextLine();
                    System.out.print("How much money do you want to send from:" + person + "-->" + person2 + "? :$");
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    transfer(Blist, person, amount, person2);
                    break;

                case "exit":
                    exit = 1;
                    System.out.println("\n\n\n\n\nGoodbye! Thanks for using us.");
                    scanner.close();
                    break;
            }
        }
    }

    public static void remove(HashMap<String, Double> Blist, String person) {
        if (Blist.containsKey(person)) {
            Blist.remove(person);
            System.out.println(person + " was removed from the database.\n");
        } else {
            System.out.println('"' + person + '"' + " was not found.\n");
        }
    }

    public static void deposit(HashMap<String, Double> Blist, double amount, String person) {
        if (amount > 0) {
            if (Blist.containsKey(person)) {    //Check if account exists
                double tempMoney = Blist.get(person);
                Blist.put(person, tempMoney + amount);
                System.out.println("\n$" + amount + " has been deposited into " + person + "'s account.\n");
            } else {
                System.out.println('"' + person + '"' + " was not found.");
            }
        } else {
            System.out.println("ERROR\nPlease enter a value greater than zero.\n"); //If user tries to deposit a negative number
        }

    }

    public static void withdraw(HashMap<String, Double> Blist, double wAmount, String person) {
        if (wAmount > 0) {
            if (Blist.containsKey(person)) {//Does account exist
                double tempMoney = Blist.get(person);
                if (tempMoney >= wAmount) {
                    double newaccountBalance = tempMoney - wAmount;
                    Blist.put(person, newaccountBalance);
                    System.out.println("\nYou have withdrawn: $" + wAmount + " and have $" + newaccountBalance + " remaining in your account\n");
                } else {
                    System.out.println("ERROR \n That amount is higher than your account total. Please pick a lower value.");
                }
            } else {
                System.out.println("ERROR\nAccount not found.");
            }
        } else {
            System.out.println("Please enter a value greater than zero.\n");
        }
    }

    public static void getBalance(HashMap<String, Double> Blist, String person) {
        if (Blist.containsKey(person)) {
            System.out.print("\n" + person + "'s balance is : $" + Blist.get(person) + "\n");
        } else {
            System.out.println('"' + person + '"' + " does not exist. Make sure that the name is written exactly as in the list.");
        }
    }

    public static void printList(HashMap<String, Double> Blist) {
        System.out.println("CURRENT ACCOUNTS:\n");
        for (String name : Blist.keySet()) {
            Double num = Blist.get(name);
            System.out.println(name + ": $" + num + "\n");
        }
        System.out.println("\n");
    }

    public static void addPerson(HashMap<String, Double> Blist, String person, Double balance) {
        if (Blist.containsKey(person) || balance < 0) {
            System.out.println("ERROR\nCould not add " + person + " to the list. Try again with different values.\n");
        } else {
            Blist.put(person, balance);
            System.out.println(person + " has been added to the system with a balance of $" + balance + ".\n");
        }
    }

    //Updating this with transfer a feature two hours later because it bothered me
    public static void transfer(HashMap<String, Double> Blist, String person, Double amount, String person2) {
        Double p1 = Blist.get(person);
        Double p2 = Blist.get(person2);

        if (!person.equals(person2)) {
            if (amount > 0 && amount <= p1) {
                if (Blist.containsKey(person) && Blist.containsKey(person2)) {// Check if the amount is less than or equal to person1's account balance
                    Blist.put(person, p1 - amount);
                    Blist.put(person2, p2 + amount);
                    System.out.println("\nThe money has been transferred successfully!");
                } else {
                    System.out.println("\nERROR\nPerson(s) not found.");
                }
            } else {
                System.out.println("\nERROR\nPlease enter a valid amount.");
            }
        } else {
            System.out.println("\nERROR\nYou cannot transfer money to the same account...");
        }
    }
}

