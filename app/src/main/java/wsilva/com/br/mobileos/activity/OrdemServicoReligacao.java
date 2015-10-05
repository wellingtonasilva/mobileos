package wsilva.com.br.mobileos.activity;

import android.os.Bundle;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.activity.CoreFragmentActivity;
import wsilva.com.br.mobileos.fragment.OrdemServicoCorteFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoReligacaoFragment;

/**
 * Created by wellingtonasilva on 03/10/15.
 */
public class OrdemServicoReligacao extends CoreFragmentActivity
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

            OrdemServicoReligacaoFragment fragment = new OrdemServicoReligacaoFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.pager, fragment).commit();
        }
    }
}
