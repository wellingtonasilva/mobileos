package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;
import wsilva.com.br.mobileos.core.entity.EntityVO;

public class RedeDiametroVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idRedeDiametro;
	public String descricaoRedeDiametro;
	
	public RedeDiametroVO() {
	}
	
	public RedeDiametroVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.idRedeDiametro=Integer.parseInt(object.getProperty("").toString());
			this.descricaoRedeDiametro=object.getProperty("").toString();
		}
	}
}
