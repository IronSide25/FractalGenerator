package application;

public class Complex
{
    public double real;
    public double imaginary;

    public Complex(double real, double imaginary)
    {
        this.real = real;
        this.imaginary = imaginary;
    }
    
    public Complex exp (double module, double argument)
    {
    	return new Complex(module * Math.cos(argument),module * Math.sin(argument));
    }
}