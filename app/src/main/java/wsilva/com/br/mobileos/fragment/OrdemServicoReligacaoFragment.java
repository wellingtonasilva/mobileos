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
import wsilva.com.br.mobileos.dao.os.ReligacaoTipoDAO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoReligacaoFragment extends Fragment
{
    OrdemServicoVO ordemServico;
    Spinner spiOSReligacaoLocalInstalacao;
    Spinner spiOSReligacaoProtecao;
    Spinner spiOSReligacaoTipo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico_religacao, container, false);
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

        //Local instalação
        spiOSReligacaoLocalInstalacao=(Spinner) root.findViewById(R.id.spiOSReligacaoLocalInstalacao);
        spiOSReligacaoLocalInstalacao.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroLocalInstalacaoDAO(root.getContext()),
                new String[]{HidrometroLocalInstalacaoDAO.COL_DESCRICAOLOCALINSTALACAOHIDROMETRO},
                new int[]{android.R.id.text1}));

        //Proteção
        spiOSReligacaoProtecao=(Spinner) root.findViewById(R.id.spiOSReligacaoProtecao);
        spiOSReligacaoProtecao.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroProtecaoDAO(root.getContext()),
                new String[]{HidrometroProtecaoDAO.COL_DESCRICAOPROTECAOHIDROMETRO},
                new int[]{android.R.id.text1}));
        //Tipo de Religação
        spiOSReligacaoTipo=(Spinner) root.findViewById(R.id.spiOSReligacaoTipo);
        spiOSReligacaoTipo.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new ReligacaoTipoDAO(root.getContext()),
                new String[]{ReligacaoTipoDAO.COL_DESCRICAOTIPORELIGACAO},
                new int[]{android.R.id.text1}));

        doPovoaTela(root, null);
    }

    protected void doPovoaTela(View view, ValaVO vo)
    {
        if (vo!=null)
        {
        }
    }
}
