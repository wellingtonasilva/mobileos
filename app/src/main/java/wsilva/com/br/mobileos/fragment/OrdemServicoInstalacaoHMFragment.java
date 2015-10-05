package wsilva.com.br.mobileos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.os.HidrometroLocalInstalacaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroProtecaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroTipoInstalacaoDAO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoInstalacaoHMFragment extends Fragment
{
    OrdemServicoVO ordemServico;
    Spinner spiOSInstalacaoHMLocalInstalacao=null;
    Spinner spiOSInstalacaoHMProtecao=null;
    Spinner spiOSInstalacaoHMTipoInstalacao=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico_instalacao_hm, container, false);
        //Argumentos
        Bundle bundle = getArguments();
        //Configuracao inicial
        init(root, bundle);

        return root;
    }

    protected void init(View root, Bundle arguments)
    {
        if (arguments!=null)
        {
            ordemServico = (OrdemServicoVO) arguments.getSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM);
        }

        //Loca da Instalação
        spiOSInstalacaoHMLocalInstalacao=(Spinner) root.findViewById(R.id.spiOSInstalacaoHMLocalInstalacao);
        spiOSInstalacaoHMLocalInstalacao.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroLocalInstalacaoDAO(root.getContext()),
                new String[]{HidrometroLocalInstalacaoDAO.COL_DESCRICAOLOCALINSTALACAOHIDROMETRO},
                new int[]{android.R.id.text1}));
        //Proteção do Hidrômetro
        spiOSInstalacaoHMProtecao=(Spinner) root.findViewById(R.id.spiOSInstalacaoHMProtecao);
        spiOSInstalacaoHMProtecao.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroProtecaoDAO(root.getContext()),
                new String[]{HidrometroProtecaoDAO.COL_DESCRICAOPROTECAOHIDROMETRO},
                new int[]{android.R.id.text1}));
        //Tipo Instalação
        spiOSInstalacaoHMTipoInstalacao=(Spinner) root.findViewById(R.id.spiOSInstalacaoHMTipoInstalacao);
        spiOSInstalacaoHMTipoInstalacao.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroTipoInstalacaoDAO(root.getContext()),
                new String[]{HidrometroTipoInstalacaoDAO.COL_DESCRICAOTIPOINSTALACAOHM, HidrometroTipoInstalacaoDAO.COL_IDTIPOINSTALACAOHM},
                new int[]{R.id.lblSpinnerItem1,R.id.lblSpinnerItem2},
                getActivity(),
                R.layout.lay_template_spinner_item,
                R.layout.lay_template_spinner_dropdown_item));

        doPovoaTela(root, null);
    }

    protected void doPovoaTela(View view, ValaVO vo)
    {
        if (vo!=null)
        {
        }
    }
}
