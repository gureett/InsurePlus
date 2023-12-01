// Importing Libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class Register extends JFrame implements ActionListener {
  // Declaring Variables
  JLabel lblName, lblAddress, lblPostal, lblCity, lblProvince, lblEmail, lblPassword;
  JComboBox<String> cmbProvince;
  JTextField txtName, txtAddress, txtPostal, txtCity, txtProvince, txtEmail;
  JPasswordField pwdPass;
  JButton btnConfirm, btnBack;
  
  public Register() {
    // Giving the window a title
    super("InsurePlus - Registration");

    // Setting window size
    setSize(400, 325);

    // Array of Strings for the combo box options
    String[] strProvinces = {"Alberta", "British Columbia", "Manitoba", "New Brunswick", "Newfoundland and Labrador", "Northwest Territories", "Nova Scotia", "Nunavut", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan", "Yukon"};
    // Making the combo box
    cmbProvince = new JComboBox<>(strProvinces);
    cmbProvince.setSelectedIndex(0);

    // Making the JPasswordField
    pwdPass = new JPasswordField(10);

    // Making the JButtons
    btnConfirm = new JButton("Register");
    btnBack = new JButton("Back");

    // Making the JTextFields
    txtName = new JTextField(20);
    txtAddress = new JTextField(20);
    txtPostal = new JTextField(20);
    txtCity = new JTextField(20);
    txtProvince = new JTextField(20);
    txtEmail = new JTextField(20);

    // Making the Labels
    lblName = new JLabel("Name:");
    lblAddress = new JLabel("Address:");
    lblPostal = new JLabel("Postal Code:");
    lblCity = new JLabel("City:");
    lblProvince = new JLabel("Province:");
    lblEmail = new JLabel("Email:");
    lblPassword = new JLabel("Password:");

    // Adding ActionListeners
    btnConfirm.addActionListener(this);
    btnBack.addActionListener(this);

    // Setting Layout
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    add(lblName, c);

    c.gridx = 1;
    add(txtName, c);

    c.gridx = 0;
    c.gridy = 1;
    add(lblAddress, c);

    c.gridx = 1;
    add(txtAddress, c);

    c.gridx = 0;
    c.gridy = 2;
    add(lblCity, c);

    c.gridx = 1;
    add(txtCity, c);

    c.gridx = 0;
    c.gridy = 3;
    add(lblPostal, c);

    c.gridx = 1;
    add(txtPostal, c);

    c.gridx = 0;
    c.gridy = 4;
    add(lblProvince, c);

    c.gridx = 1;
    add(cmbProvince, c);

    c.gridx = 0;
    c.gridy = 5;
    add(lblEmail, c);

    c.gridx = 1;
    add(txtEmail, c);

    c.gridx = 0;
    c.gridy = 6;
    add(lblPassword, c);

    c.gridx = 1;
    add(pwdPass, c);

    c.gridx = 0;
    c.gridy = 7;
    c.ipadx = 50;
    add(btnConfirm, c);

    c.gridx = 1;
    add(btnBack, c);

    // Changing Window Icon
    Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
    setIconImage(icon);

    // Making it visible
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    
    if (e.getSource() == btnConfirm) {
      // Checks if all the fields are entered correctly to its best knowledge (Checking if any of them are empty)
      if(txtName.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "Please enter all fields", "Error", JOptionPane.ERROR_MESSAGE);
      }
      else if(txtAddress.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "Please enter all fields", "Error", JOptionPane.ERROR_MESSAGE);
      }
      else if(txtCity.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "Please enter all fields", "Error", JOptionPane.ERROR_MESSAGE);
      }
      else if(txtPostal.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "Please enter all fields", "Error", JOptionPane.ERROR_MESSAGE);
      }
        // Checks the length of the postal code and if it is 6 or not
      else if(txtPostal.getText().length() > 6 || txtPostal.getText().length() < 6){
        JOptionPane.showMessageDialog(null, "Please enter a valid Postal Code!", "Error", JOptionPane.ERROR_MESSAGE);
      }
      else if(txtEmail.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "Please enter all fields", "Error", JOptionPane.ERROR_MESSAGE);
      }
      else if(pwdPass.getPassword().length == 0){
        JOptionPane.showMessageDialog(null, "Please enter a password!", "Error", JOptionPane.ERROR_MESSAGE);
      }
      else {
        // Adding all the unencrypted information into an array to send off to a different method later
        String[] strInfo = new String[5];
        strInfo[0] = txtName.getText();
        strInfo[1] = txtAddress.getText();
        strInfo[2] = txtCity.getText();
        strInfo[3] = txtPostal.getText();
        strInfo[4] = cmbProvince.getItemAt(cmbProvince.getSelectedIndex());

        // Encrypting all of the details entered to add to a Database file after
        String strEnName = EnDe.encrypt(txtName.getText());
        String strEnAddress = EnDe.encrypt(txtAddress.getText());
        String strEnCity = EnDe.encrypt(txtCity.getText());
        String strEnPostal = EnDe.encrypt(txtPostal.getText());
        String strEnProvince = EnDe.encrypt(cmbProvince.getItemAt(cmbProvince.getSelectedIndex()));
        String strEnEmail = EnDe.encrypt(txtEmail.getText());
        String strEnPass = EnDe.encrypt(pwdPass.getText());

        try {
          //Arraylist since then we dont need to declare how big it has to be
          ArrayList<String> strAccounts = new ArrayList<String>();
          // Needed for reading and writing files
          FileWriter fw = new FileWriter("Accounts.txt", true);
          Scanner sc = new Scanner(new File("Accounts.txt"));
          BufferedWriter bw = new BufferedWriter(fw);
          PrintWriter acc = new PrintWriter(fw);

          // read each line and count number of lines
          while (sc.hasNextLine()) {
            strAccounts.add(sc.nextLine());
          }

          // Checks if the account already exists with the email
          int isAcc = 0;
          for (int i = 0; i < strAccounts.size(); i++) {
            String[] split = strAccounts.get(i).split(",");
            if (strEnEmail.equals(split[split.length - 2])) {
              isAcc = -1;
            }
          }
          if (isAcc == -1) {
            // If so return error
            JOptionPane.showMessageDialog(null, "An account already exists with this Email!", "ERROR", JOptionPane.ERROR_MESSAGE);
          }
          else {
            //Otherwise add to file
            acc.println(strEnName + "," + strEnAddress + "," + strEnCity + "," + strEnPostal + "," + strEnProvince + "," + strEnEmail + "," + strEnPass);
            acc.close();
            String[] strCustomer = {txtName.getText(), txtAddress.getText(), txtCity.getText(), txtPostal.getText(), cmbProvince.getItemAt(cmbProvince.getSelectedIndex())};
            // Dispose of this window and starts the Options
            Options.main(strCustomer);
            Window w = SwingUtilities.getWindowAncestor(btnBack);
            w.dispose();
          }
        }
        catch (IOException error) {
          JOptionPane.showMessageDialog(null, "Please check all fields again and try again!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
    else if (e.getSource() == btnBack) {
      Window w = SwingUtilities.getWindowAncestor(btnBack);
      Menu.main();
      w.dispose();
    }
  }

  public static void Registration() {
    new Register();
  }
  
}