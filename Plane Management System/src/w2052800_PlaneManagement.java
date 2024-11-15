
import java.util.*;

public class w2052800_PlaneManagement {
    private static Scanner scanner = new Scanner(System.in);//i can use 'scanner' variable everytime when i want to get user input

    private static Ticket[] ticketsSold = new Ticket[100]; // Fixed-size array to store tickets sold
    private static int ticketCount = 0; // Counter to keep track of the number of tickets sold

    public static void main(String[] args) {
        // Welcome message
        System.out.println("Welcome to the Plane Management application !");

        boolean user = true ;

        // Main menu loop
        while (user){
            // Display menu options
            System.out.println("\n*************************************************");
            System.out.println("\n*                 MENU OPTIONS                 *");
            System.out.println("\n*************************************************");

            System.out.println("\n1). Buy a Seat");
            System.out.println("2). Cancel a Seat");
            System.out.println("3). Find first available seat");
            System.out.println("4). Show seating plan");
            System.out.println("5). Print tickets information and total sales");
            System.out.println("6). Search ticket");
            System.out.println("0). Quit");
            System.out.println("\n*************************************************");

            //get the user input
            System.out.println();
            System.out.print("Please select an Option : ");


            int option = scanner.nextInt();

            //calling the methods  that user inputed
                if (option == 1) {
                    buy_seat();

                } else if (option == 2) {
                    cancel_seat();

                } else if (option == 3) {
                    find_first_available();

                } else if (option == 4) {
                    show_seating_plan();

                } else if (option == 5) {
                    print_tickets_info();


                } else if (option == 6) {
                    search_ticket();

                } else if(option==0){
                    System.out.println("Quit");
                    user=false;  // Exit the loop if user selects to quit
                }else {
                    System.out.println("Invalid Option number");
                }
            }
    }
    // Seating plan represented as a 2D array
    public static char[][] Array = {
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
    };

