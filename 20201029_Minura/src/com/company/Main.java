package com.company;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        String months[] = {                // Create 12 months
                "Jan",
                "Feb",
                "Mar",
                "Apr",
                "May",
                "Jun",
                "Jul",
                "Aug",
                "Sep",
                "Oct",
                "Nov",
                "Dec"
        };
        Calendar calendar = Calendar.getInstance();

        System.out.println("\n--------------------------------------------------------------------------------");

        System.out.println("            *** * Welcome to Skin Consultation Manager * ***");
        System.out.println("--------------------------------------------------------------------------------");

        // Calculate date,months & year
        System.out.print("Date: " + months[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.DATE) + " " + calendar.get(Calendar.YEAR));
        // Calculate hour,minutes & seconds
        System.out.println("\t\t\t\t\t\tTime: " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND ));

        Scanner input = new Scanner(System.in);
        ArrayList<Doctor> doc = new ArrayList<>();
        ArrayList<Consultation> consultations = new ArrayList<>();


        for (int i = 0; i < 100; i++) {
            String pass;
            System.out.println("\nEnter Password :");
            pass = input.nextLine();   // Login password
            while  ("AAA".equals(pass)) {   // Verify password

                System.out.println("\n                             MAIN MENU");    // Console menu
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println("\n1: Add New Doctor");
                System.out.println("2: Delete Doctor");
                System.out.println("3: Print The lList of The Doctors");
                System.out.println("4: Save In File");
                System.out.println("5: Add Consultation");
                System.out.println("6: GUI Part");

                System.out.print("\nYour Choice :\n");    // User input
                String choice = input.nextLine();
                // Read user input

                if (choice.equals("1")) {
                    doc = add_Doctor(doc);     // Add doctor
                }
                if (choice.equals("2")) {
                    doc = del_Doctor(doc);     // Remove doctors
                }

                if (choice.equals("3")) {
                    doc = print_Doctor(doc);   // View doctors
                }

                if (choice.equals("4")) {
                    doc = save_Data(doc);     // Data save in file
                }

                /*if (choice.equals("5")) {
                    consultations = add_Consultation(consultations);   // Consultations
                }*/
                if (choice.equals("6")) {
                    oopGui(doc,consultations);  // Open GUI part
                }

            }

            System.out.println("Wrong Password..\nPlease Try aging..");
        }
    }

    private static ArrayList<Consultation> add_Consultation(ArrayList<Consultation> consultations) {
        return consultations;     // Consultation
    }

    private static ArrayList<Doctor> save_Data(ArrayList<Doctor> doc) throws IOException {   // Data save file
        File file = new File("Skin Consultation Manager.txt");  // Savin file name
        FileWriter saveArray = new FileWriter(file);
        saveArray.write(String.valueOf(doc.size()));
        saveArray.write("\n");
        for (Doctor doctor : doc) {
            saveArray.write(doctor.getName());
            saveArray.write("\n");
            saveArray.write(doctor.getSurName());
            saveArray.write("\n");
            saveArray.write(doctor.getMedId());
            saveArray.write("\n");
            saveArray.write(doctor.getSpecialisation());
            saveArray.write("\n");
            System.out.println("Data saved..");
            // Write other doctor information as needed

        }


        //File file = new File("C:\\cabinTask_2.txt");

        return doc;
    }

    private static ArrayList<Doctor> print_Doctor(ArrayList<Doctor> doctors) {
        doctors.sort(new Comparator<Doctor>() {
            @Override
            public int compare(Doctor d1, Doctor d2) {
                return d1.getSurName().compareTo(d2.getSurName());
            }
        });
        for (Doctor doctor : doctors) {
            System.out.println("Doctor name: " + doctor.getName() + " " + doctor.getSurName());
            System.out.println("Medical license number: " + doctor.getMedId());
            System.out.println("Specialization: " + doctor.getSpecialisation());
            System.out.println("-------------------------------------------");
            System.out.println();

        }
        return doctors;
    }

    private static ArrayList<Doctor> del_Doctor(ArrayList<Doctor> doctors) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the medical license number of the doctor to remove: ");
        String enterId = input.nextLine();

        Doctor doctor = null;
        for (Doctor d : doctors) {
            if (d.getMedId().equals(enterId)) {
                doctor = d;
                break;
            }
        }
        if (doctor == null) {
            System.out.println("The requested license number for a doctor could not be located..");
            System.out.println("Please Enter Correct license Number..");
        } else {
            doctors.remove(doctor);
            System.out.println("The doctor with name " + doctor.getName() + " " + doctor.getSurName() + " and license number " + doctor.getMedId() + " has been removed.");
            System.out.println("The centre now has " + doctors.size() + " doctors.");

        }
        return doctors;
    }

    public static ArrayList<Doctor> add_Doctor(ArrayList<Doctor> doc) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {

            System.out.print("\nEnter your Name :");
            String name = input.nextLine();

            System.out.print("Enter your Sur Name :");
            String surName = input.nextLine();

            System.out.print("Date Of Birth:");
            String dob = input.nextLine();

            System.out.print("Mobile Number :");
            String mobilenum = input.nextLine();

            System.out.print("Medical Licence Number :");
            String medId = input.nextLine();

            System.out.print("Specialisation :");
            String specialisation = input.nextLine();

            Doctor doctor = new Doctor();
            doctor.setName(name);
            doctor.setSurName(surName);
            doctor.setDob(dob);
            doctor.setMblNumber((mobilenum));
            doctor.setMedId(medId);
            doctor.setSpecialisation(specialisation);
            doc.add(doctor);

            System.out.print("\nif you want to continue press Y and press  any key exit:");
            String yes = input.nextLine();
            if (yes.equals("y")) {
                break;

            }else{
                System.out.println("Thanks for joining us...");
                System.out.println("See you again..");
                System.exit(0);
            }
        }
        return doc;
    }


    public static void oopGui (ArrayList <Doctor> doc,ArrayList<Consultation>consultations ){  // Open GUI Part
        JFrame main = new JFrame();
        JPanel panel = new JPanel();


        main.getContentPane();
        main.setTitle("Westminster Skin Consultation Manager");  // Add title in project
        JLabel label = new JLabel("Your beauty is our profession");
        label.setFont(new Font("Serif", Font.BOLD, 25));
        label.setVerticalTextPosition(JLabel.TOP);

        // Add Icon in project
        ImageIcon image = new ImageIcon("src/com/company/logo.jpg");
        main.setIconImage(image.getImage());

        /*ImageIcon back = new ImageIcon("src/com/company/BAck.jpg");
        JLabel img = new JLabel();
        JFrame frame = new JFrame();
        img.setIcon(back);
        frame.add(img);*/

        JButton curdoc = new JButton("Current Doctors");        // View All Doctor Button
        curdoc.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));    // Font
        curdoc.setPreferredSize(new Dimension(240, 40));
        curdoc.addActionListener(e ->                                      // Going in to viewDoctor
                {
                    viewDoctor(doc);
                }
        );
        Dimension size1 = curdoc.getPreferredSize();
        curdoc.setBounds(100, 80, size1.width, size1.height);
        panel.setLayout(null);
        panel.add(curdoc);

        JButton chad = new JButton("Chanel a Doctor");     // Channel a doctor  Button
        chad.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));    // Font
        chad.setPreferredSize(new Dimension(240, 40));
        chad.addActionListener(e ->                                       // Going in to viewBookDoctor
                {
                    bookDoctor(doc,consultations);
                }
        );

        Dimension size2 = chad.getPreferredSize();
        chad.setBounds(350, 80, size2.width, size2.height);
        panel.setLayout(null);
        panel.add(chad);


        JButton viewc = new JButton("View All Chanel");      // View all channels
        viewc.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));    // Font
        viewc.setPreferredSize(new Dimension(200, 40));
        viewc.addActionListener(e ->                                   // Going in to viewChannel
                {
                    viewChannel(doc,consultations);
                }
        );


        Dimension sizeViewChnl = viewc.getPreferredSize();
        viewc.setBounds(225, 170, sizeViewChnl.width, sizeViewChnl.height);
        panel.setLayout(null);
        panel.add(viewc);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // border
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.add(panel);
        main.setLocation(700,400);
        main.setSize(650, 350);          // Box size
        main.setVisible(true);
        panel.setBackground(new Color(0x000000));  //Background Colour


       // ImageIcon image = new ImageIcon("src/com/company/logo.jpg");
        //        main.setIconImage(image.getImage());
    }

    private static void viewChannel(ArrayList<Doctor> doctors, ArrayList<Consultation> consultations) { // GUI channel

        JFrame frame = new JFrame();                      // New frame
        JPanel panel = new JPanel();                // New panel

        frame.getContentPane();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));   // Border
        frame.add(panel);
        frame.setSize(1000, 300);
        frame.setVisible(true);
        ImageIcon image = new ImageIcon("src/com/company/logo.jpg");
        frame.setIconImage(image.getImage());
        frame.setTitle("Westminster Skin Consultation Manager");  // Add title in project
        panel.setBackground(new Color(0x87A3A5));          // Background Colour

        JLabel  name = new JLabel("Enter Your Name:");              // Enter name
        name.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));  // Font
        Dimension size1entrName = name.getPreferredSize();
        name.setBounds(50, 75, size1entrName.width, size1entrName.height);
        panel.setLayout(null);
        panel.add(name);

        JTextField eynt = new JTextField();  // Name box
        eynt.setSize(150,20);
        eynt.setLocation(220, 75);
        panel.add(eynt);

        JLabel  sname = new JLabel("Enter Your SurName:");         // Enter sure name
        sname.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));    // Font
        Dimension size1entrSurName = sname.getPreferredSize();
        sname.setBounds(500, 75, size1entrSurName.width, size1entrSurName.height);
        panel.setLayout(null);
        panel.add(sname);

        JTextField eyst = new JTextField();     // Sure name box
        eyst.setSize(150,20);
        eyst.setLocation(700, 75);
        panel.add(eyst);

        JButton btn = new JButton("Submit");      // Submit button
        btn.setFont(new Font("Serif", Font.BOLD, 12));
        Dimension sizeSubmit = btn.getPreferredSize();
        btn.setBounds(450, 150, sizeSubmit.width, sizeSubmit.height);

        btn.addActionListener(e ->                                 // Going in to channel info
                {
                    JFrame onlyTable = new JFrame();
                    JPanel panelOnly = new JPanel();
                    JPanel panel2 = new JPanel();
                    onlyTable.getContentPane();
                    panelOnly.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    onlyTable.add(panelOnly);
                    onlyTable.setSize(1300, 800);
                    onlyTable.setVisible(true);

                    String[] cols = { "Date", "Doctor Name", "Start Time", "End Time","Cost","Notes" };  // Details
                    DefaultTableModel tableM = new DefaultTableModel(cols, 0);
                    JTable table = new JTable(tableM);                                  // Create table
                    JScrollPane sp = new JScrollPane(table);
                    onlyTable.add(sp);
                    panel2.setBackground(new Color(0x67178D));      // / Background Colour

                    ArrayList<Consultation> c1=new ArrayList<>();
                    for (Consultation consultation:consultations) {

                        if (consultation.getPtntName().equals(eynt.getText())){

                            if (consultation.getPtnSurName().equals(eyst.getText())){

                                c1.add(consultation);
                            }else{
                                JOptionPane.showMessageDialog(null, "No old consultation  your");
                            }
                        }else {
                            JOptionPane.showMessageDialog(null, "No old consultation  your");
                        }

                    }
                    for (Consultation consultation:c1) {

                        String date = consultation.getDate();
                        String dctrName = consultation.getDctrName();
                        String Start_Time = consultation.getStartTime();
                        String End_Time = consultation.getEndTime();
                        int Cost = consultation.getCost();
                        String Notes = consultation.getNotes();

                        Object[] data = {date, dctrName, Start_Time, End_Time, Cost, Notes};

                        tableM.addRow(data);


                    }
                }
        );
        panel.add(btn);



    }

    private static void bookDoctor(ArrayList<Doctor> doctors, ArrayList<Consultation> consultations) {  // Booking doctor

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.getContentPane();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(panel);
        frame.setSize(700, 900);
        frame.setVisible(true);
        ImageIcon image = new ImageIcon("src/com/company/logo.jpg");
        frame.setIconImage(image.getImage());
        frame.setTitle("Westminster Skin Consultation Manager");  // Add title in project
        panel.setBackground(new Color(0x2C9EBA));          // Background colour

        JLabel title = new JLabel("Patient Information");
        title.setFont(new Font("Arial", Font.PLAIN, 30));  // Font
        title.setSize(300, 30);
        title.setLocation(200, 40);
        panel.add(title);

        // Name
        JLabel painame = new JLabel("Patient Name:");
        painame.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
        Dimension size1 = painame.getPreferredSize();
        painame.setBounds(100, 100, size1.width, size1.height);
        panel.setLayout(null);
        panel.add(painame);

        JTextField pnt = new JTextField();
        pnt.setSize(150,20);
        pnt.setLocation(325, 100);
        panel.add(pnt);

        // sure Name
        JLabel  ps = new JLabel("Patient Sure Name:");
        painame.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));   // Font
        Dimension size5 = ps.getPreferredSize();
        ps.setBounds(100, 150, size5.width, size5.height);
        panel.setLayout(null);
        panel.add(ps);

        JTextField pst = new JTextField();
        pst.setSize(150,20);
        pst.setLocation(325, 150);
        panel.add(pst);

        // Calendar
        JLabel dob = new JLabel("Date of Birth:");
        painame.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));   // Font
        Dimension sizedate = dob.getPreferredSize();
        dob.setBounds(100, 200, sizedate.width, sizedate.height);
        panel.setLayout(null);
        panel.add(dob);

        JTextField dobt = new JTextField(20);
        dobt.setSize(150,20);
        dobt.setLocation(325, 200);
        panel.add(dobt);

        JButton pop1 = new JButton("Calendar");     // popup button
        Dimension sizePopup = pop1.getPreferredSize();
        pop1.setBounds(500, 200, sizePopup.width, sizePopup.height);
        panel.add(pop1);
        pop1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {dobt.setText(new calender(frame).setcalender());
            }
        });

        // Telephone number
        JLabel  tno = new JLabel("Telephone No:");
        tno.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));  //Font
        Dimension size7 = tno.getPreferredSize();
        tno.setBounds(100, 250, size7.width, size7.height);
        panel.setLayout(null);
        panel.add(tno);

        JTextField tpt = new JTextField();
        tpt.setSize(150,20);
        tpt.setLocation(325, 250);
        panel.add(tpt);

        // Consult details
        JLabel cons = new JLabel("Consult Information");
        cons.setFont(new Font("Arial", Font.PLAIN, 30)); // Font
        cons.setSize(300, 30);
        cons.setLocation(150, 290);
        panel.add(cons);

        // Date
        JLabel date = new JLabel("Date:");
        date.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12)); //font
        Dimension sizeDob = date.getPreferredSize();
        date.setBounds(100, 350, sizeDob.width, sizeDob.height);
        panel.setLayout(null);
        panel.add(date);

        JTextField dt = new JTextField(20);
        dt.setSize(150,20);
        dt.setLocation(325, 350);
        panel.add(dt);

        // Calender
        JButton pop2 = new JButton("Calender");
        Dimension size = pop2.getPreferredSize();
        pop2.setBounds(500, 350, size.width, size.height);
        panel.add(pop2);
        pop2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dt.setText(new calender(frame).setcalender());
            }
        });

        // Time
        JLabel  st = new JLabel("Start Time:");
        st.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));   // Font
        Dimension size9 = st.getPreferredSize();
        st.setBounds(100, 400, size9.width, size9.height);
        panel.setLayout(null);
        panel.add(st);


        String []startTimearray=new String[18];
        for (int i = 6; i < 18; i++) {
            startTimearray[i]= String.valueOf(i);
        }
        JComboBox stt=new JComboBox(startTimearray);
        stt.setSize(150,20);
        stt.setLocation(325, 400);
        panel.add(stt);


        String []h=new String[100];
        for (int i = 0; i <12; i++) {
            h[i]= String.valueOf(i+1);

        }

        // Hours
        JLabel  hour = new JLabel("Hours");
        hour.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12)); // Font
        Dimension sizehrs = hour.getPreferredSize();
        hour.setBounds(100, 450, sizehrs.width, sizehrs.height);
        panel.setLayout(null);
        panel.add(hour);


        JComboBox cmb=new JComboBox(h);
        cmb.setSize(150,20);
        cmb.setLocation(325, 450);
        panel.add(cmb);

        // Ending time
        JLabel  end = new JLabel("End Time:");
        end.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
        Dimension size10 = end.getPreferredSize();
        end.setBounds(100, 500, size10.width, size10.height);
        panel.setLayout(null);
        panel.add(end);

        JTextField ett = new JTextField();
        ett.setSize(150,20);
        ett.setLocation(325, 500);
        panel.add(ett);

        cmb.addActionListener(e ->{
            int getStartTime= Integer.parseInt(stt.getSelectedItem().toString());

            int howMnyHurs= Integer.parseInt(cmb.getSelectedItem().toString());
            int endManualfinalTime=getStartTime+howMnyHurs;
            ett.setText(String.valueOf(endManualfinalTime));
        } );



        String []dctrName=new String[10];
        for (int i = 0; i < doctors.size(); i++) {
            dctrName[i]=doctors.get(i).getName();
        }

        // Select doctor
        JLabel  seldoc = new JLabel("Doctor Name:");
        seldoc.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));  //Font
        Dimension size11 = seldoc.getPreferredSize();
        seldoc.setBounds(100, 550, size11.width, size11.height);
        panel.setLayout(null);
        panel.add(seldoc);

        JComboBox dnt=new JComboBox(dctrName);
        dnt.setSize(150,20);
        dnt.setLocation(325, 550);
        panel.add(dnt);

        // Description
        JLabel  des = new JLabel("Description");
        des.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));  // Font
        Dimension size12 = des.getPreferredSize();
        des.setBounds(100, 600, size12.width, size12.height);
        panel.setLayout(null);
        panel.add(des);

        JTextField dest = new JTextField();
        dest.setSize(150,20);
        dest.setLocation(325, 600);
        panel.add(dest);

        // user choice
        JLabel  yn = new JLabel("Is this your first consult " +
                "this doctor:");
        yn.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));  //Font
        Dimension sizeCalCost = yn.getPreferredSize();
        yn.setBounds(100, 650, sizeCalCost.width, sizeCalCost.height);
        panel.setLayout(null);
        panel.add(yn);

        // Select YES
        JButton yes = new JButton("Yes");
        yes.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12)); // Font
        Dimension sizeYes = yes.getPreferredSize();
        yes.setBounds(500, 650, sizeYes.width, sizeYes.height);
        panel.add(yes);

        // Select NO
        JButton no = new JButton("No");
        no.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));  //Font
        Dimension sizeNo = no.getPreferredSize();
        no.setBounds(550, 650, sizeNo.width, sizeNo.height);
        panel.add(no);

        // Calculate the cost
        JLabel  cost = new JLabel("Cost :");
        cost.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));  // Font
        Dimension sizecost = cost.getPreferredSize();
        cost.setBounds(100, 700, sizecost.width, sizecost.height);
        panel.setLayout(null);
        panel.add(cost);

        JTextField tcost = new JTextField();
        tcost.setSize(150,20);
        tcost.setLocation(325, 700);
        panel.add(tcost);

        yes.addActionListener(e ->{                  // Going in to hour calculate

            int hourscal= Integer.parseInt(cmb.getSelectedItem().toString());
            int finalCost=hourscal*15;
            tcost.setText(String.valueOf(finalCost));

        });
        no.addActionListener(e ->{                  // Going in to hour calculate
            int hourscalNo= Integer.parseInt(cmb.getSelectedItem().toString());
            int finalCost=hourscalNo*25;
            tcost.setText(String.valueOf(finalCost));

        });

        //booking doctor button
        JButton button = new JButton("Book Doctor");
        button.setFont(new Font("Serif", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(200, 50));
        Dimension sizebook = button.getPreferredSize();
        button.setBounds(250, 750, sizebook.width, sizebook.height);
        panel.add(button);
        button.addActionListener(e ->               // Going in to booking doctor
        {
            System.out.println("booking doctor ");

            Consultation consultation1 = new Consultation();
            consultation1.setDctrName(dnt.getSelectedItem().toString());
            consultation1.setDate(dt.getText());
            consultation1.setStartTime(stt.getSelectedItem().toString());
            consultation1.setNotes(dest.getText());
            consultation1.setEndTime(ett.getText());
            consultation1.setPtntName(pnt.getText());
            consultation1.setCost(Integer.parseInt(tcost.getText()));
            consultation1.setPtnDOB(dobt.getText());
            consultation1.setPtnTel(tpt.getText());
            consultation1.setPtnSurName(pst.getText());

            if (consultations.isEmpty()){
                consultations.add(consultation1);
            }else {
                for (Consultation consultation:consultations) {
                    if (consultation.getDctrName().equals(consultation1.getDctrName())){
                        if (consultation.getDate().equals(consultation1.getDate())){
                            int c1= Integer.parseInt(consultation.getStartTime());
                            int c2= Integer.parseInt(consultation.getEndTime());
                            int d1= Integer.parseInt(consultation1.getEndTime());
                            int d2= Integer.parseInt(consultation1.getStartTime());
                            if (c1>d1 || c2<d2){
                                consultations.add(consultation1);
                            }else{
                                System.out.println("This doctor was book..");
                                JOptionPane.showMessageDialog(null, "booked");
                            }
                        }else{
                            consultations.add(consultation1);
                        }

                    }else {
                        consultations.add(consultation1);
                    }
                }
            }
        });
        System.out.println("end");

    }
    private static void viewDoctor(ArrayList<Doctor> doc){          // View Doctor list

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.getContentPane();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(panel);
        frame.setSize(500, 300);
        frame.setVisible(true);
        ImageIcon image = new ImageIcon("src/com/company/logo.jpg");
        frame.setTitle("Westminster Skin Consultation Manager");  // Add title in project
        frame.setIconImage(image.getImage());
        //panel.setBackground(new Color(0xBBA121));           // Background colour));
        doc.sort(new Comparator<Doctor>() {


            @Override
            public int compare(Doctor d1, Doctor d2) {return d1.getSurName().compareTo(d2.getSurName());}

        });

        String[] Col = { "Name", "SurName", "Date of Birthday", "Mobile Num.","license Num.","Specialization" };
        DefaultTableModel tableM = new DefaultTableModel(Col, 0);
        JTable table = new JTable(tableM);
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);
        for (int i =0; i<doc.size();i++) {         // Doctosr Details
            String Name = doc.get(i).getName();
            String surName = doc.get(i).getSurName();
            String Dob = doc.get(i).getDob();
            String mblNumber = doc.get(i).getMblNumber();
            String liceneNumber = doc.get(i).getMedId();
            String Specialication = doc.get(i).getSpecialisation();
            Object[] data = {Name, surName, Dob, mblNumber,liceneNumber,Specialication};
            tableM.addRow(data);
        }

    }






}










