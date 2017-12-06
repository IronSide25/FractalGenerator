package application;
import java.util.HashMap;

import javafx.scene.image.PixelWriter;
import package_Calculate_Complex_Fractal.*;
import package_Paint_Complex_Fractal.*;

public class ComplexFractal {

	private HashMap<String, Double> fractalParam = new HashMap<String, Double>();
	
	private Calculate_Complex_Fractal calc;
	private Paint_Complex_Fractal paint;
	
	private double planeArray[][];
	private double scale = 200;
    private double moveX = 0;
    private double moveY = 0;
    private int accuracy = 800;
	
	public ComplexFractal(int width, int height,double scale,double moveX, double moveY)
	{
		this.moveX=moveX;
		this.moveY=moveY;
		this.scale = scale;
		planeArray = new double[width][height];
		paint = new ColorPalette1();
	}
	
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
		calc.calculateComplexFractal(planeArray,accuracy, scale, moveX, moveY);
	}
	
	public double getPixelPercent(int i,int j)
	{
		return this.planeArray[i][j];
	}
	
	public void paintFractal(PixelWriter pw)
	{
		paint.paintComplexFractal(planeArray, pw);
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
