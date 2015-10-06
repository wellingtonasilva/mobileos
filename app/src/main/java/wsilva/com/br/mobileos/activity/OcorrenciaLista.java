package wsilva.com.br.mobileos.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.adapter.ListaOcorrenciaAdapter;
import wsilva.com.br.mobileos.dao.ocorrencia.InterrupcaoDAO;
import wsilva.com.br.mobileos.entity.ocorrencia.InterrupcaoVO;

public class OcorrenciaLista extends Activity {

	public static final String TEMPLATE_SELECTED_ITEM 	= "TEMPLATE_SELECTED_ITEM";
	public static final int TEMPLATE_DIALOG_NOVO		= 1;
	public static final int TEMPLATE_DIALOG_EDIT		= 2;
	public static final int TEMPLATE_DIALOG_LOCALIZAR	= 3;
	public static final int TEMPLATE_DIALOG_DELETE		= 4;
	private ListaOcorrenciaAdapter listTemplateAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_template_list);
		
		init(savedInstanceState);
	}
	
	@Override
	protected void onResume() 
	{
		super.onResume();
		doListar();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id, final Bundle args) 
	{
		switch (id) 
		{
			case TEMPLATE_DIALOG_DELETE:
				return new AlertDialog.Builder(this)
				.setTitle(getBaseContext().getResources().getText(R.string.app_confirmacao))
				.setMessage(getBaseContext().getResources().getText(R.string.app_confirma_exclusao))
				.setCancelable(false)
				.setPositiveButton(getBaseContext().getResources().getText(R.string.app_sim), new DialogInterface.OnClickListener() 
				{
					@Override
					public void onClick(DialogInterface dialog, int which) 
					{
						InterrupcaoVO vo = (InterrupcaoVO) args.getSerializable(TEMPLATE_SELECTED_ITEM);
						if (vo!=null) {
							if (doExcluir(vo)) {
								doListar();
							}
						}
					}
				})
				.setNegativeButton(getBaseContext().getResources().getText(R.string.app_nao), new DialogInterface.OnClickListener() 
				{
					@Override
					public void onClick(DialogInterface dialog, int which) 
					{
					}
				})
				.create();	
			default:
				break;
		}
		return null;
	}

	protected void doVoltar()
	{
		finish();
	}
	
	protected boolean doRefresh()
	{
		return true;
	}
	
	protected boolean doLocalizar()
	{
		return true;
	}
	
	protected void doNovo()
	{
		Intent intent = new Intent(OcorrenciaLista.this, OcorrenciaInicio.class);
		startActivityForResult(intent, TEMPLATE_DIALOG_NOVO);
	}
	
	protected void doEditar(InterrupcaoVO vo)
	{
		Intent intent = new Intent(OcorrenciaLista.this, OcorrenciaFim.class);
		Bundle extras = new Bundle();
		extras.putSerializable(OcorrenciaLista.TEMPLATE_SELECTED_ITEM, vo);
		intent.putExtras(extras);
		startActivity(intent);
	}
	
	protected boolean doExcluir(InterrupcaoVO vo)
	{
		InterrupcaoDAO dao = new InterrupcaoDAO(OcorrenciaLista.this);
		return dao.remover(vo._id);
	}
	
	protected void doListar()
	{
		InterrupcaoDAO dao = new InterrupcaoDAO(OcorrenciaLista.this);
		if (dao.quantidadeRegistros() > 0) 
		{
			listTemplateAdapter = new ListaOcorrenciaAdapter(OcorrenciaLista.this,
					R.layout.lay_ocorrencia_list_row, dao.listar());
			ListView list = (ListView) findViewById(R.id.list);
			list.setOnItemClickListener(new AdapterView.OnItemClickListener() 
			{
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
				{
					InterrupcaoVO vo = (InterrupcaoVO) parent.getAdapter().getItem(position);
					doEditar(vo);
				}
			});
			list.setAdapter(listTemplateAdapter);	
		}
		
		dao.close();
	}
	
	public void onDeleteClick(View v) 
	{
		if (v !=null && v.getContentDescription()!=null)
		{
			String id = v.getContentDescription().toString();
			InterrupcaoDAO dao = new InterrupcaoDAO(OcorrenciaLista.this);
			InterrupcaoVO  selectedItem = dao.obterPorId(Long.parseLong(id));
			Bundle extras = new Bundle();
			extras.putSerializable(TEMPLATE_SELECTED_ITEM, selectedItem);
			showDialog(TEMPLATE_DIALOG_DELETE, extras);
		}
	}

	protected void init(Bundle savedInstanceState)
	{
		//Voltar
		Button btnVoltar = (Button) findViewById(R.id.btnVoltar);
		btnVoltar.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				doVoltar();
			}
		});

		Button btnRefresh = (Button) findViewById(R.id.btnRefresh);
		btnRefresh.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				doRefresh();
			}
		});

		Button btnLocalizar = (Button) findViewById(R.id.btnLocalizar);
		btnLocalizar.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				doLocalizar();
			}
		});

		Button btnNovo = (Button) findViewById(R.id.btnNovo);
		btnNovo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				doNovo();
			}
		});

		alterarTitulo();
		alterarSubtitulo();
	}

	protected void alterarTitulo()
	{
		TextView lblTitulo = (TextView) findViewById(R.id.lblTitulo);
		lblTitulo.setText(getResources().getText(R.string.app_ocorrencias));
	}

	protected void alterarSubtitulo()
	{
		TextView lblSubtitulo = (TextView) findViewById(R.id.lblSubtitulo);
		lblSubtitulo.setText(getResources().getText(R.string.app_listagem));
	}
}
