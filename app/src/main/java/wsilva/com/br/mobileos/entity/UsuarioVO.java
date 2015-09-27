package wsilva.com.br.mobileos.entity;

import org.ksoap2.serialization.SoapObject;

import wsilva.com.br.mobileos.core.entity.EntityVO;

public class UsuarioVO extends EntityVO
{

	private static final long serialVersionUID = 1L;
	public int idUsuario;
	public String login;
	public String nome;
	public String senha;
	
	public UsuarioVO() {
	}
	
	public UsuarioVO(SoapObject object) {
		serialize(object);
	}
	
	private void serialize(SoapObject object)
	{
		if (object.getPropertyCount() != 0) 
		{
			this.idUsuario=Integer.parseInt(object.getProperty("idUsuario").toString());
			this.login=object.getProperty("login").toString();
			this.nome=object.getProperty("usuario").toString();
			this.senha=object.getProperty("senha").toString();
		}
	}

	
}
