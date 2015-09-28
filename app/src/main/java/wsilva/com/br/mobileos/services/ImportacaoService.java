package wsilva.com.br.mobileos.services;

import android.content.Intent;
import android.content.Context;
import android.util.Log;

import wsilva.com.br.mobileos.business.ImportacaoBusiness;
import wsilva.com.br.mobileos.core.services.CoreService;
import wsilva.com.br.mobileos.core.util.Util;

public class ImportacaoService extends CoreService
{
    public static final String ACTION_IMPORTACAO    = "wsilva.com.br.mobileos.service.action.IMPORTACAO";
    public static final String EXTRA_FILENAME       = "wsilva.com.br.mobileos.service.extra.FILENAME";
    private Context context;

    public static Intent newInstance(Context context, String filename)
    {
        Intent intent = new Intent(context, ImportacaoService.class);
        intent.setAction(ACTION_IMPORTACAO);
        intent.putExtra(EXTRA_FILENAME, filename);

        return intent;
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        Log.w("onHandleIntent", "ImportacaoService>onHandleIntent");
        context = getApplicationContext();

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_IMPORTACAO.equals(action)) {
                enviarMessageInicio();
                final String filename = intent.getStringExtra(EXTRA_FILENAME);
                //Gerar arquivo de Demonstração
                Util.gerarArquivoDemoOrdemServico(context, filename);
                //Iniciar importação
                ImportacaoBusiness.importarArquivo(getApplicationContext(), filename);
                enviarMessageFim();
            }
        }
    }
}
