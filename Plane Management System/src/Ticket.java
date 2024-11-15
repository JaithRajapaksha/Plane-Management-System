import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private char row;

    private int seat;

    private double price;

    private Person person;

    // add person

    public Ticket(char row, int seat, double price, Person person){
        this.row=row;
        this.seat=seat;
        this.price=price;
        this.person=person;

    }
    public char getRow(){
        return row;
    }
    public int getSeat(){
        return seat;
    }
    public double getPrice(){
        return price;
    }
    public Person getPerson(){
        return person;
    }

    public void setRow(char row){
        this.row=row;
    }
    public void setSeat( int seat){
        this.seat=seat;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public void setPerson(Person person){
        this.person=person;
    }

    public void Ticketinfo(){

        System.out.println("Row : "+ row);
        System.out.println("Seat : "+seat);
        System.out.println("Price : "+"£"+price);


        System.out.print("\n");
        System.out.println("Person info ");
        person.Personinfo();
        System.out.print("\n");


    }
    public void save() {
            String filename = row + "" + seat + ".txt"; //  filename based on row and seat number
            try (FileWriter writer = new FileWriter(filename)) {
                // Write ticket information to the file
                writer.write("Ticket Information \n\n");
                writer.write("Row: " + row + "\n");
                writer.write("Seat: " + seat + "\n");
                writer.write("Price: £" + price + "\n\n");
                writer.write("Person Information\n\n");
                writer.write("Name: " + person.getName() + "\n");
                writer.write("Surname: " + person.getSurname() + "\n");
                writer.write("Email: " + person.getEmail() + "\n");
                writer.close();
                System.out.println("Ticket information saved to file: " + filename);
            } catch (IOException e) {
                System.out.println("An error occurred while saving the ticket information to file: " + e.getMessage());
            }
        }
    }


