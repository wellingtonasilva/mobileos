package wsilva.com.br.mobileos.entity;

import java.io.Serializable;

import org.ksoap2.serialization.SoapObject;

public class MaterialUtilizadoVO extends EntityVO implements Serializable 
{

	private static final long serialVersionUID = 1L;
	private int NumeroOS;
	private int IdMaterial;
	private String DescricaoMateriall;
	private int IdUnidadeMedida;
	private String DescricaoUnidadeMedida;
	private Double Quantidade;
	private int idEquipeExecucao;
	private String descricaoEquipeExecucao;
	private int indicadorEnvio;
	
	public MaterialUtilizadoVO() {
	}
	
	public MaterialUtilizadoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getNumeroOS() {
		return NumeroOS;
	}
	public void setNumeroOS(int numeroOS) {
		NumeroOS = numeroOS;
	}
	public int getIdMaterial() {
		return IdMaterial;
	}
	public void setIdMaterial(int idMaterial) {
		IdMaterial = idMaterial;
	}
	public String getDescricaoMateriall() {
		return DescricaoMateriall;
	}
	public void setDescricaoMateriall(String descricaoMateriall) {
		DescricaoMateriall = descricaoMateriall;
	}
	public int getIdUnidadeMedida() {
		return IdUnidadeMedida;
	}
	public void setIdUnidadeMedida(int idUnidadeMedida) {
		IdUnidadeMedida = idUnidadeMedida;
	}
	public String getDescricaoUnidadeMedida() {
		return DescricaoUnidadeMedida;
	}
	public void setDescricaoUnidadeMedida(String descricaoUnidadeMedida) {
		DescricaoUnidadeMedida = descricaoUnidadeMedida;
	}
	public Double getQuantidade() {
		return Quantidade;
	}
	public void setQuantidade(Double quantidade) {
		Quantidade = quantidade;
	}
	public int getIdEquipeExecucao() {
		return idEquipeExecucao;
	}
	public void setIdEquipeExecucao(int idEquipeExecucao) {
		this.idEquipeExecucao = idEquipeExecucao;
	}
	public String getDescricaoEquipeExecucao() {
		return descricaoEquipeExecucao;
	}
	public void setDescricaoEquipeExecucao(String descricaoEquipeExecucao) {
		this.descricaoEquipeExecucao = descricaoEquipeExecucao;
	}
	public int getIndicadorEnvio() {
		return indicadorEnvio;
	}
	public void setIndicadorEnvio(int indicadorEnvio) {
		this.indicadorEnvio = indicadorEnvio;
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.NumeroOS=Integer.parseInt(object.getProperty("numeroOS").toString());
			this.IdMaterial=Integer.parseInt(object.getProperty("idMaterial").toString());
			this.DescricaoMateriall=object.getProperty("descricaoMaterial").toString();
			this.IdUnidadeMedida=Integer.parseInt(object.getProperty("idUnidadeMedida").toString());
			this.DescricaoUnidadeMedida=object.getProperty("unidadeMedida").toString();
			this.Quantidade=Double.parseDouble(object.getProperty("quantidade").toString());
		}
	}
	
	
}
