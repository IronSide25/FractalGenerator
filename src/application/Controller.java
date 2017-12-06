package application;

import javafx.scene.canvas.*;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.control.ComboBox;

import java.util.HashMap;
import java.util.Random;

import com.sun.javafx.collections.MappingChange.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Controller 
{	
	private HashMap<String, Double> fractalParam = new HashMap<String, Double>();
	
	private static ComplexFractal cFractal;
	private double scale=100;
	private double moveX = 0;
	private double moveY = 0;
	double PrzybMn=1.5;	
	
	private String currentFractal = "Mandelbrot Set";
			
	@FXML	private  Canvas mainCanvas ;
	@FXML   private ComboBox<String> selectFractalComboBox;	
	ObservableList<String> options = FXCollections.observableArrayList("Mandelbrot Set","Julia Set","Burning Ship Set"/*, "Animated Julia Set"*/);
	
	
	@FXML	
    private void initialize()	
    {		
		fractalParam.put("scale", 100.0);
		fractalParam.put("moveX", 0.0);
		fractalParam.put("moveY", 0.0);
		
		
		selectFractalComboBox.getItems().addAll(options);
		selectFractalComboBox.setValue("Mandelbrot Set");
		drawFractal();
		
		selectFractalComboBox.valueProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue ov, String t, String t1) {
	            currentFractal = t1;	
	            	            	            	            
	            scale=100;
	            moveX = 0;
	        	moveY = 0;
	        	
	        	if(!t1.equals("Animated Julia Set"))
	        	drawFractal();
	        	else
	        	{
	        		currentFractal = t1;
	        		animateJuliaSet(0.7885,0,0.7885,3.14*2);
	        	}
	        }	       
	    });
    }
	
	
	
	@FXML void onMainCanvasClick(MouseEvent e)
	{											
		double x = e.getX();
	    double y = e.getY();		    
	    double OffsetX=mainCanvas.getWidth()/2- x;
        double OffsetY=mainCanvas.getHeight()/2- y;
        
        fractalParam.put("scale", fractalParam.get("scale")*PrzybMn);
		fractalParam.put("moveX", (fractalParam.get("moveX")-OffsetX)*PrzybMn);
		fractalParam.put("moveY", (fractalParam.get("moveY")-OffsetY)*PrzybMn);
        
        moveX -= OffsetX;
        moveY -= OffsetY;
        scale *= PrzybMn;
        moveX *= PrzybMn;
        moveY *= PrzybMn;
        drawFractal();		
	}
	
	private void drawFractal()
	{
		cFractal = new ComplexFractal((int)mainCanvas.getWidth() ,(int)mainCanvas.getHeight() ,scale,moveX,moveY);
		
		switch(currentFractal)
        {
        case "Mandelbrot Set": cFractal.setMandelbrotfractal();
        	break;
        case "Julia Set": cFractal.setJuliafractal();
    		break;
        case "Burning Ship Set": cFractal.setBurningShipfractal();
    		break;
    	default: System.out.println("blad");
             break;	
        }
		
		cFractal.calcFractal();
		
		GraphicsContext gc = mainCanvas.getGraphicsContext2D() ;
		PixelWriter pw = gc.getPixelWriter();
		cFractal.paintFractal(pw);
	}
	
	
	
	private void animateJuliaSet(double startComplexMod,double startComplexArgument,double endComplexMod,double endComplexArgument)
	{		
		cFractal.setJuliafractal();
		
		double startReal = startComplexMod*Math.cos(startComplexArgument);
		double startImaginary = startComplexMod*Math.sin(startComplexArgument);
		
		Complex start = new Complex(startReal,startImaginary);
		
		double endReal = endComplexMod*Math.cos(endComplexArgument);
		double endImaginary = endComplexMod*Math.sin(endComplexArgument);
		
		Complex end = new Complex(endReal,endImaginary);
		
		int steps = 10;
		Complex complexSteps[] = new Complex[10];
		
		double singleStep = (endComplexArgument-startComplexArgument)*10;
		
		for(int i=0;i<10;i++)
		{
			double real = startComplexMod * Math.cos((i+1)*singleStep);
			double imaginary = startComplexMod * Math.sin((i+1)*singleStep);			
			complexSteps[i] =  new Complex(real,imaginary);
		}
		
		for(int i=0;i<10;i++)
		{
			cFractal = new ComplexFractal((int)mainCanvas.getWidth() ,(int)mainCanvas.getHeight() ,scale ,moveX,moveY );
			cFractal.setJuliafractal();
			cFractal.calcFractal();
			
			GraphicsContext gc = mainCanvas.getGraphicsContext2D() ;
			PixelWriter pw = gc.getPixelWriter();
			cFractal.paintFractal(pw);
			
		}
		
		
		
		
		
	}
	


	
}
