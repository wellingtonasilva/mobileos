package wsilva.com.br.mobileos.activity;

import android.os.Bundle;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.activity.CoreFragmentActivity;
import wsilva.com.br.mobileos.fragment.OrdemServicoSubstituicaoHMFragment;

/**
 * Created by wellingtonasilva on 03/10/15.
 */
public class OrdemServicoSubstituicaoHM extends CoreFragmentActivity
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

            OrdemServicoSubstituicaoHMFragment fragment = new OrdemServicoSubstituicaoHMFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.pager, fragment).commit();
        }
    }
}
