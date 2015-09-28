package wsilva.com.br.mobileos.entity.os;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class LigacaoAguaSituacaoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	public int idSituacaoLigacaoAgua;
	public String descricaoSituacaoLigacaoAgua;
	
	public LigacaoAguaSituacaoVO() {
	}
	
	public LigacaoAguaSituacaoVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{	
			this.idSituacaoLigacaoAgua=Integer.parseInt(object.getProperty("idSituacaoLigacaoAgua").toString());
			this.descricaoSituacaoLigacaoAgua=object.getProperty("descricaoSituacaoLigacaoAgua").toString();
		}
	}
}
