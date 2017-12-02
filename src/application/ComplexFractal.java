package application;
import javafx.scene.image.PixelWriter;
import package_Calculate_Complex_Fractal.*;
import package_Paint_Complex_Fractal.*;

public class ComplexFractal {

	private Calculate_Complex_Fractal calc;
	private Paint_Complex_Fractal paint;
	
	private double planeArray[][];
	private double scale = 200;
    private double moveX = 0;
    private double moveY = 0;
    private int accuracy = 50;
	
	public ComplexFractal(int width, int height,double scale,double moveX, double moveY)
	{
		this.moveX=moveX;
		this.moveY=moveY;
		this.scale = scale;
		planeArray = new double[width][height];
		calc = new CalculateBurningShipFractal();
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
	
	public void increaseScale(double mn)
	{
		this.scale=scale*mn;
	}
	
	
}
