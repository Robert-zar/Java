package calc.operation;

public class Exponentiation {
    private int exp;

    public Exponentiation()
    {
        exp = 0;
    }

    public void exp(int b)
    {
        exp = b*b;
    }

    public int getExp()
    {
        return exp;
    }

}
