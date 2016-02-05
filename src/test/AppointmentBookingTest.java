package test;

import javax.swing.JFrame;

import appointmentbooking.appointmentbooking;
import junit.framework.TestCase;

public class AppointmentBookingTest extends TestCase{
	
	   public void testBindAction() {	
	      System.out.println("start testBindAction()");    
	      JFrame frame = new JFrame("appointment booking system");
	      appointmentbooking a = new appointmentbooking(frame);
	      a.BindActions();
	      System.out.println("end testBindAction()");    
	      System.out.println("--------------------------------");
	   }
	   
	   public void testValidateDate_Empty(){
		   System.out.println("start testValidateDate_Empty");
		   JFrame frame = new JFrame("appointment booking system");
		   appointmentbooking a = new appointmentbooking(frame);
		   a.ValidateDate("");
		   System.out.println("end testValidateDate_Empty empty date");
		   System.out.println("--------------------------------");
	   }
	   
	   
	   public void testValidateDate_InvalidFormat(){
		   System.out.println("start testValidateDate_InvalidFormat");
		   JFrame frame = new JFrame("appointment booking system");
		   appointmentbooking a = new appointmentbooking(frame);
		   a.ValidateDate("2016-1-1");
		   System.out.println("end testValidateDate_InvalidFormat");
		   System.out.println("--------------------------------");
	   }
	   
	   
	   public void testValidateDate_validFormat(){
		   System.out.println("start testValidateDate_validFormat");
		   JFrame frame = new JFrame("appointment booking system");
		   appointmentbooking a = new appointmentbooking(frame);
		   boolean isValid = a.ValidateDate("10-12-2016");
		   assertTrue(isValid);
		   System.out.println("end testValidateDate_validFormat");
		   System.out.println("--------------------------------");
	   }
	   
	   public void testValidateTime_invalidFormat(){
		   System.out.println("start testValidateTime_invalidFormat");
		   JFrame frame = new JFrame("appointment booking system");
		   appointmentbooking a = new appointmentbooking(frame);
		   
		   // this should return false
		   boolean isValid = a.validateTime("44:00");
		   // below 3 lines won't be executed since it failed in the method call above
		   assertEquals(true, isValid);
		   System.out.println("end testValidateTime_invalidFormat");
		   System.out.println("--------------------------------");
	   }
	   
	   public void testValidateTime_validFormat(){
		   System.out.println("start testValidateTime_validFormat");
		   JFrame frame = new JFrame("appointment booking system");
		   appointmentbooking a = new appointmentbooking(frame);
		   
		   // this should return false
		   boolean isValid = a.validateTime("11:00");
		   assertEquals(true, isValid);
		   System.out.println("end testValidateTime_validFormat");
		   System.out.println("--------------------------------");
	   }
	   
	   public void testShowAppointment(){
		   System.out.println("start testShowAppointment");
		   appointmentbooking.ShowAppointment();		   
		   System.out.println("end testShowAppointment");
		   System.out.println("--------------------------------");

	   }
	   
	   public void testFindMe_valid(){
		   System.out.println("start testFindMe_valid");
		   boolean isMatch = appointmentbooking.findMe("test", "testme");
		   assertEquals(true, isMatch);
		   System.out.println("end testFindMe_valid");
		   System.out.println("--------------------------------");
	   }
	   
	   public void testFindMe_Invalid(){
		   System.out.println("start testFindMe_Invalid");
		   boolean isMatch = appointmentbooking.findMe("abc", "xyz");
		   assertEquals(false, isMatch);
		   System.out.println("end testFindMe_Invalid");
		   System.out.println("--------------------------------");
	   }
	   
}
