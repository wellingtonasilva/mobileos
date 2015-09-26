package wsilva.com.br.mobileos.entity;

import java.io.Serializable;

import org.ksoap2.serialization.SoapObject;

public class RedeDiametroVO extends EntityVO implements Serializable 
{

	private static final long serialVersionUID = 1L;
	private int IdRedeDiametro;
	private String DescricaoRedeDiametro;
	
	public RedeDiametroVO() {
	}
	
	public RedeDiametroVO(SoapObject object) {
		serialize(object);
	}
	public int getIdRedeDiametro() {
		return IdRedeDiametro;
	}
	public void setIdRedeDiametro(int idRedeDiametro) {
		IdRedeDiametro = idRedeDiametro;
	}
	public String getDescricaoRedeDiametro() {
		return DescricaoRedeDiametro;
	}
	public void setDescricaoRedeDiametro(String descricaoRedeDiametro) {
		DescricaoRedeDiametro = descricaoRedeDiametro;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.IdRedeDiametro=Integer.parseInt(object.getProperty("").toString());
			this.DescricaoRedeDiametro=object.getProperty("").toString();
		}
	}
	
}
