package package_Paint_Complex_Fractal;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class ColorPalette1 implements Paint_Complex_Fractal
{

	Color Colors[];
										
	
	@Override
	public void paintComplexFractal(double planeArray[][],PixelWriter pw)
	{
		
		
		Colors = new Color[768];            
    for (int i = 0; i < 768; i++)
    {
        int colorValueR = 0;
        int colorValueG = 0;
        int colorValueB = 0;
        if (i >= 512)
        {
            colorValueR = i - 512;
            colorValueG = 255 - colorValueR;
        }
        else if (i >= 256)
        {
            colorValueG = i - 256;
            colorValueB = 255 - colorValueG;
        }
        else
        {
            colorValueB = i;
        }
        Colors[i] = Color.rgb(colorValueR, colorValueG, colorValueB);
        
    }		
		
		
		for (int y = 0 ; y < 900 ; y++) 
	        for (int x = 0 ; x < 900 ; x++) 
	        	{
	        	double constant = 767;
                double wynik = (planeArray[y][x])*constant;                                                                 
                pw.setColor(x, y, Colors[(int)wynik]);                                                         
	        	}
		
	}
}
