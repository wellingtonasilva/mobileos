package wsilva.com.br.mobileos.entity.os;

import org.ksoap2.serialization.SoapObject;
import wsilva.com.br.mobileos.core.entity.EntityVO;

public class AgenteExternoVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idAgenteExterno;
	public String agenteExterno;
	
	public AgenteExternoVO() {
	}
	
	public AgenteExternoVO(SoapObject object) {
		serialize(object);
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0)
		{
			this.idAgenteExterno=Integer.parseInt(object.getProperty("idAgenteExterno").toString());
			this.agenteExterno=object.getProperty("descricaoAgenteExterno").toString();
		}
	}
}
