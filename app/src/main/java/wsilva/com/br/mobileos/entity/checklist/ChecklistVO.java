package wsilva.com.br.mobileos.entity.checklist;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class ChecklistVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	public String dataMovimento;
	public String tipoVeiculo;
	public int matricula;
	public String nomeCondutor;
	public String nomeSetor;
	public String dataSaida;
	public String horaSaida;
	public int kmSaida;
	public String dataRetorno;
	public String horaRetorno;
	public int kmRetorno;
	public String numeroPlacaVeiculo;
	public int finalizouSaida;
	public int finalizouRetorno;
	public String numeroCNH;
	
	public ChecklistVO() {
	}
	
	public ChecklistVO(String dataMovimento, String tipoVeiculo, int matricula,
			String nomeCondutor, String nomeSetor, String dataSaida,String horaSaida,
			int kmSaida, String numeroPlacaVeiculo, String numeroCNH) 
	{
		this.dataMovimento=dataMovimento;
		this.tipoVeiculo=tipoVeiculo;
		this.matricula=matricula;
		this.nomeCondutor=nomeCondutor;
		this.nomeSetor=nomeSetor;
		this.dataSaida=dataSaida;
		this.horaSaida=horaSaida;
		this.kmSaida=kmSaida;
		this.numeroPlacaVeiculo=numeroPlacaVeiculo;
		this.numeroCNH=numeroCNH;
	}

}
