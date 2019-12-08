package AccountingPkg;

public abstract class Account {

    private int ID;
    private double balance;

    public int getID() {
        return ID;
    }

	public double getBalance() {
        return balance;
    }	
    

    public Account(int ID, double balance) {
        this.ID = ID;
        this.balance = balance;
    }

    @Override
    public String toString() {
        String str = "ID = " + this.ID + "\nbalance = " + this.balance + "$\n";
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        //IF THIS AND OBJ ARE REFRENCES TO THE SAME OBJECT IN MEMORY
        if (this == obj) {
            return true;
        }

        //check if this and obj are the same type
        if (!(obj instanceof Account)) {
            return false;
        }

//       Account ac = (Account)obj;
//       if( this.ID ==  ac.ID)
//           return true;
//OR Similarly
        if (this.ID == ((Account) obj).ID) {
            return true;
        }

        return false;
    }

    public void print() {
        String s = this.toString();
        System.out.println(s);
    }
}
