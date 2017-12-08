package application;
import java.util.HashMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import package_Calculate_Complex_Fractal.*;
import package_Paint_Complex_Fractal.*;

public class ComplexFractal {

	private HashMap<String, Double> fractalParam = new HashMap<String, Double>();	
	private Calculate_Complex_Fractal calc;
	private Paint_Complex_Fractal paint;	
	private double planeArray[][];	
	
	public ComplexFractal(HashMap<String, Double> fractalParam)
	{
		this.fractalParam = fractalParam;

		int width = fractalParam.get("width").intValue();
		int height =fractalParam.get("height").intValue();		
		planeArray = new double[width][height];
		paint = new ColorPalette1();
	}
	
	public void calcFractal()
	{
		calc.calculateComplexFractal(planeArray,fractalParam);
	}
	
	public double getPixelPercent(int i,int j)
	{
		return this.planeArray[i][j];
	}
	
	public Image paintFractal(GraphicsContext gc)
	{
		return paint.paintComplexFractal(planeArray, gc);
	}
	
	public void setJuliafractal()
	{
		this.calc = new CalculateJuliaFractal();
	}
	
	public void setMandelbrotfractal()
	{
		this.calc = new CalculateMandelbrotFractal();
	}
	
	public void setBurningShipfractal()
	{
		this.calc = new CalculateBurningShipFractal();
	}
	
	
}
