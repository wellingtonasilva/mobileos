package wsilva.com.br.mobileos.core.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import wsilva.com.br.mobileos.core.interfaces.ICoreAcaoFim;
import wsilva.com.br.mobileos.core.interfaces.ICoreAcaoResultado;

public class CoreAcaoResultadoReceiver extends BroadcastReceiver
{
    public static final String ACTION_CORE_ACAO_RESULTADO = "wsilva.com.br.mobileos.receiver.action.CORE_ACAO_RESULTADO";
    private ICoreAcaoResultado coreAcaoResultado;

    public CoreAcaoResultadoReceiver() {
    }

    public void setCoreAcaoResultado(ICoreAcaoResultado coreAcaoResultado)
    {
        this.coreAcaoResultado = coreAcaoResultado;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (coreAcaoResultado!=null)
        {
            coreAcaoResultado.onCoreAcaoResultado(intent);
        }
    }
}
