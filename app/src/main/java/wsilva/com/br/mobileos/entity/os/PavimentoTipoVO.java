package wsilva.com.br.mobileos.entity.os;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class PavimentoTipoVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idPavimentoTipo;
	public String descricaoPavimentoTipo;
	
	public PavimentoTipoVO() {
	}
	
	public PavimentoTipoVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{	
			this.idPavimentoTipo=Integer.parseInt(object.getProperty("idPavimentoTipo").toString());
			this.descricaoPavimentoTipo=object.getProperty("pavimentoTipo").toString();
		}
	}

}
