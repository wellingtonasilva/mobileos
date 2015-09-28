package wsilva.com.br.mobileos.entity.os;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class HidrometroTipoSubstituicaoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	public int idTipoSubstituicaoHM;
	public String descricaoTipoSubstituicaoHM;
	
	public HidrometroTipoSubstituicaoVO() {
	}
	
	public HidrometroTipoSubstituicaoVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.idTipoSubstituicaoHM=Integer.parseInt(object.getProperty("idTipoSubstituicaoHM").toString());
			this.descricaoTipoSubstituicaoHM=object.getProperty("descricaoTipoSubstituicaoHM").toString();
		}
	}

}
