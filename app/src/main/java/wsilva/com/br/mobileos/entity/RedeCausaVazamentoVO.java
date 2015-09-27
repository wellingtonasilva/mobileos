package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class RedeCausaVazamentoVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idCausaVazamento;
	public String descricaoCausaVazamento;
	
	public RedeCausaVazamentoVO() {
	}
	
	public RedeCausaVazamentoVO(SoapObject object) {
		serialize(object);
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.idCausaVazamento=Integer.parseInt(object.getProperty("idRedeCausaVazamento").toString());
			this.descricaoCausaVazamento=object.getProperty("redeCausaVazamento").toString();
		}
	}
}
