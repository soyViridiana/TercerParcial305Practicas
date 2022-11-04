package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	Connection cx=null;
	public Connection conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			cx=DriverManager.getConnection("jdbc:sqlite:sistemas.db");
			System.out.println("Conexion Exitosa");
		}catch (ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
		return cx;
		}
	public static void main(String[]args) {
		Conexion cx=new Conexion();
		cx.conectar();
	}

	}
