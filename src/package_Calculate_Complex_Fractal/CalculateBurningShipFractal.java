package package_Calculate_Complex_Fractal;

import java.util.HashMap;

import application.Complex;

public class CalculateBurningShipFractal implements Calculate_Complex_Fractal 
{

	
	@Override
	public void calculateComplexFractal(double planeArray[][], HashMap<String, Double> fractalParam)
	{
		 int centerX = planeArray.length/2;
         int centerY = planeArray[1].length/2;
         
         double moveX = fractalParam.get("moveX");
         double moveY = fractalParam.get("moveY");
         double scale = fractalParam.get("scale");
         int accuracy = fractalParam.get("accuracy").intValue();
		
         for (double i = 0; i < planeArray.length; i++)
         {
             for (double j = 0; j < planeArray[1].length; j++)
             {
                 Complex n = new Complex(((j - centerX+moveX) / scale), ((i - centerY+moveY) / scale));
                 double percent = CalculateBurningShipFractal.isPartOf(n,accuracy);              
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
            new_x =Math.abs(x * x - y * y + real);
            y = Math.abs(2 * x * y + imaginary);
            x = new_x;
            iterations++;
        }

        return ((double)iterations) / ((double)accuracy);
    }
	
	
	
}
