// Importing Libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class Login extends JFrame implements ActionListener {
  // Declaring Variables Needed
  JLabel lblEmail, lblPass, lblInstruc;
  JTextField txtEmail;
  JPasswordField pwdPass;
  JButton btnConfirm, btnBack;
  
  public Login() {
    // Giving the window a title
    super("InsurePlus - Registration");

    // Setting window size
    setSize(400, 325);

    // Making the JPasswordField
    pwdPass = new JPasswordField(20);

    // Making the TextField
    txtEmail = new JTextField(20);

    // JLabels
    lblInstruc = new JLabel("Please enter your account credentials!");
    lblEmail = new JLabel("Email:");
    lblPass = new JLabel("Pass:");

    // Making the JButtons
    btnConfirm = new JButton("Login");
    btnBack = new JButton("Back");

    // Adding Action Listeners
    btnConfirm.addActionListener(this);
    btnBack.addActionListener(this);

    // Setting Layout
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = 2;
    c.gridx = 0;
    c.gridy = 0;
    add(lblInstruc, c);

    c.gridwidth = 1;
    c.gridy = 1;
    add(lblEmail, c);

    c.gridx = 1;
    add(txtEmail, c);

    c.gridy = 2;
    c.gridx = 0;
    add(lblPass, c);

    c.gridx = 1;
    add(pwdPass, c);

    c.gridy = 3;
    c.gridx = 0;
    add(btnConfirm, c);

    c.gridx = 1;
    c.ipadx = 0;
    c.insets = new Insets(0,150,0,0);
    add(btnBack, c);

    // Changing Window Icon
    Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
    setIconImage(icon);

    // Making it visible
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnConfirm) {
      // Checks if the fields are entered or empty
      if (txtEmail.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter a valid email and or password", "ERROR", JOptionPane.ERROR_MESSAGE);
      }
      else if (pwdPass.getPassword().length == 0) {
        JOptionPane.showMessageDialog(null, "Please enter a valid email and or password", "ERROR", JOptionPane.ERROR_MESSAGE);
      }
      else {
        // Encrypting all of the details entered to add to a Database file after
        String strEnEmail = txtEmail.getText();
        String strEnPass = EnDe.encrypt(pwdPass.getText());

        try {
          //Arraylist since then we dont need to declare how big it has to be
          ArrayList<String> strAccounts = new ArrayList<String>();
          // Needed for reading files
          Scanner sc = new Scanner(new File("Accounts.txt"));

          // read each line and count number of lines
          while (sc.hasNextLine()) {
            strAccounts.add(sc.nextLine());
          }

          int index = 0;
          boolean isCorrect = false;
          // Checks through the database file and the user entered details and if any match
          for (int i = 0; i < strAccounts.size(); i++) {
            String[] split = strAccounts.get(i).split(",");
            // Decrypts the email portion since an email isn't a case sensitive piece of information so this will ignore the cases which cannot be done with it encrypted
            if (strEnEmail.equalsIgnoreCase(EnDe.decrypt(split[split.length - 2]))) {
              if(strEnPass.equals(split[split.length - 1])) {
                index = i;
                isCorrect = true;
              }
            }
          }

          if (isCorrect == false) {
            // If false then prints out error
            JOptionPane.showMessageDialog(null, "Please check your email or password or sign up!", "ERROR", JOptionPane.ERROR_MESSAGE);
          }
          else {
            // Otherwise it logs in the user and decrypts the information
            String[] split = strAccounts.get(index).split(",");
            String[] strCustomer = new String[5];
            strCustomer[0] = EnDe.decrypt(split[0]);
            strCustomer[1] = EnDe.decrypt(split[1]);
            strCustomer[2] = EnDe.decrypt(split[2]);
            strCustomer[3] = EnDe.decrypt(split[3]);
            strCustomer[4] = EnDe.decrypt(split[4]);
            JOptionPane.showMessageDialog(null, "Logged in!", "InsurePlus", JOptionPane.PLAIN_MESSAGE);
            // Runs the option menu and dispose's of this window
            Options.main(strCustomer);
            Window w = SwingUtilities.getWindowAncestor(btnBack);
            w.dispose();
          }
          
        }
        catch (IOException error) {
          JOptionPane.showMessageDialog(null, "Please recheck all the fields and try again!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
    else if (e.getSource() == btnBack) {
      // Dispose of this window and go backs to the Menu
      Window w = SwingUtilities.getWindowAncestor(btnBack);
      Menu.main();
      w.dispose();
    }
  }

  public static void Existing() {
    new Login();
  }
}