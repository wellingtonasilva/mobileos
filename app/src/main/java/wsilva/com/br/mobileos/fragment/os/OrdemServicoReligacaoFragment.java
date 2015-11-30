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
import wsilva.com.br.mobileos.activity.OrdemServicoReligacao;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.os.HidrometroLocalInstalacaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroProtecaoDAO;
import wsilva.com.br.mobileos.dao.os.MotivoEncerramentoDAO;
import wsilva.com.br.mobileos.dao.os.ReligacaoTipoDAO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoReligacaoVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;
import wsilva.com.br.mobileos.interfaces.IOrdemServicoReligacao;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoReligacaoFragment extends Fragment
{
    IOrdemServicoReligacao listener;
    OrdemServicoVO ordemServico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico_religacao, container, false);
        //Argumentos
        Bundle bundle = getArguments();
        //Configuracao inicial
        init(root, bundle);

        return root;
    }

    public void setListener(IOrdemServicoReligacao listener)
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

        //Local instalação
        Spinner spiOSReligacaoLocalInstalacao=(Spinner) root.findViewById(R.id.spiOSReligacaoLocalInstalacao);
        spiOSReligacaoLocalInstalacao.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroLocalInstalacaoDAO(root.getContext()),
                new String[]{HidrometroLocalInstalacaoDAO.COL_DESCRICAOLOCALINSTALACAOHIDROMETRO},
                new int[]{android.R.id.text1}));

        //Proteção
        Spinner spiOSReligacaoProtecao=(Spinner) root.findViewById(R.id.spiOSReligacaoProtecao);
        spiOSReligacaoProtecao.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroProtecaoDAO(root.getContext()),
                new String[]{HidrometroProtecaoDAO.COL_DESCRICAOPROTECAOHIDROMETRO},
                new int[]{android.R.id.text1}));
        //Tipo de Religação
        Spinner spiOSReligacaoTipo=(Spinner) root.findViewById(R.id.spiOSReligacaoTipo);
        spiOSReligacaoTipo.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new ReligacaoTipoDAO(root.getContext()),
                new String[]{ReligacaoTipoDAO.COL_DESCRICAOTIPORELIGACAO},
                new int[]{android.R.id.text1}));

        doPovoaTela(root, ordemServico);
    }

    protected void doPovoaTela(View view, OrdemServicoVO vo)
    {
        if (vo!=null)
        {
            //N�mero da OS
            if (ordemServico!=null) {
                EditText txtOsReligacaoNumeroOs=(EditText) view.findViewById(R.id.txtOsReligacaoNumeroOs);
                txtOsReligacaoNumeroOs.setText(String.valueOf(ordemServico.numeroOS));
            }

            EditText txtOsReligacaoDataReligacao=(EditText) view.findViewById(R.id.txtOsReligacaoDataReligacao);
            EditText txtOsReligacaoHoraReligacao=(EditText) view.findViewById(R.id.txtOsReligacaoHoraReligacao);
            EditText txtOsReligacaoDataInstalacao=(EditText) view.findViewById(R.id.txtOsReligacaoDataInstalacao);

            //Data da Religaçao
            txtOsReligacaoDataReligacao.setText(Util.dateToString("dd/MM/yyyy", new Date()));
            //Hora da Religacaoo
            txtOsReligacaoHoraReligacao.setText(Util.dateToString("HH:mm", new Date()));
            //Data da Instalacao do HM
            txtOsReligacaoDataInstalacao.setText(Util.dateToString("dd/MM/yyyy", new Date()));
        }
    }

    public void salvar()
    {
        if (listener != null)
        {
            View view = getView();
            OrdemServicoReligacaoVO ordemServicoReligacao = new OrdemServicoReligacaoVO();

            //Carrega objetos da tela
            EditText txtOsReligacaoDataReligacao=(EditText) view.findViewById(R.id.txtOsReligacaoDataReligacao);
            EditText txtOsReligacaoHoraReligacao=(EditText) view.findViewById(R.id.txtOsReligacaoHoraReligacao);
            EditText txtOsReligacaoNumeroHM=(EditText) view.findViewById(R.id.txtOsReligacaoNumeroHM);
            EditText txtOsReligacaoDataInstalacao=(EditText) view.findViewById(R.id.txtOsReligacaoDataInstalacao);
            EditText txtOsReligacaoLeituraReligacao=(EditText) view.findViewById(R.id.txtOsReligacaoLeituraReligacao);
            EditText txtOsReligacaoNumeroLacre=(EditText) view.findViewById(R.id.txtOsReligacaoNumeroLacre);
            EditText txtOsEncerramentoMotivoParecer=(EditText) view.findViewById(R.id.txtOsEncerramentoMotivoParecer);
            CheckBox chkOsReligacaoTrocaRegistro=(CheckBox) view.findViewById(R.id.chkOsReligacaoTrocaRegistro);
            CheckBox chkOsReligacaoTrocaProtecao=(CheckBox) view.findViewById(R.id.chkOsReligacaoTrocaProtecao);
            CheckBox chkOsReligacaoCavalete=(CheckBox) view.findViewById(R.id.chkOsReligacaoCavalete);
            //
            Spinner spiOsEncerramentoMotivoEncerramento = (Spinner) view.findViewById(R.id.spiOsEncerramentoMotivoEncerramento);
            Spinner spiOSReligacaoLocalInstalacao=(Spinner) view.findViewById(R.id.spiOSReligacaoLocalInstalacao);
            Spinner spiOSReligacaoProtecao=(Spinner) view.findViewById(R.id.spiOSReligacaoProtecao);
            Spinner spiOSReligacaoTipo=(Spinner) view.findViewById(R.id.spiOSReligacaoTipo);

            //Número da OS
            ordemServicoReligacao.idOrdemServico = ordemServico.numeroOS;
            //Data da Instalaçao do Hidrometro
            if (txtOsReligacaoDataInstalacao.getText().toString().length()>0) {
                ordemServicoReligacao.dataInstalacaoHidrometro = Util.stringToDate("dd/MM/yyyy",
                        txtOsReligacaoDataInstalacao.getText().toString());
            }
            //Data da Religaçao
            if (txtOsReligacaoDataReligacao.getText().toString().length() > 0) {
                ordemServicoReligacao.dataReligacao = Util.stringToDate("dd/MM/yyyy",
                        txtOsReligacaoDataReligacao.getText().toString());
            }
            //Hora da Religacao
            ordemServicoReligacao.horaReligacao = txtOsReligacaoHoraReligacao.getText().toString();
            //Numero do HM
            ordemServicoReligacao.numeroHidrometro = txtOsReligacaoNumeroHM.getText().toString();
            //Leitura
            if (txtOsReligacaoLeituraReligacao.getText().toString().length() > 0) {
                ordemServicoReligacao.leituraInstalacao = Integer.parseInt(txtOsReligacaoLeituraReligacao.getText().toString());
            }
            //Numero do Lacre
            ordemServicoReligacao.numeroSelo = txtOsReligacaoNumeroLacre.getText().toString();
            //Troca de Registro
            ordemServicoReligacao.indicadorTrocaRegistro = (chkOsReligacaoTrocaRegistro.isChecked()==true ? 1:2);
            //Troca de Protecao
            ordemServicoReligacao.indicadorTrocaProtecao = (chkOsReligacaoTrocaProtecao.isChecked()==true ? 1:2);
            //Com ou sem Cavalete
            ordemServicoReligacao.indicadorCavalete = (chkOsReligacaoCavalete.isChecked()==true? "C" : "S");
            //Local instalação
            ordemServicoReligacao.idLocalInstalacaoHidrometro = Util.getItemId(spiOSReligacaoLocalInstalacao,
                    HidrometroLocalInstalacaoDAO.COL_IDLOCALINSTALACAOHIDROMETRO);
            //Protecao
            ordemServicoReligacao.idProtecaoHidrometro = Util.getItemId(spiOSReligacaoProtecao,
                    HidrometroProtecaoDAO.COL_IDPROTECAOHIDROMETRO);
            //Tipo de Religacao
            ordemServicoReligacao.idTipoReligacao = Util.getItemId(spiOSReligacaoTipo,
                    ReligacaoTipoDAO.COL_IDTIPORELIGACAO);
            //Equipe Execução
            ordemServicoReligacao.idEquipeExecucao = ordemServico.idEquipeExecucao;
            ordemServicoReligacao.descricaoEquipeExecucao = ordemServico.descricaoEquipeExecucao;

            //Dados do Enceerramento da Ordem de Serviço
            ordemServico.horaEncerramentoOS = ordemServicoReligacao.horaReligacao;
            ordemServico.dataEncerramentoOS = ordemServicoReligacao.dataReligacao;
            ordemServico.idMotivoEncerramento = Util.getItemId(spiOsEncerramentoMotivoEncerramento,
                    MotivoEncerramentoDAO.COL_IDMOTIVOENCERRAMENTO);
            ordemServico.descricaoMotivoEncerramento = Util.getItemDescricao(spiOsEncerramentoMotivoEncerramento,
                    MotivoEncerramentoDAO.COL_DESCRICAOMOTIVOENCERRAMENTO);
            ordemServico.parecerEncerramento = txtOsEncerramentoMotivoParecer.getText().toString();
            ordemServico.situacaoOS = Util.OS_ID_STATUS_ENCERRADA;
            ordemServico.descricaoSituacaoOS = Util.OS_STATUS_ENCERRADA;
            ordemServico.syncDirty = 1;
            ordemServico.syncCansync = 1;

            if (listener!=null)
            {
                listener.onSalvar(ordemServico, ordemServicoReligacao);
            }
        }
    }
}
