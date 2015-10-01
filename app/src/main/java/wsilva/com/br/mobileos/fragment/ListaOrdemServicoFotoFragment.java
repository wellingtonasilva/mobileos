package wsilva.com.br.mobileos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Date;
import java.util.List;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.adapter.ListaOrdemServicoFotosAdapter;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.os.FotoDAO;
import wsilva.com.br.mobileos.entity.os.FotoVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class ListaOrdemServicoFotoFragment extends Fragment
{
    OrdemServicoVO ordemServico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico_foto, container, false);
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

        doPovoaTela(root, ordemServico);

        FotoDAO dao = new FotoDAO(getActivity());
        dao.removerTodos();
        if (dao.quantidadeRegistros() ==0)
        {
            dao.inserir(new FotoVO(9999, "Foto 1", "Foto 1", new Date(), "14:00"));
            dao.inserir(new FotoVO(9999, "Foto 2", "Foto 2", new Date(), "14:00"));
            dao.inserir(new FotoVO(9999, "Foto 3", "Foto 3", new Date(), "14:00"));
            dao.inserir(new FotoVO(9999, "Foto 4", "Foto 4", new Date(), "14:00"));
            dao.inserir(new FotoVO(9999, "Foto 5", "Foto 5", new Date(), "14:00"));
            dao.inserir(new FotoVO(9999, "Foto 6", "Foto 6", new Date(), "14:00"));
        }

        doListar(root, dao.listar());
    }

    protected void doPovoaTela(View view, OrdemServicoVO vo)
    {
        if (vo!=null)
        {
        }
    }

    protected void doListar(View view, List<FotoVO> items)
    {
        ListaOrdemServicoFotosAdapter listAdoFotosAdapter =
                new ListaOrdemServicoFotosAdapter(getActivity(),
                        R.layout.lay_ordem_servico_foto_list_row, items);
        ListView list = (ListView) view.findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
            }
        });
        list.setAdapter(listAdoFotosAdapter);
    }
}
