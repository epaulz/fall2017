/*
    Eric Paulz (epaulz)
    CPSC 2310-002
    Assignment 2: Binary Division
    Due Date: Wed, October 4, 11:59:59 pm
    Description: This program shows the hardware steps for dividing a
                 16-bit dividend by an 8-bit divisor.
*/

#include <iostream>
#include <string>
#include <bitset>

using namespace std;

//function prototype
void bin_division(bitset<8> &acc, bitset<8> &mq, bitset<8> &mdr,
                  bool &carry, int &step);

int main(){
    cout << "BINARY DIVISION\n\n";

    //read in user input, checking for correct usage
    int dividend, divisor;
    bool good = false;
    while(!good){
      cout << "enter dividend: ";
      cin >> dividend;
      if(dividend < 0 || dividend > 65535)
          cout << "**Number must be between 0 and 65535.\n";
      else
        good = true;
    }
    good = false;
    while(!good){
      cout << "enter divisor: ";
      cin >> divisor;
      cout << endl;
      if(divisor <= 0 || divisor > 255)
        cout<<"**Number must be between 1 and 255. (cannot divide by zero)\n";
      else
        good = true;
    }

    //split dividend into acc and mq
    bitset<16> acc_mq_bin(dividend);
    string temp = acc_mq_bin.to_string();
    string accString = "        ";
    string mqString = "        ";

    for(int i = 0; i < 8; i++){
        accString[i] = temp[i];
        mqString[i] = temp[i+8];
    }

    //create bitsets to hold each of the binary numbers we will use
    bitset<8> acc_bin(accString);
    bitset<8> const original_acc(accString);
    bitset<8> mq_bin(mqString);
    bitset<8> const original_mq(mqString);
    bitset<8> mdr_bin(divisor);
    bitset<8> const original_mdr(divisor);

    bool c = false;

    cout << "c set to " << c << endl;
    cout << "acc:mq set to dividend = " << dividend << " decimal and ";
    cout << acc_bin << mq_bin << " binary\n";
    cout << "mdr set to divisor = " << divisor << " decimal and ";
    cout << mdr_bin << " binary\n";

    if(mdr_bin.to_ulong() <= acc_bin.to_ulong()){
         cout << "\nMDR <= ACC.  Quotient overflow.\n";
        return 1;
    }
    else{
        cout << "\n(step 0: (MDR != 0) and (MDR > ACC) so no exception)\n";
    }

    //perform binary division
    int step = 1;
    bin_division(acc_bin, mq_bin, mdr_bin, c, step);

    //print the result
    cout<<"---------------------------------------------------\n\n";
    cout<<"remainder     quotient\nis in ACC     is in MQ\n";
    cout<<" "<<acc_bin<<"     "<<mq_bin<<"\n\n";
    cout<<" check:       binary         decimal\n";
    cout<<"         "<<original_acc<<original_mq<< "        "<<dividend<< endl;
    cout<<"         /       "<<original_mdr<<"     /   "<<divisor<<endl;
    cout<<"         ----------------     ------\n";
    cout<<"        "<<acc_bin<<" "<<mq_bin;
    cout<<"         "<<(dividend/divisor)<<" R "<<(dividend%divisor)<<"\n\n";

    return 0;
}

