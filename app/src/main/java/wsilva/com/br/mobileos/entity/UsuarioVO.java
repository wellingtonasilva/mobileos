package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

public class UsuarioVO extends EntityVO 
{

	private static final long serialVersionUID = 1L;
	private int IdUsuario;
	private String Login;
	private String Nome;
	private String Senha;
	
	public UsuarioVO() {
	}
	
	public UsuarioVO(SoapObject object) {
		serialize(object);
	}
	
	public int getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.IdUsuario=Integer.parseInt(object.getProperty("idUsuario").toString());
			this.Login=object.getProperty("login").toString();
			this.Nome=object.getProperty("usuario").toString();
			this.Senha=object.getProperty("senha").toString();
		}
	}

	
}
