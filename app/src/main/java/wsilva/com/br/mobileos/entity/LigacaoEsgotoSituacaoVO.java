package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

public class LigacaoEsgotoSituacaoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	private int idSituacaoLigacaoEsgoto;
	private String descricaoSituacaoLigacaoEsgoto;
	
	public LigacaoEsgotoSituacaoVO() {
	}
	
	public LigacaoEsgotoSituacaoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}
	public void setIdSituacaoLigacaoEsgoto(int idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}
	public String getDescricaoSituacaoLigacaoEsgoto() {
		return descricaoSituacaoLigacaoEsgoto;
	}
	public void setDescricaoSituacaoLigacaoEsgoto(
			String descricaoSituacaoLigacaoEsgoto) {
		this.descricaoSituacaoLigacaoEsgoto = descricaoSituacaoLigacaoEsgoto;
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