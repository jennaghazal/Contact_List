package ContactList;

public class Person {

    String firstName,lastName,phoneNumber,address;
    public Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        phoneNumber = null;
        address = null;
    }
    public Person(String firstName, String lastName, String phoneNumber, String address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }


    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber(){
        if(phoneNumber==null){
            return "";
        }
        return phoneNumber;
    }

    public void setAddress(String address){

        this.address = address;
    }

    public String getAddress(){
        if(address == null){
        return "";
    }
        return address;
    }
    @Override
    public String toString(){
        return lastName +", " + firstName;
    }
}
