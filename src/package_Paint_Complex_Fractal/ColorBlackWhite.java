package package_Paint_Complex_Fractal;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class ColorBlackWhite implements Paint_Complex_Fractal
{

	@Override
	public void paintComplexFractal(double planeArray[][],PixelWriter pw) 
	{
		
		
		for (int y = 0 ; y < 900 ; y++) 
	        for (int x = 0 ; x < 900 ; x++) 
	        	{
	        	if(planeArray[y][x]==1.0)
	        		pw.setColor(x, y, Color.BLACK);                                                         
	        	}
	}

	
}
