package wsilva.com.br.mobileos.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.business.LoginBusiness;
import wsilva.com.br.mobileos.core.activity.CoreActivity;
import wsilva.com.br.mobileos.core.util.Util;

public class Login extends CoreActivity
{
	int numeroTentativas=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.lay_login);

		//Configuracao inicial
		init(savedInstanceState);

		//Carrega tabelas basicas
		if (!Util.isOnline(Login.this))
		{
			Toast.makeText(Login.this,  getResources().getText(R.string.app_sem_conexao).toString(),
					Toast.LENGTH_SHORT).show();
		}
		else
		{
			//Carregar tabelas auxiliares
		}

	}
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) 
	{
		super.onSaveInstanceState(outState);
	}

	protected void init(Bundle savedInstanceState)
	{
		Button btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				doLogin();
			}
		});
		
		Button btnSair = (Button) findViewById(R.id. btnSair);
		btnSair.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				doSair();
			}
		});
	}
	
	protected void doLogin()
	{
		EditText edtLogin = (EditText) findViewById(R.id.edtLogin);
		EditText edtPassword = (EditText) findViewById(R.id.edtPassword);

		if (Util.isOnline(Login.this))
		{
			if (LoginBusiness.login(Login.this, edtLogin.getText().toString(), edtPassword.getText().toString()))
			{
				Intent intent = new Intent(Login.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
			else
			{
				Util.createAlertDialog(Login.this, R.string.app_usuario_senha_invalido,
						R.string.app_aviso, R.string.app_ok);
				return;
			}
		}
		else
		{
			Util.createAlertDialog(Login.this, R.string.app_sem_conexao,
					R.string.app_informacao, R.string.app_ok);
			return;
		}

	}
	
	protected void doSair()
	{
		finish();
	}
	
	protected void saveUserId()
	{
	}
	
	protected void loadUserId() {
    }
}
