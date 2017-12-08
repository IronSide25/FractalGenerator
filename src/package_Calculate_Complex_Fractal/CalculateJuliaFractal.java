package package_Calculate_Complex_Fractal;

import java.util.HashMap;

import application.Complex;

public class CalculateJuliaFractal implements Calculate_Complex_Fractal {

	
	@Override
	public void calculateComplexFractal(double planeArray[][], HashMap<String, Double> fractalParam)
	{
		 int centerX = planeArray.length/2;
         int centerY = planeArray[1].length/2;
         
         double moveX = fractalParam.get("moveX");
         double moveY = fractalParam.get("moveY");
         double scale = fractalParam.get("scale");
         int accuracy = fractalParam.get("accuracy").intValue();
         
         double cReal =fractalParam.get("cReal");
         double cImaginary = fractalParam.get("cImaginary");;
         
		
         for (double i = 0; i < planeArray.length; i++)
         {
             for (double j = 0; j < planeArray[1].length; j++)
             {
                 Complex n = new Complex(((j - centerX+moveX) / scale), ((i - centerY+moveY) / scale));
                 double percent = CalculateJuliaFractal.isPartOf(n,accuracy,cReal,cImaginary);              
                 planeArray[(int)i][(int)j] = percent;
                 
             }
         }
	}
	
	//-0.7269,+0.1889
		
	public static double isPartOf(Complex n, int accuracy, double cReal, double cImaginary)
	{
	    double real = n.real;
	    double imaginary = n.imaginary;
	    int iterations = 0;
	    
	    double c_real = cReal;
	    double c_imaginary = cImaginary;

	    double x = real;
	    double y = imaginary;
	    double new_x = 0;                       // or <=
	    while ((iterations < accuracy) && (x * x + y * y < 4))
	    {
	    	new_x = (x * x - y * y)+c_real;
	        y = (2 * x * y )+c_imaginary;
	        x = new_x;
	        iterations++;
	    }   

	    return ((double)iterations) / ((double)accuracy);
	}
	
	

	
	
	
}







	
	
	
	
	
	
	
	
	
	
	
	

