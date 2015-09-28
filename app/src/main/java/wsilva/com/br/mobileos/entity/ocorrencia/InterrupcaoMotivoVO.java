package wsilva.com.br.mobileos.entity.ocorrencia;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class InterrupcaoMotivoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	public int idInterrupcaoMotivo;
	public String descricao;
	public int indicadorEnviarSMSInicio;
	public int indicadorEnviarSMSFim;
	public int indicadorInicioAtividade;
	public int indicadorFimAtividade;
	public int indicadorChecklistSaida;
	public int indicadorChecklistRetorno;
	public int indicadorSolicitarKMInicio;
	public int indicadorSolicitarKMFim;

}
