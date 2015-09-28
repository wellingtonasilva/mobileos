package wsilva.com.br.mobileos.entity.os;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class ReligacaoTipoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	public int idTipoReligacao;
	public String descricaoTipoReligacao;
	
	public ReligacaoTipoVO() {
	}
	
	public ReligacaoTipoVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.idTipoReligacao=Integer.parseInt(object.getProperty("idTipoReligacao").toString());
			this.descricaoTipoReligacao=object.getProperty("descricaoTipoReligacao").toString();
		}
	}
}
