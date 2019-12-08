package AccountingPkg;

import java.util.ArrayList;

public class Bank 
{
    private ArrayList<Account> accs;
    public Bank() 
    {
        accs = new ArrayList<>();
    }
    
    public void addAccount(Account a) 
    {
        accs.add(a);
    }

    public void removeAccount(Account a) 
    {
        accs.remove(a);
    }

    public Account getAccount(int ID) 
    {
        for (Account acc : accs) 
        {
            if (acc.getID() == ID) 
                return acc;            
        }
        //if the loop finished and we didn't find an account with the same ID
        return null;
    }

    public void listAll() {
        System.out.println("-----------------------------------------");
        System.out.println("------------ Bank Accounts --------------");
        System.out.println("-----------------------------------------");

        //Using for each
//        for (Account acc : accs) 
//        {
//            acc.print();            
//        }

        //OR using For loop
        for (int i = 0; i < accs.size(); i++) 
        {
            Account acc = accs.get(i);
            acc.print();
        }
    }
}
