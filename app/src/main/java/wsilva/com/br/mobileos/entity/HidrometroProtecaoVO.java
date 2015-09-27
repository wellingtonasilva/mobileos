package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class HidrometroProtecaoVO extends EntityVO
{
	private static final long serialVersionUID = 1L;
	public int idProtecaoHidrometro;
	public String descricaoProtecaoHidrometro;

	public HidrometroProtecaoVO() {
	}
	
	public HidrometroProtecaoVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.idProtecaoHidrometro=Integer.parseInt(object.getProperty("idProtecaoHidrometro").toString());
			this.descricaoProtecaoHidrometro=object.getProperty("descricaoProtecaoHidrometro").toString();
		}
	}
}

