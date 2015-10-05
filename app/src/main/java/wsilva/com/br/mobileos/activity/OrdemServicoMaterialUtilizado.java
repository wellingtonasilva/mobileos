package wsilva.com.br.mobileos.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.business.Fachada;
import wsilva.com.br.mobileos.core.activity.CoreFragmentActivity;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.os.MaterialUtilizadoVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.fragment.OrdemServicoDadosValaFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoMaterialUtilizadoFragment;
import wsilva.com.br.mobileos.interfaces.IOrdemServicoMaterialUtilizado;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

/**
 * Created by wellingtonasilva on 03/10/15.
 */
public class OrdemServicoMaterialUtilizado extends CoreFragmentActivity implements IOrdemServicoMaterialUtilizado
{
    OrdemServicoVO ordemServico;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_template_fragment_dialog);

        init(savedInstanceState);
    }

    @Override
    public void onSalvar(OrdemServicoVO ordemServico, MaterialUtilizadoVO materialUtilizado)
    {
        Log.w("$$$", "OrdemServicoMaterialUtilizado>onSalvar");
        if (Fachada.inserirMaterialUtilizado(OrdemServicoMaterialUtilizado.this, ordemServico,
                materialUtilizado))
        {
            Util.createAlertDialog(OrdemServicoMaterialUtilizado.this,
                    getResources().getText(R.string.app_processo_realizado_sucesso).toString(),
                    R.string.app_name,
                    R.string.app_ok, R.string.app_cancelar,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }, null);
        }
    }

    protected void init(Bundle savedInstanceState)
    {
        //Alterar titulo da página
        alterarSubtitulo();

        if (findViewById(R.id.pager)!=null)
        {
            if (savedInstanceState == null)
            {
                Bundle extras = getIntent().getExtras();
                savedInstanceState = extras;
                if (extras != null)
                {
                    ordemServico = (OrdemServicoVO) extras.getSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM);
                }
            } else
            {
                ordemServico = (OrdemServicoVO) savedInstanceState.getSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM);
            }

            Button btnCancelar = (Button) findViewById(R.id.btnCancelar);
            btnCancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    doCancelar();
                }
            });

            Button btnOK = (Button) findViewById(R.id.btnOK);
            btnOK.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    doOK();
                }
            });

            OrdemServicoMaterialUtilizadoFragment fragment = new OrdemServicoMaterialUtilizadoFragment();
            fragment.setListener(this);
            fragment.setArguments(savedInstanceState);
            getSupportFragmentManager().beginTransaction().add(R.id.pager, fragment).commit();
        }
    }

    protected void doCancelar()
    {
        finish();
    }

    protected void doOK()
    {
        OrdemServicoMaterialUtilizadoFragment fragment =  (OrdemServicoMaterialUtilizadoFragment)
                getSupportFragmentManager().findFragmentById(R.id.pager);
        if (fragment!=null)
        {
            fragment.salvar();
        }
    }

    protected void alterarSubtitulo()
    {
        TextView lblSubtitulo = (TextView) findViewById(R.id.lblSubtitulo);
        lblSubtitulo.setText(getResources().getText(R.string.lbl_material_utilizado_titulo).toString());
    }
}
