package data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import business.Product;

public class RandomIO {
private static File cFile = new File("ProductText.txt"); //file name
	
	public static void saveProduct(String pID, String pName, String pDesc, int pQuantity, int pUnitPrice) throws Exception {
		ArrayList productIDlist = readProductId(); //arraylist to read the product id
		
		if(productIDlist.contains(pID)) //if already exists will prompt an error
			throw new Exception("Product Already Exist");
		try(DataOutputStream dOUT = new DataOutputStream(new FileOutputStream(cFile, true));){
			writeFixedString(dOUT, Product.SIZE_P_ID, pID);
			writeFixedString(dOUT, Product.SIZE_P_NAME, pName);
			writeFixedString(dOUT, Product.SIZE_P_DESC, pDesc);
			dOUT.writeInt(pQuantity);
			dOUT.writeInt(pUnitPrice);
		}
	}
	
	public static Product readProduct(int recordNumber) throws IOException{ //read product
		Product product = null;
		try(RandomAccessFile dFile = new RandomAccessFile(cFile, "r")){
			if(getNumberOfRecords() >= recordNumber) {
				dFile.seek((recordNumber-1) * Product.RECORD_SIZE);
				product = new Product();
				product.setPID(getFixedString(dFile, Product.SIZE_P_ID));
				product.setPName(getFixedString(dFile, Product.SIZE_P_NAME));
				product.setPDesc(getFixedString(dFile, Product.SIZE_P_DESC));
				product.setPQuantity(dFile.readInt());
				product.setPUnitPrice(dFile.readInt());
			}
		}
		return product;
		
	}
	
	public static int getNumberOfRecords() throws IOException{
		int count = 0;
		try(RandomAccessFile dFile = new RandomAccessFile(cFile, "r")){ //get number of records
			count = (int)dFile.length()/Product.RECORD_SIZE;
		}
		return count;
		
	}
	
	public static void writeFixedString(DataOutput out, int length, String s) throws IOException{
		for (int i = 0; i < length; i++) {
			if(i < s.length())
				out.writeChar(s.charAt(i)); 
			else
				out.writeChar(0);
		}
	}
	
	public static String getFixedString(DataInput in, int length) throws IOException{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			char c = in.readChar();
			if (c != 0)
				sb.append(c);
		}
		return sb.toString();
	}
	public static void updateProduct(String pID, String pName, String pDesc, int pQuantity, int pUnitPrice , int top) throws IOException{
		
		int position = top - 1; //to update the product
		
		Product productUpdated = new Product();
		productUpdated.setPID(pID);
		productUpdated.setPName(pName);
		productUpdated.setPDesc(pDesc);
		productUpdated.setPQuantity(pQuantity);
		productUpdated.setPUnitPrice(pUnitPrice);
		
		ArrayList<Product> productList = readProducts();
		
		productList.remove(position);
		productList.add(position, productUpdated);
		
		try(RandomAccessFile dFile = new RandomAccessFile(cFile, "rw");
				DataOutputStream dOut = new DataOutputStream(new FileOutputStream(cFile, true));){
			dFile.setLength(0);
			dFile.seek(0);
			for (int i=0; i < productList.size(); i++) {
				dFile.seek((i) * Product.RECORD_SIZE);
				writeFixedString(dOut, Product.SIZE_P_ID, productList.get(i).getPID());
				writeFixedString(dOut, Product.SIZE_P_NAME, productList.get(i).getPName());
				writeFixedString(dOut, Product.SIZE_P_DESC, productList.get(i).getPDesc());
				dOut.writeInt( productList.get(i).getPQuantity());
				dOut.writeInt(productList.get(i).getPUnitPrice());
			}
		}
	}
	public static ArrayList<Product> readProducts() throws IOException { //read products in the arraylist
		ArrayList<Product> products = new ArrayList<Product>();
		Product product = null;
		int length = getNumberOfRecords();
		try(RandomAccessFile dFile = new RandomAccessFile(cFile, "r")){
			for (int i = 0; i < length; i++) {
				dFile.seek((i) * Product.RECORD_SIZE);
				product = new Product();
				product.setPID(getFixedString(dFile, Product.SIZE_P_ID));
				product.setPName(getFixedString(dFile, Product.SIZE_P_NAME));
				product.setPDesc(getFixedString(dFile, Product.SIZE_P_DESC));
				product.setPQuantity(dFile.readInt());
				product.setPUnitPrice(dFile.readInt());
				products.add(product);
			}
		}
		return products;
	}
	public static ArrayList readProductId() throws IOException { //read product by id
		ArrayList productid = new ArrayList();
		Product product = null;
		int length = getNumberOfRecords();
		try(RandomAccessFile dFile = new RandomAccessFile(cFile, "r")){
			for (int i = 0; i < length; i++) {
				dFile.seek((i) * Product.RECORD_SIZE);
				productid.add(getFixedString(dFile, Product.SIZE_P_ID));
			}
		}
		return productid;
	}
	public static String readProductsAll() throws IOException { //read all products
		ArrayList<Product> products = readProducts();
		String output = "";
		for( int i = 0; i < products.size() ; i++) {
			output += "PRODUCT ID : " + products.get(i).getPID() + ", " +
					"PRODUCT Name : " + products.get(i).getPName() + ", " +
					"PRODUCT Description : " + products.get(i).getPDesc() + ", " +
					"PRODUCT Quantity : " + products.get(i).getPQuantity() + ", " +
					"PRODUCT Price : " + products.get(i).getPUnitPrice() +"\n";
		}
		return output;
	}

	public static String readProductsKeyword(String keyword) throws IOException { //read product by id
		ArrayList<Product> products = readProducts();
		String output = "";
		for( int i = 0; i < products.size() ; i++) {
			if(products.get(i).getPID().toLowerCase().contains( keyword.toLowerCase() )) {
				output += "PRODUCT ID : " + products.get(i).getPID() + ", " +
						"PRODUCT Name : " + products.get(i).getPName() + ", " +
						"PRODUCT Description : " + products.get(i).getPDesc() + ", " +
						"PRODUCT Quantity : " + products.get(i).getPQuantity() + ", " +
						"PRODUCT Price : " + products.get(i).getPUnitPrice() +"\n";
			}
		}
		return output;
	}
	
	public static String readProductsToFrom(int to, int from) throws IOException { //read product by price range
		ArrayList<Product> products = readProducts();
		String output = "";
		for( int i = 0; i < products.size() ; i++) {
			if( from <= products.get(i).getPUnitPrice() && products.get(i).getPUnitPrice() <= to ) {
				output += "PRODUCT ID : " + products.get(i).getPID() + ", " +
						"PRODUCT Name : " + products.get(i).getPName() + ", " +
						"PRODUCT Description : " + products.get(i).getPDesc() + ", " +
						"PRODUCT Quantity : " + products.get(i).getPQuantity() + ", " +
						"PRODUCT Price : " + products.get(i).getPUnitPrice() +"\n";
			}
		}
		return output;
	}
}
