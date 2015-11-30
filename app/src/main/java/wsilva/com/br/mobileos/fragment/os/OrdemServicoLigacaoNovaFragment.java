package wsilva.com.br.mobileos.fragment.os;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.activity.OrdemServicoLista;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.os.MotivoEncerramentoDAO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoLigacaoNovaVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;
import wsilva.com.br.mobileos.interfaces.IOrdemServicoLigacaoNova;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoLigacaoNovaFragment extends Fragment
{
    IOrdemServicoLigacaoNova listener;
    OrdemServicoVO ordemServico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico_ligacao_nova, container, false);
        //Argumentos
        Bundle bundle = getArguments();
        //Configuracao inicial
        init(root, bundle);

        return root;
    }

    public void setListener(IOrdemServicoLigacaoNova listener)
    {
        this.listener = listener;
    }

    protected void init(View root, Bundle arguments)
    {
        if (arguments!=null)
        {
            ordemServico = (OrdemServicoVO) arguments.getSerializable(OrdemServicoLista.TEMPLATE_SELECTED_ITEM);
        }

        //Motivo do Encerramento
        Spinner spiOsEncerramentoMotivoEncerramento = (Spinner) root.findViewById(R.id.spiOsEncerramentoMotivoEncerramento);
        spiOsEncerramentoMotivoEncerramento.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new MotivoEncerramentoDAO(root.getContext()),
                new String[]{MotivoEncerramentoDAO.COL_DESCRICAOMOTIVOENCERRAMENTO},
                new int[]{android.R.id.text1}));

        doPovoaTela(root, ordemServico);
    }

    protected void doPovoaTela(View view, OrdemServicoVO vo)
    {
        if (vo!=null)
        {
        }
    }

    public void salvar()
    {
        if (listener!=null)
        {
            View view = getView();
            OrdemServicoLigacaoNovaVO ordemServicoLigacaoNova = new OrdemServicoLigacaoNovaVO();

            //Campos
            EditText txtOsLigacaoNovaNumeroLacre=(EditText) view.findViewById(R.id.txtOsLigacaoNovaNumeroLacre);
            EditText txtOsLigacaoNovaNumeroHidrometro=(EditText) view.findViewById(R.id.txtOsLigacaoNovaNumeroHidrometro);
            EditText txtOsLigacaoNovaLeitura=(EditText) view.findViewById(R.id.txtOsLigacaoNovaLeitura);
            EditText txtOsEncerramentoMotivoParecer=(EditText) view.findViewById(R.id.txtOsEncerramentoMotivoParecer);
            Spinner spiOsEncerramentoMotivoEncerramento = (Spinner) view.findViewById(R.id.spiOsEncerramentoMotivoEncerramento);

            //Número da OS
            ordemServicoLigacaoNova.idOrdemServico = ordemServico.numeroOS;
            //Numero do Lacre
            if (txtOsLigacaoNovaNumeroLacre.getText().toString().length() > 0) {
                ordemServicoLigacaoNova.numeroSelo = txtOsLigacaoNovaNumeroLacre.getText().toString();
            }
            //Numero do Hidrometro
            ordemServicoLigacaoNova.numeroHidrometro = txtOsLigacaoNovaNumeroHidrometro.getText().toString();
            //Leitura
            if (txtOsLigacaoNovaLeitura.getText().toString().length() > 0) {
                ordemServicoLigacaoNova.leituraInstalacao = Integer.parseInt(txtOsLigacaoNovaLeitura.getText().toString());
            }
            //Equipe Execução
            ordemServicoLigacaoNova.idEquipeExecucao = ordemServico.idEquipeExecucao;
            ordemServicoLigacaoNova.descricaoEquipeExecucao = ordemServico.descricaoEquipeExecucao;

            //Dados do Enceerramento da Ordem de Serviço
            ordemServico.horaEncerramentoOS = ordemServicoLigacaoNova.horaLigacao;
            ordemServico.dataEncerramentoOS = ordemServicoLigacaoNova.dataLigacaoNova;
            ordemServico.idMotivoEncerramento = Util.getItemId(spiOsEncerramentoMotivoEncerramento,
                    MotivoEncerramentoDAO.COL_IDMOTIVOENCERRAMENTO);
            ordemServico.descricaoMotivoEncerramento = Util.getItemDescricao(spiOsEncerramentoMotivoEncerramento,
                    MotivoEncerramentoDAO.COL_DESCRICAOMOTIVOENCERRAMENTO);
            ordemServico.parecerEncerramento = txtOsEncerramentoMotivoParecer.getText().toString();
            ordemServico.situacaoOS = Util.OS_ID_STATUS_ENCERRADA;
            ordemServico.descricaoSituacaoOS = Util.OS_STATUS_ENCERRADA;
            ordemServico.syncCansync = 1;
            ordemServico.syncDirty = 1;

            listener.onSalvar(ordemServico, ordemServicoLigacaoNova);
        }
    }
}
