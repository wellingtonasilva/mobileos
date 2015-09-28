package wsilva.com.br.mobileos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoDadosValaFragment extends Fragment
{
    OrdemServicoVO ordemServico;
    ValaVO vala;
    Spinner spiDadosValaLocalOcorrencia;
    Spinner spiDadosValaPavimento;
    SimpleCursorAdapter scaLocalOcorrencia;
    SimpleCursorAdapter scaPavimentoTipo;
    EditText txtDadosValaNumeroOs;
    CheckBox chkDadosValaIndicadorAterroPelaEquipe;
    CheckBox chkDadosValaIndicadorAterro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico_dados_vala_manter, container, false);
        //Argumentos
        Bundle bundle = getArguments();
        //Configuracao inicial
        init(root, bundle);

        return root;
    }

    protected void init(View root, Bundle arguments)
    {
        if (arguments!=null)
        {
            ordemServico = (OrdemServicoVO) arguments.getSerializable(OrdemServicoPagerAdapter.KEY_ORDEM_SERVICO);
            vala = (ValaVO) arguments.getSerializable(OrdemServicoPagerAdapter.KEY_ORDEM_SERVICO);
        }

        doPovoaTela(root, vala);
    }

    protected void doPovoaTela(View view, ValaVO vo)
    {
        if (vo!=null)
        {
            EditText txtDadosValaComprimento=(EditText) view.findViewById(R.id.txtDadosValaComprimento);
            EditText txtDadosValaLargura=(EditText) view.findViewById(R.id.txtDadosValaLargura);
            EditText txtDadosValaProfundidade=(EditText) view.findViewById(R.id.txtDadosValaProfundidade);
            CheckBox chkDadosValaIndicadorEntulho=(CheckBox) view.findViewById(R.id.chkDadosValaIndicadorEntulho);

            txtDadosValaComprimento.setText(String.valueOf(vala.comprimento));
            txtDadosValaLargura.setText(String.valueOf(vala.largura));
            txtDadosValaProfundidade.setText(String.valueOf(vala.profundidade));
            chkDadosValaIndicadorAterro.setChecked(vala.indicadorAterro==1 ? true:false);
            chkDadosValaIndicadorEntulho.setChecked(vala.indicadorEntulho==1 ? true:false);
            chkDadosValaIndicadorAterroPelaEquipe.setChecked(vala.indicadorAterradoPelaEquipe==1 ?true:false);
        }
    }
}
