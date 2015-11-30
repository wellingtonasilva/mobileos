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
import wsilva.com.br.mobileos.dao.os.HidrometroLocalInstalacaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroProtecaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroTipoInstalacaoDAO;
import wsilva.com.br.mobileos.dao.os.MotivoEncerramentoDAO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoHidrometroInstalacaoVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.interfaces.IOrdemServicoInstalacaoHM;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class  OrdemServicoInstalacaoHMFragment extends Fragment
{
    IOrdemServicoInstalacaoHM listener;
    OrdemServicoVO ordemServico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico_instalacao_hm, container, false);
        //Argumentos
        Bundle bundle = getArguments();
        //Configuracao inicial
        init(root, bundle);

        return root;
    }

    public void setListener(IOrdemServicoInstalacaoHM listener)
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

        //Loca da Instalação
        Spinner spiOSInstalacaoHMLocalInstalacao=(Spinner) root.findViewById(R.id.spiOSInstalacaoHMLocalInstalacao);
        spiOSInstalacaoHMLocalInstalacao.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroLocalInstalacaoDAO(root.getContext()),
                new String[]{HidrometroLocalInstalacaoDAO.COL_DESCRICAOLOCALINSTALACAOHIDROMETRO},
                new int[]{android.R.id.text1}));
        //Proteção do Hidrômetro
        Spinner spiOSInstalacaoHMProtecao=(Spinner) root.findViewById(R.id.spiOSInstalacaoHMProtecao);
        spiOSInstalacaoHMProtecao.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroProtecaoDAO(root.getContext()),
                new String[]{HidrometroProtecaoDAO.COL_DESCRICAOPROTECAOHIDROMETRO},
                new int[]{android.R.id.text1}));
        //Tipo Instalação
        Spinner spiOSInstalacaoHMTipoInstalacao=(Spinner) root.findViewById(R.id.spiOSInstalacaoHMTipoInstalacao);
        spiOSInstalacaoHMTipoInstalacao.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new HidrometroTipoInstalacaoDAO(root.getContext()),
                new String[]{HidrometroTipoInstalacaoDAO.COL_DESCRICAOTIPOINSTALACAOHM, HidrometroTipoInstalacaoDAO.COL_IDTIPOINSTALACAOHM},
                new int[]{R.id.lblSpinnerItem1,R.id.lblSpinnerItem2},
                getActivity(),
                R.layout.lay_template_spinner_item,
                R.layout.lay_template_spinner_dropdown_item));

        doPovoaTela(root, ordemServico);
    }

    protected void doPovoaTela(View view, OrdemServicoVO vo)
    {
        if (vo!=null)
        {
            //Numero da OS
            if (ordemServico!=null) {
                EditText txtOsInstalacaoHMNumeroOs=(EditText) view.findViewById(R.id.txtOsInstalacaoHMNumeroOs);
                txtOsInstalacaoHMNumeroOs.setText(String.valueOf(ordemServico.numeroOS));
            }

            //Data Instalacao
            EditText txtOsInstalacaoHMDataInstalacao=(EditText) view.findViewById(R.id.txtOsInstalacaoHMDataInstalacao);
            txtOsInstalacaoHMDataInstalacao.setText(Util.dateToString("dd/MM/yyyy", new Date()));

            //Hora Instalacao
            EditText txtOsInstalacaoHMHoraInstalacao=(EditText) view.findViewById(R.id.txtOsInstalacaoHMHoraInstalacao);
            txtOsInstalacaoHMHoraInstalacao.setText(Util.dateToString("HH:mm", new Date()));
        }
    }

    public void salvar()
    {
        if (listener != null)
        {
            View view = getView();
            //
            OrdemServicoHidrometroInstalacaoVO ordemServicoHidrometroInstalacao = new OrdemServicoHidrometroInstalacaoVO();
            //Carrega objetos da tela
            EditText txtOsInstalacaoHMNumeroHM=(EditText) view.findViewById(R.id.txtOsInstalacaoHMNumeroHM);
            EditText txtOsInstalacaoHMDataInstalacao=(EditText) view.findViewById(R.id.txtOsInstalacaoHMDataInstalacao);
            EditText txtOsInstalacaoHMHoraInstalacao=(EditText) view.findViewById(R.id.txtOsInstalacaoHMHoraInstalacao);
            EditText txtOsInstalacaoHMLeituraInstalacao=(EditText) view.findViewById(R.id.txtOsInstalacaoHMLeituraInstalacao);
            EditText txtOsEncerramentoMotivoParecer=(EditText) view.findViewById(R.id.txtOsEncerramentoMotivoParecer);
            EditText txtOsInstalacaoHMNumeroLacre=(EditText) view.findViewById(R.id.txtOsInstalacaoHMNumeroLacre);
            CheckBox chkOsInstalacaoHMTrocaRegistro=(CheckBox) view.findViewById(R.id.chkOsInstalacaoHMTrocaRegistro);
            CheckBox chkOsInstalacaoHMTrocaProtecao=(CheckBox) view.findViewById(R.id.chkOsInstalacaoHMTrocaProtecao);
            CheckBox chkOsInstalacaoHMCavalete=(CheckBox) view.findViewById(R.id.chkOsInstalacaoHMCavalete);
            //
            Spinner spiOsEncerramentoMotivoEncerramento = (Spinner) view.findViewById(R.id.spiOsEncerramentoMotivoEncerramento);
            Spinner spiOSInstalacaoHMLocalInstalacao=(Spinner) view.findViewById(R.id.spiOSInstalacaoHMLocalInstalacao);
            Spinner spiOSInstalacaoHMProtecao=(Spinner) view.findViewById(R.id.spiOSInstalacaoHMProtecao);
            Spinner spiOSInstalacaoHMTipoInstalacao=(Spinner) view.findViewById(R.id.spiOSInstalacaoHMTipoInstalacao);

            //Data da Instalacao
            ordemServicoHidrometroInstalacao.dataInstalacaoHidrometro = Util.stringToDate("dd/MM/yyyy",
                    txtOsInstalacaoHMDataInstalacao.getText().toString());
            //Hora da Instalacao
            ordemServicoHidrometroInstalacao.horaInstalacaoHidrometro = txtOsInstalacaoHMHoraInstalacao.getText().toString();
            //Local da Instalacao
            ordemServicoHidrometroInstalacao.idLocalInstalacaoHidrometro = Util.getItemId(spiOSInstalacaoHMLocalInstalacao,
                    HidrometroLocalInstalacaoDAO.COL_IDLOCALINSTALACAOHIDROMETRO);
            //Numero da OS
            ordemServicoHidrometroInstalacao.idOrdemServico = ordemServico.numeroOS;
            //Tipo Protecao Hidrometro
            ordemServicoHidrometroInstalacao.idProtecaoHidrometro = Util.getItemId(spiOSInstalacaoHMProtecao,
                    HidrometroProtecaoDAO.COL_IDPROTECAOHIDROMETRO);
            //Tipo Instalacao
            if (spiOSInstalacaoHMTipoInstalacao.getAdapter()!=null
                    && spiOSInstalacaoHMTipoInstalacao.getAdapter().getCount() > 0)
            {
                ordemServicoHidrometroInstalacao.idTipoInstalacaoHM = Util.getItemId(spiOSInstalacaoHMTipoInstalacao,
                        HidrometroTipoInstalacaoDAO.COL_IDTIPOINSTALACAOHM);
            }

            //Leitura Instalacao
            if (txtOsInstalacaoHMLeituraInstalacao.getText().toString().length()>0) {
                ordemServicoHidrometroInstalacao.leituraInstalacao = Integer.parseInt(txtOsInstalacaoHMLeituraInstalacao.getText().toString());
            }
            //Numero do Hidrometro
            ordemServicoHidrometroInstalacao.numeroHidrometro = txtOsInstalacaoHMNumeroHM.getText().toString();
            //Numero do Lacre
            ordemServicoHidrometroInstalacao.numeroSelo = txtOsInstalacaoHMNumeroLacre.getText().toString();
            //Troca de Registro
            ordemServicoHidrometroInstalacao.indicadorTrocaRegistro = (chkOsInstalacaoHMTrocaRegistro.isChecked() ? 1:2);
            //Troca de Protecao
            ordemServicoHidrometroInstalacao.indicadorTrocaProtecao = (chkOsInstalacaoHMTrocaProtecao.isChecked() ? 1:2);
            //Com ou sem Cavalete
            ordemServicoHidrometroInstalacao.indcadorCavalete = (chkOsInstalacaoHMCavalete.isChecked() ? "C" : "S");
            //Equipe Execucao
            ordemServicoHidrometroInstalacao.idEquipeExecucao = ordemServico.idEquipeExecucao;
            ordemServicoHidrometroInstalacao.descricaoEquipeExecucao = ordemServico.descricaoEquipeExecucao;
            ordemServicoHidrometroInstalacao.idTipoMedicao = "1";

            //Dados do Enceerramento da Ordem de Serviço
            ordemServico.horaEncerramentoOS = ordemServicoHidrometroInstalacao.horaInstalacaoHidrometro;
            ordemServico.dataEncerramentoOS = ordemServicoHidrometroInstalacao.dataInstalacaoHidrometro;
            ordemServico.idMotivoEncerramento = Util.getItemId(spiOsEncerramentoMotivoEncerramento,
                    MotivoEncerramentoDAO.COL_IDMOTIVOENCERRAMENTO);
            ordemServico.descricaoMotivoEncerramento = Util.getItemDescricao(spiOsEncerramentoMotivoEncerramento,
                    MotivoEncerramentoDAO.COL_DESCRICAOMOTIVOENCERRAMENTO);
            ordemServico.parecerEncerramento = txtOsEncerramentoMotivoParecer.getText().toString();
            ordemServico.situacaoOS = Util.OS_ID_STATUS_ENCERRADA;
            ordemServico.descricaoSituacaoOS = Util.OS_STATUS_ENCERRADA;
            ordemServico.syncCansync = 1;
            ordemServico.syncDirty = 1;

            listener.onSalvar(ordemServico, ordemServicoHidrometroInstalacao);
        }
    }

}
