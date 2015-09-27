package wsilva.com.br.mobileos.entity;

import java.io.Serializable;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class MaterialUtilizadoVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int numeroOS;
	public int idMaterial;
	public String descricaoMateriall;
	public int idUnidadeMedida;
	public String descricaoUnidadeMedida;
	public Double quantidade;
	public int idEquipeExecucao;
	public String descricaoEquipeExecucao;
	public int indicadorEnvio;
	
	public MaterialUtilizadoVO() {
	}
	
	public MaterialUtilizadoVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.numeroOS=Integer.parseInt(object.getProperty("numeroOS").toString());
			this.idMaterial=Integer.parseInt(object.getProperty("idMaterial").toString());
			this.descricaoMateriall=object.getProperty("descricaoMaterial").toString();
			this.idUnidadeMedida=Integer.parseInt(object.getProperty("idUnidadeMedida").toString());
			this.descricaoUnidadeMedida=object.getProperty("unidadeMedida").toString();
			this.quantidade=Double.parseDouble(object.getProperty("quantidade").toString());
		}
	}
}
