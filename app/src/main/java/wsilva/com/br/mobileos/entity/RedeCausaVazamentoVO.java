package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

public class RedeCausaVazamentoVO extends EntityVO 
{

	private static final long serialVersionUID = 1L;
	private int IdCausaVazamento;
	private String DescricaoCausaVazamento;
	
	public RedeCausaVazamentoVO() {
	}
	
	public RedeCausaVazamentoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdCausaVazamento() {
		return IdCausaVazamento;
	}
	public void setIdCausaVazamento(int idCausaVazamento) {
		IdCausaVazamento = idCausaVazamento;
	}
	public String getDescricaoCausaVazamento() {
		return DescricaoCausaVazamento;
	}
	public void setDescricaoCausaVazamento(String descricaoCausaVazamento) {
		DescricaoCausaVazamento = descricaoCausaVazamento;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.IdCausaVazamento=Integer.parseInt(object.getProperty("idRedeCausaVazamento").toString());
			this.DescricaoCausaVazamento=object.getProperty("redeCausaVazamento").toString();
		}
	}
}
