package wsilva.com.br.mobileos.entity.os;

import org.ksoap2.serialization.SoapObject;
import wsilva.com.br.mobileos.core.entity.EntityVO;

public class RedeTipoVO extends EntityVO
{
	
	private static final long serialVersionUID = 1L;
	public int idRedeTipo;
	public String descricaoRedeTipo;
	
	public RedeTipoVO() {
	}
	
	public RedeTipoVO(SoapObject object) {
		serialize(object);
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.idRedeTipo=Integer.parseInt(object.getProperty("idRedeTipo").toString());
			this.descricaoRedeTipo=object.getProperty("redeTipo").toString();
		}
	}
}
