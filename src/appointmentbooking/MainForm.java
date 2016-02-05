//Package
package appointmentbooking;

//Imports
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*; 

//Main class
public class MainForm 
{ 
    //Frame
    static JFrame frame;
        
    //Main method
    public static void main(String[] args)
    {
         try
        {
            frame = new JFrame("Appointment Booking System");
            frame.setFont(new Font("courier new", Font.PLAIN, 11));
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();                

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                
            frame.setContentPane(new appointmentbooking(frame));
            frame.pack();
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        }
        catch(Exception e)
        {
            System.out.println("Error:"+ e.getMessage());
        }
    }

}