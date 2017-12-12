package application;

import java.util.HashMap;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.canvas.*;

public class AnimatedComplexFractal extends ComplexFractal{

	private HashMap<String, Double> fractalParam;
	private HashMap<String, Double> animParam;
	private static GraphicsContext gc;
	
	
	public AnimatedComplexFractal(HashMap<String, Double> fractalParam ,HashMap<String, Double> animParam,GraphicsContext gc) 
	{
		super(fractalParam);
		AnimatedComplexFractal.gc = gc;
		this.fractalParam = fractalParam;
		this.animParam = animParam;
	}
	
	public void playAnimatedJulia() 
	{	
			Task<Integer> myThread = new playJuliaAnimation(fractalParam, animParam, gc);
			new Thread(myThread).start();		
	}
	
	
	
		
	private static class playJuliaAnimation extends Task<Integer> 
	{
	    private HashMap<String, Double> fractalParam;
	    private double startArgument;
		private double endArgument;
		private double module;
		private int steps;
	    
	    public playJuliaAnimation(HashMap<String, Double> fractalParam ,HashMap<String, Double> animParam,GraphicsContext gc) 
	    {
	    	this.fractalParam = fractalParam;
	    	this.startArgument = animParam.get("startArgument");
	    	this.endArgument = animParam.get("endArgument");
	    	this.module = animParam.get("module");
	    	this.steps = animParam.get("steps").intValue();
	    }

	  
	    
	    @Override
	    protected Integer call() throws Exception  
	    {	    	
	    	int iterations;
	        double singleStep = (endArgument-startArgument)/steps;		
	        Image imageContainer[] = new Image[steps];	        
	        
	        for(int i=0;i<steps;i++)
			{															
	        	fractalParam.put("cReal", new Complex(0,0).exp(module, (((i)*(singleStep))+startArgument)).real);
	        	fractalParam.put("cImaginary", new Complex(0,0).exp(module, (((i)*(singleStep))+startArgument)).imaginary);   
				ComplexFractal frac= new ComplexFractal(fractalParam);
				frac.setJuliafractal();
				frac.calcFractal();	        	
				imageContainer[i] = frac.paintFractal(gc);
				System.out.println(i);
			}	
	        	        	    		    		    		       	        	        
	        for (iterations = 0; iterations < steps; iterations++) 
	        {	            
	            //updateMessage("Iteration " + iterations);
	            //updateProgress(iterations, steps);	            	          			
	            	        	
	            gc.drawImage(imageContainer[iterations], 0, 0);	
	        			            
				try 
	            {
	                Thread.sleep(30);					
	            } catch (InterruptedException interrupted) 
	            {
	                if (isCancelled()) 
	                {
	                    updateMessage("Cancelled");
	                    break;
	                }
	            }
	        }
	        return iterations;
	    }	 
	}		
}







/*@Override
protected Integer call() throws Exception  
{	    	
	int iterations;
    //Complex complexSteps[] = new Complex[steps];		
    double singleStep = (endArgument-startArgument)/steps;		
    ComplexFractal animTab[] = new ComplexFractal[steps];
    //Image imageContainer[] = new Image[steps];
    
    
    for(int i=0;i<steps;i++)
	{											
		//complexSteps[i] =  new Complex(0,0).exp(module, (((i)*(singleStep))+startArgument));		//double real = module * Math.cos(((i)*(singleStep))+startArgument);//double imaginary = module * Math.sin(((i)*(singleStep))+startArgument);
		fractalParam.put("cReal", new Complex(0,0).exp(module, (((i)*(singleStep))+startArgument)).real);
    	fractalParam.put("cImaginary", new Complex(0,0).exp(module, (((i)*(singleStep))+startArgument)).imaginary);   
		animTab[i] = new ComplexFractal(fractalParam);		      	      		        	
		animTab[i].setJuliafractal();
		animTab[i].calcFractal();	
		//imageContainer[i] = animTab[i].paintFractal(gc);
		System.out.println(i);
	}	
    	        	    		    		    		       	        	        
    for (iterations = 0; iterations < steps; iterations++) 
    {	            
        //updateMessage("Iteration " + iterations);
        //updateProgress(iterations, steps);	            	          			
        
    	long durationTime=20000;
    	long startTime = System.nanoTime(); 
    	long estimatedTime = System.nanoTime() - startTime;
    	
    	Image immage = animTab[iterations].paintFractal(gc);
    	
    	if(System.nanoTime() - startTime < durationTime)
    		Thread.sleep((durationTime-(System.nanoTime() - startTime)));
    	
        gc.drawImage(immage, 0, 0);	
    		
        
		try 
        {
            Thread.sleep(30);					
        } catch (InterruptedException interrupted) 
        {
            if (isCancelled()) 
            {
                updateMessage("Cancelled");
                break;
            }
        }
    }
    return iterations;
}*/





