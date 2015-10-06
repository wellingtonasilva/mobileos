package wsilva.com.br.mobileos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import java.util.Date;
import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.ocorrencia.InterrupcaoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;
import wsilva.com.br.mobileos.interfaces.IOcorrenciaFim;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OcorrenciaFimFragment extends Fragment
{
    IOcorrenciaFim listener;
    InterrupcaoVO interrupcao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ocorrencia_fim, container, false);
        //Argumentos
        Bundle bundle = getArguments();
        //Configuracao inicial
        init(root, bundle);

        return root;
    }

    public void setListener(IOcorrenciaFim listener)
    {
        this.listener = listener;
    }

    protected void init(View root, Bundle arguments)
    {
        if (arguments!=null)
        {
            interrupcao = (InterrupcaoVO) arguments.getSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM);
        }

        doPovoaTela(root, interrupcao);
    }

    protected void doPovoaTela(View view, InterrupcaoVO vo)
    {
        if (vo!=null)
        {
            EditText txtInterrupcaoMatricula= (EditText) view.findViewById(R.id.txtInterrupcaoMatricula);
            EditText txtInterrupcaoNumeroOS= (EditText) view.findViewById(R.id.txtInterrupcaoNumeroOS);
            EditText txtInterrupcaoDataFim= (EditText) view.findViewById(R.id.txtInterrupcaoDataFim);
            EditText txtInterrupcaoHoraFim= (EditText) view.findViewById(R.id.txtInterrupcaoHoraFim);
            EditText txtInterrupcaoMotivo=(EditText) view.findViewById(R.id.txtInterrupcaoMotivo);
            //
            txtInterrupcaoMatricula.setText(String.valueOf(interrupcao.matricula));
            txtInterrupcaoNumeroOS.setText(String.valueOf(interrupcao.numeroOS));
            txtInterrupcaoDataFim.setText(Util.dateToString("dd/MM/yyyy", new Date()));
            txtInterrupcaoHoraFim.setText(Util.dateToString("HH:mm", new Date()));
            txtInterrupcaoMotivo.setText(interrupcao.descricaoInterrupcaoMotivo);
        }
    }

    public void salvar()
    {
        if (listener != null)
        {
            View view = getView();
            //
            EditText txtInterrupcaoDataFim= (EditText) view.findViewById(R.id.txtInterrupcaoDataFim);
            EditText txtInterrupcaoHoraFim= (EditText) view.findViewById(R.id.txtInterrupcaoHoraFim);
            EditText txtInterrupcaoObservacaoFim= (EditText) view.findViewById(R.id.txtInterrupcaoObservacaoFim);
            EditText txtInterrupcaoKmFinal= (EditText) view.findViewById(R.id.txtInterrupcaoKmFinal);

            interrupcao.dataFim=txtInterrupcaoDataFim.getText().toString();
            interrupcao.horaFim=txtInterrupcaoHoraFim.getText().toString();
            interrupcao.observacaoFim=txtInterrupcaoObservacaoFim.getText().toString();
            //Km Inicial
            if (txtInterrupcaoKmFinal.getText() !=null && txtInterrupcaoKmFinal.getText().toString().length()>0) {
                interrupcao.kmFinal= Integer.parseInt(txtInterrupcaoKmFinal.getText().toString());
            }
            //
            listener.onSalvar(interrupcao);
        }
    }
}
