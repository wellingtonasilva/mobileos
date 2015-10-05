package wsilva.com.br.mobileos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.os.HidrometroLocalArmazenagemDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroLocalInstalacaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroProtecaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroSituacaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroTipoSubstituicaoDAO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoSubstituicaoHMFragment extends Fragment
{
    OrdemServicoVO ordemServico;
    Spinner spiOSSubstituicaoHMTipoMedicaoAtual=null;
    Spinner spiOSSubstituicaoHMSituacaoHM=null;
    Spinner spiOSSubstituicaoHMLocalArmazenagem=null;
    Spinner spiOSSubstituicaoHMLocalInstalacao=null;
    Spinner spiOSSubstituicaoHMProtecao=null;
    Spinner spiOSSubstituicaoHMTipoSubstituicao=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico_substituicao_hm, container, false);
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

        //Tipo Medição Atual
        spiOSSubstituicaoHMTipoMedicaoAtual=(Spinner) root.findViewById(R.id.spiOSSubstituicaoHMTipoMedicaoAtual);
        spiOSSubstituicaoHMTipoMedicaoAtual.setAdapter(Util.criarArrayAdapter(root.getContext(),
                R.array.arrTipoMedicao, android.R.layout.simple_spinner_item));
        //Situação do Hidrômetro
        spiOSSubstituicaoHMSituacaoHM=(Spinner) root.findViewById(R.id.spiOSSubstituicaoHMSituacaoHM);
        spiOSSubstituicaoHMSituacaoHM.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroSituacaoDAO(root.getContext()) ,
                new String[]{HidrometroSituacaoDAO.COL_DESCRICAOSITUACAOHIDROMETRO},
                new int[]{android.R.id.text1}));
        //Local Armazenagem
        spiOSSubstituicaoHMLocalArmazenagem=(Spinner) root.findViewById(R.id.spiOSSubstituicaoHMLocalArmazenagem);
        spiOSSubstituicaoHMLocalArmazenagem.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroLocalArmazenagemDAO(root.getContext()) ,
                new String[]{HidrometroLocalArmazenagemDAO.COL_DESCRICAOLOCALARMAZENAGEMHIDROMETRO},
                new int[]{android.R.id.text1}));
        //Local Instalaçãoo
        spiOSSubstituicaoHMLocalInstalacao=(Spinner) root.findViewById(R.id.spiOSSubstituicaoHMLocalInstalacao);
        spiOSSubstituicaoHMLocalInstalacao.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroLocalInstalacaoDAO(root.getContext()),
                new String[]{HidrometroLocalInstalacaoDAO.COL_DESCRICAOLOCALINSTALACAOHIDROMETRO},
                new int[]{android.R.id.text1}));
        //Proteçõa do Hidrômetro
        spiOSSubstituicaoHMProtecao=(Spinner) root.findViewById(R.id.spiOSSubstituicaoHMProtecao);
        spiOSSubstituicaoHMProtecao.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroProtecaoDAO(root.getContext()),
                new String[]{HidrometroProtecaoDAO.COL_DESCRICAOPROTECAOHIDROMETRO},
                new int[]{android.R.id.text1}));
        //Tipo substituição
        spiOSSubstituicaoHMTipoSubstituicao=(Spinner) root.findViewById(R.id.spiOSSubstituicaoHMTipoSubstituicao);
        spiOSSubstituicaoHMTipoSubstituicao.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroTipoSubstituicaoDAO(root.getContext()),
                new String[]{HidrometroTipoSubstituicaoDAO.COL_DESCRICAOTIPOSUBSTITUICAOHM},
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