// my implementation of binary division is in this recursive function
void bin_division(bitset<8> &acc, bitset<8> &mq, bitset<8> &mdr,
                  bool &carry, int &step)
{

    if(step > 8)    //base case
        return;

    bool acc_carry = carry;
    bool mdr_carry = carry;

    //print top line (c:acc:mq)
    cout << "---------------------------------------------------\n";
    cout << "step " << step << ":   ";
    cout << acc_carry << " ";
    cout << acc << " " << mq;
    cout << "\n       <<              shift left\n";

    //perform left shift and make sure 1's carry over
    if(acc[7] == 1)
        acc_carry = 1;

    bool temp = mq[7];
    mq<<=1;
    acc<<=1;

    if(temp == 1)
        acc[0] = 1;

    //create a new bitset that combines the c:acc (for arithmetic)
    string cAccString = "         ";
    string acc_temp = acc.to_string();  //<-- converts acc bitset to a string
    cAccString.at(0) = acc_carry==false ? '0' : '1';
    for(size_t i = 1; i < cAccString.size(); i++){
        cAccString.at(i) = acc_temp.at(i-1);
    }
    bitset<9> c_acc(cAccString);

    //same as ^^^ but for c:mdr
    string cMdrString = "         ";
    string mdr_temp = mdr.to_string();
    cMdrString.at(0) = mdr_carry==false ? '0' : '1';
    for(size_t i = 1; i < cMdrString.size(); i++){
        cMdrString.at(i) = mdr_temp.at(i-1);
    }
    bitset<9> c_mdr(cMdrString);

    //print c:acc:mq after left shift
    cout << "          " << c_acc[c_acc.size()-1] << " ";
    for(int i = c_acc.size()-2; i >= 0; i--){
        cout << c_acc[i];
    }
    cout << " ";
    for(int i = mq.size()-1; i > 0; i--){
        cout << mq[i];
    }
    cout << ".\n";

    //print the mdr
    cout << "        - " << c_mdr[c_mdr.size()-1] << " ";
    for(int i = c_mdr.size()-2; i >= 0; i--){
        cout << c_mdr[i];
    }

    //perform 2's complement on mdr (flip the bits and add 1)
    c_mdr.flip();
    c_mdr = c_mdr.to_ulong() + 1;

    //print that we will add 2's complement of mdr in order for subtraction
    //to work correctly
    cout << "         (add " << c_mdr[c_mdr.size()-1] << " ";
    for(int i = c_mdr.size()-2; i >= 0; i--){
        cout << c_mdr[i];
    }
    cout << ")\n";
    cout << "          ----------\n";

    //perform addition and store the result
    unsigned long diff1 = c_acc.to_ulong() + c_mdr.to_ulong();
    bitset<9> result1(diff1);

    //print the result
    cout << "          " << result1[result1.size()-1] << " ";
    for(int i = result1.size()-2; i >= 0; i--){
        cout << result1[i];
    }

    if(result1[result1.size()-1] == 1){ // addition unsuccessful

        mq[0] = false;  //set rightmost bit of mq to 0 if result is negative
        cout << " " << mq;

        //perform 2's complement again to restore addition
        c_mdr.flip();
        c_mdr = c_mdr.to_ulong() + 1;

        cout << "\n                            ";
        cout << "^ set to 0 since subtract unsuccessful\n";
        cout << "        + " << c_mdr[c_mdr.size()-1] << " ";
        for(int i = c_mdr.size()-2; i >= 0; i--){
            cout << c_mdr[i];
        }
        cout << "          restoring add\n";
        cout << "          ----------\n";

        //perform addition again and store the result
        unsigned long diff2 = result1.to_ulong() + c_mdr.to_ulong();
        bitset<9> result2(diff2);

        //print final c:acc:mq for current step
        cout << "          " << result2[result2.size()-1] << " ";
        for(int i = result2.size()-2; i >= 0; i--){
            cout << result2[i];
        }
        cout << " " << mq << endl;

        //break c:acc apart so they can be passed to bin_division
        //again individually
        bool newCarry = result2[result2.size()-1];
        string r2_temp = result2.to_string();
        string newAccString = "        ";
        for(size_t i = 1; i < r2_temp.size(); i++){
            newAccString.at(i-1) = r2_temp.at(i);
        }
        bitset<8> newAcc(newAccString);
        acc = newAcc; //<-- keeps acc in main up-to-date (passed by reference)

        //increment step and call bin_division again (recursive)
        step++;
        bin_division(acc, mq, mdr, newCarry, step);
    }

    else{ //addition successful

        //same implementation as ^^^ except without restoring addition
        mq[0] = true;
        cout << " " << mq << endl;
        cout << "                            ";
        cout << "^ set to 1 since subtract successful\n";
        c_mdr.flip();
        c_mdr = c_mdr.to_ulong() + 1;

        bool newCarry = result1[8];
        string r1_temp = result1.to_string();
        string newAccString = "        ";
        for(int i = 1; i < 9; i++){
            newAccString.at(i-1) = r1_temp.at(i);
        }
        bitset<8> newAcc(newAccString);
        acc = newAcc;

        step++;
        bin_division(acc, mq, mdr, newCarry, step);
    }
}
