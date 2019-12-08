package AccountingPkg;

public class SavingAccount extends Account
{
    private double interestRate;

    public SavingAccount(int ID, double balance , double interestRate) {
        super(ID, balance);
        this.interestRate = interestRate;
    }

    @Override
    public String toString() 
    {
        String str = super.toString() + "interest rate = " +this.interestRate +"\n";
        return str;        
    }

    @Override
    public boolean equals(Object obj) 
    {
        if(this == obj)
            return true;
        
        if(super.equals(obj) == false)
            return false;
        
        if( !(obj instanceof SavingAccount))
            return false;
        
        if(this.interestRate == ( (SavingAccount)obj).interestRate)
            return true;
        
        return false;
    }
    
    
    
    
}
