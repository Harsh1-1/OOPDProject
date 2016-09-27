package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Qualification;
import smart.Global;

interface UserForm {

	default ArrayList<Qualification> getQualifications(){
		ArrayList<Qualification> qualifications = new ArrayList<Qualification>();
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery("Select * from Qualification") )
		{
			while(rs.next()){
				qualifications.add(
						new Qualification(rs.getInt("QualificationID"),rs.getString("Description")));
			}
			con.close();
		}
		catch(SQLException ex){
			System.out.println("Some error occured while retreiving available qualifications");
			ex.getMessage();
			ex.printStackTrace();
		}
		return qualifications;
	}
}
