package wsilva.com.br.mobileos.entity.checklist;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class ChecklistRespostaGrupoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	public int idGrupo;
	public String dataMovimento;
	public int salvouSaida;
	public int salvouRetorno;
	
	public ChecklistRespostaGrupoVO() {
	}
	
	public ChecklistRespostaGrupoVO(int idgrupo, String datamovimento, int salvousaida, int salvouretorno) {
		this.idGrupo=idgrupo;
		this.dataMovimento=datamovimento;
		this.salvouSaida=salvousaida;
		this.salvouRetorno=salvouretorno;
	}
}
