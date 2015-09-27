package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class HidrometroLocalArmazenagemVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idLocalArmazenagemHidrometro;
	public String descricaoLocalArmazenagemHidrometro;
	
	public HidrometroLocalArmazenagemVO() {
	}
	
	public HidrometroLocalArmazenagemVO(SoapObject object) {
		serialize(object);
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.idLocalArmazenagemHidrometro=Integer.parseInt(object.getProperty("idLocalArmazenagemHidrometro").toString());
			this.descricaoLocalArmazenagemHidrometro=object.getProperty("descricaoLocalArmazenagemHidrometro").toString();
		}
	}
}


