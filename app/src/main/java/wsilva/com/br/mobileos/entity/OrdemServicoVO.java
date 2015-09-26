package wsilva.com.br.mobileos.entity;

import java.io.Serializable;
import java.util.Date;

import org.ksoap2.serialization.SoapObject;

import br.com.wsilva.mobileos.util.Util;

public class OrdemServicoVO extends EntityVO implements Serializable 
{

	private static final long serialVersionUID = 1L;
	private int NumeroOS;
	private int NumeroRA;
	private int SituacaoOS;
	private int SituacaoRA;
	private String DesricaoSituacaoRA;
	private String DescricaoSituacaoOS;
	private Date DataGeracaoOS;
	private Date DataGeracaoRA;
	private int IdTipoServico;
	private String DescricaoTipoServico;
	private String ObservacaoOS;
	private String ObservacaoRA;
	private int IdUnidadeGeracao;
	private String DescricaoUnidadeGeracao;
	private int IdTipoServicoExecutado;
	private String DescricaoTipoServicoExecutado;
	
	//Dados do Encerramento
	private Date DataEncerramentoOS;
	private String HoraEncerramentoOS;
	private int IdMotivoEncerramento;
	private String DescricaoMotivoEncerramento;
	private String ParecerEncerramento;
	
	//Dados de Execu��o
	private Date DataExecucao;
	private String HoraInicialExecucao;
	private String HoraFinalExecucao;
	private int IdEquipeExecucao;
	private String DescricaoEquipeExecucao;
	
	//Dados Rede/Ramal de �gua
	private int TipoRede;
	private String DescricaoTipoRede;
	private int IdDiametroRede;
	private String DescricaoDiametroRede;
	private int IdMaterialRede;
	private String DescricaoMaterialRede;
	private String ProfundidadeRede;
	private String PressaoRede;
	private int IdAgenteExterno;
	private String AgenteExterno;
	private int IdCausaRede;
	private String DescricaoCausaRede;
	
	private int IdUsuario;
	private String DescricaoUsuario;
	
	//Endere�o
	private String Logradouro;
	private String Bairro;
	private String NumeroImovel;
	private int IdCliente;
	private int IdImovel;
	private String NomeCliente;
	
	private Date DataCancelamento;
	private String HoraCancelamento;
	
	private int IdTipoServicoGerar;
	private String DescricaoTipoServicoGerar;
	private int IdMovimentoRecebito;
	private int IdColetorEnvioItem;
	private int IdColetorEnvio;
	
	private int kmInicial;
	private int kmFinal;
	private int idOrdemServico;
	private int numeroLacreAnterior;
	private int numeroLocreNovo;
	private String numeroHidrometro;
	private int leitura;
	private String observacaoCampo;
	private int indicadorOrdemServicoAvulsa;
	
	/**
	 * Adiciona os campos:
	 * 	1. Setor Comercial
	 *  2. Lote
	 *  3. Sublote
	 *  4. Sequencia na Rota
	 *  5. Quadra
	 */
	private int idSetorComercial;
	private int numeroLote;
	private int numeroSublote;
	private int sequenciaRota;
	private String numeroQuadra;
	
	private int indicadorEnvio;
	private String tipoLogradouro;
	private String latitude;
	private String longitude;

	
	public OrdemServicoVO() {
	}
	
	public OrdemServicoVO(SoapObject object) {
		serialize(object);
	}
	
	public int getKmInicial() {
		return kmInicial;
	}
	
	public void setKmInicial(int kmInicial) {
		this.kmInicial = kmInicial;
	}
	public int getKmFinal() {
		return kmFinal;
	}
	
