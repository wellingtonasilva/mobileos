package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;
import wsilva.com.br.mobileos.core.entity.EntityVO;

public class LocalOcorrenciaVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idLocalOcorrencia;
	public String descricaoLocalOcorrencia;
	
	public LocalOcorrenciaVO() {
	}
	
	public LocalOcorrenciaVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.idLocalOcorrencia=Integer.parseInt(object.getProperty("").toString());
			this.descricaoLocalOcorrencia=object.getProperty("").toString();
		}
	}
}
