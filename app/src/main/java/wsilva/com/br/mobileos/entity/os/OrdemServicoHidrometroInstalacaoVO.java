package wsilva.com.br.mobileos.entity.os;

import java.util.Date;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class OrdemServicoHidrometroInstalacaoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	public int idOrdemServicoInstalacaoHM;
	public String numeroHidrometro;
	public Date dataInstalacaoHidrometro;
	public String idTipoMedicao;
	public int idLocalInstalacaoHidrometro;
	public int idProtecaoHidrometro;
	public int idOrdemServico;
	public int indicadorTrocaProtecao;
	public int indicadorTrocaRegistro;
	public int leituraInstalacao;
	public String numeroSelo;
	public String indcadorCavalete;
	public String idFuncionario;
	public int idTipoInstalacaoHM;
	public String horaInstalacaoHidrometro;
	public int idEquipeExecucao;
	public String descricaoEquipeExecucao;
	public int indicadorEnvio;
	
	public OrdemServicoHidrometroInstalacaoVO() {
	}
	
	public OrdemServicoHidrometroInstalacaoVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.idOrdemServicoInstalacaoHM=Integer.parseInt(object.getProperty("idOrdemServicoInstalacaoHM").toString());
			this.numeroHidrometro=object.getProperty("numeroHidrometro").toString();
			this.dataInstalacaoHidrometro=new Date();
			this.idTipoMedicao=object.getProperty("idTipoMedicao").toString();
			this.idLocalInstalacaoHidrometro=Integer.parseInt(object.getProperty("idLocalInstalacaoHidrometro").toString());
			this.idProtecaoHidrometro=Integer.parseInt(object.getProperty("idProtecaoHidrometro").toString());
			this.idOrdemServico=Integer.parseInt(object.getProperty("idOrdemServico").toString());
			this.indicadorTrocaProtecao=Integer.parseInt(object.getProperty("indicadorTrocaProtecao").toString());
			this.indicadorTrocaRegistro=Integer.parseInt(object.getProperty("indicadorTrocaRegistro").toString());
			this.leituraInstalacao=Integer.parseInt(object.getProperty("leituraInstalacao").toString());
			this.numeroSelo=object.getProperty("numeroSelo").toString();
			this.indcadorCavalete=object.getProperty("indcadorCavalete").toString();
			this.idFuncionario=object.getProperty("idFuncionario").toString();
			this.idTipoInstalacaoHM=Integer.parseInt(object.getProperty("idTipoInstalacaoHM").toString());
			this.horaInstalacaoHidrometro=object.getProperty("horaInstalacaoHidrometro").toString();
		}
	}
}