package wsilva.com.br.mobileos.entity.checklist;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class ChecklistItemVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	public int idGrupo;
	public int idItem;
	public String descricaoItem;
	public int idOpcaoChecked;
	
	public ChecklistItemVO() {
	}
	
	public ChecklistItemVO(int idGrupo, int idItem, String descricaoItem, int idOpcaoChecked) {
		this.idGrupo=idGrupo;
		this.idItem=idItem;
		this.descricaoItem=descricaoItem;
		this.idOpcaoChecked=idOpcaoChecked;
	}
}
