package com.guoquan.learning;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.guoquan.bean.Product;
import com.guoquan.db.MongoDBConnector;

/**
 * Hello world!
 *
 */
public class App 
{   private static ExecutorService executorService;

	public App(int threadPoolSize){
		executorService = Executors.newFixedThreadPool(threadPoolSize);
	}
	class ProductQuerier implements Runnable{

		public void run() {
			 MongoDBConnector mongoDBConnector= new MongoDBConnector();
			 ArrayList<Product> products =  mongoDBConnector.getProducts("webapp", "products");
	        for (Product product : products){
	        	System.out.println(product.toString());
	        }
		}
	}
	
	public void executeProductQueriers(){
		for (int i = 0; i < 10; i++){
        	Thread thread = new Thread(new ProductQuerier());
        	executorService.submit(thread);
        }
	}
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        App app =  new App(10);
        app.executeProductQueriers();
        
        
    }
    
    
}
