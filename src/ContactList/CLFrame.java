package ContactList;
import java.awt.*;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import javax.swing.text.AbstractDocument;

public class CLFrame extends JFrame {






    private JLabel lbl_contacts = new JLabel("Contacts");


    private ArrayList<Person> contacts = FileStuff.load();


    JList<Person> contactList = new JList<>();
    private JScrollPane scrContacts;
    private JLabel firstLabel = new JLabel("First Name:");
    private JLabel lastLabel = new JLabel("Last Name:");


    private JLabel phoneLabel = new JLabel("Phone Number:");
    private JLabel addressLabel = new JLabel("Address:");
    private JButton saveBTN = new JButton("Save");
    private JButton deleteBTN = new JButton("Delete");
    private JButton btn_clear = new JButton("Clear");
    private JTextField firstType= new JTextField();
    private JTextField lastType = new JTextField();
   private JTextField phoneType = new JTextField();
    private JTextField addressType = new JTextField();
    private JTextArea info = new JTextArea();
    private JTable list = new JTable();
    Font comic = new Font("Comic Sans MS", Font.PLAIN, 12);
    Font comicBold = new Font("Comic Sans MS", Font.BOLD, 12);


    public CLFrame(){
        super("Contact List");
        setLayout(null);
        getContentPane().setBackground(new Color(186, 213, 230));
        setResizable(false);
//        contactList.add(person);
        contactList.setFont(comic);
        info.setFont(comicBold);
        scrContacts = new JScrollPane(contactList);
        scrContacts.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrContacts.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrContacts.setBounds(20,20,270,200);

        add(scrContacts);
        setSize(600,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sortAlphabet();


        btn_clear.setBounds(20,230,80,30);
        info.setBounds(320,150, 200,70);
        info.setEditable(false);
        add(info);
        btn_clear.addActionListener(e -> clearSelection());
        btn_clear.setEnabled(false);
        deleteBTN.setEnabled(false);
        add(btn_clear);
        ((AbstractDocument) phoneType.getDocument()).setDocumentFilter(new NumberFilter(10));

        firstLabel.setFont(comicBold);
        lastLabel.setFont(comicBold);
        phoneLabel.setFont(comicBold);
        addressLabel.setFont(comicBold);
        saveBTN.setFont(comicBold);
        deleteBTN.setFont(comicBold);
        saveBTN.addActionListener(e->save());
        saveBTN.setBounds(105,230,100,30);
        deleteBTN.setBounds(210,230,80,30);
        deleteBTN.addActionListener(e-> delete());
        add(saveBTN);
        //20,40,120,200
        firstLabel.setBounds(320,20,90,20);
        lastLabel.setBounds(320, 50, 70,20);
        phoneLabel.setBounds(320,80,100,20);
        addressLabel.setBounds(320,110,70,20);
        firstType.setBounds(440,20,120,20);
        lastType.setBounds(440,50,120,20);
        phoneType.setBounds(440,80,120,20);
        addressType.setBounds(440,110,120,20);
        add(lastLabel);
        add(lastType);
        add(firstLabel);
        add(firstType);
        add(phoneLabel);
        add(deleteBTN);
        add(phoneType);
        add(addressLabel);
        add(addressType);
        contactList.addListSelectionListener(e -> selectionChange());
        contactList.setListData(contacts.toArray(new Person[0]));











        setVisible(true);


    }


    public void clearSelection(){
        contactList.clearSelection();
    }



    public void reformat(){

    }


    public void selectionChange(){
        if(contactList.getSelectedIndex()==-1){
            btn_clear.setEnabled(false);
            deleteBTN.setEnabled(false);
            firstType.setText("");
            lastType.setText("");
            phoneType.setText("");
            addressType.setText("");
            info.setText("");
        }
        else{
            btn_clear.setEnabled(true);
            deleteBTN.setEnabled(true);
            firstType.setText(contactList.getSelectedValue().firstName);
            lastType.setText(contactList.getSelectedValue().lastName);
            addressType.setText(contactList.getSelectedValue().getAddress());
            phoneType.setText(contactList.getSelectedValue().getPhoneNumber());//doublecheckk
            info.setText(firstType.getText()+" " + lastType.getText()+ "\n" + phoneType.getText() + "\n" + addressType.getText());
        }


    }
    public void delete(){
        contacts.remove(contactList.getSelectedIndex());
        contactList.setListData(contacts.toArray(new Person[0]));
        sortAlphabet();
        FileStuff.save(contacts);
    }


    public void save(){
        if(firstType.getText().isEmpty()||lastType.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "You didn't enter a first or last name! Be sure to do this before saving your contact.");
            return;
        }
        if(contactList.getSelectedIndex()==-1){
            Person person = new Person(firstType.getText(),lastType.getText(), phoneType.getText(), addressType.getText());
            contacts.add(person);
            sortAlphabet();
            contactList.setListData(contacts.toArray(new Person[0]));
        }
        else{
            Person person = contactList.getSelectedValue();
            person.setFirstName(firstType.getText());
            person.setLastName(lastType.getText());
            person.setPhoneNumber(phoneType.getText());
            person.setAddress(addressType.getText());
            sortAlphabet();
            contactList.setListData(contacts.toArray(new Person[0]));
        }
        firstType.setText("");
        lastType.setText("");
        phoneType.setText("");
        addressType.setText("");
        FileStuff.save(contacts);
    }
    public void sortAlphabet(){
       Collections.sort(contacts, Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName));
       contactList.setListData(contacts.toArray(new Person[0]));
    }



}


