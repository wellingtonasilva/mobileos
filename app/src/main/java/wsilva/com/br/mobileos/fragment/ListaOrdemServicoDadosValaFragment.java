package wsilva.com.br.mobileos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.adapter.ListaOrdemServicoDadosValaAdapter;
import wsilva.com.br.mobileos.adapter.ListaOrdemServicoFotosAdapter;
import wsilva.com.br.mobileos.dao.os.ValaDAO;
import wsilva.com.br.mobileos.entity.os.FotoVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class ListaOrdemServicoDadosValaFragment extends Fragment
{
    OrdemServicoVO ordemServico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico_dados_vala_list, container, false);
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

        ValaDAO dao = new ValaDAO(getActivity());
        dao.removerTodos();
        if (dao.quantidadeRegistros() == 0)
        {
            dao.inserir(new ValaVO(9999, 1, 1.0,2.0,3.0));
            dao.inserir(new ValaVO(9999, 2, 1.0,2.0,3.0));
            dao.inserir(new ValaVO(9999, 3, 1.0,2.0,3.0));
            dao.inserir(new ValaVO(9999, 4, 1.0,2.0,3.0));
            dao.inserir(new ValaVO(9999, 5, 1.0,2.0,3.0));
        }

        doListar(root, dao.listar());
    }

    protected void doPovoaTela(View view, OrdemServicoVO vo)
    {
        if (vo!=null)
        {
        }
    }

    protected void doListar(View view, List<ValaVO> items)
    {
        ListaOrdemServicoDadosValaAdapter listAdapter =
                new ListaOrdemServicoDadosValaAdapter(getActivity(),
                        R.layout.lay_ordem_servico_dados_vala_list_row, items);
        ListView list = (ListView) view.findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
            }
        });
        list.setAdapter(listAdapter);
    }
}
