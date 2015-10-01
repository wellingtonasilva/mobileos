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
import wsilva.com.br.mobileos.adapter.ListaOrdemServicoMaterialUtilizadoAdapter;
import wsilva.com.br.mobileos.dao.os.MaterialUtilizadoDAO;
import wsilva.com.br.mobileos.entity.os.MaterialUtilizadoVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class ListaOrdemServicoMaterialUtilizadoFragment extends Fragment
{
    OrdemServicoVO ordemServico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico_material_utilizado_list, container, false);
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
        MaterialUtilizadoDAO dao = new MaterialUtilizadoDAO(getActivity());
        dao.removerTodos();
        if (dao.quantidadeRegistros() ==0)
        {
            dao.inserir(new MaterialUtilizadoVO(9999, "Unidade", "ADAPT. FoFo FF JE DE 107,2MMX133,2MM", 1.0 ));
            dao.inserir(new MaterialUtilizadoVO(9999, "Unidade", "ADAPT. FoFo FF JE DE 107,2MMX133,2MM", 2.0));
            dao.inserir(new MaterialUtilizadoVO(9999, "Unidade", "ADAPT. FoFo FF JE DE 107,2MMX133,2MM", 3.0));
            dao.inserir(new MaterialUtilizadoVO(9999, "Unidade", "ADAPT. FoFo FF JE DE 107,2MMX133,2MM", 4.0));
            dao.inserir(new MaterialUtilizadoVO(9999, "Unidade", "ADAPT. FoFo FF JE DE 107,2MMX133,2MM", 5.0));
            dao.inserir(new MaterialUtilizadoVO(9999, "Unidade", "ADAPT. FoFo FF JE DE 107,2MMX133,2MM", 6.0));
        }

        doListar(root, dao.listar());
    }

    protected void doPovoaTela(View view, OrdemServicoVO vo)
    {
        if (vo!=null)
        {
        }
    }

    protected void doListar(View view, List<MaterialUtilizadoVO> items)
    {
        ListaOrdemServicoMaterialUtilizadoAdapter listAdoFotosAdapter =
                new ListaOrdemServicoMaterialUtilizadoAdapter(getActivity(),
                        R.layout.lay_ordem_servico_material_utilizado_list_row, items);
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
