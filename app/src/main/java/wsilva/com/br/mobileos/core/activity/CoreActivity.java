package wsilva.com.br.mobileos.core.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.interfaces.ICoreAcaoFim;
import wsilva.com.br.mobileos.core.interfaces.ICoreAcaoInicio;
import wsilva.com.br.mobileos.core.receivers.CoreAcaoFimReceiver;
import wsilva.com.br.mobileos.core.receivers.CoreAcaoInicioReceiver;

public class CoreActivity extends Activity implements ICoreAcaoInicio, ICoreAcaoFim
{
    protected CoreAcaoInicioReceiver coreAcaoInicioReceiver;
    protected CoreAcaoFimReceiver coreAcaoFimReceiver;
    protected ProgressDialog progressDialog;

    @Override
    protected void onResume()
    {
        super.onResume();
        if (coreAcaoInicioReceiver==null) registerBaseFinishReceiver();
        if (coreAcaoFimReceiver==null) registerBaseStartReceiver();
        if (progressDialog ==null) registerProgress();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (coreAcaoInicioReceiver!=null)
        {
            unregisterReceiver(coreAcaoInicioReceiver);
            coreAcaoInicioReceiver = null;
        }
        if (coreAcaoFimReceiver!=null)
        {
            unregisterReceiver(coreAcaoFimReceiver);
            coreAcaoFimReceiver = null;
        }
    }

    @Override
    public void onCoreAcaoInicio() {

    }

    @Override
    public void onCoreAcaoFim() {

    }

    protected void registerBaseStartReceiver() {
        IntentFilter intentFilter = new IntentFilter(CoreAcaoInicioReceiver.ACTION_CORE_ACAO_INICIO);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        coreAcaoInicioReceiver = new CoreAcaoInicioReceiver();
        coreAcaoInicioReceiver.setCoreAcaoInicio(this);
        registerReceiver(coreAcaoInicioReceiver, intentFilter);
    }

    protected void registerBaseFinishReceiver() {
        IntentFilter intentFilter = new IntentFilter(CoreAcaoFimReceiver.ACTION_CORE_ACAO_FIM);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        coreAcaoFimReceiver = new CoreAcaoFimReceiver();
        coreAcaoFimReceiver.setCoreAcaoFim(this);
        registerReceiver(coreAcaoFimReceiver, intentFilter);
    }

    protected void registerProgress()
    {
        progressDialog=new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMax(100);
        progressDialog.setTitle(getBaseContext().getResources().getText(R.string.lbl_titulo_aguarde));
        progressDialog.setMessage(getBaseContext().getResources().getText(R.string.lbl_msg_carregando_dados));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }
}
