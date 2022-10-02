package calc;
import calc.operation.Adder;
import calc.operation.Subtraction;
import calc.operation.Multiplication;
import calc.operation.Division;
import calc.operation.Exponentiation;
public class Calculator {
    public int sum(int... a)
    {
        Adder adder=new Adder();
        for(int i:a)
        {
            adder.add(i);
        }
        return adder.getSum();
    }

    public int raz(int... a)
    {
        Subtraction subtraction=new Subtraction(a[0]);
        for (int i = 1; i < a.length; ++i) {
            subtraction.subtract(a[i]);
        }
        return subtraction.getRaz();
    }

    public int mult(int... a)
    {
        Multiplication multiplication=new Multiplication(a[0]);
        for (int i = 1; i < a.length; ++i) {
            multiplication.multiply(a[i]);
        }

        return multiplication.getMult();
    }

    public int div(int... a)
    {
        Division division=new Division(a[0]);
        for (int i = 1; i < a.length; ++i) {
            division.divide(a[i]);
        }

        return division.getDiv();
    }

    public int exp(int a)
    {
        Exponentiation exponentiation=new Exponentiation();
        exponentiation.exp(a);

        return exponentiation.getExp();
    }
}