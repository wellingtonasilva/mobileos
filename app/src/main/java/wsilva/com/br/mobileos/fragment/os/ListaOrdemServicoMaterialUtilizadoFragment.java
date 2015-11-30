package wsilva.com.br.mobileos.fragment.os;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.activity.OrdemServicoLista;
import wsilva.com.br.mobileos.adapter.os.ListaOrdemServicoMaterialUtilizadoAdapter;
import wsilva.com.br.mobileos.business.MobileOSBusiness;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.os.MaterialUtilizadoDAO;
import wsilva.com.br.mobileos.entity.os.MaterialUtilizadoVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;
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
            ordemServico = (OrdemServicoVO) arguments.getSerializable(OrdemServicoLista.TEMPLATE_SELECTED_ITEM);
        }

        if (ordemServico!=null)
        {
            doPovoaTela(root, ordemServico);
            MaterialUtilizadoDAO dao = new MaterialUtilizadoDAO(getActivity());
            doListar(root, dao.listarPorOrdemServico(ordemServico.numeroOS));
        }
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
                        R.layout.lay_ordem_servico_material_utilizado_list_row, items, onDeleteClickListener);
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

    public void doListar()
    {
        MaterialUtilizadoDAO dao = new MaterialUtilizadoDAO(getActivity());
        doListar(getView(), dao.listarPorOrdemServico(ordemServico.numeroOS));
    }

    View.OnClickListener onDeleteClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            if (ordemServico!=null && (ordemServico.situacaoOS == Util.OS_ID_STATUS_ENCERRADA
                    || ordemServico.situacaoOS == Util.OS_ID_STATUS_CANCELADA ))
            {
                Util.createAlertDialog(getActivity(),
                        getResources().getText(R.string.app_aviso_operacao_nao_permitida).toString(),
                        R.string.app_aviso,
                        R.string.app_ok,
                        R.string.app_nao,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }, null);
                return ;
            }

            if (view!=null)
            {
                MaterialUtilizadoVO materialUtilizado = (MaterialUtilizadoVO) view.getTag();
                Log.w("$$$", materialUtilizado.descricaoMateriall);
                if (MobileOSBusiness.removerMaterialUtilizado(getActivity(), materialUtilizado)) {
                    doListar();
                }
            }
        }
    };
}
