package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

public class HidrometroLocalArmazenagemVO extends EntityVO 
{

	private static final long serialVersionUID = 1L;
	private int idLocalArmazenagemHidrometro;
	private String descricaoLocalArmazenagemHidrometro;
	
	public HidrometroLocalArmazenagemVO() {
	}
	
	public HidrometroLocalArmazenagemVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdLocalArmazenagemHidrometro() {
		return idLocalArmazenagemHidrometro;
	}
	public void setIdLocalArmazenagemHidrometro(int idLocalArmazenagemHidrometro) {
		this.idLocalArmazenagemHidrometro = idLocalArmazenagemHidrometro;
	}
	public String getDescricaoLocalArmazenagemHidrometro() {
		return descricaoLocalArmazenagemHidrometro;
	}
	public void setDescricaoLocalArmazenagemHidrometro(
			String descricaoLocalArmazenagemHidrometro) {
		this.descricaoLocalArmazenagemHidrometro = descricaoLocalArmazenagemHidrometro;
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


