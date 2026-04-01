package ContactList;
import java.util.*;
import java.io.*;

public class FileStuff {
//    ArrayList<Person>
public static void save(ArrayList<Person> contacts){
    try{
        PrintWriter pw = new PrintWriter(new FileWriter("save.txt"));

        for(Person a : contacts){
            pw.println(a.getFirstName() + "," + a.getLastName() + "," +a.getPhoneNumber()+","+a.getAddress());
        }

        pw.close();
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
    public static ArrayList<Person> load(){
        ArrayList<Person> contacts = new ArrayList<>();

        try{
            File file = new File("save.txt");

            if(!file.exists()){
                return contacts;
            }
            else{
                Scanner input = new Scanner(file);
                while(input.hasNextLine()){
                    String line = input.nextLine();
                    String[] info = line.split(",");
                    String firstName = info[0];
                    String lastName = info [1];
                    if(info.length>=4){
                        String phone = info[2];
                        String address = info[3];
                        contacts.add(new Person(firstName, lastName, phone, address));
                    }
                    else{
                        contacts.add(new Person(firstName, lastName));
                    }

                }
                input.close();
            }

        } catch (Exception e) {
           e.printStackTrace();
        }

        return contacts;
    }
}
