package wsilva.com.br.mobileos.entity;

import java.util.Date;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class OrdemServicoCorteVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	public int idOrdemServicoCorte;
	public int idOrdemServico;
	public Date dataCorte;
	public String horaCorte;
	public int leituraCorte;
	public String numeroSelo;
	public String idFuncionario;
	public String numeroHidrometro;
	public int idMotivoCorte;
	public int idCorteTipo;
	public int idEquipeExecucao;
	public String descricaoEquipeExecucao;
	public int indicadorEnvio;
	
	public OrdemServicoCorteVO() {
	}
	
	public OrdemServicoCorteVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.idOrdemServicoCorte=Integer.parseInt(object.getProperty("idOrdemServicoCorte").toString());
			this.idOrdemServico=Integer.parseInt(object.getProperty("idOrdemServico").toString());
			//this.dataCorte=Integer.parseInt(object.getProperty("dataCorte").toString());
			this.horaCorte=object.getProperty("horaCorte").toString();
			this.leituraCorte=Integer.parseInt(object.getProperty("leituraCorte").toString());
			this.numeroSelo=object.getProperty("numeroSelo").toString();
			this.idFuncionario=object.getProperty("idFuncionario").toString();
			this.numeroHidrometro=object.getProperty("numeroHidrometro").toString();
			this.idMotivoCorte=Integer.parseInt(object.getProperty("idMotivoCorte").toString());
			this.idCorteTipo=Integer.parseInt(object.getProperty("idCorteTipo").toString());
		}
	}
}
