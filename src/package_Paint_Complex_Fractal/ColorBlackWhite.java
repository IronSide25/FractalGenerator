package package_Paint_Complex_Fractal;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ColorBlackWhite implements Paint_Complex_Fractal
{

	@Override
	public Image paintComplexFractal(double planeArray[][],GraphicsContext gc) 
	{				
		WritableImage image = new WritableImage(900,900);
		PixelWriter px = image.getPixelWriter();
		
		for (int y = 0 ; y < 900 ; y++) 
	        for (int x = 0 ; x < 900 ; x++) 
	        	{
	        	if(planeArray[y][x]==1.0)
	        		px.setColor(x, y, Color.BLACK);  
	        	else
	        		px.setColor(x, y, Color.WHITE);  
	        	}
		return image;
	}

}
