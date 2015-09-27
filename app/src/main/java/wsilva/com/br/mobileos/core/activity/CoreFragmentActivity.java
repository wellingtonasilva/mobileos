package wsilva.com.br.mobileos.core.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;

import wsilva.com.br.mobileos.core.interfaces.ICoreAcaoFim;
import wsilva.com.br.mobileos.core.interfaces.ICoreAcaoInicio;
import wsilva.com.br.mobileos.core.receivers.CoreAcaoFimReceiver;
import wsilva.com.br.mobileos.core.receivers.CoreAcaoInicioReceiver;

public class CoreFragmentActivity extends FragmentActivity
        implements ICoreAcaoInicio, ICoreAcaoFim
{
    protected CoreAcaoInicioReceiver coreAcaoInicioReceiver;
    protected CoreAcaoFimReceiver coreAcaoFimReceiver;

    @Override
    protected void onResume()
    {
        super.onResume();
        if (coreAcaoInicioReceiver==null) registerBaseFinishReceiver();
        if (coreAcaoFimReceiver==null) registerBaseStartReceiver();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (coreAcaoInicioReceiver!=null) unregisterReceiver(coreAcaoInicioReceiver);
        if (coreAcaoFimReceiver!=null) unregisterReceiver(coreAcaoFimReceiver);
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
}
