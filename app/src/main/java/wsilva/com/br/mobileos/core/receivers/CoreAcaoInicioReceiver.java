package wsilva.com.br.mobileos.core.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import wsilva.com.br.mobileos.core.interfaces.ICoreAcaoFim;
import wsilva.com.br.mobileos.core.interfaces.ICoreAcaoInicio;

public class CoreAcaoInicioReceiver extends BroadcastReceiver
{
    public static final String ACTION_CORE_ACAO_INICIO = "wsilva.com.br.mobileos.receiver.action.CORE_ACAO_INICIO";
    private ICoreAcaoInicio coreAcaoInicio;

    public CoreAcaoInicioReceiver() {
    }

    public void setCoreAcaoInicio(ICoreAcaoInicio coreAcaoInicio)
    {
        this.coreAcaoInicio = coreAcaoInicio;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (coreAcaoInicio!=null)
        {
            coreAcaoInicio.onCoreAcaoInicio();
        }
    }
}