    // Method to buy a seat
    public static void buy_seat( ) {

        char row;
        int row_num;

        //user inputs
        while (true) {
            System.out.print("Input a row letter 'A-D': ");
            String rowInput = scanner.next().toUpperCase();

            row = rowInput.toUpperCase().charAt(0);
            if (row < 'A' || row > 'D') {
                System.out.println("Invalid Row letter. Try again ");
                continue;
            }
            row=rowInput.charAt(0);
            break;
        }
        // Convert row letter to corresponding array index
            if (row == 'A') {
                row_num = 0;
            } else if (row == 'B') {
                row_num = 1;
            } else if (row == 'C') {
                row_num = 2;
            } else {
                row_num = 3;
            }

        int seat;
        while (true) {
            try {
                if(row=='A'|| row=='D'){
                System.out.print("Input a seat number between 1-14: ");
                seat = scanner.nextInt();}
                else {
                    System.out.print("Input a seat number between 1-12: ");
                    seat = scanner.nextInt();
                }


                // Validate seat number based on row
                if ((row == 'A' || row == 'D') && (seat < 1 || seat > 14)) {
                    System.out.println("Invalid seat number. Try again");
                    continue;
                } else if ((row == 'B' || row == 'C') && (seat < 1 || seat > 12)) {
                    System.out.println("Invalid seat number. Try again");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid seat number.");
                scanner.next();  // Consume the invalid input to avoid an infinite loop
            }
        }
        // Book the seat if available
        if ( row== 'A' || row=='D'){
            if (seat>0 && seat<15){
                if (Array[row_num][seat - 1] == 'O') {
                Array[row_num][seat - 1] = 'X';
                System.out.println("Ticket booked");
                System.out.print("\n");

                //person informations
                System.out.print("Enter your name : ");
                String name= scanner.next();
                System.out.print("Enter your surname : ");
                String surname = scanner.next();
                System.out.print("Enter your email : ");
                String email = scanner.next();
                System.out.println();

                // Create Person and Ticket objects and store the ticket
                Person person = new Person (name,surname,email) ;
                Ticket ticket =new Ticket(row,seat,calculateTicketPrice(seat-1),person);

                ticketsSold[ticketCount] = new Ticket(row, seat, calculateTicketPrice(seat - 1), person);
                ticketCount++;
                ticketsSold[ticketCount - 1].save();

                }
                else {
                System.out.println("Already Booked ");
                }
            }else {
                System.out.println("Invalid Seat number");
            }

        } else if (row=='B' || row=='C') {
            if (seat>0 && seat<13){
                if (Array[row_num][seat - 1] == 'O') {
                    Array[row_num][seat - 1] = 'X';
                    System.out.println("Ticket booked");
                    System.out.print("\n");

                    //person informations
                    System.out.print("Enter your name : ");
                    String name= scanner.next();
                    System.out.print("Enter your surname : ");
                    String surname = scanner.next();
                    System.out.print("Enter your email : ");
                    String email = scanner.next();
                    System.out.println();

                    // Create Person and Ticket objects and store the ticket
                    Person person = new Person (name,surname,email) ;
                    Ticket ticket =new Ticket(row,seat,calculateTicketPrice(seat-1),person);

                    ticketsSold[ticketCount] = new Ticket(row, seat, calculateTicketPrice(seat - 1), person);
                    ticketCount++;
                    ticketsSold[ticketCount - 1].save();

                }
                else {
                    System.out.println("Already Booked ");
                }
            }else {
                System.out.println("Invalid Seat number");
            }
        }

    }
    // Method to cancel a seat
    public static void cancel_seat() {

        char row;
        while (true) {
            System.out.print("Input a row letter 'A-D' : ");
            String rowInput = scanner.next().toUpperCase();

            row = rowInput.charAt(0);

            if (row < 'A' || row > 'D') {
                System.out.println("Invalid Row letter ");
                continue;
            }
            break;
        }

        int letter;

        if (row == 'A') {
            letter = 0;
        } else if (row == 'B') {
            letter = 1;
        } else if (row == 'C') {
            letter = 2;
        } else {
            letter = 3;
        }
        int seat;
        while (true) {
            try {
                System.out.print("Input a seat number: ");
                seat = scanner.nextInt();

                if ((row == 'A' || row == 'D') && (seat < 1 || seat > 14)) {
                    System.out.println("Invalid seat number. Try again");
                    continue;
                } else if ((row == 'B' || row == 'C') && (seat < 1 || seat > 12)) {
                    System.out.println("Invalid seat number. Try again");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid seat number.");
                scanner.next();
            }
        }

        if (Array[letter][seat - 1] == 'X') {
            Array[letter][seat - 1] = 'O';
            System.out.println("Ticket Canceled");

            // Cancel the ticket from ticketsSold array
            for (int i = 0; i < ticketCount; i++) {
                if (ticketsSold[i] != null && ticketsSold[i].getRow() == row && ticketsSold[i].getSeat() == seat) {
                    ticketsSold[i] = null;
                    break; // Exit loop after cancelling the ticket
                }
            }
        } else {
            System.out.println("Seat is not booked");
        }
    }

    //method to display seating plan
    public static void show_seating_plan() {
        System.out.println("\nSeating plan ");
        for (int i =0 ; i<Array.length ; i++){
            System.out.println();
            for (int j=0 ; j<Array[i].length ; j++){
                System.out.print(" " + Array[i][j]);
            }
            if (i==0 || i==2){
                System.out.println();
            }
        }System.out.println();
    }

    //method to find fisrt available seat
    public static void find_first_available(){
        for (int i =0 ; i<Array.length ; i++){
            System.out.println();
            String output;
            if(i==0){
                output="A";
            } else if (i==1) {
                output="B";

            } else if (i==2) {
                output="C";
            }else {
                output="D";
            }
            for (int j=0 ; j<Array[i].length ; j++){
               if (Array[i][j]=='O'){
                   System.out.println("First Available seat is : " + output +" " + (j+1));
                   i=15;
                   break;
               }
            }
        }
    }

    //method to calculate tickets prices
    public static double calculateTicketPrice(int seat_num){

        if(seat_num <5 ){
            return 200.0;
        } else if (seat_num<9) {
            return 150.0;
        }else {
            return 180.0;
        }
    }

    //method to print tickets information
    public static void print_tickets_info() {
        double totalSales = 0;

        if (ticketCount == 0) {
            System.out.println("No tickets have been sold during this session.");
            return;
        }

        System.out.println("Tickets Sold :");
        for (int i = 0; i < ticketCount; i++) {
            Ticket ticket = ticketsSold[i];
            if (ticket != null) {
                System.out.println("Ticket " + (i + 1) );
                ticket.Ticketinfo();
                totalSales += ticket.getPrice();
            }
        }
        System.out.println("Total Sales: " + "£"+totalSales);
    }

    //method to search about tickets
    public static void search_ticket() {
        System.out.print("Input a row letter 'A-D': ");
        String rowInput = scanner.next().toUpperCase();

        // Validate row input
        if (!(rowInput.charAt(0) >= 'A' && rowInput.charAt(0) <= 'D')) {
            System.out.println("Invalid row letter.");
            return;
        }

        char row = rowInput.charAt(0);
        System.out.print("Input a seat number: ");
        int seat = scanner.nextInt();

        // Validate seat number
        if (seat < 1 || seat > 14) {
            System.out.println("Invalid seat number.");
            return;
        }
        // Check if the seat is booked
        boolean seatFound = false;
        for (int i = 0; i < ticketCount; i++) {
            Ticket ticket = ticketsSold[i];
            if (ticket != null && ticket.getRow() == row && ticket.getSeat() == seat) {
                System.out.println("Ticket found:");
                ticket.Ticketinfo();
                seatFound = true;
                break;
            }
        }
        if (!seatFound) {
            System.out.println("This seat is available.");
        }
    }
}



