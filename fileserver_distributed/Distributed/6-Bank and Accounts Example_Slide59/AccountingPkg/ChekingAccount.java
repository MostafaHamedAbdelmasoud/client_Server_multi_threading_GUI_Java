package AccountingPkg;

public class ChekingAccount extends Account
{
    private double transactionFees;

    public ChekingAccount( int ID, double balance,double transactionFees) 
    {
        super(ID, balance);
        this.transactionFees = transactionFees;
    }

    @Override
    public String toString() 
    {
        String str = super.toString() + "Transaction Fees = "
                + this.transactionFees + "\n";
        
        return str;
        
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj)
            return true;
        
        if(!super.equals(obj))
            return false;
        
        if(!(obj instanceof ChekingAccount))
            return false;
        
        if(this.transactionFees == ((ChekingAccount)obj).transactionFees)
            return true;
        
        return false;
    }
    
    
    
    
    
    
}
