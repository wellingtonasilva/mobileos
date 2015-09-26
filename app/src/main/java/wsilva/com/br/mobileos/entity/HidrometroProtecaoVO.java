package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

public class HidrometroProtecaoVO extends EntityVO 
{
	private static final long serialVersionUID = 1L;
	private int idProtecaoHidrometro;
	private String descricaoProtecaoHidrometro;
	
	public HidrometroProtecaoVO() {
	}
	
	public HidrometroProtecaoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdProtecaoHidrometro() {
		return idProtecaoHidrometro;
	}
	public void setIdProtecaoHidrometro(int idProtecaoHidrometro) {
		this.idProtecaoHidrometro = idProtecaoHidrometro;
	}
	public String getDescricaoProtecaoHidrometro() {
		return descricaoProtecaoHidrometro;
	}
	public void setDescricaoProtecaoHidrometro(String descricaoProtecaoHidrometro) {
		this.descricaoProtecaoHidrometro = descricaoProtecaoHidrometro;
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.idProtecaoHidrometro=Integer.parseInt(object.getProperty("idProtecaoHidrometro").toString());
			this.descricaoProtecaoHidrometro=object.getProperty("descricaoProtecaoHidrometro").toString();
		}
	}
}

