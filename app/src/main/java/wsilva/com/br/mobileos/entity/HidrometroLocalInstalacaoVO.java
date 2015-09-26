package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

public class HidrometroLocalInstalacaoVO extends EntityVO 
{

	private static final long serialVersionUID = 1L;
	private int idLocalInstalacaoHidrometro;
	private String descricaoLocalInstalacaoHidrometro;
	
	public HidrometroLocalInstalacaoVO() {
	}
	
	public HidrometroLocalInstalacaoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdLocalInstalacaoHidrometro() {
		return idLocalInstalacaoHidrometro;
	}
	public void setIdLocalInstalacaoHidrometro(int idLocalInstalacaoHidrometro) {
		this.idLocalInstalacaoHidrometro = idLocalInstalacaoHidrometro;
	}
	public String getDescricaoLocalInstalacaoHidrometro() {
		return descricaoLocalInstalacaoHidrometro;
	}
	public void setDescricaoLocalInstalacaoHidrometro(
			String descricaoLocalInstalacaoHidrometro) {
		this.descricaoLocalInstalacaoHidrometro = descricaoLocalInstalacaoHidrometro;
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
