package wsilva.com.br.mobileos.core.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import wsilva.com.br.mobileos.core.receivers.CoreAcaoFimReceiver;
import wsilva.com.br.mobileos.core.receivers.CoreAcaoInicioReceiver;
import wsilva.com.br.mobileos.core.receivers.CoreAcaoResultadoReceiver;

public class CoreService extends IntentService
{
    public static final String EXTRA_USER           = "wsilva.com.br.mobileos.service.extra.USER";
    public static final String EXTRA_RESULT_CODE    = "wsilva.com.br.mobileos.service.extra.RESULT_CODE";
    public static final String EXTRA_RESULT_DATA    = "wsilva.com.br.mobileos.service.extra.RESULT_DATA";

    public CoreService()
    {
        super("CoreService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
    }

    protected void enviarMessageInicio()
    {
        Log.w("enviarMessageInicio", "enviarMessageInicio");
        Intent intent = new Intent();
        intent.setAction(CoreAcaoInicioReceiver.ACTION_CORE_ACAO_INICIO);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        sendBroadcast(intent);
    }

    protected void enviarMessageFim()
    {
        Log.w("enviarMessageFim", "enviarMessageFim");
        Intent intent = new Intent();
        intent.setAction(CoreAcaoFimReceiver.ACTION_CORE_ACAO_FIM);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        sendBroadcast(intent);
    }

    protected void evniarResultado(int resultCode, Bundle resultData)
    {
        Log.w("evniarResultado", "evniarResultado");
        Intent intent = new Intent();
        intent.setAction(CoreAcaoResultadoReceiver.ACTION_CORE_ACAO_RESULTADO);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(EXTRA_RESULT_CODE, resultCode);
        intent.putExtras(resultData);
        sendBroadcast(intent);
    }
}
