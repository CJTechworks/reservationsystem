//Package
package appointmentbooking;

//Import
import java.sql.*;
import java.util.ArrayList;

//Class
public class DBHandler 
{
    public DBHandler()
    {
        Connection c = null;
        Statement stmt = null;
    }

    //Insert Appointment
    public void Update(String ID, String title, String dateTime)
    {

        Connection c = null;	   
        PreparedStatement prep;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);  
            // we should not add the input from the user directly to the query. it will lead to SQL Injection security issue
            prep = c.prepareStatement("update Appointment_new set title='"+title+"', Date='"+dateTime+"' where id="+ID+";");                
            prep.execute();
            prep.close(); 
            c.commit();
            c.close();
        } 
        catch ( Exception e ) 
        {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }	    	
    }

    //Insert Appointment
    public void insert(String title, String dateTime)
    {

        Connection c = null;	   
        PreparedStatement prep;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            prep = c.prepareStatement("insert into Appointment_new values(?,?,?);");
            System.out.println(">> Insert "+title + " @ " + dateTime);                
            prep.setString(1, MaxID());
            prep.setString(2, title);
            prep.setString(3, dateTime);	      
            prep.execute();
            prep.close(); 
            c.commit();
            c.close();
        } 
        catch ( Exception e ) 
        {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }	    	
    }
    
    //Get all elements into array list from database
    public ArrayList<Appontment> select()
    {
        ArrayList<Appontment> listOfAppointment=new ArrayList<Appontment>();
        Connection c = null;
        Statement stmt = null;
  
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Appointment_new order by Date;" );

            while ( rs.next() ) 
            {      
                int ID=Integer.parseInt(rs.getString("ID"));
                String  title =rs.getString("title");                 
                String  Date =rs.getString("date").toString() .split(" ")[0];
                String  Time ="00:00";
                if(rs.getString("date").toString().split(" ").length>1)
                      Time =rs.getString("date").toString().split(" ")[1];                 
                listOfAppointment.add(new Appontment(ID, title,Date,Time));	          
            }
            rs.close();
            stmt.close();
            c.close();
        } 
        catch (Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }        
        return listOfAppointment;
    }

    //Get Max ID
    public String MaxID()
    {            
        Connection c = null;
        Statement stmt = null;
        int ID=0;
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT max(ID) as ID FROM Appointment_new order by Date;" );

            while ( rs.next() ) 
            {      
               ID=Integer.parseInt(rs.getString("ID"));
               break;
            }
            rs.close();
            stmt.close();
            c.close();
        } 
        catch ( Exception e ) 
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }	    
        return ""+(ID+1);
    }

    //Delete particular record by ID
    public void delete(String appointment)
    {
        Connection c = null;
        Statement stmt = null;
        try 
        {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:test.db");
          c.setAutoCommit(false);		     
          stmt = c.createStatement();		      
          String sql = "DELETE from Appointment_new where id='"+appointment+"';";
          System.out.println(">> DELETED SUCCESS id"+sql);
          stmt.executeUpdate(sql);
          c.commit();
          stmt.close();
          c.close();
        } 
        catch ( Exception e ) 
        {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
