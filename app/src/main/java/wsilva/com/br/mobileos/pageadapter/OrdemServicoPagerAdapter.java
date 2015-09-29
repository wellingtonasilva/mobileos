package wsilva.com.br.mobileos.pageadapter;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.fragment.ListaOrdemServicoDadosValaFragment;
import wsilva.com.br.mobileos.fragment.ListaOrdemServicoMaterialUtilizadoFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoDadosRedeFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoDadosValaFragment;
import wsilva.com.br.mobileos.fragment.ListaOrdemServicoFotoFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoMaterialUtilizadoFragment;

public class OrdemServicoPagerAdapter extends FragmentPagerAdapter
{
    public static final int TAB_ORDEM_SERVICO           = 0;
    public static final int TAB_DADOS_REDE              = 1;
    public static final int TAB_DADOS_VALA              = 2;
    public static final int TAB_MATERIAL_UTILIZADO      = 3;
    public static final int TAB_FOTOS                   = 4;
    public static final String KEY_ORDEM_SERVICO            = "KEY_ORDEM_SERVICO";

    Resources resources;
    OrdemServicoVO ordemServico;

    public OrdemServicoPagerAdapter(FragmentManager fm, Resources resources, OrdemServicoVO ordemServico)
    {
        super(fm);
        this.resources = resources;
        this.ordemServico = ordemServico;
    }

    @Override
    public Fragment getItem(int position)
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_ORDEM_SERVICO, ordemServico);

        switch (position)
        {
            case TAB_ORDEM_SERVICO:
                Fragment adoCadastroFragment = new OrdemServicoFragment();
                adoCadastroFragment.setArguments(bundle);
                return adoCadastroFragment;
            case TAB_DADOS_REDE:
                Fragment adoBOInqueritoPolicialFragment = new OrdemServicoDadosRedeFragment();
                adoBOInqueritoPolicialFragment.setArguments(bundle);
                return adoBOInqueritoPolicialFragment;
            case TAB_DADOS_VALA:
                Fragment adoDiligenciasFragment = new ListaOrdemServicoDadosValaFragment();
                adoDiligenciasFragment.setArguments(bundle);
                return adoDiligenciasFragment;
            case TAB_MATERIAL_UTILIZADO:
                Fragment adoTipoIrregularidadeFragment = new ListaOrdemServicoMaterialUtilizadoFragment();
                adoTipoIrregularidadeFragment.setArguments(bundle);
                return adoTipoIrregularidadeFragment;
            case TAB_FOTOS:
                Fragment adoExtensaoExamesFragment = new ListaOrdemServicoFotoFragment();
                adoExtensaoExamesFragment.setArguments(bundle);
                return adoExtensaoExamesFragment;
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount()
    {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case TAB_ORDEM_SERVICO:
                return resources.getText(R.string.app_ordem_servico).toString();
            case TAB_DADOS_REDE:
                return resources.getText(R.string.app_dados_rede).toString();
            case TAB_DADOS_VALA:
                return resources.getText(R.string.lbl_dados_vala_titulo).toString();
            case TAB_MATERIAL_UTILIZADO:
                return resources.getText(R.string.lbl_material_utilizado_titulo).toString();
            case TAB_FOTOS:
                return resources.getText(R.string.lbl_foto).toString();
            default:
                break;
        }

        return "";
    }
}

