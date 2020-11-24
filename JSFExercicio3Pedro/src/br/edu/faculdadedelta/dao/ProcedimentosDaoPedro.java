package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.ProcedimentoPedro;
import br.edu.faculdadedelta.util.ConexaoPedro;

public class ProcedimentosDaoPedro {

	private String incluirQuery = "INSERT INTO procedimentos (paciente_desc, "+
            "						 procedimento_desc, "+
            "						 valor_procedimento, "+
            "						 data_inicio_procedimento, "+
            "						 data_fim_procedimento, "+
            "					     quantidade_exame_procedimento) "+
            "VALUES (?, ?, ?, ?, ?, ?)";

	private String alterarQuery = "UPDATE procedimentos SET paciente_desc = ?, "+
				  "                    procedimento_desc = ?, "+
				  "                    valor_procedimento = ?, "+
				  "                    data_inicio_procedimento = ?, "+
				  "                    data_fim_procedimento = ?, "+
				     		      "    quantidade_exame_procedimento = ?"+
			      "WHERE id_procedimento = ?";
	
	private String excluirQuery = "DELETE FROM procedimentos WHERE id_procedimento = ?";
	
	private String listarQuery  = "SELECT id_procedimento, "+
				  "       paciente_desc, "+
				  "       procedimento_desc, "+
				  "       valor_procedimento, "+
				  "       data_inicio_procedimento, "+
				  "       data_fim_procedimento, "+
				  "       quantidade_exame_procedimento "+
				  "FROM procedimentos";
	
	
	public void incluir(ProcedimentoPedro procedimento) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(incluirQuery);
		
		ps.setString(1, procedimento.getPaciente());
		ps.setString(2, procedimento.getProcedimento());
		ps.setDouble(3, procedimento.getValor());
		ps.setDate(4, new java.sql.Date(procedimento.getDataInicio().getTime()));
		ps.setDate(5, new java.sql.Date(procedimento.getDataFim().getTime()));
		ps.setInt(6, procedimento.getQuantidade());
		
		Execute(ps);
		Close(ps, conn, null);
	}
	public void alterar(ProcedimentoPedro procedimento) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(alterarQuery);
		
		ps.setString(1, procedimento.getPaciente());
		ps.setString(2, procedimento.getProcedimento());
		ps.setDouble(3, procedimento.getValor());
		ps.setDate(4, new java.sql.Date(procedimento.getDataInicio().getTime()));
		ps.setDate(5, new java.sql.Date(procedimento.getDataFim().getTime()));
		ps.setInt(6, procedimento.getQuantidade());
		ps.setLong(7, procedimento.getId());
		
		Execute(ps);
		Close(ps, conn, null);
	}
	public void excluir(ProcedimentoPedro procedimento) throws ClassNotFoundException, SQLException 
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(excluirQuery);
		
		ps.setLong(1, procedimento.getId());
		
		Execute(ps);
		Close(ps, conn, null);
	}
	
	public List<ProcedimentoPedro> listar() throws ClassNotFoundException, SQLException
	{
		ConexaoPedro conexao = new ConexaoPedro();
		Connection conn = conexao.conectarNoBanco();
		PreparedStatement ps = conn.prepareStatement(listarQuery);
		
		ResultSet rs = ps.executeQuery();
		
		List<ProcedimentoPedro> listaRetorno = new ArrayList<ProcedimentoPedro>();
		
		while(rs.next()) 
		{
			ProcedimentoPedro procedimento = new ProcedimentoPedro();
			
			procedimento.setId(rs.getLong("id_procedimento"));
			procedimento.setPaciente(rs.getString("paciente_desc").trim());
			procedimento.setProcedimento(rs.getString("procedimento_desc").trim());
			procedimento.setValor(rs.getDouble("valor_procedimento"));
			procedimento.setDataInicio(rs.getDate("data_inicio_procedimento"));
			procedimento.setDataFim(rs.getDate("data_fim_procedimento"));
			procedimento.setQuantidade(rs.getInt("quantidade_exame_procedimento"));
			
			listaRetorno.add(procedimento);
		}
		
		Close(ps, conn, rs);
		
		return listaRetorno;
	}
	
	
	public void Execute(PreparedStatement ps) throws SQLException 
	{
		ps.executeUpdate();
	}	
	public void Close(PreparedStatement ps, Connection conn, ResultSet rs) throws SQLException 
	{
		if(ps != null) ps.close();
		if(conn != null) conn.close();
		if(rs != null) rs.close();
	}
}
