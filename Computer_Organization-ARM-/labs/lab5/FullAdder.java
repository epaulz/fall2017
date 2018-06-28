/*
	Eric Paulz
	CPSC 2311-003
	Lab 5
*/

public class FullAdder
{
	int sum;
	int carry;

	FullAdder()
	{
		sum = 0;
		carry = 0;
	}

	public int evaluate(int A, int B, int carryIn)
	{
	   if(A==0 && B==0 && carryIn==0){
			sum = 0;
			carry = 0;
		}
		else if(A==0 && B==0 && carryIn==1){
			sum = 1;
			carry = 0;
		}
		else if(A==0 && B==1 && carryIn==0){
			sum = 1;
			carry = 0;
		}
		else if(A==0 && B==1 && carryIn==1){
			sum = 0;
			carry = 1;
		}
		else if(A==1 && B==0 && carryIn==0){
			sum = 1;
			carry = 0;
		}
		else if(A==1 && B==0 && carryIn==1){
			sum = 0;
			carry = 1;
		}
		else if(A==1 && B==1 && carryIn==0){
			sum = 0;
			carry = 1;
		}
		else{
			sum = 1;
			carry = 1;
		}

		return sum;
	}

	public int getCarry()
	{
		return carry;
	}

	public static void main(String args[])
	{
		FullAdder testAdder = new FullAdder();

		System.out.println("Testing FullAdder Class");
		System.out.print("evaluate(0, 0, 0) returns " +
		testAdder.evaluate(0, 0, 0) );
		System.out.println(" with carry: " + testAdder.getCarry());
		System.out.println("        " +
		" Answer should be 0 with carry: 0 \n");

		System.out.print("evaluate(0, 0, 1) returns " +
		testAdder.evaluate(0, 0, 1) );
		System.out.println(" with carry: " + testAdder.getCarry());
		System.out.println("        " +
		" Answer should be 1 with carry: 0 \n");

		System.out.print("evaluate(0, 1, 0) returns " +
		testAdder.evaluate(0, 1, 0) );
		System.out.println(" with carry: " + testAdder.getCarry());
		System.out.println("        " +
		" Answer should be 1 with carry: 0 \n");

		System.out.print("evaluate(0, 1, 1) returns " +
		testAdder.evaluate(0, 1, 1) );
		System.out.println(" with carry: " + testAdder.getCarry());
		System.out.println("        " +
		" Answer should be 0 with carry: 1 \n");

		System.out.print("evaluate(1, 0, 0) returns " +
		testAdder.evaluate(1, 0, 0) );
		System.out.println(" with carry: " + testAdder.getCarry());
		System.out.println("        " +
		" Answer should be 1 with carry: 0 \n");

		System.out.print("evaluate(1, 0, 1) returns " +
		testAdder.evaluate(1, 0, 1) );
		System.out.println(" with carry: " + testAdder.getCarry());
		System.out.println("        " +
		" Answer should be 0 with carry: 1 \n");

		System.out.print("evaluate(1, 1, 0) returns " +
		testAdder.evaluate(1, 1, 0) );
		System.out.println(" with carry: " + testAdder.getCarry() );
		System.out.println("        " +
		" Answer should be 0 with carry: 1 \n");

		System.out.print("evaluate(1, 1, 1) returns " +
		testAdder.evaluate(1, 1, 1) );
		System.out.println(" with carry: " + testAdder.getCarry());
		System.out.println("        " +
		" Answer should be 1 with carry: 1 \n");

		System.out.println("Done Testing FullAdder Class");
	}
}
