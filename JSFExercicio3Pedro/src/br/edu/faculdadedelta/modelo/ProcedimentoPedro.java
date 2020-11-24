package br.edu.faculdadedelta.modelo;

import java.util.Date;

public class ProcedimentoPedro {

	private Long id;
	private String paciente;
	private String procedimento;
	private int quantidade;
	private double valor;
	private Date dataInicio;
	private Date dataFim;
	
	
	public ProcedimentoPedro() 
	{
	}
	
	public ProcedimentoPedro(Long id, String paciente, String procedimento, int quantidade, double valor, Date dataInicio, Date dataFim) 
	{
		this.id = id;
		this.paciente = paciente;
		this.procedimento = procedimento;
		this.quantidade = quantidade;
		this.valor = valor;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	
	
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}
	
	
	public String getPaciente() 
	{
		return paciente;
	}

	public void setPaciente(String paciente) 
	{
		this.paciente = paciente;
	}
	
	
	public String getProcedimento() 
	{
		return procedimento;
	}

	public void setProcedimento(String procedimento) 
	{
		this.procedimento = procedimento;
	}
	
	
	public int getQuantidade() 
	{
		return quantidade;
	}

	public void setQuantidade(int quantidade) 
	{
		this.quantidade = quantidade;
	}

	
	public double getValor() 
	{
		return valor;
	}

	public void setValor(double valor) 
	{
		this.valor = valor;
	}

	
	public Date getDataInicio() 
	{
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) 
	{
		this.dataInicio = dataInicio;
	}
	
	public Date getDataFim() 
	{
		return dataFim;
	}

	public void setDataFim(Date dataFim) 
	{
		this.dataFim = dataFim;
	}
	
	public double getValorTotal() 
	{
		double desconto = 0;
		double valorTotal = valor * quantidade;
		
		Long intervalo = (dataFim.getTime() - dataInicio.getTime() + 3600000L) / 86400000L;
		
		if(intervalo > 2) 
		{
			desconto += valor * 0.025;
		}
		if(valor > 2000) 
		{
			desconto += valorTotal * 0.015;	
		}
		
		valorTotal = valorTotal - desconto;
		
		return valorTotal;
		
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcedimentoPedro other = (ProcedimentoPedro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
