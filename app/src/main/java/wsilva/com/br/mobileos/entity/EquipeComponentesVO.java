package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;
import wsilva.com.br.mobileos.core.entity.EntityVO;

public class EquipeComponentesVO extends EntityVO
{
	private static final long serialVersionUID = 1L;
	public int idEquipe;
	public int idEquipeComponente;
	public int indicadorResponsavel;
	public String nomeComponente;
	public String idFuncionario;
	
	public EquipeComponentesVO() {
	}
	
	public EquipeComponentesVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.idEquipe=0;
			this.idEquipeComponente=Integer.parseInt(object.getProperty("idEquipeComponente").toString());
			this.indicadorResponsavel=Integer.parseInt(object.getProperty("indicadorResponsavel").toString());
			this.nomeComponente="";
			this.idFuncionario=object.getProperty("idFuncionario").toString();
		}
	}
}
