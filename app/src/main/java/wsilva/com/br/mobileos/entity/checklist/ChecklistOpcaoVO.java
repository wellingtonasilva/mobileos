package wsilva.com.br.mobileos.entity.checklist;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class ChecklistOpcaoVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	public int idGrupo;
	public int idItem;
	public int idOpcao;
	public String descricaoOpcao;
	
	public ChecklistOpcaoVO() {
	}

	public ChecklistOpcaoVO(int idGrupo, int idItem, int idOpcao, String descricao) {
		this.idGrupo=idGrupo;
		this.idItem=idItem;
		this.idOpcao=idOpcao;
		this.descricaoOpcao=descricao;
	}
}
