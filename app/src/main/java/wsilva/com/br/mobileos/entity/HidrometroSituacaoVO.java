package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class HidrometroSituacaoVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idSituacaoHidrometro;
	public String descricaoSituacaoHidrometro;
	
	public HidrometroSituacaoVO() {
	}
	
	public HidrometroSituacaoVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{	
			this.idSituacaoHidrometro=Integer.parseInt(object.getProperty("idSituacaoHidrometro").toString());
			this.descricaoSituacaoHidrometro=object.getProperty("descricaoSituacaoHidrometro").toString();
		}
	}
	
}
