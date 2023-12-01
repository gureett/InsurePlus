// Importing Libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.io.*;

public class Options extends JPanel implements ActionListener {
  // Declaring the various JPanel Elements needed
  JCheckBox chkLWD, chkColli, chkCompre, chkGTS, chkRGC, chkAcc;
  JButton btnNext, btnHelp, btnExit, btnBack;
  JLabel lblOptions, lblPrice, lblOut;
  JComboBox<String> cmbPrice;
  JPanel p, btnPanel, chkPanel;

  public static String[] strCustomer;
  
  public Options() {
    // Sets the Border Layout
    super(new BorderLayout());

    // Creating the JComboBox
    String[] prices = {"$0-500 ", "$500-750", "$750-1000", "$1000-1500", "$1500-2000", "$2000-2500", "$2500-3000", "$3000+"}; // TEMP VARIABLES!!! CHANGE THEM
    cmbPrice = new JComboBox<>(prices);
    cmbPrice.setSelectedIndex(0);

    // Making the JLabels
    lblOptions = new JLabel("Optional Coverages:");
    lblPrice = new JLabel("Preferred Price Range:");
    
    // Making the JCheckBoxes
    chkLWD = new JCheckBox("Limited Waiver of Depreciation");
    chkLWD.setMnemonic(KeyEvent.VK_C);
    chkLWD.setSelected(false);

    chkColli = new JCheckBox("Collision Coverage");
    chkColli.setMnemonic(KeyEvent.VK_C);
    chkColli.setSelected(false);

    chkCompre = new JCheckBox("Comprehensive Coverage");
    chkCompre.setMnemonic(KeyEvent.VK_C);
    chkCompre.setSelected(false);

    chkGTS = new JCheckBox("Grand Touring Solution");
    chkGTS.setMnemonic(KeyEvent.VK_C);
    chkGTS.setSelected(false);

    chkRGC = new JCheckBox("Restriction Glass Coverage");
    chkRGC.setMnemonic(KeyEvent.VK_C);
    chkRGC.setSelected(false);

    chkAcc = new JCheckBox("Accident Forgiveness");
    chkAcc.setMnemonic(KeyEvent.VK_C);
    chkAcc.setSelected(false);
    
    // Making the JButtons
    btnNext = new JButton("Next");
    btnHelp = new JButton("Help");
    btnExit = new JButton("Exit");
    btnBack = new JButton("Logout");

    // Adding All Action Listeners
    chkLWD.addActionListener(this);
    chkColli.addActionListener(this);
    chkCompre.addActionListener(this);
    chkGTS.addActionListener(this);
    chkRGC.addActionListener(this);
    chkAcc.addActionListener(this);
    btnNext.addActionListener(this);
    btnHelp.addActionListener(this);
    btnExit.addActionListener(this);
    btnBack.addActionListener(this);
    cmbPrice.addActionListener(this);

    // Adding all the check boxes and the combobox to one panel to be all together
    chkPanel = new JPanel(new GridLayout(0, 1));
    chkPanel.add(lblOptions);
    chkPanel.add(chkLWD);
    chkPanel.add(chkColli);
    chkPanel.add(chkCompre);
    chkPanel.add(chkGTS);
    chkPanel.add(chkRGC);
    chkPanel.add(chkAcc);
    chkPanel.add(lblPrice);
    chkPanel.add(cmbPrice);

    // Adding all the buttons to all be together
    btnPanel= new JPanel();
    btnPanel.add(btnNext);
    btnPanel.add(btnHelp);
    //btnPanel.add(btnExit);
    btnPanel.add(btnBack);

    // Adding them all to the JPanel
    /*add(chkPanel, BorderLayout.LINE_START);
    add(btnPanel, BorderLayout.SOUTH);*/
    p = new JPanel();
    p.setLayout(new BorderLayout());
    p.add(chkPanel, BorderLayout.LINE_START);
    p.add(btnPanel,BorderLayout.SOUTH);
    add(p, BorderLayout.CENTER);
    // Sets the Border of the Window
    setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
  }

