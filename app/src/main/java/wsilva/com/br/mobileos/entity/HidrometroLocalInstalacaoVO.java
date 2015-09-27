package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class HidrometroLocalInstalacaoVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idLocalInstalacaoHidrometro;
	public String descricaoLocalInstalacaoHidrometro;
	
	public HidrometroLocalInstalacaoVO() {
	}
	
	public HidrometroLocalInstalacaoVO(SoapObject object) {
		serialize(object);
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{	
			this.idLocalInstalacaoHidrometro=Integer.parseInt(object.getProperty("idLocalInstalacaoHidrometro").toString());
			this.descricaoLocalInstalacaoHidrometro=object.getProperty("descricaoLocalInstalacaoHidrometro").toString();
		}
	}
	
}
