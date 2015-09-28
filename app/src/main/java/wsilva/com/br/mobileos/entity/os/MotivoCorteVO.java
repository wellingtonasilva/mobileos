package wsilva.com.br.mobileos.entity.os;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class MotivoCorteVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idMotivoCorte;
	public String descricaoMotivoCorte;
	
	public MotivoCorteVO() {
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.idMotivoCorte=Integer.parseInt(object.getProperty("idMotivoCorte").toString());
			this.descricaoMotivoCorte=object.getProperty("descricaoMotivoCorte").toString();
		}
	}
}

