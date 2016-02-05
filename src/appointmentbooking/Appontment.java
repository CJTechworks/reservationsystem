//Package Name
package appointmentbooking;

//Main class
public class Appontment
{
        //Properties
        public int ID;
        public String Title;
	public String  date;	
        public String  time;
        
        //Methods
        Appontment(int _ID,String _title,String _date,String _time)
        {
            this.ID=_ID;
            Title=_title;
            date=_date;                    
            time=_time;
       }
}
