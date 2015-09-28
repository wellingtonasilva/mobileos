package wsilva.com.br.mobileos.entity.os;

import java.util.Date;
import org.ksoap2.serialization.SoapObject;
import wsilva.com.br.mobileos.core.entity.EntityVO;


public class OrdemServicoVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int numeroOS;
	public int numeroRA;
	public int situacaoOS;
	public int situacaoRA;
	public String desricaoSituacaoRA;
	public String descricaoSituacaoOS;
	public Date dataGeracaoOS;
	public Date dataGeracaoRA;
	public int idTipoServico;
	public String descricaoTipoServico;
	public String observacaoOS;
	public String observacaoRA;
	public int idUnidadeGeracao;
	public String descricaoUnidadeGeracao;
	public int idTipoServicoExecutado;
	public String descricaoTipoServicoExecutado;
	
	//Dados do Encerramento
	public Date dataEncerramentoOS;
	public String horaEncerramentoOS;
	public int idMotivoEncerramento;
	public String descricaoMotivoEncerramento;
	public String parecerEncerramento;
	
	//Dados de Execu��o
	public Date dataExecucao;
	public String horaInicialExecucao;
	public String horaFinalExecucao;
	public int idEquipeExecucao;
	public String descricaoEquipeExecucao;
	
	//Dados Rede/Ramal de �gua
	public int tipoRede;
	public String descricaoTipoRede;
	public int idDiametroRede;
	public String descricaoDiametroRede;
	public int idMaterialRede;
	public String descricaoMaterialRede;
	public String profundidadeRede;
	public String pressaoRede;
	public int idAgenteExterno;
	public String agenteExterno;
	public int idCausaRede;
	public String descricaoCausaRede;

	public int idUsuario;
	public String descricaoUsuario;
	
	//Endere�o
	public String logradouro;
	public String bairro;
	public String numeroImovel;
	public int idCliente;
	public int idImovel;
	public String nomeCliente;

	public Date dataCancelamento;
	public String horaCancelamento;

	public int idTipoServicoGerar;
	public String descricaoTipoServicoGerar;
	public int idMovimentoRecebito;
	public int idColetorEnvioItem;
	public int idColetorEnvio;

	public int kmInicial;
	public int kmFinal;
	public int idOrdemServico;
	public int numeroLacreAnterior;
	public int numeroLocreNovo;
	public String numeroHidrometro;
	public int leitura;
	public String observacaoCampo;
	public int indicadorOrdemServicoAvulsa;
	
	/**
	 * Adiciona os campos:
	 * 	1. Setor Comercial
	 *  2. Lote
	 *  3. Sublote
	 *  4. Sequencia na Rota
	 *  5. Quadra
	 */
	public int idSetorComercial;
	public int numeroLote;
	public int numeroSublote;
	public int sequenciaRota;
	public String numeroQuadra;

	public int indicadorEnvio;
	public String tipoLogradouro;
	public String latitude;
	public String longitude;

	
	public OrdemServicoVO() {
	}
	
	public OrdemServicoVO(SoapObject object) {
		serialize(object);
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() !=0) 
		{
		}
	}
}

