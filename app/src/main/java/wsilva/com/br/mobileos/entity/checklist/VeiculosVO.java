package wsilva.com.br.mobileos.entity.checklist;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class VeiculosVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	public String numeroPlacaVeiculo;
	public String modelo;
	public String tipoVeiculo;
	public String marca;
	public int kmAcumulada;
	public String dataUltimaRevisao;
	
	public VeiculosVO() {
	}
	
	public VeiculosVO(String numeroPlacaVeiculo, String modelo, String tipoVeiculo,
			String marca, int kmAcumulada, String dataUltimaRevisao) 
	{
		this.numeroPlacaVeiculo=numeroPlacaVeiculo;
		this.modelo=modelo;
		this.tipoVeiculo=tipoVeiculo;
		this.marca=marca;
		this.kmAcumulada=kmAcumulada;
		this.dataUltimaRevisao=dataUltimaRevisao;
	}

}
