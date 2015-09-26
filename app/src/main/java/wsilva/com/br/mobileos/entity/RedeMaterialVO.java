package wsilva.com.br.mobileos.entity;

import java.io.Serializable;

import org.ksoap2.serialization.SoapObject;

public class RedeMaterialVO extends EntityVO implements Serializable 
{

	private static final long serialVersionUID = 1L;
	private int IdRedeMaterial;
	private String DescricaoRedeMaterial;
	
	public RedeMaterialVO() {
	}
	
	public RedeMaterialVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdRedeMaterial() {
		return IdRedeMaterial;
	}
	public void setIdRedeMaterial(int idRedeMaterial) {
		IdRedeMaterial = idRedeMaterial;
	}
	public String getDescricaoRedeMaterial() {
		return DescricaoRedeMaterial;
	}
	public void setDescricaoRedeMaterial(String descricaoRedeMaterial) {
		DescricaoRedeMaterial = descricaoRedeMaterial;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.IdRedeMaterial=Integer.parseInt(object.getProperty("idRedeMaterial").toString());
			this.DescricaoRedeMaterial=object.getProperty("redeMaterial").toString();
		}
	}
	
}
