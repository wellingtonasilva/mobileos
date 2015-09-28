package wsilva.com.br.mobileos.entity.os;

import org.ksoap2.serialization.SoapObject;
import wsilva.com.br.mobileos.core.entity.EntityVO;

public class MotivoEncerramentoVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idMotivoEncerramento;
	public String descricaoMotivoEncerramento;
	
	public MotivoEncerramentoVO() {
	}
	
	public MotivoEncerramentoVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.idMotivoEncerramento=Integer.parseInt(object.getProperty("idMotivoEncerramento").toString());
			this.descricaoMotivoEncerramento=object.getProperty("motivoEncerramento").toString();
		}
	}
}
