package wsilva.com.br.mobileos.core.vo;

public class CustomMenuItemVO {

	public int id;
	public String description;
	public int icon;
	
	public CustomMenuItemVO() {
	}

	public CustomMenuItemVO(int id, String description, int icon) {
		this.id = id;
		this.description = description;
		this.icon = icon;
	}
}
