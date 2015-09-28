package wsilva.com.br.mobileos.entity.os;

import org.ksoap2.serialization.SoapObject;
import wsilva.com.br.mobileos.core.entity.EntityVO;

public class MaterialVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idMaterial;
	public String descricaoMaterial;
	public int idUnidadeMedida;
	public String descricaoUnidadeMedida;
	
	public MaterialVO() {
	}
	
	public MaterialVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.idMaterial=Integer.parseInt(object.getProperty("idMaterial").toString());
			this.descricaoMaterial=object.getProperty("material").toString();
			this.idUnidadeMedida=Integer.parseInt(object.getProperty("idUnidadeMedida").toString());
			this.descricaoUnidadeMedida=object.getProperty("unidadeMedida").toString();
		}
	}
}
