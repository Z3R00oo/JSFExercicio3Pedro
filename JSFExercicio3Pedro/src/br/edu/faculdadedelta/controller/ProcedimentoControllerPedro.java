package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.ProcedimentosDaoPedro;
import br.edu.faculdadedelta.modelo.ProcedimentoPedro;

@ManagedBean
@SessionScoped
public class ProcedimentoControllerPedro {

	private ProcedimentoPedro procedimento = new ProcedimentoPedro();
	private ProcedimentosDaoPedro dao = new ProcedimentosDaoPedro();
	
	private static final String PAGINA_CADASTRO = "CadastroProcedimento.xhtml";
	private static final String PAGINA_LISTA = "MostrarProcedimentos.xhtml";
	
	public ProcedimentoPedro getProcedimento() 
	{
		return procedimento;
	}
	public void setProcedimento(ProcedimentoPedro procedimento) 
	{
		this.procedimento = procedimento;
	}
	
	
	public String salvar() 
	{
		try 
		{
			if (procedimento.getDataInicio().after(new Date())) 
			{
				if(procedimento.getDataFim().after(procedimento.getDataInicio())) 
				{
					if(procedimento.getId() == null) 
					{
						dao.incluir(procedimento);
						novoProcedimento();
						gerarMensagem("Inclusão Realizada com Sucesso!");
					}
					else 
					{
						dao.alterar(procedimento);
						novoProcedimento();
						gerarMensagem("Alteração Realizada com Sucesso!"); 
					}
				}
				else 
				{
					gerarMensagem("A Data Inicial deve ser menor que a Data Final!");
				}
			}
			else 
			{
				gerarMensagem("A data inicial deve ser maior que a Data Atual!");
			}
		}
		catch(ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
			gerarMensagem("Erro ao realizar Operação. Tente Novamente. " + e.getMessage());
		}
		return PAGINA_CADASTRO;
	}
	public List<ProcedimentoPedro> getLista()
	{
		List<ProcedimentoPedro> listaRetorno = new ArrayList<ProcedimentoPedro>();
		
		try 
		{
			listaRetorno = dao.listar();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
			gerarMensagem("Erro ao realizar Operação. Tente Novamente. " + e.getMessage());
		}
		return listaRetorno;
	}
	public String editar() 
	{
		return PAGINA_CADASTRO;
	}
	public String excluir() 
	{
		try 
		{
			dao.excluir(procedimento);
			novoProcedimento();
			gerarMensagem("Exclusão Realizada com Sucesso!");
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
			gerarMensagem("Erro ao realizar Operação. Tente Novamente. " + e.getMessage());
		}
		return PAGINA_LISTA;
	}
	
	
	public void novoProcedimento() 
	{
		procedimento = new ProcedimentoPedro();
	}	
	public String limparCampos() 
	{	
		return PAGINA_CADASTRO;
	}	
	public void gerarMensagem(String texto) 
	{
		FacesMessage mensagem = new FacesMessage(texto);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
}
