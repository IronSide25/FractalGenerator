package package_Calculate_Complex_Fractal;

import application.Complex;

public class CalculateMandelbrotFractal implements Calculate_Complex_Fractal {

	
	@Override
	public void calculateComplexFractal(double planeArray[][], int accuracy,double scale,double moveX,double moveY)
	{
		 int centerX = planeArray.length/2;
         int centerY = planeArray[1].length/2;
		
         for (double i = 0; i < planeArray.length; i++)
         {
             for (double j = 0; j < planeArray[1].length; j++)
             {
                 Complex n = new Complex(((j - centerX+moveX) / scale), ((i - centerY+moveY) / scale));
                 double percent = CalculateMandelbrotFractal.isPartOf(n,accuracy);              
                 planeArray[(int)i][(int)j] = percent;
                 
             }
         }
	}
	
	
	
	public static double isPartOf(Complex n, int accuracy)
    {
        double real = n.real;
        double imaginary = n.imaginary;
        int iterations = 0;

        double x = 0;
        double y = 0;
        double new_x = 0;                       // or <=
        while ((iterations < accuracy) && (x * x + y * y < 4))
        {
        	new_x = (x * x - y * y + real);
            y = (2 * x * y +imaginary);
            x = new_x;
            iterations++;
        }   

        return ((double)iterations) / ((double)accuracy);
    }
	
	
}


















/*public static double isPartOf(Complex n, int accuracy)
    {
        double real = n.real;
        double imaginary = n.imaginary;
        int iterations = 0;

        double x = real;
        double y = imaginary;
        double new_x = 0;                       // or <=
        while ((iterations < accuracy) && (x * x + y * y < 4))
        {
        	new_x = (x * x - y * y)-0.7269;
            y = (2 * x * y )+0.1889;
            x = new_x;
            iterations++;
        }   

        return ((double)iterations) / ((double)accuracy);
    }*/
