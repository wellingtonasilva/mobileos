package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

public class HidrometroTipoSubstituicaoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	private int idTipoSubstituicaoHM;
	private String descricaoTipoSubstituicaoHM;
	
	public HidrometroTipoSubstituicaoVO() {
	}
	
	public HidrometroTipoSubstituicaoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdTipoSubstituicaoHM() {
		return idTipoSubstituicaoHM;
	}
	public void setIdTipoSubstituicaoHM(int idTipoSubstituicaoHM) {
		this.idTipoSubstituicaoHM = idTipoSubstituicaoHM;
	}
	public String getDescricaoTipoSubstituicaoHM() {
		return descricaoTipoSubstituicaoHM;
	}
	public void setDescricaoTipoSubstituicaoHM(String descricaoTipoSubstituicaoHM) {
		this.descricaoTipoSubstituicaoHM = descricaoTipoSubstituicaoHM;
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
