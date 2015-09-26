package wsilva.com.br.mobileos.entity;

import java.io.Serializable;

import org.ksoap2.serialization.SoapObject;

public class MaterialVO extends EntityVO implements Serializable 
{

	private static final long serialVersionUID = 1L;
	private int IdMaterial;
	private String DescricaoMaterial;
	private int IdUnidadeMedida;
	private String DescricaoUnidadeMedida;
	
	public MaterialVO() {
	}
	
	public MaterialVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdMaterial() {
		return IdMaterial;
	}
	public void setIdMaterial(int idMaterial) {
		IdMaterial = idMaterial;
	}
	public String getDescricaoMaterial() {
		return DescricaoMaterial;
	}
	public void setDescricaoMaterial(String descricaoMaterial) {
		DescricaoMaterial = descricaoMaterial;
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
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.IdMaterial=Integer.parseInt(object.getProperty("idMaterial").toString());
			this.DescricaoMaterial=object.getProperty("material").toString();
			this.IdUnidadeMedida=Integer.parseInt(object.getProperty("idUnidadeMedida").toString());
			this.DescricaoUnidadeMedida=object.getProperty("unidadeMedida").toString();
		}
	}
	
}
