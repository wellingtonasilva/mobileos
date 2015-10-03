package wsilva.com.br.mobileos.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.activity.CoreFragmentActivity;
import wsilva.com.br.mobileos.fragment.OrdemServicoExecucaoInicioFragment;

/**
 * Created by wellingtonasilva on 03/10/15.
 */
public class OrdemServicoExecucaoInicio extends CoreFragmentActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_template_fragment_dialog);

        init(savedInstanceState);
    }

    protected void init(Bundle savedInstanceState)
    {
        if (findViewById(R.id.pager)!=null)
        {
            if (savedInstanceState!=null) {
                return ;
            }

            OrdemServicoExecucaoInicioFragment fragment = new OrdemServicoExecucaoInicioFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.pager, fragment).commit();
        }
    }
}
