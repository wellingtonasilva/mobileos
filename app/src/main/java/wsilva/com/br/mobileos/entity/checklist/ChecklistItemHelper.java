package wsilva.com.br.mobileos.entity.checklist;

import java.util.List;

public class ChecklistItemHelper {

	public ChecklistItemVO item;
	public List<ChecklistOpcaoVO> opcoes;
	public int idOpcaoChecked=-1;
	
	public ChecklistItemHelper() {
	}
	
	public ChecklistItemHelper(ChecklistItemVO item, List<ChecklistOpcaoVO> opcoes, int idOpcaoChecked) {
		this.item=item;
		this.opcoes=opcoes;
		this.idOpcaoChecked=idOpcaoChecked;
	}
}
