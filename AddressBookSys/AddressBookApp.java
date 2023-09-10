import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.*;


class ClientInfo{
    public String name;
    public String phoneNumber;
    public String emailAddress;
    public String address;

    public ClientInfo(String n, String p, String e, String a) {
        name = n;
        phoneNumber = p;
        emailAddress = e;
        address = a;
    }

    //display on graphical user interface....
    void display(){
        JOptionPane.showMessageDialog(null, "Name:  " +  name  + "\nPhone no: " +  phoneNumber  + "\nEmail: " +  emailAddress  +   "\nAddress: " +  address );
    }
}

class AddressBook{
    ArrayList clients;
    
    AddressBook(){
        clients = new ArrayList();
        loadClient();
    }

    //Adding.................................................

    void addClient(){
        String name = JOptionPane.showInputDialog("Enter Name:");
        String phone = JOptionPane.showInputDialog("Enter Phone Number:");
        String email = JOptionPane.showInputDialog("Enter your Email:");
        String add = JOptionPane.showInputDialog("Enetr the address:");
       // creating a ClientInfo object........................
        ClientInfo c = new ClientInfo(name, phone, email, add);
        clients.add(c);
    }

    //Searching.......................................

    void searchClient(String n){
        for(int i = 0; i < clients.size(); i++){
            ClientInfo c = (ClientInfo) clients.get(i);
            if(n.equals(c.name)){
                c.display();
            }
        }

    }

    //Delete................................................

    void deleteClient(String n){
        for(int i = 0; i < clients.size(); i++){
            ClientInfo c = (ClientInfo) clients.get(i);
            if(n.equals(c.name)){
                clients.remove(i);
            }
        }
        
    }
    //Saving Client Record .....................................................
    void saveClientReco(){
        try{
            ClientInfo c;
            String line;
            FileWriter fw = new FileWriter("clients.txt");
            PrintWriter pw = new PrintWriter(fw);
            for(int i = 0 ; i<clients.size(); i++){
                c = (ClientInfo) clients.get(i);
                line = c.name + "," + c.phoneNumber +"," + c.emailAddress +"," +c.address;
                //write line to client.txt.....................
                pw.println(line);
            }
            pw.flush();
            pw.close();
            fw.close();

        }
        catch(IOException ioEx){
            System.out.println(ioEx);

        }

    }
    //loading client record from .txt file.......................

    void loadClient(){
        String tokens[] = null;
        String name, phone, email, add;
        try{
            FileReader fr = new FileReader("clients.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
               tokens = line.split(",");
               name = tokens[0];
               phone = tokens[1];
               email = tokens[2];
               add = tokens[3];
               ClientInfo c = new ClientInfo(name, phone, email, add);
               clients.add(c);
               line = br.readLine();

            }
            br.close();
            fr.close();

        }
        catch(IOException ioEx){
            System.out.println(ioEx);

        }



        
    }
}

public class AddressBookApp {
    public static void main(String[] args){
        AddressBook ab = new AddressBook();
        String input, s;
        int ch;
        while(true){
            input = JOptionPane.showInputDialog("Enter Any one option(1 to 4) :\n1: Add\n2: Search\n3: Delete\n4: Exit");
            ch = Integer.parseInt(input);



            switch(ch){
                case 1:
                    ab.addClient();
                    break;
                case 2:
                    s = JOptionPane.showInputDialog("Enter name to search: ");
                    ab.searchClient(s);
                    break;
                case 3:
                    s = JOptionPane.showInputDialog("Enter name to delete: ");
                    ab.deleteClient(s);
                    break;
                case 4:
                    ab.saveClientReco();
                    System.exit(0);
                    
            }
        }


    }
}

