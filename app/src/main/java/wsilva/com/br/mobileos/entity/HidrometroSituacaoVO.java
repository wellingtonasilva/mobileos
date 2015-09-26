package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

public class HidrometroSituacaoVO extends EntityVO 
{

	private static final long serialVersionUID = 1L;
	private int idSituacaoHidrometro;
	private String descricaoSituacaoHidrometro;
	
	public HidrometroSituacaoVO() {
	}
	
	public HidrometroSituacaoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdSituacaoHidrometro() {
		return idSituacaoHidrometro;
	}
	public void setIdSituacaoHidrometro(int idSituacaoHidrometro) {
		this.idSituacaoHidrometro = idSituacaoHidrometro;
	}
	public String getDescricaoSituacaoHidrometro() {
		return descricaoSituacaoHidrometro;
	}
	public void setDescricaoSituacaoHidrometro(String descricaoSituacaoHidrometro) {
		this.descricaoSituacaoHidrometro = descricaoSituacaoHidrometro;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{	
			this.idSituacaoHidrometro=Integer.parseInt(object.getProperty("idSituacaoHidrometro").toString());
			this.descricaoSituacaoHidrometro=object.getProperty("descricaoSituacaoHidrometro").toString();
		}
	}
	
}
