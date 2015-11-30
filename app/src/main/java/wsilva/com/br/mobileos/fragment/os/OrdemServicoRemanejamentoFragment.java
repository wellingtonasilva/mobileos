package wsilva.com.br.mobileos.fragment.os;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.activity.OrdemServicoLista;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.os.HidrometroLocalInstalacaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroProtecaoDAO;
import wsilva.com.br.mobileos.dao.os.MotivoEncerramentoDAO;
import wsilva.com.br.mobileos.dao.os.ReligacaoTipoDAO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoRemanejamentoVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;
import wsilva.com.br.mobileos.interfaces.IOrdemServicoRemanejamento;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoRemanejamentoFragment extends Fragment
{
    IOrdemServicoRemanejamento listener;
    OrdemServicoVO ordemServico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico_remanejamento, container, false);
        //Argumentos
        Bundle bundle = getArguments();
        //Configuracao inicial
        init(root, bundle);

        return root;
    }

    public void setListener(IOrdemServicoRemanejamento listener)
    {
        this.listener = listener;
    }

    protected void init(View root, Bundle arguments)
    {
        if (arguments!=null)
        {
            ordemServico = (OrdemServicoVO) arguments.getSerializable(OrdemServicoLista.TEMPLATE_SELECTED_ITEM);
        }

        doPovoaTela(root, ordemServico);
    }

    protected void doPovoaTela(View view, OrdemServicoVO vo)
    {
        if (vo!=null)
        {
            EditText txtOsRemanejamentoNumeroOs=(EditText) view.findViewById(R.id.txtOsRemanejamentoNumeroOs);
            EditText txtOsRemanejamentoData=(EditText) view.findViewById(R.id.txtOsRemanejamentoData);
            EditText txtOsRemanejamentoHora=(EditText) view.findViewById(R.id.txtOsRemanejamentoHora);
            //
            txtOsRemanejamentoNumeroOs.setText(vo.numeroOS);
            txtOsRemanejamentoData.setText(Util.dateToString("dd/MM/yyyy", new Date()));
            txtOsRemanejamentoHora.setText(Util.dateToString("HH:mm", new Date()));
        }
    }

    public void salvar()
    {
        if (listener != null)
        {
            View view = getView();
            OrdemServicoRemanejamentoVO ordemServicoRemanejamento = new OrdemServicoRemanejamentoVO();

            //Carrega objetos da tela
            EditText txtOsRemanejamentoData=(EditText) view.findViewById(R.id.txtOsRemanejamentoData);
            EditText txtOsRemanejamentoHora=(EditText) view.findViewById(R.id.txtOsRemanejamentoHora);
            EditText txtOsRemanejamentoNumeroHidrometro=(EditText) view.findViewById(R.id.txtOsRemanejamentoNumeroHidrometro);
            EditText txtOsRemanejamentoNumeroLacre=(EditText) view.findViewById(R.id.txtOsRemanejamentoNumeroLacre);
            EditText txtOsRemanejamentoLeitura=(EditText) view.findViewById(R.id.txtOsRemanejamentoLeitura);
            EditText txtOsRemanejamentoObservacao=(EditText) view.findViewById(R.id.txtOsRemanejamentoObservacao);
            EditText txtOsEncerramentoMotivoParecer=(EditText) view.findViewById(R.id.txtOsEncerramentoMotivoParecer);
            Spinner spiOsEncerramentoMotivoEncerramento=(Spinner) view.findViewById(R.id.spiOsEncerramentoMotivoEncerramento);

            //Número da OS
            ordemServicoRemanejamento.idOrdemServico = ordemServico.numeroOS;
            //Data
            if (txtOsRemanejamentoData.getText().toString().length()>0) {
                ordemServicoRemanejamento.dataRemanejamento= Util.stringToDate("dd/MM/yyyy",
                        txtOsRemanejamentoData.getText().toString());
            }
            //Hora
            ordemServicoRemanejamento.horaRemanejamento = txtOsRemanejamentoHora.getText().toString();
            //Numero do HM
            ordemServicoRemanejamento.numeroHidrometro = txtOsRemanejamentoNumeroHidrometro.getText().toString();
            //Leitura
            if (txtOsRemanejamentoLeitura.getText().toString().length() > 0) {
                ordemServicoRemanejamento.leituraInstalacao = Integer.parseInt(txtOsRemanejamentoLeitura.getText().toString());
            }
            //Numero do Lacre
            ordemServicoRemanejamento.numeroSelo = txtOsRemanejamentoNumeroLacre.getText().toString();
            //Equipe Execução
            ordemServicoRemanejamento.idEquipeExecucao = ordemServico.idEquipeExecucao;
            ordemServicoRemanejamento.descricaoEquipeExecucao = ordemServico.descricaoEquipeExecucao;

            //Dados do Enceerramento da Ordem de Serviço
            ordemServico.horaEncerramentoOS = ordemServicoRemanejamento.horaRemanejamento;
            ordemServico.dataEncerramentoOS = ordemServicoRemanejamento.dataRemanejamento;
            ordemServico.idMotivoEncerramento = Util.getItemId(spiOsEncerramentoMotivoEncerramento,
                    MotivoEncerramentoDAO.COL_IDMOTIVOENCERRAMENTO);
            ordemServico.descricaoMotivoEncerramento = Util.getItemDescricao(spiOsEncerramentoMotivoEncerramento,
                    MotivoEncerramentoDAO.COL_DESCRICAOMOTIVOENCERRAMENTO);
            ordemServico.parecerEncerramento = txtOsEncerramentoMotivoParecer.getText().toString();
            ordemServico.situacaoOS = Util.OS_ID_STATUS_ENCERRADA;
            ordemServico.descricaoSituacaoOS = Util.OS_STATUS_ENCERRADA;
            ordemServico.syncDirty = 1;
            ordemServico.syncCansync = 1;

            listener.onSalvar(ordemServico, ordemServicoRemanejamento);
        }
    }
}
