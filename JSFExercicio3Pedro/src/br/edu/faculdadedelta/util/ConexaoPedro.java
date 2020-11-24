package br.edu.faculdadedelta.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPedro {

	public Connection conectarNoBanco() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		
		Connection conn = null;
		
		String url = "jdbc:postgresql://localhost/Delta";
		String usuario = "postgres";
		String senha = "estudos";
		
		conn = DriverManager.getConnection(url, usuario, senha);
		
		return conn;
	}
	
	public static void main(String[] args) {
		ConexaoPedro conexao = new ConexaoPedro();
		try {
			conexao.conectarNoBanco();
			System.out.println("Conectou no banco de dados!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
