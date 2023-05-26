package presentation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import business.Product;
import data.RandomIO;

import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AddUpdateGUI extends JFrame {
	private int top;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtQuantity;
	private JTextField txtUnitPrice;
	public AddUpdateGUI() {
		//create GUI
		setTitle("Add or Update Product");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabelID = new JLabel("Product Id");
		lblNewLabelID.setBounds(25, 30, 65, 13);
		getContentPane().add(lblNewLabelID);
		
		JLabel lblNewLabelName = new JLabel("Name");
		lblNewLabelName.setBounds(50, 67, 45, 13);
		getContentPane().add(lblNewLabelName);
		
		JLabel lblNewLabelDesc = new JLabel("Description");
		lblNewLabelDesc.setBounds(18, 102, 68, 13);
		getContentPane().add(lblNewLabelDesc);
		
		txtID = new JTextField();
		txtID.setBounds(102, 27, 100, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(102, 64, 170, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JTextArea textAreaDescription = new JTextArea();
		textAreaDescription.setBounds(104, 102, 167, 64);
		getContentPane().add(textAreaDescription);
		
		JLabel lblNewLabelQuantity = new JLabel("Stocks");
		lblNewLabelQuantity.setBounds(350, 102, 96, 13);
		getContentPane().add(lblNewLabelQuantity);
		
		JLabel lblNewLabelUnitPrice = new JLabel("Price");
		lblNewLabelUnitPrice.setBounds(360, 144, 84, 13);
		getContentPane().add(lblNewLabelUnitPrice);
		
		txtQuantity = new JTextField();
		txtQuantity.setBounds(401, 102, 96, 20);
		getContentPane().add(txtQuantity);
		txtQuantity.setColumns(10);
		
		txtUnitPrice = new JTextField();
		txtUnitPrice.setBounds(401, 142, 96, 20);
		getContentPane().add(txtUnitPrice);
		txtUnitPrice.setColumns(10);
		
		//add product 
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( Validator.isPresent(txtID, "Product Id") && Validator.isPresent(txtName, "Product Name") && Validator.isPresent(textAreaDescription, "Product Description") && Validator.isPresent(txtQuantity, "Product Quantity") && Validator.isPresent(txtUnitPrice, "Product Unit Price") ) {
					String productId = txtID.getText();
					String productName = txtName.getText();
					String productDesc = textAreaDescription.getText();
					try {
						int productQuantity = Integer.parseInt(txtQuantity.getText());
						if(productQuantity <= 0) {
							throw new Exception ("Quantity Should be greater than zero"); //error if quantity is less than or equal to zero
						}
						try {
							int productUnitPrice = Integer.parseInt(txtUnitPrice.getText());
							if(productUnitPrice <= 0) {
								throw new Exception ("Unit Price Should be greater than zero"); //error if price is less than 0
							}
							RandomIO.saveProduct(productId, productName, productDesc, productQuantity, productUnitPrice); //once all validation done, product is saved to file
							JOptionPane.showMessageDialog(null, "Product saved to file.", "Product Saved", JOptionPane.INFORMATION_MESSAGE);
						}
						catch(NumberFormatException nfe){ //validate price = should be integer
							JOptionPane.showMessageDialog(null, "Unit Price Should be Integer", "Unit Price Error", JOptionPane.ERROR_MESSAGE);
						}
						catch (Exception e2) { 
							JOptionPane.showMessageDialog(null, e2, "Unit Price Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					catch(NumberFormatException nfe){ //validate quantity = should be integer
						JOptionPane.showMessageDialog(null, "Quantity Should be Integer", "Quantity Error", JOptionPane.ERROR_MESSAGE);
					}
					catch (Exception e1) { 
						JOptionPane.showMessageDialog(null, e1, "Quantity Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAdd.setBounds(200, 199, 85, 20);
		getContentPane().add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( Validator.isPresent(txtID, "Product Id") && Validator.isPresent(txtName, "Product Name") && Validator.isPresent(textAreaDescription, "Product Description") && Validator.isPresent(txtQuantity, "Product Quantity") && Validator.isPresent(txtUnitPrice, "Product Unit Price") ) {
					String productId = txtID.getText();
					String productName = txtName.getText();
					String productDesc = textAreaDescription.getText();
					try {
						int productQuantity = Integer.parseInt(txtQuantity.getText());
						if(productQuantity <= 0) {
							throw new Exception("Quantity Should be greater than zero");
						}
						try {
							int productUnitPrice = Integer.parseInt(txtUnitPrice.getText());
							if(productUnitPrice <= 0) {
								throw new Exception("Unit Price Should be greater than zero");
							}
							RandomIO.updateProduct(productId, productName, productDesc, productQuantity, productUnitPrice, top);
							JOptionPane.showMessageDialog(null, "Product saved to file.", "Product Saved", JOptionPane.INFORMATION_MESSAGE);
						}
						catch(NumberFormatException nfe){
							JOptionPane.showMessageDialog(null, "Unit Price Should be Integer", "Unit Price Error", JOptionPane.ERROR_MESSAGE);
						}
						catch (Exception e2) {
							JOptionPane.showMessageDialog(null, e2, "Unit Price Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					catch(NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, "Quantity Should be Integer", "Quantity Error", JOptionPane.ERROR_MESSAGE);
					}
					catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1, "Quantity Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnUpdate.setBounds(300, 199, 85, 20);
		getContentPane().add(btnUpdate);
		
		JButton btnFirst = new JButton("First");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					top = 1; //to display the first product
					Product p = RandomIO.readProduct(top);
					txtID.setText(p.getPID());
					txtName.setText(p.getPName());
					textAreaDescription.setText(p.getPDesc());
					txtQuantity.setText(String.valueOf(p.getPQuantity()));
					txtUnitPrice.setText(String.valueOf(p.getPUnitPrice()));
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnFirst.setBounds(100, 230, 85, 20);
		getContentPane().add(btnFirst);
		
		JButton btnPrevious = new JButton("Prev");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					top = top - 1; //shows prev product
					if( top > 0 ) {
						Product p = RandomIO.readProduct(top);
						txtID.setText(p.getPID());
						txtName.setText(p.getPName());
						textAreaDescription.setText(p.getPDesc());
						txtQuantity.setText(String.valueOf(p.getPQuantity()));
						txtUnitPrice.setText(String.valueOf(p.getPUnitPrice()));
					} else {
						throw new Exception("Data Not Available.");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1, "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1, "Unit Price Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPrevious.setBounds(200, 230, 85, 20);
		getContentPane().add(btnPrevious);
		
		JButton btnNext = new JButton("Next"); //shows the next product
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					top = top + 1;
					if( top <= RandomIO.getNumberOfRecords() ) {
						Product p = RandomIO.readProduct(top);
						txtID.setText(p.getPID());
						txtName.setText(p.getPName());
						textAreaDescription.setText(p.getPDesc());
						txtQuantity.setText(String.valueOf(p.getPQuantity()));
						txtUnitPrice.setText(String.valueOf(p.getPUnitPrice()));
					} else {
						throw new Exception("Data Not Available."); 
					}
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1, "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1, "Unit Price Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNext.setBounds(301, 230, 85, 20);
		getContentPane().add(btnNext);
		
		JButton btnLast = new JButton("Last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					top = RandomIO.getNumberOfRecords(); //shows the last record
					Product p = RandomIO.readProduct(top);
					txtID.setText(p.getPID());
					txtName.setText(p.getPName());
					textAreaDescription.setText(p.getPDesc());
					txtQuantity.setText(String.valueOf(p.getPQuantity()));
					txtUnitPrice.setText(String.valueOf(p.getPUnitPrice()));
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1, "Last show Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLast.setBounds(402, 230, 85, 20);
		getContentPane().add(btnLast);
	}
}
