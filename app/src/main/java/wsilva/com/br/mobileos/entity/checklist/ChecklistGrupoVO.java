package wsilva.com.br.mobileos.entity.checklist;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class ChecklistGrupoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	public int idGrupo;
	public String descricaoGrupo;
	
	public ChecklistGrupoVO() {
	}
	
	public ChecklistGrupoVO(int id, String descricao) {
		this.idGrupo=id;
		this.descricaoGrupo=descricao;
	}
}
