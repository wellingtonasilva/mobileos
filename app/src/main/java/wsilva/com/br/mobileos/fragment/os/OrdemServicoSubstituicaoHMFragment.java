package wsilva.com.br.mobileos.fragment.os;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.activity.OrdemServicoLista;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.os.HidrometroLocalArmazenagemDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroLocalInstalacaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroProtecaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroSituacaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroTipoSubstituicaoDAO;
import wsilva.com.br.mobileos.dao.os.MotivoEncerramentoDAO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoHidrometroSubstituicaoVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.interfaces.IOrdemServicoSubstituicaoHM;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoSubstituicaoHMFragment extends Fragment
{
    IOrdemServicoSubstituicaoHM listener;
    OrdemServicoVO ordemServico;

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

    public void setListener(IOrdemServicoSubstituicaoHM listener)
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
        //Tipo Medição Atual
        Spinner spiOSSubstituicaoHMTipoMedicaoAtual=(Spinner) root.findViewById(R.id.spiOSSubstituicaoHMTipoMedicaoAtual);
        spiOSSubstituicaoHMTipoMedicaoAtual.setAdapter(Util.criarArrayAdapter(root.getContext(),
                R.array.arrTipoMedicao, android.R.layout.simple_spinner_item));
        //Situação do Hidrômetro
        Spinner spiOSSubstituicaoHMSituacaoHM=(Spinner) root.findViewById(R.id.spiOSSubstituicaoHMSituacaoHM);
        spiOSSubstituicaoHMSituacaoHM.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroSituacaoDAO(root.getContext()) ,
                new String[]{HidrometroSituacaoDAO.COL_DESCRICAOSITUACAOHIDROMETRO},
                new int[]{android.R.id.text1}));
        //Local Armazenagem
        Spinner spiOSSubstituicaoHMLocalArmazenagem=(Spinner) root.findViewById(R.id.spiOSSubstituicaoHMLocalArmazenagem);
        spiOSSubstituicaoHMLocalArmazenagem.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroLocalArmazenagemDAO(root.getContext()) ,
                new String[]{HidrometroLocalArmazenagemDAO.COL_DESCRICAOLOCALARMAZENAGEMHIDROMETRO},
                new int[]{android.R.id.text1}));
        //Local Instalaçãoo
        Spinner spiOSSubstituicaoHMLocalInstalacao=(Spinner) root.findViewById(R.id.spiOSSubstituicaoHMLocalInstalacao);
        spiOSSubstituicaoHMLocalInstalacao.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroLocalInstalacaoDAO(root.getContext()),
                new String[]{HidrometroLocalInstalacaoDAO.COL_DESCRICAOLOCALINSTALACAOHIDROMETRO},
                new int[]{android.R.id.text1}));
        //Proteçõa do Hidrômetro
        Spinner spiOSSubstituicaoHMProtecao=(Spinner) root.findViewById(R.id.spiOSSubstituicaoHMProtecao);
        spiOSSubstituicaoHMProtecao.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroProtecaoDAO(root.getContext()),
                new String[]{HidrometroProtecaoDAO.COL_DESCRICAOPROTECAOHIDROMETRO},
                new int[]{android.R.id.text1}));
        //Tipo substituição
        Spinner spiOSSubstituicaoHMTipoSubstituicao=(Spinner) root.findViewById(R.id.spiOSSubstituicaoHMTipoSubstituicao);
        spiOSSubstituicaoHMTipoSubstituicao.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroTipoSubstituicaoDAO(root.getContext()),
                new String[]{HidrometroTipoSubstituicaoDAO.COL_DESCRICAOTIPOSUBSTITUICAOHM},
                new int[]{android.R.id.text1}));

        doPovoaTela(root, ordemServico);
    }

    protected void doPovoaTela(View view, OrdemServicoVO vo)
    {
        if (vo!=null)
        {
            //Numero da OS
            if (vo!=null)
            {
                EditText txtOsSubstituicaoHMNumeroOs=(EditText) view.findViewById(R.id.txtOsSubstituicaoHMNumeroOs);
                txtOsSubstituicaoHMNumeroOs.setText(String.valueOf(ordemServico.numeroOS));
            }
            //Data Retirada
            EditText txtOsSubstituicaoHMDataRetirada=(EditText) view.findViewById(R.id.txtOsSubstituicaoHMDataRetirada);
            txtOsSubstituicaoHMDataRetirada.setText(Util.dateToString("dd/MM/yyyy", new Date()));
            //Data
            EditText txtOsSubstituicaoHMDataInstalacaoNovoHM=(EditText) view.findViewById(R.id.txtOsSubstituicaoHMDataInstalacaoNovoHM);
            txtOsSubstituicaoHMDataInstalacaoNovoHM.setText(Util.dateToString("dd/MM/yyyy", new Date()));
            //Hora
            EditText txtOsSubstituicaoHMHoraInstalacaoNovoHM=(EditText) view.findViewById(R.id.txtOsSubstituicaoHMHoraInstalacaoNovoHM);
            txtOsSubstituicaoHMHoraInstalacaoNovoHM.setText(Util.dateToString("HH:mm", new Date()));
        }
    }

    public void salvar()
    {
        if (listener!=null)
        {
            View view = getView();
            OrdemServicoHidrometroSubstituicaoVO ordemServicoHidrometroSubstituicao = new OrdemServicoHidrometroSubstituicaoVO();

            //Carrega objetos
            CheckBox chkOsSubstituicaoHMTrocaRegistro=(CheckBox) view.findViewById(R.id.chkOsSubstituicaoHMTrocaRegistro);
            CheckBox chkOsSubstituicaoHMTrocaProtecao=(CheckBox) view.findViewById(R.id.chkOsSubstituicaoHMTrocaProtecao);
            CheckBox chkOsSubstituicaoHMComCavalete=(CheckBox) view.findViewById(R.id.chkOsSubstituicaoHMComCavalete);
            EditText txtOsSubstituicaoHMNumeroHM=(EditText) view.findViewById(R.id.txtOsSubstituicaoHMNumeroHM);
            EditText txtOsSubstituicaoHMDataRetirada=(EditText) view.findViewById(R.id.txtOsSubstituicaoHMDataRetirada);
            EditText txtOsSubstituicaoHMLeituraRetirada=(EditText) view.findViewById(R.id.txtOsSubstituicaoHMLeituraRetirada);
            EditText txtOsSubstituicaoHMNovoHidrometro=(EditText) view.findViewById(R.id.txtOsSubstituicaoHMNovoHidrometro);
            EditText txtOsSubstituicaoHMDataInstalacaoNovoHM=(EditText) view.findViewById(R.id.txtOsSubstituicaoHMDataInstalacaoNovoHM);
            EditText txtOsSubstituicaoHMHoraInstalacaoNovoHM=(EditText) view.findViewById(R.id.txtOsSubstituicaoHMHoraInstalacaoNovoHM);
            EditText txtOsSubstituicaoHMLeituraInstalacao=(EditText) view.findViewById(R.id.txtOsSubstituicaoHMLeituraInstalacao);
            EditText txtOsSubstituicaoHMNumeroLacre=(EditText) view.findViewById(R.id.txtOsSubstituicaoHMNumeroLacre);
            EditText txtOsEncerramentoMotivoParecer=(EditText) view.findViewById(R.id.txtOsEncerramentoMotivoParecer);
            //
            Spinner spiOsEncerramentoMotivoEncerramento = (Spinner) view.findViewById(R.id.spiOsEncerramentoMotivoEncerramento);
            Spinner spiOSSubstituicaoHMTipoMedicaoAtual=(Spinner) view.findViewById(R.id.spiOSSubstituicaoHMTipoMedicaoAtual);
            Spinner spiOSSubstituicaoHMSituacaoHM=(Spinner) view.findViewById(R.id.spiOSSubstituicaoHMSituacaoHM);
            Spinner spiOSSubstituicaoHMLocalArmazenagem=(Spinner) view.findViewById(R.id.spiOSSubstituicaoHMLocalArmazenagem);
            Spinner spiOSSubstituicaoHMLocalInstalacao=(Spinner) view.findViewById(R.id.spiOSSubstituicaoHMLocalInstalacao);
            Spinner spiOSSubstituicaoHMProtecao=(Spinner) view.findViewById(R.id.spiOSSubstituicaoHMProtecao);
            Spinner spiOSSubstituicaoHMTipoSubstituicao=(Spinner) view.findViewById(R.id.spiOSSubstituicaoHMTipoSubstituicao);

            //Numero da OS
            ordemServicoHidrometroSubstituicao.idOrdemServico = ordemServico.numeroOS;
            //Numero do Hidrumetro Atual
            ordemServicoHidrometroSubstituicao.numerHidrometroAtual = txtOsSubstituicaoHMNumeroHM.getText().toString();
            //Data da Retirada
            ordemServicoHidrometroSubstituicao.dataRetirada = Util.stringToDate("dd/MM/yyyy", txtOsSubstituicaoHMDataRetirada.getText().toString());
            //Leitura na Retirada
            if (txtOsSubstituicaoHMLeituraRetirada.getText().toString().length() > 0) {
                ordemServicoHidrometroSubstituicao.leituraRetirada = Integer.parseInt(txtOsSubstituicaoHMLeituraRetirada.getText().toString());
            }
            //Numero do Novo Hidrometro
            ordemServicoHidrometroSubstituicao.numeroHidrometroNovo = txtOsSubstituicaoHMNovoHidrometro.getText().toString();
            //Data Instalacao Novo Hidrometro
            ordemServicoHidrometroSubstituicao.dataInstalacaoHidrometroNovo = Util.stringToDate("dd/MM/yyyy",
                    txtOsSubstituicaoHMDataInstalacaoNovoHM.getText().toString());
            //Hora Instalacao Novo Hidrometro
            ordemServicoHidrometroSubstituicao.horaInstalacaoHidrometroNovo = txtOsSubstituicaoHMHoraInstalacaoNovoHM.getText().toString();
            //Leitura na Instalacao
            if (txtOsSubstituicaoHMLeituraInstalacao.getText().toString().length() > 0) {
                ordemServicoHidrometroSubstituicao.leituraInstalacao = Integer.parseInt(txtOsSubstituicaoHMLeituraInstalacao.getText().toString());
            }
            //N�mero do Lacre
            ordemServicoHidrometroSubstituicao.numeroSelo = txtOsSubstituicaoHMNumeroLacre.getText().toString();
            //Local Armazenagem
            ordemServicoHidrometroSubstituicao.idLocalArmazenagemHidrometro = Util.getItemId(spiOSSubstituicaoHMLocalArmazenagem,
                    HidrometroLocalArmazenagemDAO.COL_IDLOCALARMAZENAGEMHIDROMETRO);
            //Local Instalacao
            ordemServicoHidrometroSubstituicao.idLocalInstalacaoHidrometro = Util.getItemId(spiOSSubstituicaoHMLocalInstalacao,
                    HidrometroLocalInstalacaoDAO.COL_IDLOCALINSTALACAOHIDROMETRO);
            //Protecao
            ordemServicoHidrometroSubstituicao.idProtecaoHidrometro = Util.getItemId(spiOSSubstituicaoHMProtecao,
                    HidrometroProtecaoDAO.COL_IDPROTECAOHIDROMETRO);
            //Situacao Hidrometro
            ordemServicoHidrometroSubstituicao.idSituacaoHidrometro = Util.getItemId(spiOSSubstituicaoHMSituacaoHM,
                    HidrometroSituacaoDAO.COL_IDSITUACAOHIDROMETRO);
            //Tipo Substituicao
            ordemServicoHidrometroSubstituicao.idTipoSubstituicaoHM = Util.getItemId(spiOSSubstituicaoHMTipoSubstituicao,
                    HidrometroTipoSubstituicaoDAO.COL_IDTIPOSUBSTITUICAOHM);
            //Troca registro
            ordemServicoHidrometroSubstituicao.indicadorTrocaRegistro = (chkOsSubstituicaoHMTrocaRegistro.isChecked() ? 1:2);
            //Troca protecao
            ordemServicoHidrometroSubstituicao.indicadorTrocaProtecao = (chkOsSubstituicaoHMTrocaProtecao.isChecked() ? 1:2);
            //Com ou sem Cavalete
            ordemServicoHidrometroSubstituicao.indicadorCavalete = (chkOsSubstituicaoHMComCavalete.isChecked() ? "1" : "2");
            //Equipe Execucao
            ordemServicoHidrometroSubstituicao.idEquipeExecucao = ordemServico.idEquipeExecucao;
            ordemServicoHidrometroSubstituicao.descricaoEquipeExecucao = ordemServico.descricaoEquipeExecucao;
            ordemServicoHidrometroSubstituicao.indicadorTipoMedicao = "1";

            //Dados do Enceerramento da Ordem de Serviço
            ordemServico.horaEncerramentoOS = ordemServicoHidrometroSubstituicao.horaInstalacaoHidrometroNovo;
            ordemServico.dataEncerramentoOS = ordemServicoHidrometroSubstituicao.dataInstalacaoHidrometroNovo;
            ordemServico.idMotivoEncerramento = Util.getItemId(spiOsEncerramentoMotivoEncerramento,
                    MotivoEncerramentoDAO.COL_IDMOTIVOENCERRAMENTO);
            ordemServico.descricaoMotivoEncerramento = Util.getItemDescricao(spiOsEncerramentoMotivoEncerramento,
                    MotivoEncerramentoDAO.COL_DESCRICAOMOTIVOENCERRAMENTO);
            ordemServico.parecerEncerramento = txtOsEncerramentoMotivoParecer.getText().toString();
            ordemServico.situacaoOS = Util.OS_ID_STATUS_ENCERRADA;
            ordemServico.descricaoSituacaoOS = Util.OS_STATUS_ENCERRADA;
            ordemServico.syncDirty = 1;
            ordemServico.syncCansync = 1;

            listener.onSalvar(ordemServico, ordemServicoHidrometroSubstituicao);
        }

    }
}
