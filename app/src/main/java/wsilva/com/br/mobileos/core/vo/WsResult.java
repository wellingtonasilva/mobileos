package wsilva.com.br.mobileos.core.vo;

import java.util.List;

public class WsResult {

	public String errorCode;
	public String errorMensage;
	public String erroType;
	public List result;
	
	public WsResult() {
		this.errorCode = "";
		this.errorMensage = "";
		this.erroType = "";
	}
}
