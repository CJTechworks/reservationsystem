

//Packet Name
package appointmentbooking;

 
//Import Statements

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class appointmentbooking extends JPanel
{
    static JFrame frame;
    static DBHandler handler = new DBHandler();	
    static private JTextField txtID, txttitle, txtDate, txtTime,txtSearchKey,txtStartDate,txtEndDate;
    private JPanel mainPanel, iconPanel,txtLblPanel,buttonPanel,searchPanel,listPanel,navigationPanel,PicPanel;	
    private JLabel label, lblIcon,lblSearch,lblID;	
    private JButton btnSave,btnReset,btnDelete,btnSearch;
    private JButton btndtStart,btndtEnd,btnDate;
    private JButton btnNext,btnPrev,btnLast,btnFirst;
    Image img ;
    JList list;
    static DefaultListModel model;

    public appointmentbooking(JFrame _frame)
    {
            frame=_frame;
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


            txtLblPanel=new JPanel();
            buttonPanel=new JPanel();
            searchPanel=new JPanel();
            iconPanel= new JPanel();
            PicPanel=new  JPanel();
            listPanel= new JPanel();
            model = new DefaultListModel();
            navigationPanel= new JPanel();

            SetControlLayout();

            mainPanel=new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
            mainPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Appontment details"),BorderFactory.createEmptyBorder(5,5,5,5)));
            mainPanel.add(txtLblPanel);
            mainPanel.add(buttonPanel);
            mainPanel.add(searchPanel);
            mainPanel.add(PicPanel);
            mainPanel.add(listPanel);
            mainPanel.add(navigationPanel);

            JPanel layout = new JPanel();
            layout.setLayout(new BoxLayout(layout, BoxLayout.PAGE_AXIS));
            layout.add(Box.createRigidArea(new Dimension(5,5)));
            layout.add(iconPanel);
            layout.add(Box.createRigidArea(new Dimension(5,5)));
            layout.add(mainPanel);


            add(layout,BorderLayout.PAGE_END);

            BindActions();

    }



    public void SetValue(String ID,String strTitle, String strStartDate, String strEndTime)
    {
        txtID.setText(String.valueOf(ID));
        txttitle.setText(String.valueOf(strTitle));
        txtDate.setText(strStartDate);
        if(strEndTime.trim().equals(""))
            txtTime.setText("00:00");  
        else
            txtTime.setText(strEndTime);  
        txttitle.requestFocus();
    }

    public void SetControlLayout()
    {

        //Lables Panel
        JPanel lblPanel = new JPanel();
        lblPanel.setLayout(new GridLayout(4,1,25,5));
        lblPanel.add(label = new JLabel("Sr.No: ",JLabel.RIGHT));
        lblPanel.add(label = new JLabel("Title: ",JLabel.RIGHT));
        lblPanel.add(label = new JLabel("Date: ",JLabel.RIGHT));
        lblPanel.add(label = new JLabel("Time: ",JLabel.RIGHT));

        txtLblPanel.add(lblPanel);  

        //Add Textbox Panel
        JPanel txtPanel = new JPanel();
        txtPanel.setLayout(new GridLayout(4,1,25,0));            
        txtPanel.add(txtID= new JTextField(25));
        txtID.setEnabled(false);
        txtPanel.add(txttitle= new JTextField(25));
        txtPanel.add(txtDate= new JTextField(25));
        txtPanel.add(txtTime = new JTextField(25));					
        txtTime.setText("00:00");
        txtLblPanel.setLayout(new BoxLayout(txtLblPanel, BoxLayout.LINE_AXIS));                                
        txtLblPanel.add(txtPanel);               	

         //Calander button
        JPanel lblcal = new JPanel();
        lblcal.setLayout(new GridLayout(3,1,25,5));
        lblcal.add(label = new JLabel("",JLabel.RIGHT));
        btnDate = new JButton();
        btnDate.setSize(20, 20);
        img = Toolkit.getDefaultToolkit().getImage("image/cal.png");
        btnDate.setIcon(new ImageIcon(img));            

        lblcal.add(btnDate);                        
        lblcal.add(label = new JLabel("",JLabel.RIGHT));
        txtLblPanel.add(lblcal);



       //Action button panel
        buttonPanel.add(btnSave= new JButton("Save"));                                
        buttonPanel.add(btnReset= new JButton("Reset"));                                
        buttonPanel.add(btnDelete= new JButton("Delete"));


        btnNext = new JButton();
        img = Toolkit.getDefaultToolkit().getImage("image/next.gif");
        btnNext.setIcon(new ImageIcon(img));

        btnFirst = new JButton();
        img = Toolkit.getDefaultToolkit().getImage("image/first.gif");
        btnFirst.setIcon(new ImageIcon(img));

        btnLast = new JButton();
        img = Toolkit.getDefaultToolkit().getImage("image/last.gif");
        btnLast.setIcon(new ImageIcon(img));

        btnPrev = new JButton();
        img = Toolkit.getDefaultToolkit().getImage("image/prev.gif");
        btnPrev.setIcon(new ImageIcon(img));

        navigationPanel.add(btnFirst);
        navigationPanel.add(btnPrev);
        navigationPanel.add(btnNext);            
        navigationPanel.add(btnLast);


        //Search Panel                
        searchPanel.add(label = new JLabel("Search keyword:",JLabel.RIGHT));
        searchPanel.add(txtSearchKey = new JTextField(15));				
        searchPanel.add(btnSearch= new JButton("Search"));				

        //List Panel
        list = new JList(model); 
        list.setFont(new Font("courier new", Font.PLAIN, 11));


    JScrollPane pane = new JScrollPane(list);                                  
    list.setFixedCellWidth(400);           
    ShowAppointment();
    listPanel.add(pane);


       ImageIcon icon = new ImageIcon("image/list.gif"); 
       JLabel thumb = new JLabel();
       thumb.setIcon(icon);
       PicPanel.add(thumb);


        // Header Image panel
        iconPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,1,1));
        iconPanel.add(lblIcon = new JLabel(new ImageIcon("image/header.gif")));
        iconPanel.setBorder(BorderFactory.createEtchedBorder());
    }

    //Bind action command
    public void BindActions()
    {
        //Click on Reset
        btnReset.addActionListener(new ActionListener() 
        {
        public void actionPerformed(ActionEvent ae) 
        {
                SetValue("", "", "","");
        }
        });

        //Datetime picker StarDate
        btnDate.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
                txtDate.setText(new DatePicker().setPickedDate());
        }
        });

        btnDelete.addActionListener(new ActionListener() 
        {
        public void actionPerformed(ActionEvent ae) 
        {
            if(list.getSelectedIndex()<0)
            {
                JOptionPane.showMessageDialog(frame,"Please select any appointment...!","Message",JOptionPane.INFORMATION_MESSAGE);               
            }
            else
            {
                int n = JOptionPane.showConfirmDialog(frame,"Are you sure? you want to  delete this record 'Appointment'","Confirm delete",JOptionPane.YES_NO_OPTION);        
                if(n == JOptionPane.YES_OPTION)
                {        		                
                    String val[]=list.getSelectedValue().toString().split("\t");
                    handler.delete(txtID.getText().trim());
                    JOptionPane.showMessageDialog(frame,"Appointment deleted....!","Message",JOptionPane.INFORMATION_MESSAGE);               
                    SetValue("", "", "", "");
                }
                ShowAppointment() ;
            }
        }
        });

        btnFirst.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) 
        {
            try{
                list.setSelectedIndex(0);
            }catch(Exception e){}
        }});

        btnNext.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) 
        {
            try
            {                   
                list.setSelectedIndex(list.getSelectedIndex()+1);
            }catch(Exception e){}
        }});

        btnPrev.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) 
        {
            try
            {                   
                list.setSelectedIndex(list.getSelectedIndex()-1);
            }catch(Exception e){}
        }});

        btnLast.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) 
        {
            try
            {                   
                list.setSelectedIndex(list.getModel().getSize()-1);
            }catch(Exception e){}
        }});

        btnSave.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) 
        {                
            try 
            {
                if(txttitle.getText().trim().length()==0)
                {
                    JOptionPane.showMessageDialog(frame,"Enter valid title...!","Error",JOptionPane.ERROR_MESSAGE);
                    txttitle.requestFocus();
                }
                else if(ValidateDate(txtDate.getText().trim())==false)
                {
                    JOptionPane.showMessageDialog(frame,"Date not valid...!","Error",JOptionPane.ERROR_MESSAGE);
                    txtDate.requestFocus();
                }
                else if(validateTime(txtTime.getText().trim())==false)
                {
                    JOptionPane.showMessageDialog(frame,"Time not valid...!","Error",JOptionPane.ERROR_MESSAGE);
                    txtTime.requestFocus();
                }
                else
                {                                 
                    if(txtID.getText().trim().equals("") || txtID.getText().trim().equals("0"))                                    
                        handler.insert(txttitle.getText().trim(), txtDate.getText().trim() + " " +txtTime.getText().trim());
                    else
                        handler.Update(txtID.getText().trim(), txttitle.getText().trim(), txtDate.getText().trim() + " " +txtTime.getText().trim());

                    ShowAppointment() ; 
                    JOptionPane.showMessageDialog(frame,"Appointment saved successfully....!","Message",JOptionPane.INFORMATION_MESSAGE);               
                }
            } 
            catch (Exception e) 
            { 
                JOptionPane.showMessageDialog(frame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        });

        //List Box
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) 
            {
                try{
                if (!lse.getValueIsAdjusting()) 
                {
                    SetValue("", "", "", "");
                    String val[]=list.getSelectedValue().toString().split("\t");
                    if(val.length>0)txtID.setText(val[0].trim());
                    if(val.length>1)txttitle.setText(val[1].trim());
                    if(val.length>2)txtDate.setText(val[2].trim());
                    if(val.length>3)txtTime.setText(val[3].trim());
                }
                }
                catch(Exception ex)
                {
                    System.out.println("Er:"+ex.getMessage());
                }

            }
         });

         btnSearch.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) 
        {
                model.clear();              
                ArrayList<Appontment> lst=handler.select();                                        
                boolean isFind=false;
                for(int i=0;i<lst.size();i++) 
                {


                    if(findMe(txtSearchKey.getText().toLowerCase(),lst.get(i).Title.toLowerCase()))                            
                    {
                        isFind=true;
                        String str=String.format("%-3s\t%-25s\t%-15s\t%-5s", lst.get(i).ID, lst.get(i).Title,lst.get(i).date,lst.get(i).time);                                                            
                        model.addElement(str);
                    }

                }
                if(isFind==false)
                model.addElement("No Appointment Found....");

        }
        });             

    }
    //Validate Date
    public boolean ValidateDate(String inputDate)
    {
        try{
            inputDate = inputDate.replaceAll("-", "/");
            Date date;
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            formatter.setLenient(false);
            date = formatter.parse(inputDate);
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Err"+e.getMessage());
            return false;
        }
    }
    //Validate Time
    public boolean validateTime(final String time)
    {
        Pattern pattern;  
        Matcher matcher;   
        String TIME24HOURS_PATTERN =  "(([0-1]?[0-9])|(2[0-3])):[0-5][0-9]"; 
        pattern = Pattern.compile(TIME24HOURS_PATTERN); 
        matcher = pattern.matcher(time);        
        return matcher.matches();	    	    
     }
        
    //Show all records    
    public static void ShowAppointment() 
    {
        model.clear();
        ArrayList<Appontment> lst=handler.select();                                        

        for(int i=0;i<lst.size();i++) 
        {
            String str=String.format("%-3s\t%-25s\t%-15s\t%-5s", lst.get(i).ID, lst.get(i).Title,lst.get(i).date,lst.get(i).time);                                                                        
            model.addElement(str);                
        }
         if(lst.size()==0)
            model.addElement("No Appointment Found....");
        
      }
    //Find function
        public static boolean findMe(String subString, String mainString) {
        boolean foundme = false;
        int max = mainString.length() - subString.length();

        // Implement your own Contains Method with Recursion
        checkrecusion: for (int i = 0; i <= max; i++) {
            int n = subString.length();

            int j = i;
            int k = 0;

            while (n-- != 0) {
                if (mainString.charAt(j++) != subString.charAt(k++)) {
                    continue checkrecusion;
                }
            }
            foundme = true;
            break checkrecusion;
        }

        return foundme;
    }
	
}