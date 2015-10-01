package wsilva.com.br.mobileos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoInstalacaoHMFragment extends Fragment
{
    OrdemServicoVO ordemServico;

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
            ordemServico = (OrdemServicoVO) arguments.getSerializable(OrdemServicoPagerAdapter.KEY_ORDEM_SERVICO);
        }

        doPovoaTela(root, null);
    }

    protected void doPovoaTela(View view, ValaVO vo)
    {
        if (vo!=null)
        {
        }
    }
}