  public void actionPerformed(ActionEvent e) {
    // Sets the selectedPrice string to the default value given by the ComboBox
    if (e.getSource() == btnNext) {
      // When the ComboBox is changed, it gets the combobox selected item and replace the string information
      //JComboBox<String> cb = (JComboBox)e.getSource();
      String selectedPrice = cmbPrice.getItemAt(cmbPrice.getSelectedIndex());//(String)cb.getSelectedItem();

      // Finding how long to make the array
      int ctr = 0;
      if (chkLWD.isSelected()) {
        ctr++;
      }
      if(chkColli.isSelected()) {
        ctr++;
      }
      if(chkCompre.isSelected()) {
        ctr++;
      }
      if(chkGTS.isSelected()) {
        ctr++;
      }
      if(chkRGC.isSelected()) {
        ctr++;
      }
      if(chkAcc.isSelected()) {
        ctr++;
      }

      String[] options = new String[ctr];
      ctr = 0;
      if (chkLWD.isSelected()) {
        chkLWD.setSelected(false);
        options[ctr] = "LWD";
        ctr++;
      }
      if(chkColli.isSelected()) {
        chkColli.setSelected(false);
        options[ctr] = "COL";
        ctr++;
      }
      if(chkCompre.isSelected()) {
        chkCompre.setSelected(false);
        options[ctr] = "COM";
        ctr++;
      }
      if(chkGTS.isSelected()) {
        chkGTS.setSelected(false);
        options[ctr] = "GTS";
        ctr++;
      }
      if(chkRGC.isSelected()) {
        chkRGC.setSelected(false);
        options[ctr] = "RGC";
        ctr++;
      }
      if(chkAcc.isSelected()) {
        chkAcc.setSelected(false);
        options[ctr] = "ACC";
        ctr++;
      }
      
      // Asking User to import the text file with all the policies
      String file = "policies.txt";
      // Plan: Make Seperate arrays, one for name and one for the prices, sort the prices by highest to lowest, print out to file and reciept, make two JPanels to switch, one for the help screen and one for the reciept
      String[] array = FindBestDeal.findBest(file, selectedPrice, options);
      try {
        // Putting the information in suitable Variables
        String name = array[0];
        String[] selected = new String[array.length - 1];
        double[] price = new double[array.length - 1];
        // Assigning Values
        selected[0] = "Bodily Injury: ";
        selected[1] = "Property Damage: ";
        selected[2] = "Direct Compensation: ";
        selected[3] = "Accident Benefits: ";
        selected[4] = "Uninsured Motorist: ";
        for (int i = 5; i < selected.length - 1; i++) {
          if (options[i-5].equals("LWD")) {
            selected[i] = "Limited Waiver of Depreciation: ";
          }
          else if (options[i-5].equals("COL")) {
            selected[i] = "Collision Coverage: ";
          }
          else if (options[i-5].equals("COM")) {
            selected[i] = "Comprehensive Coverage: ";
          }
          else if (options[i-5].equals("GTS")) {
            selected[i] = "Grand Touring Solution: ";
          }
          else if (options[i-5].equals("RGC")) {
            selected[i] = "Restriction Glass Coverage: ";
          }
          else if (options[i-5].equals("ACC")) {
            selected[i] = "Accident Forgiveness: ";
          }
        }
        selected[selected.length - 1] = "Total: ";
        for (int l = 1; l < array.length; l++) {
          price[l - 1] = Double.parseDouble(array[l]);
        }
        Sorting.quickSort2(price, selected, 0, price.length - 1);
        // either call another method or use joptionpane
        String out = "Name: " + strCustomer[0] + "\nAddress: " + strCustomer[1] + "\nCity: " + strCustomer[2] + "\nPostal: " + strCustomer[3] + "\nProvince: " + strCustomer[4] + "\n\n"; 
        //Number Formatter
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        for (int i = 0; i < selected.length; i++) {
          out = out + selected[i] + nf.format(price[i]) + "\n";
        }
        //Outputs to File
        PrintWriter pw = new PrintWriter(new FileWriter("reciept.txt"));
        pw.println(out);
        pw.close();
        // Outputs to JOptionPane
        JOptionPane.showMessageDialog(null, out, "Reciept", JOptionPane.PLAIN_MESSAGE);
      } catch (Exception error) {
        JOptionPane.showMessageDialog(null, "There is no insurance policies within your preferred price range", "ERROR", JOptionPane.ERROR_MESSAGE);
      }
      
    }
    else if (e.getSource() == btnHelp) {
      // JOptionPane showing Help Information
      JOptionPane.showMessageDialog(null, "Help: \n Select the optional coverages you would \n like on your Insurance Plan \n Then select your preferred price range \n Then click Next \n Press Exit to Exit the Program", "Help", JOptionPane.PLAIN_MESSAGE);
    }
    else if (e.getSource() == btnExit) {
      // Exits Program
      System.exit(0);
    }
    else if (e.getSource() == btnBack) {
      // Dispose of this window and go backs to the Menu
      Window w = SwingUtilities.getWindowAncestor(btnBack);
      Menu.main();
      w.dispose();
    }
  }

  

  private static void createAndShowGUI() {
    // Creates New JFrame with that Window Name
    JFrame frame = new JFrame("InsurePlus");
    // Setting the Windows Icon
    Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
    frame.setIconImage(icon);
    // Sets the default closing operation to exit
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Making a new content pane which is the Main()
    JComponent newContentPane = new Options();
    // Makes it opaque so it isn't transparent (See Through)
    newContentPane.setOpaque(true);
    // Setting the JFrames contents with the new Content Pane
    frame.setContentPane(newContentPane);

    // Packs it together and makes it visible to user
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] strTemp) {
    strCustomer = strTemp;
    // Runs the JFrame
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
}