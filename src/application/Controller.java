package application;

import javafx.scene.canvas.*;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.control.ComboBox;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import com.sun.javafx.collections.MappingChange.Map;
import javafx.concurrent.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Controller 
{	
	private HashMap<String, Double> fractalParam = new HashMap<String, Double>();	
	private static ComplexFractal cFractal;
	double PrzybMn=1.5;		
	private String currentFractal = "Mandelbrot Set";
			
	@FXML	private Canvas mainCanvas ;
	@FXML   private ComboBox<String> selectFractalComboBox;	
	ObservableList<String> options = FXCollections.observableArrayList("Mandelbrot Set","Julia Set","Burning Ship Set", "Animated Julia Set");
		
	
	
	@FXML	
    private void initialize()	
    {	
		fractalParam.put("scale", 200.0);
		fractalParam.put("moveX", 0.0);
		fractalParam.put("moveY", 0.0);
		fractalParam.put("accuracy", 150.0);
		fractalParam.put("width", mainCanvas.getWidth());
		fractalParam.put("height", mainCanvas.getWidth());
        		
		selectFractalComboBox.getItems().addAll(options);
		selectFractalComboBox.setValue("Mandelbrot Set");
		drawFractal();
		
		selectFractalComboBox.valueProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue ov, String t, String t1) 
	        {
	            currentFractal = t1;		            	       
	            fractalParam.put("scale", 350.0);
	    		fractalParam.put("moveX", 0.0);
	    		fractalParam.put("moveY", 0.0);	           	            	           
	        	
	        	if(!t1.equals("Animated Julia Set"))
	        	drawFractal();
	        	else
	        	{
	        		currentFractal = t1;
	        		animateJuliaSet(0.7885 ,0, 3.14*2.0, 300); 
	        	}
	        }	       
	    });
    }
	
	
	
	@FXML void onMainCanvasClick(MouseEvent e)
	{											
		if(currentFractal!="Animated Julia Set")
		{					
		double x = e.getX();
	    double y = e.getY();		    
	    double OffsetX=mainCanvas.getWidth()/2- x;
        double OffsetY=mainCanvas.getHeight()/2- y;        
        fractalParam.put("scale", fractalParam.get("scale")*PrzybMn);
		fractalParam.put("moveX", (fractalParam.get("moveX")-OffsetX)*PrzybMn);
		fractalParam.put("moveY", (fractalParam.get("moveY")-OffsetY)*PrzybMn);       
        drawFractal();		
		}
	}
	
	private void drawFractal()
	{        
		cFractal = new ComplexFractal(fractalParam);
		
		switch(currentFractal)
        {
        case "Mandelbrot Set": cFractal.setMandelbrotfractal();
        	break;
        case "Burning Ship Set": cFractal.setBurningShipfractal();
    		break;
        case "Julia Set": {
        	cFractal.setJuliafractal();
        	fractalParam.put("cReal", -0.1);
        	fractalParam.put("cImaginary", 0.65);
        				  }
			break;
    	default: System.out.println("blad");
             break;	
        }
		
		cFractal.calcFractal();
		
		GraphicsContext gc = mainCanvas.getGraphicsContext2D() ;		
		gc.drawImage(cFractal.paintFractal(gc),0,0);		
	}
	
	
	
	private void animateJuliaSet(double complexMod,double startComplexArgument,double endComplexArgument, double steps)
	{				
		HashMap<String, Double> animParam = new HashMap<String, Double>();	
		animParam.put("module", complexMod);
		animParam.put("startArgument", startComplexArgument);
		animParam.put("endArgument", endComplexArgument);
		animParam.put("steps", steps);
		
		GraphicsContext gc = mainCanvas.getGraphicsContext2D() ;		
		AnimatedComplexFractal animComplexFractal = new AnimatedComplexFractal(fractalParam,animParam,gc);
		animComplexFractal.playAnimatedJulia();		
	}
		
}
