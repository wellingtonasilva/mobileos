package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class LigacaoEsgotoSituacaoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	public int idSituacaoLigacaoEsgoto;
	public String descricaoSituacaoLigacaoEsgoto;
	
	public LigacaoEsgotoSituacaoVO() {
	}
	
	public LigacaoEsgotoSituacaoVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.idSituacaoLigacaoEsgoto=Integer.parseInt(object.getProperty("idSituacaoLigacaoEsgoto").toString());
			this.descricaoSituacaoLigacaoEsgoto=object.getProperty("descricaoSituacaoLigacaoEsgoto").toString();
		}
	}
}