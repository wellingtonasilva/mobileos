package wsilva.com.br.mobileos.entity;

import java.util.Date;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class OrdemServicoHidrometroSubstituicaoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	public int idOrdemServicoSubstituicaoHM;
	public String numerHidrometroAtual;
	public String indicadorTipoMedicaoAtual;
	public Date dataRetirada;
	public int leituraRetirada;
	public int idSituacaoHidrometro;
	public int idLocalArmazenagemHidrometro;
	public String numeroHidrometroNovo;
	public Date dataInstalacaoHidrometroNovo;
	public String indicadorTipoMedicao;
	public int idLocalInstalacaoHidrometro;
	public int idProtecaoHidrometro;
	public int indicadorTrocaProtecao;
	public int indicadorTrocaRegistro;
	public int leituraInstalacao;
	public String numeroSelo;
	public String indicadorCavalete;
	public String idFuncionario;
	public int idOrdemServico;
	public int idTipoSubstituicaoHM;
	public String horaInstalacaoHidrometroNovo;
	public int idEquipeExecucao;
	public String descricaoEquipeExecucao;
	public int indicadorEnvio;
	
	public OrdemServicoHidrometroSubstituicaoVO() {
	}
	
	public OrdemServicoHidrometroSubstituicaoVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{		
			this.idOrdemServicoSubstituicaoHM=Integer.parseInt(object.getProperty("idOrdemServicoSubstituicaoHM").toString());
			this.numerHidrometroAtual=object.getProperty("numerHidrometroAtual").toString();
			this.indicadorTipoMedicaoAtual=object.getProperty("indicadorTipoMedicaoAtual").toString();
			//this.dataRetirada=object.getProperty("indicadorTipoMedicaoAtual").toString();
			this.leituraRetirada=Integer.parseInt(object.getProperty("leituraRetirada").toString());
			this.idSituacaoHidrometro=Integer.parseInt(object.getProperty("idSituacaoHidrometro").toString());
			this.idLocalArmazenagemHidrometro=Integer.parseInt(object.getProperty("idLocalArmazenagemHidrometro").toString());
			this.numeroHidrometroNovo=object.getProperty("numeroHidrometroNovo").toString();
			//this.dataInstalacaoHidrometroNovo=object.getProperty("dataInstalacaoHidrometroNovo").toString();
			this.indicadorTipoMedicao=object.getProperty("indicadorTipoMedicao").toString();
			this.idLocalInstalacaoHidrometro=Integer.parseInt(object.getProperty("idLocalInstalacaoHidrometro").toString());
			this.idProtecaoHidrometro=Integer.parseInt(object.getProperty("idProtecaoHidrometro").toString());
			this.indicadorTrocaProtecao=Integer.parseInt(object.getProperty("indicadorTrocaProtecao").toString());
			this.indicadorTrocaRegistro=Integer.parseInt(object.getProperty("indicadorTrocaRegistro").toString());
			this.leituraInstalacao=Integer.parseInt(object.getProperty("leituraInstalacao").toString());
			this.numeroSelo=object.getProperty("numeroSelo").toString();
			this.indicadorCavalete=object.getProperty("indicadorCavalete").toString();
			this.idFuncionario=object.getProperty("idFuncionario").toString();
			this.idOrdemServico=Integer.parseInt(object.getProperty("idOrdemServico").toString());
			this.idTipoSubstituicaoHM=Integer.parseInt(object.getProperty("idTipoSubstituicaoHM").toString());
			this.horaInstalacaoHidrometroNovo=object.getProperty("idOrdemServicoSubstituicaoHM").toString();
		}
	}
}
