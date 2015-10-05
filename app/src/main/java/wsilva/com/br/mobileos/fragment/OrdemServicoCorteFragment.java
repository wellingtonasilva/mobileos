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
import wsilva.com.br.mobileos.dao.os.CorteTipoDAO;
import wsilva.com.br.mobileos.dao.os.MotivoCorteDAO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoCorteFragment extends Fragment
{
    OrdemServicoVO ordemServico;
    ValaVO vala;
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

    protected void init(View root, Bundle arguments)
    {
        if (arguments!=null)
        {
            ordemServico = (OrdemServicoVO) arguments.getSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM);
            vala = (ValaVO) arguments.getSerializable(OrdemServicoPagerAdapter.KEY_ORDEM_VALA);
        }

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

        doPovoaTela(root, null);
    }

    protected void doPovoaTela(View view, ValaVO vo)
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
}
