package Payment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Paynew {
	//A common method to connect to the DB
		private Connection connect()
		{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");

				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf?useTimezone=true&serverTimezone=UTC", "root", "");
			}
			catch (Exception e)
			{e.printStackTrace();}
			return con;
		} 
		

		// reading an items -------------------------
		public String readPayment()
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>ID</th> <th>Patient Name</th> <th>Doctor Name</th><th>Fee</th>"
						+ "<th>Update</th><th>Remove</th></tr>";
				String query = "select * from payment";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next())
				{
					String ID = Integer.toString(rs.getInt("ID"));
					String PName = rs.getString("PName");
					String DName = rs.getString("DName");
					String Fee = Double.toString(rs.getDouble("Fee"));

					// Add into the html table
					output += "<tr><td><input id='hidIDUpdate'name='hidIDUpdate' type='hidden'value='" + ID + "'>" + ID + "</td>";
					output += "<td>" + PName + "</td>";
					output += "<td>" + DName + "</td>";
					output += "<td>" + Fee + "</td>";
					// buttons
					output += "<td><input name='btnUpdate' type='button'"+ "value='Update'"+"class='btnUpdate btn btn-secondary'></td>"+"<td><input name='btnRemove' type='button'"+" value='Remove'"+"class='btnRemove btn btn-danger' data-ID='"+ ID + "'>" + "</td></tr>";
				}
				con.close();
				// Complete the html table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//inserting---------------------
		public String insertPayment(String ID, String PName,String DName,String Fee)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = " INSERT INTO `payment`(`ID`, `PName`, `DName`, `Fee`) VALUES (?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, ID);
				preparedStmt.setString(2, PName);
				preparedStmt.setString(3, DName);
				preparedStmt.setDouble(4, Double.parseDouble(Fee));
				// execute the statement
				preparedStmt.execute();
				
				 System.out.print("successfuly inserted");
				 
				con.close();
				String newPayment = readPayment();
				output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}

		//update items
		public String updatePayment(String ID, String PName, String DName,String Fee)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE payment SET PName=?,DName=?,Fee=? WHERE ID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
			
				preparedStmt.setString(1, PName);
				preparedStmt.setString(2, DName);
				preparedStmt.setDouble(3, Double.parseDouble(Fee));
				preparedStmt.setString(4, ID);
				// execute the statement
				preparedStmt.execute();
				con.close();
				String newPayment = readPayment();
				output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//delete items------------------------
		public String deletePayment(String ID) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "DELETE FROM `payment` WHERE ID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, ID);
				// execute the statement
				preparedStmt.execute();
				con.close();
				//output = "Deleted successfully";
				String newPayment = readPayment();
				output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
			} catch (Exception e) {
				//output = "Error while deleting the item.";
				output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
				
				System.err.println(e.getMessage());
			}
			return output;
		}
	}


