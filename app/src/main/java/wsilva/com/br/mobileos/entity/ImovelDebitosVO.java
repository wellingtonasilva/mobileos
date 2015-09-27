package wsilva.com.br.mobileos.entity;

import java.util.Date;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class ImovelDebitosVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idImovelDebitos;
	public int idImovel;
	public int referencia;
	public Date dataVencimento;
	public String descricaoTipoDocumento;
	public Double valorDocumento;
	public Double valorAcrescimento;
	public Double valorTotal;
	public int numeroDiasVencido;
	
	public ImovelDebitosVO() {
	}
	
	public ImovelDebitosVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			idImovelDebitos=Integer.parseInt(object.getProperty("idImovelDebitos").toString());
			idImovel=Integer.parseInt(object.getProperty("idImovel").toString());
			referencia=Integer.parseInt(object.getProperty("referencia").toString());
			//dataVencimento=Integer.parseInt(object.getProperty("").toString());
			descricaoTipoDocumento=object.getProperty("descricaoTipoDocumento").toString();
			valorDocumento=Double.parseDouble(object.getProperty("valorDocumento").toString());
			valorAcrescimento=Double.parseDouble(object.getProperty("valorAcrescimento").toString());
			valorTotal=Double.parseDouble(object.getProperty("valorTotal").toString());
			numeroDiasVencido=Integer.parseInt(object.getProperty("numeroDiasVencido").toString());
		}
	}
}
