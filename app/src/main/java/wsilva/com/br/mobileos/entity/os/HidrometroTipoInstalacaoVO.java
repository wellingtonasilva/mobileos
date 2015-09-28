package wsilva.com.br.mobileos.entity.os;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class HidrometroTipoInstalacaoVO extends EntityVO {

	private static final long serialVersionUID = 1L;

	public int idTipoInstalacaoHM;
	public String descricaoTipoInstalacaoHM;
	
	public HidrometroTipoInstalacaoVO() {
	}
	
	public HidrometroTipoInstalacaoVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{	
			this.idTipoInstalacaoHM=Integer.parseInt(object.getProperty("idTipoInstalacaoHM").toString());
			this.descricaoTipoInstalacaoHM=object.getProperty("descricaoTipoInstalacaoHM").toString();
		}
	}
}
