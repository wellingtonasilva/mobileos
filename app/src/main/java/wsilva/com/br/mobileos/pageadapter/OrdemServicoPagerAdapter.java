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
import wsilva.com.br.mobileos.fragment.OrdemServicoCorteFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoDadosRedeFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoDadosValaFragment;
import wsilva.com.br.mobileos.fragment.ListaOrdemServicoFotoFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoInstalacaoHMFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoLigacaoNovaFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoMaterialUtilizadoFragment;

public class OrdemServicoPagerAdapter extends FragmentPagerAdapter
{
    public static final int TAB_ORDEM_SERVICO           = 0;
    public static final int TAB_DADOS_REDE              = 1;
    public static final int TAB_DADOS_VALA              = 2;
    public static final int TAB_MATERIAL_UTILIZADO      = 3;
    public static final int TAB_FOTOS                   = 4;
    public static final int TAB_ORDEM_SERVICO_CORTE     = 5;
    public static final int TAB_ORDEM_SERVICO_INST_HM   = 6;
    public static final int TAB_ORDEM_SERVICO_LIG_NOVA  = 7;
    public static final String TEMPLATE_SELECTED_ITEM   = "TEMPLATE_SELECTED_ITEM";
    public static final String KEY_ORDEM_VALA           = "KEY_ORDEM_SERVICO_VALA";

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
        bundle.putSerializable(TEMPLATE_SELECTED_ITEM, ordemServico);

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
            case TAB_ORDEM_SERVICO_CORTE:
                Fragment mOrdemServicoCorteFragment = new OrdemServicoCorteFragment();
                mOrdemServicoCorteFragment.setArguments(bundle);
                return mOrdemServicoCorteFragment;
            case TAB_ORDEM_SERVICO_INST_HM:
                Fragment mOrdemServicoInstalacaoHMFragment = new OrdemServicoInstalacaoHMFragment();
                mOrdemServicoInstalacaoHMFragment.setArguments(bundle);
                return mOrdemServicoInstalacaoHMFragment;
            case TAB_ORDEM_SERVICO_LIG_NOVA:
                Fragment mOrdemServicoLigacaoNovaFragment = new OrdemServicoLigacaoNovaFragment();
                mOrdemServicoLigacaoNovaFragment.setArguments(bundle);
                return mOrdemServicoLigacaoNovaFragment;
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount()
    {
        return 7;
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
            case TAB_ORDEM_SERVICO_CORTE:
                return resources.getText(R.string.lbl_os_corte_titulo).toString();
            case TAB_ORDEM_SERVICO_INST_HM:
                return resources.getText(R.string.lbl_titulo_instalacao_hidrometro).toString();
            case TAB_ORDEM_SERVICO_LIG_NOVA:
                return resources.getText(R.string.lbl_os_ligacao_nova_titulo).toString();
            default:
                break;
        }

        return "";
    }
}

