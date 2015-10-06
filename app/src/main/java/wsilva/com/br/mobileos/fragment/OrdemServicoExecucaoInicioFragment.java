package wsilva.com.br.mobileos.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.activity.CoreActivity;
import wsilva.com.br.mobileos.core.activity.CoreFragmentActivity;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.os.OrdemServicoDAO;
import wsilva.com.br.mobileos.dao.os.RedeDiametroDAO;
import wsilva.com.br.mobileos.dao.os.ServicoTipoDAO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.interfaces.IOrdemServicoExecucaoEncerramento;
import wsilva.com.br.mobileos.interfaces.IOrdemServicoExecucaoInicio;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

/**
 * Created by wellingtonasilva on 01/10/15.
 */
public class OrdemServicoExecucaoInicioFragment extends Fragment
{
    IOrdemServicoExecucaoInicio listener;
    OrdemServicoVO ordemServico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico_execucao_inicio, container, false);
        //Argumentos
        Bundle bundle = getArguments();
        //Configuracao inicial
        init(root, bundle);

        return root;
    }

    protected void init(View root, Bundle arguments)
    {
        if (arguments != null) {
            ordemServico = (OrdemServicoVO) arguments.getSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM);
        }

        //Diametro da Rede
        Spinner spiTipoServico = (Spinner) root.findViewById(R.id.spiTipoServico);
        spiTipoServico.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new ServicoTipoDAO(root.getContext()),
                new String[]{ServicoTipoDAO.COL_DESCRICAOSERVICOTIPO},
                new int[]{android.R.id.text1}));

        doPovoaTela(root, ordemServico);
    }

    protected void doPovoaTela(View view, OrdemServicoVO vo)
    {
        if (vo != null)
        {
            EditText txtOsIniciarData=(EditText) view.findViewById(R.id.txtOsIniciarData);
            EditText txtOsIniciarHora=(EditText) view.findViewById(R.id.txtOsIniciarHora);
            EditText txtOsIniciarNumeroOs=(EditText) view.findViewById(R.id.txtOsIniciarNumeroOs);
            //
            txtOsIniciarData.setText(Util.dateToString("dd/MM/yyyy", new Date()));
            txtOsIniciarHora.setText(Util.dateToString("HH:mm", new Date()));
            txtOsIniciarNumeroOs.setText(String.valueOf(vo.numeroOS));
        }
    }

    public void setListener(IOrdemServicoExecucaoInicio listener)
    {
        this.listener = listener;
    }

    public void salvar()
    {
        if (listener != null)
        {
            View view = getView();
            OrdemServicoDAO ordemServicoDAO=new OrdemServicoDAO(getActivity());
            OrdemServicoVO vo=ordemServicoDAO.obterPorId(ordemServico._id);
            //
            Spinner spiTipoServico = (Spinner) view.findViewById(R.id.spiTipoServico);
            EditText txtOsIniciarData=(EditText) view.findViewById(R.id.txtOsIniciarData);
            EditText txtOsIniciarHora=(EditText) view.findViewById(R.id.txtOsIniciarHora);
            EditText txtOsIniciarKMInicial=(EditText) view.findViewById(R.id.txtOsIniciarKMInicial);
            //
            //txtOsIniciarData.setText(Util.dateToString("dd/MM/yyyy", new Date()));
            //txtOsIniciarHora.setText(Util.dateToString("HH:mm", new Date()));
            vo.idTipoServicoExecutado = Util.getItemId(spiTipoServico,
                    ServicoTipoDAO.COL_IDSERVICOTIPO);
            vo.descricaoTipoServicoExecutado = Util.getItemDescricao(spiTipoServico,
                    ServicoTipoDAO.COL_DESCRICAOSERVICOTIPO);
            vo.dataExecucao = new Date();
            vo.horaInicialExecucao = txtOsIniciarHora.getText().toString();
            if (txtOsIniciarKMInicial.getText()!=null && txtOsIniciarKMInicial.getText().toString().length() > 0)
            {
                vo.kmInicial = Integer.parseInt(txtOsIniciarKMInicial.getText().toString());
            }
            listener.onSalvar(vo);
        }
    }
}
