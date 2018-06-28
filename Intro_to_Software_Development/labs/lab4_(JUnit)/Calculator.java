/*
	Eric Paulz - epaulz
	CPSC 2151-03
	Lab 4
 */

package cpsc2150.lab4;

/**
 * Created by epaulz on 9/19/2017.
 */
public class Calculator {
    /**
     * @param x first double
     * @param y second double
     * @return x + y
     * @requires
     * [ x and y are of type double ]
     * @ensures
     * [ addition will be performed accurately ]
     */
    public double add(double x, double y)
    {
        return x + y;
    }

    /**
     * @param x first double
     * @param y second double
     * @return x * y
     * @requires
     * [ x and y are of type double ]
     * @ensures
     * [ multiplication will be performed accurately ]
     */
    public double mult(double x, double y)
    {
        return x * y;
    }

    /**
     * @param x first double
     * @param y second double
     * @return x - y
     * @requires
     * [ x and y are of type double ]
     * @ensures
     * [ subtraction will be performed accurately ]
     */
    public double subtract(double x, double y)
    {
        return x - y;
    }

    /**
     * @param x first double
     * @param y second double
     * @return x / y
     * @requires
     * [ x and y are of type double ] and y != 0
     * @ensures
     * [ division will be performed correctly ] and [ no division by zero]
     */
    public double divide(double x, double y)
    {
        return x / y;
    }
}
