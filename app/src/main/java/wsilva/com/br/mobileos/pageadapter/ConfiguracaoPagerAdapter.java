package wsilva.com.br.mobileos.pageadapter;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.entity.os.ConfiguracoesVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.fragment.ConfiguracaoFragment;
import wsilva.com.br.mobileos.fragment.ListaOrdemServicoFotoFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoDadosRedeFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoDadosValaFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoMaterialUtilizadoFragment;

public class ConfiguracaoPagerAdapter extends FragmentPagerAdapter
{
    public static final int TAB_CONFIGURACAO            = 0;
    public static final String KEY_CONFIGURACAO         = "KEY_CONFIGURACAO";

    Resources resources;
    ConfiguracoesVO configuracoes;

    public ConfiguracaoPagerAdapter(FragmentManager fm, Resources resources, ConfiguracoesVO configuracoes)
    {
        super(fm);
        this.resources = resources;
        this.configuracoes = configuracoes;
    }

    @Override
    public Fragment getItem(int position)
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_CONFIGURACAO, configuracoes);

        switch (position)
        {
            case TAB_CONFIGURACAO:
                Fragment adoCadastroFragment = new ConfiguracaoFragment();
                adoCadastroFragment.setArguments(bundle);
                return adoCadastroFragment;
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount()
    {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case TAB_CONFIGURACAO:
                return resources.getText(R.string.app_configuracao).toString();
            default:
                break;
        }

        return "";
    }
}

