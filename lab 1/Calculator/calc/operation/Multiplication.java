package calc.operation;

public class Multiplication {
    private int mult;
    public Multiplication()
    {
        mult=0;
    }
    public Multiplication(int a)
    {
        this.mult=a;
    }

    public void multiply(int b)
    {
        mult*=b;
    }
    public int getMult()
    {
        return mult;
    }
}
