
import java.util.Scanner;

public class EmployeeSalaryManagementSystem {

    //Arrays to hold names, ids, and salaries
    static String[] names = new String[10]; // The static here can be accessed by any methods. This is the same concepts as the global keyword for variable in Python
    static int[] ids = new int[10];
    static double[] salaries = new double[10];
    static double[] bonuses = new double[10];
    static double[] salariesAfterBonuses = new double[10];
    static int count = 0; // This is the location or index of the array

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while(!exit){
            System.out.println("---------------------------------------------------------------------");
            System.out.print("Welcome to the Menu Interface. Select your options below: " +
                    "\n---------------------------------------------------------------------" +
                    "\n add - Add Employee Details " +
                    "\n calc - Calculate Salary After Bonus" +
                    "\n rec - Display All Employee Records" +
                    "\n search id - Search Employee by ID" +
                    "\n exit - Exit the System " +
                    "\n >>>  ");
            // Have to convert the input to lowercase because our switch checks the lowercase string conditions.
            String options = scanner.nextLine().toLowerCase();

            // This is the enhanced-switch. Better than regular switch. Doesn't need to break since it has built-in breaks.
            switch (options) {
                case "add"-> AddEmployeeDetails(scanner);
                case "calc" -> CalculateSalary(scanner);
                case "rec"->EmployeeDetails(scanner);
                case "search id"-> SearchId(scanner);
                case "exit" -> {
                    System.out.println("===========================================");
                    System.out.println("Existing the system...");
                    System.out.println("===========================================");
                    exit = true;
                }
                default -> System.out.println("Invalid arguments, sir/mam");
            }
        }
        scanner.close();
    }

    public static void AddEmployeeDetails(Scanner scanner){

        if(count>=10){
            System.out.println("No more space for adding more employees!");
            return;
            // Return here is to stop the method from continuing the program belows
            // makes Java jump back to where the method was called from, which is the AddEmployeeDetails(scanner); break; at the main method.
            // Basically, breaking/stopping the entire program.
        }

        System.out.println("-----------------------------------------------------------");
        System.out.print("Please add the employee's name: ");
        String employeeName = scanner.nextLine();

        System.out.print("Please add the employee's ID: ");
        int employeeId = scanner.nextInt();

        System.out.print("Please add the employee's Salary $: ");
        double employeeSalary = scanner.nextDouble();

        names[count]=employeeName;
        ids[count]=employeeId;
        salaries[count]=employeeSalary;
        bonuses[count]=0;
        salariesAfterBonuses[count]=0;
        count++;
    }

    public static void EmployeeDetails(Scanner scanner){
        if(count==0){
            System.out.println("No employees are added yet...");
            return;
        }
        //Formatting
        System.out.println("========================= Employees Records =========================");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "ID", "Names", "Salaries", "Bonuses", "Salaries After Bonus");

        /*
        %-10s means
        - means string left-aligned
        10s = 10 space
         */

        // Printing records
        for(int i=0;i<count;i++){    // This is to iterate the array and print the entire table
            System.out.printf("%-10s %-10s %-10.2f %-10.2f %-10.2f\n",ids[i], names[i],salaries[i], bonuses[i],salariesAfterBonuses[i]);
        }
        System.out.println("=====================================================================");
    }

    public static void CalculateSalary(Scanner scanner){
        if(count==0){
            System.out.println("No employees are added yet...");
            return;
        }
        System.out.println("=============Apply Bonus=============");
        System.out.print("Enter search ID to give bonus: ");
        int bonusId = Integer.parseInt(scanner.nextLine());

        for(int z =0;z<count;z++){
            if(ids[z]==bonusId){
                System.out.println("===========================================");
                System.out.print("What is the performance of employee ID: "+ bonusId+" on a scale of 1-5: ");
                int bonusPerformanceScale = scanner.nextInt();
                scanner.nextLine(); //Clear buffer for the if statement

                double bonusPercentage = 0;
                if(bonusPerformanceScale==5){
                    bonusPercentage = 20;
                }
                else if(bonusPerformanceScale==4){
                    bonusPercentage = 15;
                }
                else if(bonusPerformanceScale==3){
                    bonusPercentage = 10;
                }
                else{
                    bonusPercentage = 0;
                }
                double oldSalary = salaries[z];
                double bonusAmountInCash = oldSalary * (bonusPercentage / 100);
                salariesAfterBonuses[z] = oldSalary + bonusAmountInCash; // Update salaries after the bonus
                bonuses[z] = bonusAmountInCash; // Update bonus

                System.out.println("Bonus Applied to Employee ID: "+bonusId+" Successfully.");
                System.out.println("Bonus Received "+bonusPercentage+"%");
                System.out.printf("Old Salary: $%.2f%n", oldSalary);
                System.out.printf("New Salary: $%.2f%n", salariesAfterBonuses[z]);
            }
        }
    }

    public static void SearchId(Scanner scanner){
        if(count==0){
            System.out.println("No employees are added yet...");
            return;
        }

        System.out.println("=============Search ID=============");
        System.out.print("Enter search ID: ");
        int searchId = Integer.parseInt(scanner.nextLine());

        System.out.println("===========================================");
        for(int j=0;j<count;j++){
            if(ids[j]==searchId){ // Adding ids[j] so that it can iterate using the number from j. Don't use the number from count array because this array mostly stores indexes of the name array
                System.out.println("<Employee found>");
                System.out.println("Name: "+names[j]);
                System.out.println("ID: "+ids[j]);
                System.out.println("Salary: $"+salaries[j]);
                System.out.println("Bonus: $"+bonuses[j]);
                return;
            }
        }
        System.out.println("Employee not found");
        System.out.println("===========================================");
    }
}

