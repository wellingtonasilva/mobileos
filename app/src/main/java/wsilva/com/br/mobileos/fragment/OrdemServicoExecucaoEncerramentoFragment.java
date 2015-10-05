package wsilva.com.br.mobileos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.os.MotivoEncerramentoDAO;
import wsilva.com.br.mobileos.dao.os.OrdemServicoDAO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.interfaces.IOrdemServicoExecucaoEncerramento;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

/**
 * Created by wellingtonasilva on 01/10/15.
 */
public class OrdemServicoExecucaoEncerramentoFragment extends Fragment
{
    IOrdemServicoExecucaoEncerramento listener;
    OrdemServicoVO ordemServico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico_execucao_encerramento, container, false);
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
        Spinner spiOsEncerramentoMotivoEncerramento = (Spinner) root.findViewById(R.id.spiOsEncerramentoMotivoEncerramento);
        spiOsEncerramentoMotivoEncerramento.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new MotivoEncerramentoDAO(root.getContext()),
                new String[]{MotivoEncerramentoDAO.COL_DESCRICAOMOTIVOENCERRAMENTO},
                new int[]{android.R.id.text1}));

        doPovoaTela(root, ordemServico);
    }

    public void setListener(IOrdemServicoExecucaoEncerramento listener)
    {
        this.listener = listener;
    }

    protected void doPovoaTela(View view, OrdemServicoVO vo)
    {
        if (vo != null)
        {
            EditText txtNumeroOS=(EditText) view.findViewById(R.id.txtNumeroOS);
            EditText txtOsEncerramentoDataEncerramento=(EditText) view.findViewById(R.id.txtOsEncerramentoDataEncerramento);
            EditText txtOsEncerramentoHoraEncerramento=(EditText) view.findViewById(R.id.txtOsEncerramentoHoraEncerramento);
            //
            txtNumeroOS.setText(String.valueOf(vo.numeroOS));
            txtOsEncerramentoDataEncerramento.setText(Util.dateToString("dd/MM/yyyy", new Date()));
            txtOsEncerramentoHoraEncerramento.setText(Util.dateToString("HH:mm", new Date()));
        }
    }

    public void salvar()
    {
        if (listener!=null)
        {
            View view = getView();
            OrdemServicoDAO ordemServicoDAO=new OrdemServicoDAO(getActivity());
            OrdemServicoVO vo=ordemServicoDAO.obterPorId(ordemServico._id);
            //
            EditText txtOsEncerramentoDataEncerramento=(EditText) view.findViewById(R.id.txtOsEncerramentoDataEncerramento);
            EditText txtEncerramentoOSKMFinal=(EditText) view.findViewById(R.id.txtEncerramentoOSKMFinal);
            EditText txtOsEncerramentoHoraEncerramento=(EditText) view.findViewById(R.id.txtOsEncerramentoHoraEncerramento);
            EditText  txtOsEncerramentoMotivoParecer=(EditText) view.findViewById(R.id.txtOsEncerramentoMotivoParecer);
            Spinner spiOsEncerramentoMotivoEncerramento = (Spinner) view.findViewById(R.id.spiOsEncerramentoMotivoEncerramento);
            //KM Final
            if (txtEncerramentoOSKMFinal.getText()!=null && txtEncerramentoOSKMFinal.getText().length() > 0)
            {
                vo.kmFinal = Integer.parseInt(txtEncerramentoOSKMFinal.getText().toString());
            }
            //Hora do Encerramento
            vo.horaEncerramentoOS = txtOsEncerramentoHoraEncerramento.getText().toString();
            //Data do Encerramento
            vo.dataEncerramentoOS = Util.stringToDate("dd/MM/yyyy", txtOsEncerramentoDataEncerramento.getText().toString());
            vo.idMotivoEncerramento = Util.getItemId(spiOsEncerramentoMotivoEncerramento, MotivoEncerramentoDAO.COL_IDMOTIVOENCERRAMENTO);
            vo.descricaoMotivoEncerramento = Util.getItemDescricao(spiOsEncerramentoMotivoEncerramento, MotivoEncerramentoDAO.COL_DESCRICAOMOTIVOENCERRAMENTO);
            vo.parecerEncerramento = txtOsEncerramentoMotivoParecer.getText().toString();
            //Situação da OS
            vo.situacaoOS = 2;
            vo.descricaoSituacaoOS = "Encerrada";
            //Salvar na base de dados
            listener.onSalvar(vo);
        }
    }
}
