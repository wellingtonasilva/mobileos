package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class CorteTipoVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idCorteTipo;
	public String descricaoCorteTipo;
	
	public CorteTipoVO() {
	}
	
	public CorteTipoVO(SoapObject object) {
		serialize(object);
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{	
			this.idCorteTipo=Integer.parseInt(object.getProperty("idCorteTipo").toString());
			this.descricaoCorteTipo=object.getProperty("descricaoCorteTipo").toString();
		}
	}
}
