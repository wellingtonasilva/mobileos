package wsilva.com.br.mobileos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.activity.OcorrenciaLista;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.ocorrencia.InterrupcaoMotivoDAO;
import wsilva.com.br.mobileos.entity.ocorrencia.InterrupcaoMotivoVO;
import wsilva.com.br.mobileos.entity.ocorrencia.InterrupcaoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;
import wsilva.com.br.mobileos.interfaces.IOcorrenciaInicio;

public class OcorrenciaInicioFragment extends Fragment
{
    IOcorrenciaInicio listener;
    InterrupcaoVO interrupcao=null;
    Spinner spiInterrupcaoMotivo=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ocorrencia_inicio, container, false);
        //Argumentos
        Bundle bundle = getArguments();
        //Configuracao inicial
        init(root, bundle);

        return root;
    }

    public void setListener(IOcorrenciaInicio listener)
    {
        this.listener = listener;
    }

    protected void init(View root, Bundle arguments)
    {
        if (arguments!=null)
        {
            interrupcao = (InterrupcaoVO) arguments.getSerializable(OcorrenciaLista.TEMPLATE_SELECTED_ITEM);
        }

        //Motivo
        spiInterrupcaoMotivo=(Spinner) root.findViewById(R.id.spiInterrupcaoMotivo);
        spiInterrupcaoMotivo.setAdapter(Util.criarSimpleCursorAdapter(getActivity(),
                new InterrupcaoMotivoDAO(getActivity()),
                new String[]{InterrupcaoMotivoDAO.COL_DESCRICAO},
                new int[]{android.R.id.text1}));

        doPovoaTela(root, null);
    }

    protected void doPovoaTela(View view, ValaVO vo)
    {
        if (vo!=null)
        {

        }
        else
        {
            EditText txtInterrupcaoDataInicio= (EditText) view.findViewById(R.id.txtInterrupcaoDataInicio);
            EditText txtInterrupcaoHoraInicio= (EditText) view.findViewById(R.id.txtInterrupcaoHoraInicio);
            txtInterrupcaoDataInicio.setText(Util.dateToString("dd/MM/yyyy", new Date()));
            txtInterrupcaoHoraInicio.setText(Util.dateToString("HH:mm", new Date()));
        }
    }

    public void salvar()
    {
        if (listener != null)
        {
            View view = getView();
            //
            String dataMovimento=Util.dateToString("yyyyMMdd", new Date());
            EditText txtInterrupcaoMatricula= (EditText) view.findViewById(R.id.txtInterrupcaoMatricula);
            EditText txtInterrupcaoNumeroOS= (EditText) view.findViewById(R.id.txtInterrupcaoNumeroOS);
            EditText txtInterrupcaoDataInicio= (EditText) view.findViewById(R.id.txtInterrupcaoDataInicio);
            EditText txtInterrupcaoHoraInicio= (EditText) view.findViewById(R.id.txtInterrupcaoHoraInicio);
            EditText txtInterrupcaoObservacaoInicio= (EditText) view.findViewById(R.id.txtInterrupcaoObservacaoInicio);
            EditText txtInterrupcaoKmInicial= (EditText) view.findViewById(R.id.txtInterrupcaoKmInicial);

            if (interrupcao==null) {
                interrupcao=new InterrupcaoVO();
            }

            //Definir valores
            //Matricula
            if (txtInterrupcaoMatricula.getText()!=null && txtInterrupcaoMatricula.getText().length()!=0)
            {
                interrupcao.matricula= Integer.parseInt(txtInterrupcaoMatricula.getText().toString());
            }
            //Número da OS
            if (txtInterrupcaoNumeroOS.getText()!=null && txtInterrupcaoNumeroOS.getText().length()!=0)
            {
                interrupcao.numeroOS= Integer.parseInt(txtInterrupcaoNumeroOS.getText().toString());
            }
            interrupcao.dataMovimento=dataMovimento;
            interrupcao.nomeEquipe=".";
            //Data Início
            interrupcao.dataInicio=txtInterrupcaoDataInicio.getText().toString();
            //Hora Início
            interrupcao.horaInicio=txtInterrupcaoHoraInicio.getText().toString();
            //Id. Motivo da Interrupçao
            interrupcao.idInterrupcaoMotivo=Util.getItemId(spiInterrupcaoMotivo,
                    InterrupcaoMotivoDAO.COL_IDINTERRUPCAOMOTIVO);
            //Motivo da Interrupção
            interrupcao.descricaoInterrupcaoMotivo=Util.getItemDescricao(spiInterrupcaoMotivo,
                    InterrupcaoMotivoDAO.COL_DESCRICAO);
            //Observa��o
            interrupcao.observacaoInicio=txtInterrupcaoObservacaoInicio.getText().toString();

            //Informações sobre o motivo da interrupção
            InterrupcaoMotivoDAO motivoDAO=new InterrupcaoMotivoDAO(getActivity());
            InterrupcaoMotivoVO motivoVO=motivoDAO.obterPorIdInterrupcaoMotivo(interrupcao.idInterrupcaoMotivo);

            //In�cio da atividade
            interrupcao.indicadorInicioAtividade=(motivoVO.indicadorInicioAtividade==1 ?true:false);
            //Fim da atividade
            interrupcao.indicadorFimAtividade=(motivoVO.indicadorFimAtividade==1 ?true:false);
            //Checklist de Saida
            interrupcao.indicadorChecklistSaida=(motivoVO.indicadorChecklistSaida==1? true:false);
            //Checklist de Retorno
            interrupcao.indicadorChecklistRetorno=(motivoVO.indicadorChecklistRetorno==1?true:false);
            //Solicitar KM no in�cio
            interrupcao.indicadorSolicitarKMInicio=(motivoVO.indicadorSolicitarKMInicio==1?true:false);
            //Solicitar KM no fim
            interrupcao.indicadorSolicitarKMFim=(motivoVO.indicadorSolicitarKMFim==1?true:false);

            /*
            if (Util.isInterrupcaoVazio(getBaseContext()) && !interrupcao.indicadorInicioAtividade) {
                Toast.makeText(getBaseContext(), "� necess�rio informar o in�cio das atividades.",
                        Toast.LENGTH_SHORT).show();
                return false;
            }

            if (!Util.isInterrupcaoVazio(getBaseContext()) && interrupcao.indicadorInicioAtividade) {
                Toast.makeText(getBaseContext(), "Voc� j� iniciou as atividades.",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
            */

            //Km Inicial
            if (interrupcao.indicadorSolicitarKMInicio)
            {
                if (txtInterrupcaoKmInicial.getText() !=null && txtInterrupcaoKmInicial.getText().toString().length()>0)
                {
                    interrupcao.kmInicial= Integer.parseInt(txtInterrupcaoKmInicial.getText().toString());
                } else
                {
                    Toast.makeText(getActivity(), "É necessário informar a KM no Início da atividade.",
                            Toast.LENGTH_SHORT).show();
                    return ;
                }
            }

            if (interrupcao.indicadorInicioAtividade || interrupcao.indicadorFimAtividade)
            {
                interrupcao.dataFim=interrupcao.dataInicio;
                interrupcao.horaFim=interrupcao.horaInicio;
                interrupcao.observacaoFim=interrupcao.observacaoInicio;
                interrupcao.kmFinal=interrupcao.kmInicial;
            }

            listener.onSalvar(interrupcao);
        }
    }
}
