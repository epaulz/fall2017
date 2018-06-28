/* Eric Paulz
 * CPSC 2151-003
 * Lab 2
 */

package cpsc2150.Lab2;

/**
 * Created by epaulz on 9/5/2017.
 */
public class BankAccount {

    private String accHolderName, accNumber;
    private double balance;

    BankAccount(String name, String number, double startingBalance){
        accHolderName = name;
        accNumber = number;
        balance = startingBalance;
    }

    /**
     * @param dep amount the account holder wants to deposit
     * @requires
     * dep > 0
     * @ensures
     * [no redundant deposits ($0) or negative deposits]
     */
    public void deposit(double dep){
        if(dep <= 0)
            System.out.println("Please enter a positive amount to be deposited.");
        else
            balance += dep;
    }

    /**
     * @param wth amount the account holder wants to withdraw
     * @requires
     * 0 < wth < balance
     * @ensures
     * [there are no negative withdrawals and account holder will no overdraft]
     */
    public void withdraw(double wth){
        if(wth >= balance)
            System.out.println("You cannot withdraw more money than you have.");
        else if(wth <= 0)
            System.out.println("Please enter a positive amount to be withdrawn.");
        else
            balance -= wth;
    }

    /**
     * @param obj other object that we are comparing to current one
     * @return whether or not the two objects are equal
     * @requires
     * the argument is an object
     * @ensures
     * the function will work correctly
     */
    public boolean equals(Object obj){
        if(obj instanceof BankAccount) {
            BankAccount acc = (BankAccount) obj;
            if(acc.accHolderName.equals(this.accHolderName) && acc.accNumber.equals(this.accNumber))
                return true;
            else
                return false;
        }
        else {
            return false;
        }
    }

    /**
     * @return string to output
     */
    public String toString(){
        String output = "Account holder name: " + accHolderName +
                        "\nAccount number: " + accNumber +
                        "\nBalance: $" + balance + "\n";
        return output;
    }
}