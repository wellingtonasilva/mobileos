package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;
import wsilva.com.br.mobileos.core.entity.EntityVO;

public class EquipeVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idEquipe;
	public String nomeEquipe;
	public String numeroPlacaVeiculo;
	public int cargaHorarioTrabalhoDia;
	
	public EquipeVO() {
	}
	
	public EquipeVO(SoapObject object) {
		serialize(object);
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{	
			this.idEquipe=Integer.parseInt(object.getProperty("idEquipe").toString());
			this.nomeEquipe=object.getProperty("nomeEquipe").toString();
			this.numeroPlacaVeiculo=object.getProperty("numeroPlacaVeiculo").toString();
			this.cargaHorarioTrabalhoDia=Integer.parseInt(object.getProperty("cargaHorarioTrabalho").toString());
		}
	}
	
}