	public void setKmFinal(int kmFinal) {
		this.kmFinal = kmFinal;
	}
	public int getIdMovimentoRecebito() {
		return IdMovimentoRecebito;
	}
	public void setIdMovimentoRecebito(int idMovimentoRecebito) {
		IdMovimentoRecebito = idMovimentoRecebito;
	}
	public int getIdColetorEnvioItem() {
		return IdColetorEnvioItem;
	}
	public void setIdColetorEnvioItem(int idColetorEnvioItem) {
		IdColetorEnvioItem = idColetorEnvioItem;
	}
	public int getIdColetorEnvio() {
		return IdColetorEnvio;
	}
	public void setIdColetorEnvio(int idColetorEnvio) {
		IdColetorEnvio = idColetorEnvio;
	}
	public String getBairro() {
		return Bairro;
	}
	public void setBairro(String bairro) {
		Bairro = bairro;
	}
	public int getNumeroOS() {
		return NumeroOS;
	}
	public void setNumeroOS(int numeroOS) {
		NumeroOS = numeroOS;
	}
	public int getNumeroRA() {
		return NumeroRA;
	}
	public void setNumeroRA(int numeroRA) {
		NumeroRA = numeroRA;
	}
	public int getSituacaoOS() {
		return SituacaoOS;
	}
	public void setSituacaoOS(int situacaoOS) {
		SituacaoOS = situacaoOS;
	}
	public int getSituacaoRA() {
		return SituacaoRA;
	}
	public void setSituacaoRA(int situacaoRA) {
		SituacaoRA = situacaoRA;
	}
	public String getDesricaoSituacaoRA() {
		return DesricaoSituacaoRA;
	}
	public void setDesricaoSituacaoRA(String desricaoSituacaoRA) {
		DesricaoSituacaoRA = desricaoSituacaoRA;
	}
	public String getDescricaoSituacaoOS() {
		return DescricaoSituacaoOS;
	}
	public void setDescricaoSituacaoOS(String descricaoSituacaoOS) {
		DescricaoSituacaoOS = descricaoSituacaoOS;
	}
	public Date getDataGeracaoOS() {
		return DataGeracaoOS;
	}
	public void setDataGeracaoOS(Date dataGeracaoOS) {
		DataGeracaoOS = dataGeracaoOS;
	}
	public Date getDataGeracaoRA() {
		return DataGeracaoRA;
	}
	public void setDataGeracaoRA(Date dataGeracaoRA) {
		DataGeracaoRA = dataGeracaoRA;
	}
	public int getIdTipoServico() {
		return IdTipoServico;
	}
	public void setIdTipoServico(int idTipoServico) {
		IdTipoServico = idTipoServico;
	}
	public String getDescricaoTipoServico() {
		return DescricaoTipoServico;
	}
	public void setDescricaoTipoServico(String descricaoTipoServico) {
		DescricaoTipoServico = descricaoTipoServico;
	}
	public String getObservacaoOS() {
		return ObservacaoOS;
	}
	public void setObservacaoOS(String observacaoOS) {
		ObservacaoOS = observacaoOS;
	}
	public String getObservacaoRA() {
		return ObservacaoRA;
	}
	public void setObservacaoRA(String observacaoRA) {
		ObservacaoRA = observacaoRA;
	}
	public int getIdUnidadeGeracao() {
		return IdUnidadeGeracao;
	}
	public void setIdUnidadeGeracao(int idUnidadeGeracao) {
		IdUnidadeGeracao = idUnidadeGeracao;
	}
	public String getDescricaoUnidadeGeracao() {
		return DescricaoUnidadeGeracao;
	}
	public void setDescricaoUnidadeGeracao(String descricaoUnidadeGeracao) {
		DescricaoUnidadeGeracao = descricaoUnidadeGeracao;
	}
	public int getIdTipoServicoExecutado() {
		return IdTipoServicoExecutado;
	}
	public void setIdTipoServicoExecutado(int idTipoServicoExecutado) {
		IdTipoServicoExecutado = idTipoServicoExecutado;
	}
	public String getDescricaoTipoServicoExecutado() {
		return DescricaoTipoServicoExecutado;
	}
	public void setDescricaoTipoServicoExecutado(
			String descricaoTipoServicoExecutado) {
		DescricaoTipoServicoExecutado = descricaoTipoServicoExecutado;
	}
	public Date getDataEncerramentoOS() {
		return DataEncerramentoOS;
	}
	public void setDataEncerramentoOS(Date dataEncerramentoOS) {
		DataEncerramentoOS = dataEncerramentoOS;
	}
	public String getHoraEncerramentoOS() {
		return HoraEncerramentoOS;
	}
	public void setHoraEncerramentoOS(String horaEncerramentoOS) {
		HoraEncerramentoOS = horaEncerramentoOS;
	}
	public int getIdMotivoEncerramento() {
		return IdMotivoEncerramento;
	}
	public void setIdMotivoEncerramento(int idMotivoEncerramento) {
		IdMotivoEncerramento = idMotivoEncerramento;
	}
	public String getDescricaoMotivoEncerramento() {
		return DescricaoMotivoEncerramento;
	}
	public void setDescricaoMotivoEncerramento(String descricaoMotivoEncerramento) {
		DescricaoMotivoEncerramento = descricaoMotivoEncerramento;
	}
	public String getParecerEncerramento() {
		return ParecerEncerramento;
	}
	public void setParecerEncerramento(String parecerEncerramento) {
		ParecerEncerramento = parecerEncerramento;
	}
	public Date getDataExecucao() {
		return DataExecucao;
	}
	public void setDataExecucao(Date dataExecucao) {
		DataExecucao = dataExecucao;
	}
	public String getHoraInicialExecucao() {
		return HoraInicialExecucao;
	}
	public void setHoraInicialExecucao(String horaInicialExecucao) {
		HoraInicialExecucao = horaInicialExecucao;
	}
	public String getHoraFinalExecucao() {
		return HoraFinalExecucao;
	}
	public void setHoraFinalExecucao(String horaFinalExecucao) {
		HoraFinalExecucao = horaFinalExecucao;
	}
	public int getIdEquipeExecucao() {
		return IdEquipeExecucao;
	}
	public void setIdEquipeExecucao(int idEquipeExecucao) {
		IdEquipeExecucao = idEquipeExecucao;
	}
	public String getDescricaoEquipeExecucao() {
		return DescricaoEquipeExecucao;
	}
	public void setDescricaoEquipeExecucao(String descricaoEquipeExecucao) {
		DescricaoEquipeExecucao = descricaoEquipeExecucao;
	}
	public int getTipoRede() {
		return TipoRede;
	}
	public void setTipoRede(int tipoRede) {
		TipoRede = tipoRede;
	}
	public String getDescricaoTipoRede() {
		return DescricaoTipoRede;
	}
	public void setDescricaoTipoRede(String descricaoTipoRede) {
		DescricaoTipoRede = descricaoTipoRede;
	}
	public int getIdDiametroRede() {
		return IdDiametroRede;
	}
	public void setIdDiametroRede(int idDiametroRede) {
		IdDiametroRede = idDiametroRede;
	}
	public String getDescricaoDiametroRede() {
		return DescricaoDiametroRede;
	}
	public void setDescricaoDiametroRede(String descricaoDiametroRede) {
		DescricaoDiametroRede = descricaoDiametroRede;
	}
	public int getIdMaterialRede() {
		return IdMaterialRede;
	}
	public void setIdMaterialRede(int idMaterialRede) {
		IdMaterialRede = idMaterialRede;
	}
	public String getDescricaoMaterialRede() {
		return DescricaoMaterialRede;
	}
	public void setDescricaoMaterialRede(String descricaoMaterialRede) {
		DescricaoMaterialRede = descricaoMaterialRede;
	}
	public String getProfundidadeRede() {
		return ProfundidadeRede;
	}
	public void setProfundidadeRede(String profundidadeRede) {
		ProfundidadeRede = profundidadeRede;
	}
	public String getPressaoRede() {
		return PressaoRede;
	}
	public void setPressaoRede(String pressaoRede) {
		PressaoRede = pressaoRede;
	}
	public int getIdAgenteExterno() {
		return IdAgenteExterno;
	}
	public void setIdAgenteExterno(int idAgenteExterno) {
		IdAgenteExterno = idAgenteExterno;
	}
	public String getAgenteExterno() {
		return AgenteExterno;
	}
	public void setAgenteExterno(String agenteExterno) {
		AgenteExterno = agenteExterno;
	}
	public int getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}
	public String getDescricaoUsuario() {
		return DescricaoUsuario;
	}
	public void setDescricaoUsuario(String descricaoUsuario) {
		DescricaoUsuario = descricaoUsuario;
	}
	public String getLogradouro() {
		return Logradouro;
	}
	public void setLogradouro(String logradouro) {
		Logradouro = logradouro;
	}
	public String getNumeroImovel() {
		return NumeroImovel;
	}
	public void setNumeroImovel(String numeroImovel) {
		NumeroImovel = numeroImovel;
	}
	public int getIdCliente() {
		return IdCliente;
	}
	public void setIdCliente(int idCliente) {
		IdCliente = idCliente;
	}
	public int getIdImovel() {
		return IdImovel;
	}
	public void setIdImovel(int idImovel) {
		IdImovel = idImovel;
	}
	public String getNomeCliente() {
		return NomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		NomeCliente = nomeCliente;
	}
	public int getIdCausaRede() {
		return IdCausaRede;
	}
	public void setIdCausaRede(int idCausaRede) {
		IdCausaRede = idCausaRede;
	}
	public String getDescricaoCausaRede() {
		return DescricaoCausaRede;
	}
	public void setDescricaoCausaRede(String descricaoCausaRede) {
		DescricaoCausaRede = descricaoCausaRede;
	}
	public String getHoraCancelamento() {
		return HoraCancelamento;
	}
	public void setHoraCancelamento(String horaCancelamento) {
		HoraCancelamento = horaCancelamento;
	}
	public Date getDataCancelamento() {
		return DataCancelamento;
	}
	public void setDataCancelamento(Date dataCancelamento) {
		DataCancelamento = dataCancelamento;
	}
	public int getIdTipoServicoGerar() {
		return IdTipoServicoGerar;
	}
	public void setIdTipoServicoGerar(int idTipoServicoGerar) {
		IdTipoServicoGerar = idTipoServicoGerar;
	}
	public String getDescricaoTipoServicoGerar() {
		return DescricaoTipoServicoGerar;
	}
	public void setDescricaoTipoServicoGerar(String descricaoTipoServicoGerar) {
		DescricaoTipoServicoGerar = descricaoTipoServicoGerar;
	}

	public int getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(int idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public int getNumeroLacreAnterior() {
		return numeroLacreAnterior;
	}

	public void setNumeroLacreAnterior(int numeroLacreAnterior) {
		this.numeroLacreAnterior = numeroLacreAnterior;
	}

	public int getNumeroLocreNovo() {
		return numeroLocreNovo;
	}

	public void setNumeroLocreNovo(int numeroLocreNovo) {
		this.numeroLocreNovo = numeroLocreNovo;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public int getLeitura() {
		return leitura;
	}

	public void setLeitura(int leitura) {
		this.leitura = leitura;
	}

	public String getObservacaoCampo() {
		return observacaoCampo;
	}

	public void setObservacaoCampo(String observacaoCampo) {
		this.observacaoCampo = observacaoCampo;
	}
	
	public int getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(int idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public int getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(int numeroLote) {
		this.numeroLote = numeroLote;
	}

	public int getNumeroSublote() {
		return numeroSublote;
	}

	public void setNumeroSublote(int numeroSublote) {
		this.numeroSublote = numeroSublote;
	}

	public int getSequenciaRota() {
		return sequenciaRota;
	}

	public void setSequenciaRota(int sequenciaRota) {
		this.sequenciaRota = sequenciaRota;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
	
	public int getIndicadorEnvio() {
		return indicadorEnvio;
	}
	public void setIndicadorEnvio(int indicadorEnvio) {
		this.indicadorEnvio = indicadorEnvio;
	}
	
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	
	public int getIndicadorOrdemServicoAvulsa() {
		return indicadorOrdemServicoAvulsa;
	}

	public void setIndicadorOrdemServicoAvulsa(int indicadorOrdemServicoAvulsa) {
		this.indicadorOrdemServicoAvulsa = indicadorOrdemServicoAvulsa;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() !=0) 
		{
			this.NumeroOS = Integer.parseInt(object.getProperty("idNumeroOS").toString()); 
			this.NumeroRA =  (object.getProperty("idNumeroRA") == null ? 0 : Integer.parseInt(object.getProperty("idNumeroRA").toString()));
			this.SituacaoOS = (object.getProperty("idSituacaoOS") == null ? 0: Integer.parseInt(object.getProperty("idSituacaoOS").toString()));
			this.SituacaoRA = (object.getProperty("idSituacaoRA") == null ? 0: Integer.parseInt(object.getProperty("idSituacaoRA").toString()));
			this.DesricaoSituacaoRA = (object.getProperty("idSituacaoRA") == null ? "" : object.getProperty("descricaoSituacaoOS").toString());
			this.DescricaoSituacaoOS = (object.getProperty("descricaoSituacaoRA") == null ? "" : object.getProperty("descricaoSituacaoRA").toString());

			try {
				this.DataGeracaoOS = Util.stringToDate("yyyy-MM-dd", object.getProperty("dataGeracaoOS").toString());
			} catch (Exception e) {
			}
					
			try {
				this.DataGeracaoRA = Util.stringToDate("yyyy-MM-dd", object.getProperty("dataGeracaoRA").toString());
			} catch (Exception e) {
			}
			
			this.IdTipoServico = Integer.parseInt(object.getProperty("servicoTipo").toString());
			this.DescricaoTipoServico = (object.getProperty("descricaoServicoTipo").toString());
			this.ObservacaoOS = (object.getProperty("observacaoOS") ==null ? "" : object.getProperty("observacaoOS").toString());
			this.ObservacaoRA = (object.getProperty("observacaoRA") ==null ? "" : object.getProperty("observacaoRA").toString());
			this.IdUnidadeGeracao =  ( object.getProperty("idUnidadeGeracao") == null ? 0: Integer.parseInt(object.getProperty("idUnidadeGeracao").toString()));
			this.DescricaoUnidadeGeracao = (object.getProperty("descricaoUnidadeGeracao") == null ? "" :object.getProperty("descricaoUnidadeGeracao").toString());
			this.Logradouro = (object.getProperty("logradouro")  == null ? "" : object.getProperty("logradouro").toString());
			this.Bairro = (object.getProperty("bairro") == null ? "" : object.getProperty("bairro").toString());
			this.NumeroImovel = (object.getProperty("numeroImovel") == null ? "" : object.getProperty("numeroImovel").toString());
			this.IdCliente = (object.getProperty("idCliente") == null ? 0 : Integer.parseInt(object.getProperty("idCliente").toString()));
			this.IdImovel = (object.getProperty("idImovel") == null ? 0: Integer.parseInt(object.getProperty("idImovel").toString()));
			this.NomeCliente = ((object.getProperty("nomeCliente") == null ? "" : object.getProperty("nomeCliente").toString()));
			this.idOrdemServico = Integer.parseInt(object.getProperty("idOrdemServico").toString());
			this.numeroHidrometro = (object.getProperty("numeroHidrometro") == null ? "" :object.getProperty("numeroHidrometro").toString());
			this.idSetorComercial = (object.getProperty("idSetorComercial") == null ? 0 : Integer.parseInt(object.getProperty("idSetorComercial").toString()));
			this.numeroLote = (object.getProperty("numeroLote") == null ? 0 : Integer.parseInt(object.getProperty("numeroLote").toString()));
			this.numeroSublote = (object.getProperty("numeroSublote") == null ? 0 :Integer.parseInt(object.getProperty("numeroSublote").toString()));
			this.sequenciaRota = ( object.getProperty("numeroSequencialRota") == null ? 0 :Integer.parseInt(object.getProperty("numeroSequencialRota").toString()));
			this.numeroQuadra = (object.getProperty("numeroQuadra") == null ? "" : object.getProperty("numeroQuadra").toString());
			this.indicadorEnvio = 2;
			this.indicadorOrdemServicoAvulsa = 2;
		}
	}
	
}

