package presentation;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import data.RandomIO;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class FindDisplayGUI extends JFrame {
	private JTextField txtTo;
	private JTextField txtFrom;
	private JTextField txtKeyword;
	private JRadioButton rdbtnPriceRange,rdbtnKeyword,rdbtnAll;
	JTextArea textArea = new JTextArea();
	
	//create GUI
	public FindDisplayGUI() {
		setTitle("Find/Display/Delete Product ");
		getContentPane().setLayout(null);	
		
		rdbtnPriceRange = new JRadioButton("Price Range");
		rdbtnPriceRange.setBounds(20, 25, 103, 20);
		getContentPane().add(rdbtnPriceRange);
		
		rdbtnKeyword = new JRadioButton("Product ID");
		rdbtnKeyword.setBounds(20, 50, 103, 20);
		getContentPane().add(rdbtnKeyword);
		
		rdbtnAll = new JRadioButton("All");
		rdbtnAll.setSelected(true);
		rdbtnAll.setBounds(20, 75, 103, 20);
		getContentPane().add(rdbtnAll);
		
		ButtonGroup btr = new ButtonGroup();
		btr.add(rdbtnPriceRange);
		btr.add(rdbtnKeyword);
		btr.add(rdbtnAll);
		
		txtKeyword = new JTextField();
		txtKeyword.setText("keyword");
		txtKeyword.setBounds(140, 50, 125, 20);
		getContentPane().add(txtKeyword);
		txtKeyword.setColumns(10);
		
		txtTo = new JTextField();
		txtTo.setText("To");
		txtTo.setBounds(205, 25, 60, 20);
		getContentPane().add(txtTo);
		txtTo.setColumns(10);
		
		txtFrom = new JTextField();
		txtFrom.setText("From");
		txtFrom.setBounds(140, 25, 60, 20);
		getContentPane().add(txtFrom);
		txtFrom.setColumns(10);
		
		//find button
		JButton btnFind = new JButton("Find/Display");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String radio = "All";
				if(rdbtnPriceRange.isSelected()) { //find products by price range
					radio = "PriceRange";
				}
				else if(rdbtnKeyword.isSelected()) {
					radio = "Keyword";
				}
				if(radio.equals("All")) { //for all
					try {
						textArea.setText("");
						String detail = RandomIO.readProductsAll(); //display all products
						textArea.append(detail);		
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Read Data", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(radio.equals("PriceRange")) {
					if( Validator.isPresent(txtTo, "To") && Validator.isPresent(txtFrom, "From") ) {
						try {
							textArea.setText(""); //read products get data from and to field
							String detail = RandomIO.readProductsToFrom(Integer.parseInt(txtTo.getText()), Integer.parseInt(txtFrom.getText()) );
							textArea.append(detail);		
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Read Data", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				else if(radio.equals("Keyword")) { //find products by product id
					if( Validator.isPresent(txtKeyword, "Keyword") ) {
						try {
							textArea.setText("");
							String detail = RandomIO.readProductsKeyword(txtKeyword.getText());
							textArea.append(detail);		
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Read Data", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				
			}
		});
		btnFind.setBounds(300, 25, 122, 19);
		getContentPane().add(btnFind);
		
		
		//delete product
		JButton btnDelete = new JButton("Delete Product");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textArea.setText("");
					@SuppressWarnings("unused")
					//String detail = RandomIO.deleteProduct(txtKeyword.getText());
					String detail2 = RandomIO.readProductsAll();
					textArea.setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnDelete.setBounds(300, 50, 122, 19);
		getContentPane().add(btnDelete);
		
		//clear data
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAll.setSelected(true);
				txtFrom.setText("from");
				txtTo.setText("to");
				txtKeyword.setText("Product ID");
				textArea.setText("");

			}
		});
		btnClear.setBounds(300, 75, 122, 19);
		getContentPane().add(btnClear);	
		
		textArea.setBounds(22, 114, 743, 225);
		getContentPane().add(textArea);
	}

}
