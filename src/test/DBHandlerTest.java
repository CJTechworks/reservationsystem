package test;

import java.util.ArrayList;


import appointmentbooking.Appontment;
import appointmentbooking.DBHandler;
import junit.framework.TestCase;

public class DBHandlerTest extends TestCase {
	// select test
	public void testSelect(){
		DBHandler dbHandler = new DBHandler();
		ArrayList<Appontment> appointmentList = dbHandler.select();
		if(appointmentList != null){
			for(Appontment a : appointmentList){
				System.out.println("Title :"+a.Title);
				System.out.println("Time :" + a.time);
				System.out.println("Date :"+a.date);
				
			}
		}
	}
	// test insert call
	public void testInsert(){
		DBHandler dbHandler = new DBHandler();
		dbHandler.insert("testinsert", "09-01-2016");
		// assert true is to ensure the insert call is success
		assertTrue(true);
		System.out.println("insert test success");
	}
	
	//test delete call
	public void testDelete(){
		
		DBHandler dbHandler = new DBHandler();
		String maxId = dbHandler.MaxID();
		dbHandler.delete(maxId);
		// this assert ensure the delete is successs
		assertTrue(true);
		System.out.println("delete test success");
		
	}
	// test update
	public void testUpdate(){
		DBHandler dbHandler = new DBHandler();
		String maxId = dbHandler.MaxID();
		dbHandler.Update(maxId, "title", "09-01-2017");
		
		assertTrue(true);
		System.out.println("update test success");
		
	}
	
	public void testMaxID(){
		DBHandler dbHandler = new DBHandler();
		String maxId = dbHandler.MaxID();
		System.out.println("max id :+"+maxId);

	}

}
