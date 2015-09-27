package wsilva.com.br.mobileos.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.activity.CoreActivity;

public class MainActivity extends CoreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_menu_principal);

        //Configuração
        init();
    }

    protected void init()
    {
        Button btnOrdemServico = (Button) findViewById(R.id.btnOrdemServico);
        btnOrdemServico.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });

        Button btnOcorrencias = (Button) findViewById(R.id.btnOcorrencias);
        btnOcorrencias.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });

        Button btnChecklist = (Button) findViewById(R.id.btnChecklist);
        btnChecklist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });

        Button btnConfiguracao = (Button) findViewById(R.id.btnConfiguracao);
        btnConfiguracao.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });

        Button btnSair = (Button) findViewById(R.id.btnSair);
        btnSair.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });
    }
}
