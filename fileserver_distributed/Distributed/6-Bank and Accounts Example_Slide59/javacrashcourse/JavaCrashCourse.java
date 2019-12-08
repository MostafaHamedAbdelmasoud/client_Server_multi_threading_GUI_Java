package javacrashcourse;
//Must Import the Accounting Package in order to use it
import AccountingPkg.*;




public class JavaCrashCourse {

    public static void main(String[] args) 
    {        
        Bank b = new  Bank();
        //Add some accounts to the bank
        //You could ask user to enter the account details here
        b.addAccount(new SavingAccount(123, 3000, 0.1));
        b.addAccount(new ChekingAccount(124, 3445000, 10));
        b.addAccount(new SavingAccount(125, 5000, 0.15));
       
        //show all
        b.listAll();
        //remove a certain account
        b.removeAccount(new ChekingAccount(124, 5000, 10));
       
        //list all to check 
        b.listAll();
        
        //search for an account
        Account a = b.getAccount(123);
        
        if(a != null)//if not found it will return a null
        {
            a.print();
        }
        
    }
}
