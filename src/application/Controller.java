package application;

import javafx.scene.canvas.*;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Random;

import javafx.fxml.FXML;

public class Controller 
{
	
	private static ComplexFractal cFractal;
	private double scale=100;
	private double moveX = 0;
	private double moveY = 0;
	double PrzybMn=1.5;
		
	@FXML	private Canvas mainCanvas ;
	
	
	@FXML void draw(MouseEvent e)
	{		
		double x = e.getX();
	    double y = e.getY();
	    
	    double OffsetX=500- x;
        double OffsetY=500- y;

        moveX -= OffsetX;
        moveY -= OffsetY;

        scale *= PrzybMn;
        moveX *= PrzybMn;
        moveY *= PrzybMn;
        


		cFractal = new ComplexFractal(900,900,scale,moveX,moveY);
		cFractal.calcFractal();
		
		GraphicsContext gc = mainCanvas.getGraphicsContext2D() ;
		PixelWriter pw = gc.getPixelWriter();
		cFractal.paintFractal(pw);
	}
	
	
	
	
	
	
	
}
