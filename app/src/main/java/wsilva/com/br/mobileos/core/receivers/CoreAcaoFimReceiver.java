package wsilva.com.br.mobileos.core.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import wsilva.com.br.mobileos.core.interfaces.ICoreAcaoFim;

public class CoreAcaoFimReceiver extends BroadcastReceiver
{
    public static final String ACTION_CORE_ACAO_FIM = "wsilva.com.br.mobileos.receiver.action.CORE_ACAO_FIM";
    private ICoreAcaoFim coreAcaoFim;

    public CoreAcaoFimReceiver() {
    }

    public void setCoreAcaoFim(ICoreAcaoFim coreAcaoFim)
    {
        this.coreAcaoFim = coreAcaoFim;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (coreAcaoFim!=null)
        {
            coreAcaoFim.onCoreAcaoFim();
        }
    }
}
