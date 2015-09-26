package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

public class HidrometroTipoInstalacaoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	
	private int idTipoInstalacaoHM;
	private String descricaoTipoInstalacaoHM;
	
	public HidrometroTipoInstalacaoVO() {
	}
	
	public HidrometroTipoInstalacaoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdTipoInstalacaoHM() {
		return idTipoInstalacaoHM;
	}
	public void setIdTipoInstalacaoHM(int idTipoInstalacaoHM) {
		this.idTipoInstalacaoHM = idTipoInstalacaoHM;
	}
	public String getDescricaoTipoInstalacaoHM() {
		return descricaoTipoInstalacaoHM;
	}
	public void setDescricaoTipoInstalacaoHM(String descricaoTipoInstalacaoHM) {
		this.descricaoTipoInstalacaoHM = descricaoTipoInstalacaoHM;
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
