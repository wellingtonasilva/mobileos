package wsilva.com.br.mobileos.entity;

import java.io.Serializable;

import org.ksoap2.serialization.SoapObject;

public class RedeTipoVO extends EntityVO implements Serializable 
{
	
	private static final long serialVersionUID = 1L;
	private int IdRedeTipo;
	private String DescricaoRedeTipo;
	
	public RedeTipoVO() {
	}
	
	public RedeTipoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdRedeTipo() {
		return IdRedeTipo;
	}
	public void setIdRedeTipo(int idRedeTipo) {
		IdRedeTipo = idRedeTipo;
	}
	public String getDescricaoRedeTipo() {
		return DescricaoRedeTipo;
	}
	public void setDescricaoRedeTipo(String descricaoRedeTipo) {
		DescricaoRedeTipo = descricaoRedeTipo;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.IdRedeTipo=Integer.parseInt(object.getProperty("idRedeTipo").toString());
			this.DescricaoRedeTipo=object.getProperty("redeTipo").toString();
		}
	}
	
}
