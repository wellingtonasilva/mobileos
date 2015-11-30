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
import wsilva.com.br.mobileos.dao.os.CorteTipoDAO;
import wsilva.com.br.mobileos.dao.os.MotivoCorteDAO;
import wsilva.com.br.mobileos.dao.os.MotivoEncerramentoDAO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoCorteVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;
import wsilva.com.br.mobileos.interfaces.IOrdemServicoCorte;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoCorteFragment extends Fragment
{
    IOrdemServicoCorte listener;
    OrdemServicoVO ordemServico;
    Spinner spiOSCorteMotivoCorte=null;
    Spinner spiOSCorteTipoCorte=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico_corte, container, false);
        //Argumentos
        Bundle bundle = getArguments();
        //Configuracao inicial
        init(root, bundle);

        return root;
    }

    public void setListener(IOrdemServicoCorte listener)
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

        //Motivo do Corte
        spiOSCorteMotivoCorte=(Spinner) root.findViewById(R.id.spiOSCorteMotivoCorte);
        spiOSCorteMotivoCorte.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new MotivoCorteDAO(root.getContext()),
                new String[]{MotivoCorteDAO.COL_DESCRICAOMOTIVOCORTE},
                new int[]{android.R.id.text1}));
        //Tipo de Corte/Supressão
        spiOSCorteTipoCorte=(Spinner) root.findViewById(R.id.spiOSCorteTipoCorte);
        spiOSCorteTipoCorte.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new CorteTipoDAO(root.getContext()),
                new String[]{CorteTipoDAO.COL_DESCRICAOCORTETIPO},
                new int[]{android.R.id.text1}));

        doPovoaTela(root, ordemServico);
    }

    protected void doPovoaTela(View view, OrdemServicoVO vo)
    {
        if (vo!=null)
        {
            EditText txtOsCorteNumeroOs=(EditText) view.findViewById(R.id.txtOsCorteNumeroOs);
            EditText txtOsCorteData=(EditText) view.findViewById(R.id.txtOsCorteData);
            EditText txtOsCorteHora=(EditText) view.findViewById(R.id.txtOsCorteHora);

            //N�mero da OS
            txtOsCorteNumeroOs.setText(String.valueOf(ordemServico.numeroOS));
            //Data do Corte
            txtOsCorteData.setText(Util.dateToString("dd/MM/yyyy", new Date()));
            //Hora do Corte
            txtOsCorteHora.setText(Util.dateToString("HH:mm", new Date()));
        }
    }

    public void salvar()
    {
        if (listener!=null)
        {
            View view = getView();
            OrdemServicoCorteVO ordemServicoCorte = new OrdemServicoCorteVO();

            //Carrega objetos da tela
            EditText txtOsCorteData=(EditText) view.findViewById(R.id.txtOsCorteData);
            EditText txtOsCorteHora=(EditText) view.findViewById(R.id.txtOsCorteHora);
            EditText txtOsCorteLeitura= (EditText) view.findViewById(R.id.txtOsCorteLeitura);
            EditText txtOsCorteNumeroLacre=(EditText) view.findViewById(R.id.txtOsCorteNumeroLacre);
            EditText txtOsCorteNumeroHidrometro=(EditText) view.findViewById(R.id.txtOsCorteNumeroHidrometro);
            EditText txtOsEncerramentoMotivoParecer=(EditText) view.findViewById(R.id.txtOsEncerramentoMotivoParecer);
            Spinner spiOsEncerramentoMotivoEncerramento = (Spinner) view.findViewById(R.id.spiOsEncerramentoMotivoEncerramento);
            Spinner spiOSCorteMotivoCorte=(Spinner) view.findViewById(R.id.spiOSCorteMotivoCorte);
            Spinner spiOSCorteTipoCorte=(Spinner) view.findViewById(R.id.spiOSCorteTipoCorte);

            //Número da OS
            ordemServicoCorte.idOrdemServico = ordemServico.numeroOS;
            //Data do Corte
            ordemServicoCorte.dataCorte = Util.stringToDate("dd/MM/yyyy", txtOsCorteData.getText().toString());
            //Hora do Corte
            ordemServicoCorte.horaCorte = txtOsCorteHora.getText().toString();
            //Id. Motivo do Corte
            ordemServicoCorte.idMotivoCorte = Util.getItemId(spiOSCorteMotivoCorte,
                    MotivoCorteDAO.COL_IDMOTIVOCORTE);
            //Id. Corte tipo
            ordemServicoCorte.idCorteTipo = Util.getItemId(spiOSCorteTipoCorte,
                    CorteTipoDAO.COL_IDCORTETIPO);
            //Leitura no Corte
            if (txtOsCorteLeitura.getText().toString().length() > 0) {
                ordemServicoCorte.leituraCorte = Integer.parseInt(txtOsCorteLeitura.getText().toString());
            }
            //Numero do Lacre
            ordemServicoCorte.numeroSelo = txtOsCorteNumeroLacre.getText().toString();
            //Numero do Hidrometro
            ordemServicoCorte.numeroHidrometro = txtOsCorteNumeroHidrometro.getText().toString();
            //Id. Equipe Execução
            ordemServicoCorte.idEquipeExecucao = ordemServico.idEquipeExecucao;
            ordemServicoCorte.descricaoEquipeExecucao = ordemServico.descricaoEquipeExecucao;
            ordemServicoCorte.indicadorEnvio = 2;

            //Dados do Enceerramento da Ordem de Serviço
            ordemServico.horaEncerramentoOS = ordemServicoCorte.horaCorte;
            ordemServico.dataEncerramentoOS = ordemServicoCorte.dataCorte;
            ordemServico.idMotivoEncerramento = Util.getItemId(spiOsEncerramentoMotivoEncerramento,
                    MotivoEncerramentoDAO.COL_IDMOTIVOENCERRAMENTO);
            ordemServico.descricaoMotivoEncerramento = Util.getItemDescricao(spiOsEncerramentoMotivoEncerramento,
                    MotivoEncerramentoDAO.COL_DESCRICAOMOTIVOENCERRAMENTO);
            ordemServico.parecerEncerramento = txtOsEncerramentoMotivoParecer.getText().toString();
            ordemServico.situacaoOS = Util.OS_ID_STATUS_ENCERRADA;
            ordemServico.descricaoSituacaoOS = Util.OS_STATUS_ENCERRADA;
            ordemServico.syncCansync = 1;
            ordemServico.syncDirty = 1;

            listener.onSalvar(ordemServico, ordemServicoCorte);
        }
    }
}
