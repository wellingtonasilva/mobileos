package wsilva.com.br.mobileos.entity.checklist;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class FuncionariosVO extends EntityVO {

	private static final long serialVersionUID = 1L;
	public int matricula;
	public String nomeFuncionario;
	public String nomeSetor;
	public String numeroCNH;
	public String senha;
	public boolean isAtivo;
	
	public FuncionariosVO() {
	}
	
	public FuncionariosVO(int matricula, String nomeFuncionario, String nomeSetor,
			String numeroCNH, String senha, boolean isAtivo) 
	{
		this.matricula=matricula;
		this.nomeFuncionario=nomeFuncionario;
		this.nomeSetor=nomeSetor;
		this.numeroCNH=numeroCNH;
		this.senha=senha;
		this.isAtivo=isAtivo;
	}

}
