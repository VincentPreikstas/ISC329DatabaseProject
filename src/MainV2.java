//Java Swing/ awt Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Java JDBC Related Imports
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.*;
import java.io.File;
import java.io.IOException;

public class MainV2 {
    //  Database credentials
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/PetDatabase";

    static final String USER = "root";
    static final String PASS = "dongleDONG95";

    public static void main(String[] args){


            //Create all needed input fields
            JTextField customerNameTextField = new JTextField();
            JTextField customerIDTextField = new JTextField();
            JTextField petNameTextField = new JTextField();
            JTextField petIDTextField = new JTextField();
            JTextField employeeNameTextField = new JTextField();
            JTextField employeeIDTextField = new JTextField();
            JTextField appointmentByPetNameTextField = new JTextField();
            JTextField appointmentByEmployNameTextField = new JTextField();
            JTextField appointmentByDateTextField = new JTextField();

            //Format location of Text Fields
            customerNameTextField.setBounds(25, 25, 300, 20);
            customerIDTextField.setBounds(25, 75, 300, 20);
            petNameTextField.setBounds(25, 125, 300, 20);
            petIDTextField.setBounds(25, 175, 300, 20);
            employeeNameTextField.setBounds(25, 225, 300, 20);
            employeeIDTextField.setBounds(25, 275, 300, 20);
            appointmentByPetNameTextField.setBounds(25, 325, 300, 20);
            appointmentByEmployNameTextField.setBounds(25, 375, 300, 20);
            appointmentByDateTextField.setBounds(25, 425, 300, 20);


            //Create all necessary buttons
            Button customerNameButton = new Button("Search");
            Button customerIDButton = new Button("Search");
            Button petNameButton = new Button("Search");
            Button petIDButton = new Button("Search");
            Button employeeNameButton = new Button("Search");
            Button employeeIDButton = new Button("Search");
            Button appointmentByPetNameButton = new Button("Search");
            Button appointmentByEmployeeNameButton = new Button("Search");
            Button appointmentByDateButton = new Button("Search");

            //Format location of Buttons
            customerNameButton.setBounds(350, 25, 50, 20);
            customerIDButton.setBounds(350, 75, 50, 20);
            petNameButton.setBounds(350, 125, 50, 20);
            petIDButton.setBounds(350, 175, 50, 20);
            employeeNameButton.setBounds(350, 225, 50, 20);
            employeeIDButton.setBounds(350, 275, 50, 20);
            appointmentByPetNameButton.setBounds(350, 325, 50, 20);
            appointmentByEmployeeNameButton.setBounds(350, 375, 50, 20);
            appointmentByDateButton.setBounds(350, 425, 50, 20);

            //Create all necessary Labels
            JLabel customerNameLabel = new JLabel("Customer Name");
            JLabel customerIDLabel = new JLabel("Customer ID");
            JLabel petNameLabel = new JLabel("Pet Name");
            JLabel petIDLabel = new JLabel("Pet ID");
            JLabel employeeNameLabel = new JLabel("Employee Name");
            JLabel employeeIDLabel = new JLabel("Employee ID");
            JLabel appointmentByPetNameLabel = new JLabel("Appointment by Pet ID");
            JLabel appointmentByEmployeeLabel = new JLabel("Appointment by Employee ID");
            JLabel appointmentByDateLabel = new JLabel("Appointment by Date (yyyy-mm-dd)");

            //Format location of Labels
            customerNameLabel.setBounds(25, 50, 300, 20);
            customerIDLabel.setBounds(25, 100, 300, 20);
            petNameLabel.setBounds(25, 150, 300, 20);
            petIDLabel.setBounds(25, 200, 300, 20);
            employeeNameLabel.setBounds(25, 250, 300, 20);
            employeeIDLabel.setBounds(25, 300, 300, 20);
            appointmentByPetNameLabel.setBounds(25, 350, 300, 20);
            appointmentByEmployeeLabel.setBounds(25, 400, 300, 20);
            appointmentByDateLabel.setBounds(25, 450, 300, 20);

            //Create the text area
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            //Create scroll pane, put text area in it and set bounds
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setBounds(450, 25, 500, 475);

            class buttonListener implements ActionListener {

                Connection connection = null;
                Statement statement = null;
                PreparedStatement preparedStatement;
                ResultSet resultSet,resultSet2;



                public void actionPerformed(ActionEvent e) {
                    try {
                        Class.forName(JDBC_DRIVER);
                        connection = DriverManager.getConnection(DB_URL, USER, PASS);
                        statement = connection.createStatement();

                        if (e.getSource() == customerNameButton) {
                            //NOTE THIS WILL NOT WORK IF TWO CUSTOMERS HAVE EXACTLY THE SAME NAME
                            textArea.setText(null);
                            String userInput = customerNameTextField.getText();
                            String SQLquery = "SELECT customerID, phoneNumber, name, picture FROM Customer WHERE name = '" + userInput + "'";
                            String custName,custPhoneNumber,custID,custPicturePath,finished;
                            custID = "";
                            custName = "";
                            String petID, petName, petBreed, petType,finished2;
                            resultSet = statement.executeQuery(SQLquery);
                            while (resultSet.next()) {
                                custID = resultSet.getString("customerID");
                                custPhoneNumber = resultSet.getString("phoneNumber");
                                custName = resultSet.getString("name");
                                custPicturePath = resultSet.getString("picture");
                                finished = "Customer Name: " + custName + "\n" + "Customer ID: " + custID + "\n" + "Phone Number: " + custPhoneNumber + "\n" + "Picture Folder Location: " + custPicturePath + "\n\n" + "Pet(s) of " + custName + "\n";
                                textArea.append(finished);
                            }
                            SQLquery = "SELECT petID, name, breed, type FROM Pet WHERE customerID = " + custID;
                            resultSet2 = statement.executeQuery(SQLquery);
                            while (resultSet2.next()){
                                petID = resultSet2.getString("petID");
                                petName = resultSet2.getString("name");
                                petBreed = resultSet2.getString("breed");
                                petType = resultSet2.getString("type");
                                finished2 = "Pet Name: " + petName + "\n" + "Pet ID: " + petID + "\n" + "Type: " + petType + "\n" + "Breed: " + petBreed + "\n\n";
                                textArea.append(finished2);
                            }
                        }
                        if (e.getSource() == customerIDButton) {
                            textArea.setText(null);
                            String userInput = customerIDTextField.getText();
                            String SQLquery = "SELECT customerID, phoneNumber, name, picture FROM Customer WHERE customerID = " + userInput;
                            String custName,custPhoneNumber,custID,custPicturePath,finished;
                            custID = "";
                            custName = "";
                            String petID, petName, petBreed, petType,finished2;
                            resultSet = statement.executeQuery(SQLquery);
                            while (resultSet.next()) {
                                custID = resultSet.getString("customerID");
                                custPhoneNumber = resultSet.getString("phoneNumber");
                                custName = resultSet.getString("name");
                                custPicturePath = resultSet.getString("picture");
                                finished = "Customer Name: " + custName + "\n" + "Customer ID: " + custID + "\n" + "Phone Number: " + custPhoneNumber + "\n" + "Picture Folder Location: " + custPicturePath + "\n\n" + "Pet(s) of " + custName + "\n";
                                textArea.append(finished);
                            }
                            SQLquery = "SELECT petID, name, breed, type FROM Pet WHERE customerID = " + custID;
                            resultSet2 = statement.executeQuery(SQLquery);
                            while (resultSet2.next()){
                                petID = resultSet2.getString("petID");
                                petName = resultSet2.getString("name");
                                petBreed = resultSet2.getString("breed");
                                petType = resultSet2.getString("type");
                                finished2 = "Pet Name: " + petName + "\n" + "Pet ID: " + petID + "\n" + "Type: " + petType + "\n" + "Breed: " + petBreed + "\n\n";
                                textArea.append(finished2);
                            }

                        }
                        if (e.getSource() == petNameButton) {
                            textArea.setText(null);
                            String userInput = petNameTextField.getText();
                            String SQLquery = "SELECT petID, customerID, picture, name, age, breed, type, comments FROM Pet WHERE name = '" + userInput + "'";
                            String petName, custID, petPicturePath, petID, petAge, petBreed, petType, petComments, finished;
                            custID = "";
                            petID = "";
                            petName = "";
                            String recID, boolRabies, boolBordetella, boolDP2, recCertificatePDFs, finished2;
                            resultSet = statement.executeQuery(SQLquery);
                            while (resultSet.next()) {
                                petID = resultSet.getString("petID");
                                custID = resultSet.getString("customerID");
                                petPicturePath = resultSet.getString("picture");
                                petName = resultSet.getString("name");
                                petAge = resultSet.getString("age");
                                petBreed = resultSet.getString("breed");
                                petType = resultSet.getString("type");
                                petComments = resultSet.getString("comments");
                                finished = "Pet Name: " + petName + "\n" + "Pet ID: " + petID + "\n" + "Owner ID: " + custID + "\n" + "Picture Folder Location: " + petPicturePath + "\n" + "Age: " + petAge + "\n" + "Type: " + petType + "\n" + "Breed: " + petBreed + "\n" + "Comments: " + petComments + "\n\n";
                                textArea.append(finished);
                            }
                            SQLquery = "SELECT recordID, booleanRabies, booleanBordetella, booleanDP2, certificatePDFs FROM VetRecord WHERE petID = " + petID;
                            resultSet2 = statement.executeQuery(SQLquery);
                            while (resultSet2.next()){
                                recID = resultSet2.getString("recordID");
                                boolRabies = resultSet2.getString("booleanRabies");
                                boolBordetella = resultSet2.getString("booleanBordetella");
                                boolDP2 = resultSet2.getString("booleanDP2");
                                recCertificatePDFs = resultSet2.getString("certificatePDFs");
                                finished2 = "Vet Record for " + petName + "\n" + "Record ID: " + recID + "\n" + "Vaccine Rabies: " + boolRabies + "\n" + "Vaccine Bordetella: " + boolBordetella + "\n" + "Vaccine DP2: " + boolDP2 + "\n" + "Certificate Folder Location: " + recCertificatePDFs;
                                textArea.append(finished2);
                            }

                        }
                        if (e.getSource() == petIDButton) {
                            textArea.setText(null);
                            String userInput = petIDTextField.getText();
                            String SQLquery = "SELECT petID, customerID, picture, name, age, breed, type, comments FROM Pet WHERE petID = " + userInput;
                            String petName, custID, petPicturePath, petID, petAge, petBreed, petType, petComments, finished;
                            custID = "";
                            petID = "";
                            petName = "";
                            String recID, boolRabies, boolBordetella, boolDP2, recCertificatePDFs, finished2;
                            resultSet = statement.executeQuery(SQLquery);
                            while (resultSet.next()) {
                                petID = resultSet.getString("petID");
                                custID = resultSet.getString("customerID");
                                petPicturePath = resultSet.getString("picture");
                                petName = resultSet.getString("name");
                                petAge = resultSet.getString("age");
                                petBreed = resultSet.getString("breed");
                                petType = resultSet.getString("type");
                                petComments = resultSet.getString("comments");
                                finished = "Pet Name: " + petName + "\n" + "Pet ID: " + petID + "\n" + "Owner ID: " + custID + "\n" + "Picture Folder Location: " + petPicturePath + "\n" + "Age: " + petAge + "\n" + "Type: " + petType + "\n" + "Breed: " + petBreed + "\n" + "Comments: " + petComments + "\n\n";
                                textArea.append(finished);
                            }
                            SQLquery = "SELECT recordID, booleanRabies, booleanBordetella, booleanDP2, certificatePDFs FROM VetRecord WHERE petID = " + petID;
                            resultSet2 = statement.executeQuery(SQLquery);
                            while (resultSet2.next()){
                                recID = resultSet2.getString("recordID");
                                boolRabies = resultSet2.getString("booleanRabies");
                                boolBordetella = resultSet2.getString("booleanBordetella");
                                boolDP2 = resultSet2.getString("booleanDP2");
                                recCertificatePDFs = resultSet2.getString("certificatePDFs");
                                finished2 = "Vet Record for " + petName + "\n" + "Record ID: " + recID + "\n" + "Vaccine Rabies: " + boolRabies + "\n" + "Vaccine Bordetella: " + boolBordetella + "\n" + "Vaccine DP2: " + boolDP2 + "\n" + "Certificate Folder Location: " + recCertificatePDFs;
                                textArea.append(finished2);
                            }
                        }
                        if (e.getSource() == employeeNameButton) {
                            textArea.setText(null);
                            String userInput = employeeNameTextField.getText();
                            String SQLquery = "SELECT employeeID, address, name, picture, email, phoneNumber FROM Employee WHERE name = '" + userInput + "'";
                            String empID, empAddress, empName, empPicturePath, empEmail, empPhoneNumber, finished;
                            resultSet = statement.executeQuery(SQLquery);
                            while (resultSet.next()) {
                                empID = resultSet.getString("employeeID");
                                empAddress = resultSet.getString("address");
                                empName = resultSet.getString("name");
                                empPicturePath = resultSet.getString("picture");
                                empEmail = resultSet.getString("email");
                                empPhoneNumber = resultSet.getString("phoneNumber");
                                finished = "Employee Name: " + empName + "\n" + "ID: " + empID + "\n" + "Phone Number: " + empPhoneNumber + "\n" + "Email: " + empEmail + "\n" + "Address: " + empAddress + "\n" + "Picture Folder Location: " + empPicturePath;
                                textArea.append(finished);
                            }

                        }
                        if (e.getSource() == employeeIDButton) {
                            textArea.setText(null);
                            String userInput = employeeIDTextField.getText();
                            String SQLquery = "SELECT employeeID, address, name, picture, email, phoneNumber FROM Employee WHERE employeeID = " + userInput;
                            String empID, empAddress, empName, empPicturePath, empEmail, empPhoneNumber, finished;
                            resultSet = statement.executeQuery(SQLquery);
                            while (resultSet.next()) {
                                empID = resultSet.getString("employeeID");
                                empAddress = resultSet.getString("address");
                                empName = resultSet.getString("name");
                                empPicturePath = resultSet.getString("picture");
                                empEmail = resultSet.getString("email");
                                empPhoneNumber = resultSet.getString("phoneNumber");
                                finished = "Employee Name: " + empName + "\n" + "ID: " + empID + "\n" + "Phone Number: " + empPhoneNumber + "\n" + "Email: " + empEmail + "\n" + "Address: " + empAddress + "\n" + "Picture Folder Location: " + empPicturePath;
                                textArea.append(finished);
                            }
                        }
                        if (e.getSource() == appointmentByPetNameButton) {
                            textArea.setText(null);
                            String userInput = appointmentByPetNameTextField.getText();
                            String SQLquery = "SELECT employeeID, petID, date, time, comments FROM Appointment WHERE petID = " + userInput;
                            String employeeID, petID, date, time, comments, finished;
                            resultSet = statement.executeQuery(SQLquery);
                            while (resultSet.next()) {
                                employeeID = resultSet.getString("employeeID");
                                petID = resultSet.getString("petID");
                                date = resultSet.getString("date");
                                time = resultSet.getString("time");
                                comments = resultSet.getString("comments");
                                finished = "Employee ID: " + employeeID + "\n" + "Pet ID: " + petID + "\n" + "Date: " + date + "\n" + "Time: " + time + "\n" + "Comments: " + comments + "\n\n";
                                textArea.append(finished);
                            }
                        }
                        if (e.getSource() == appointmentByEmployeeNameButton) {
                            textArea.setText(null);
                            String userInput = appointmentByEmployNameTextField.getText();
                            String SQLquery = "SELECT employeeID, petID, date, time, comments FROM Appointment WHERE employeeID = " + userInput;
                            String employeeID, petID, date, time, comments, finished;
                            resultSet = statement.executeQuery(SQLquery);
                            while (resultSet.next()) {
                                employeeID = resultSet.getString("employeeID");
                                petID = resultSet.getString("petID");
                                date = resultSet.getString("date");
                                time = resultSet.getString("time");
                                comments = resultSet.getString("comments");
                                finished = "Employee ID: " + employeeID + "\n" + "Pet ID: " + petID + "\n" + "Date: " + date + "\n" + "Time: " + time + "\n" + "Comments: " + comments + "\n\n";
                                textArea.append(finished);
                            }
                        }
                        if (e.getSource() == appointmentByDateButton) {
                            textArea.setText(null);
                            String userInput = appointmentByDateTextField.getText();
                            String SQLquery = "SELECT employeeID, petID, date, time, comments FROM Appointment WHERE date = '" + userInput + "'";
                            String employeeID, petID, date, time, comments, finished;
                            resultSet = statement.executeQuery(SQLquery);
                            while (resultSet.next()) {
                                employeeID = resultSet.getString("employeeID");
                                petID = resultSet.getString("petID");
                                date = resultSet.getString("date");
                                time = resultSet.getString("time");
                                comments = resultSet.getString("comments");
                                finished = "Employee ID: " + employeeID + "\n" + "Pet ID: " + petID + "\n" + "Date: " + date + "\n" + "Time: " + time + "\n" + "Comments: " + comments + "\n\n";
                                textArea.append(finished);
                            }
                        }
                        //Could put closes here
                    } catch (SQLException se) {
                        //HANDLING BAD INPUT IN EXCEPTION CATCH IS SLOPPY SHOULD CHANGE LATER
                        //se.printStackTrace();
                        textArea.setText("That is not valid Input! Please try again.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        try {
                            if (statement != null)
                                statement.close();
                        } catch (SQLException se2) {
                        }// nothing we can do
                        try {
                            if (connection != null)
                                connection.close();
                        } catch (SQLException se) {
                            se.printStackTrace();
                        }
                    }
                }
            }

            //Create the frame to fit everything
            JFrame frame = new JFrame("Pet Database GUI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Create listener
            buttonListener listener = new buttonListener();

            //Add listener to buttons
            customerNameButton.addActionListener(listener);
            customerIDButton.addActionListener(listener);
            petNameButton.addActionListener(listener);
            petIDButton.addActionListener(listener);
            employeeNameButton.addActionListener(listener);
            employeeIDButton.addActionListener(listener);
            appointmentByPetNameButton.addActionListener(listener);
            appointmentByEmployeeNameButton.addActionListener(listener);
            appointmentByDateButton.addActionListener(listener);

            //Add Text Fields to Frame
            frame.add(customerNameTextField);
            frame.add(customerIDTextField);
            frame.add(petNameTextField);
            frame.add(petIDTextField);
            frame.add(employeeNameTextField);
            frame.add(employeeIDTextField);
            frame.add(appointmentByPetNameTextField);
            frame.add(appointmentByEmployNameTextField);
            frame.add(appointmentByDateTextField);

            //Add Buttons to Frame
            frame.add(customerNameButton);
            frame.add(customerIDButton);
            frame.add(petNameButton);
            frame.add(petIDButton);
            frame.add(employeeNameButton);
            frame.add(employeeIDButton);
            frame.add(appointmentByPetNameButton);
            frame.add(appointmentByEmployeeNameButton);
            frame.add(appointmentByDateButton);

            //Add Labels to Frame
            frame.add(customerNameLabel);
            frame.add(customerIDLabel);
            frame.add(petNameLabel);
            frame.add(petIDLabel);
            frame.add(employeeNameLabel);
            frame.add(employeeIDLabel);
            frame.add(appointmentByPetNameLabel);
            frame.add(appointmentByEmployeeLabel);
            frame.add(appointmentByDateLabel);

            //Add Text Field to Frame and set Frame Variables
            frame.setSize(1000, 550);
            frame.add(scrollPane);
            frame.setLayout(null);
            frame.setVisible(true);



    }
}
