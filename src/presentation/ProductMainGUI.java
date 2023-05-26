package presentation;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductMainGUI extends JFrame {
	public ProductMainGUI() {
		setTitle("Product Maintenance");
		//create GUI
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenuFile = new JMenu("File"); //menubar File
		menuBar.add(mnNewMenuFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit"); // menuitem exit the application
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenuFile.add(mntmExit);
		
		JMenu mnNewMenuProduct = new JMenu("Product"); //menubar Product, add update find delete
		menuBar.add(mnNewMenuProduct);
		
		JMenuItem mntmAdd = new JMenuItem("Add/Update");
		mntmAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUpdateGUI g1 = new AddUpdateGUI();
				g1.setSize(600,400);
				g1.setVisible(true);
			}
		});
		mnNewMenuProduct.add(mntmAdd);
		
		JMenuItem mntmFind = new JMenuItem("Find/Display");
		mntmFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindDisplayGUI g1 = new FindDisplayGUI();
				g1.setSize(800,400);
				g1.setVisible(true);
			}
		});
		mnNewMenuProduct.add(mntmFind);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product Maintenance System"); //cover
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(110, 80, 342, 64);
		getContentPane().add(lblNewLabel);
	}
}
