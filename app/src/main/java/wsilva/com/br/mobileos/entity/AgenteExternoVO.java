package wsilva.com.br.mobileos.entity;

import java.io.Serializable;

import org.ksoap2.serialization.SoapObject;

public class AgenteExternoVO extends EntityVO implements Serializable 
{

	private static final long serialVersionUID = 1L;
	private int IdAgenteExterno;
	private String AgenteExterno;
	
	public AgenteExternoVO() {
	}
	
	public AgenteExternoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdAgenteExterno() {
		return IdAgenteExterno;
	}
	public void setIdAgenteExterno(int idAgenteExterno) {
		IdAgenteExterno = idAgenteExterno;
	}
	public String getAgenteExterno() {
		return AgenteExterno;
	}
	public void setAgenteExterno(String agenteExterno) {
		AgenteExterno = agenteExterno;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.IdAgenteExterno=Integer.parseInt(object.getProperty("idAgenteExterno").toString());
			this.AgenteExterno=object.getProperty("descricaoAgenteExterno").toString();
		}
	}
	
	
}
