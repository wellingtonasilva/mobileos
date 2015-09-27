package wsilva.com.br.mobileos.entity;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class InterrupcaoVO extends EntityVO
{
	private static final long serialVersionUID = 1L;
	public String dataMovimento;
	public String nomeEquipe;
	public int matricula;
	public int numeroOS;
	public int idInterrupcaoMotivo;
	public String descricaoInterrupcaoMotivo;
	public String observacaoInicio;
	public String observacaoFim;
	public String dataInicio;
	public String horaInicio;
	public String dataFim;
	public String horaFim;
	public int indicadorAtivo;
	public int indicadorEnviouSMSInicio;
	public int indicadorEnviouSMSFim;
	public int kmInicial;
	public int kmFinal;
	public boolean indicadorInicioAtividade;
	public boolean indicadorFimAtividade;
	public boolean indicadorChecklistSaida;
	public boolean indicadorChecklistRetorno;
	public boolean indicadorSolicitarKMInicio;
	public boolean indicadorSolicitarKMFim;
}
