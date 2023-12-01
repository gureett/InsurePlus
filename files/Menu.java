/*
Name: Gureet Kharod
Date: December 14th / 2022
Desc: Takes in multiple insurance policies and according to what the user wants and the price they want, it will search and sort by price and give the best price option to the User.
*/

// Importing Libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Menu extends JFrame implements ActionListener {

  JButton btnRegister, btnExisting;
  JLabel lblAsk1, lblAsk2;

  public Menu() {
    // Giving the window a title
    super("InsurePlus");

    // Setting window size
    setSize(350, 150);

    btnRegister = new JButton("Register");
    btnExisting = new JButton("Login");
    lblAsk1 = new JLabel("If you are a new user, click the register button");
    lblAsk2 = new JLabel("Existing users click login!");

    // Adding Action Listener
    btnRegister.addActionListener(this);
    btnExisting.addActionListener(this);

    // Setting Layout
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(0,10,0,0);
    c.weightx = 3;
    c.gridwidth = 2;
    c.gridx = 0;
    c.gridy = 0;
    add(lblAsk1, c);

    c.gridy = 1;
    c.insets = new Insets(0,75,0,0);
    add(lblAsk2, c);

    c.gridwidth = 1;
    c.gridy = 2;
    c.insets = new Insets(10,50,0,0);
    add(btnRegister, c);

    c.gridx = 1;
    c.insets = new Insets(10,72,0,50);
    add(btnExisting, c);
    
    // Changing Window Icon
    Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
    setIconImage(icon);

    // Making it visible
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnRegister) {
      Window w = SwingUtilities.getWindowAncestor(btnRegister); 
      Register.Registration();
      w.dispose();
    }
    else if (e.getSource() == btnExisting) {
      Window w = SwingUtilities.getWindowAncestor(btnExisting); 
      Login.Existing();
      w.dispose();
    }
  }
  
  public static void main() {
    // Runs the GUI
    new Menu();
  }
}