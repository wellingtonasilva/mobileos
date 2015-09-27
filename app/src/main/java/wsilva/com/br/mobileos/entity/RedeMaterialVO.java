package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;
import wsilva.com.br.mobileos.core.entity.EntityVO;

public class RedeMaterialVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idRedeMaterial;
	public String descricaoRedeMaterial;
	
	public RedeMaterialVO() {
	}
	
	public RedeMaterialVO(SoapObject object) {
		serialize(object);
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.idRedeMaterial=Integer.parseInt(object.getProperty("idRedeMaterial").toString());
			this.descricaoRedeMaterial=object.getProperty("redeMaterial").toString();
		}
	}
}
