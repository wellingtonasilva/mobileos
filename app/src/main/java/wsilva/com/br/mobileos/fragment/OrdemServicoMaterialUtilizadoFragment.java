package wsilva.com.br.mobileos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.os.MaterialDAO;
import wsilva.com.br.mobileos.entity.os.MaterialUtilizadoVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoMaterialUtilizadoFragment extends Fragment
{
    OrdemServicoVO ordemServico;
    MaterialUtilizadoVO materialUtilizado;
    Spinner spiOsMaterialUtilizadoMaterial;
    EditText txtOsMaterialUtilizadoCodigo;
    EditText txtOsMaterialUtilizadoQuantidade;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico_material_utilizado_manter, container, false);
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
            materialUtilizado = (MaterialUtilizadoVO) arguments.getSerializable(OrdemServicoPagerAdapter.KEY_ORDEM_SERVICO);
        }

        //Descrição do Material
        spiOsMaterialUtilizadoMaterial=(Spinner) root.findViewById(R.id.spiOsMaterialUtilizadoMaterial);
        spiOsMaterialUtilizadoMaterial.setAdapter(Util.criarSimpleCursorAdapter(getActivity(),
                new MaterialDAO(getActivity()),
                new String[]{MaterialDAO.COL_DESCRICAOMATERIAL, MaterialDAO.COL_IDMATERIAL},
                new int[]{R.id.lblSpinnerItem1,R.id.lblSpinnerItem2},
                getActivity(),
                R.layout.lay_template_spinner_item,
                R.layout.lay_template_spinner_dropdown_item));

        //Evento
        spiOsMaterialUtilizadoMaterial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                txtOsMaterialUtilizadoCodigo.setText(String.valueOf(Util.getItemId(spiOsMaterialUtilizadoMaterial,
                        MaterialDAO.COL_IDMATERIAL)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        //Pesquisa de Material
        ImageButton btnPesquisar=(ImageButton) root.findViewById(R.id.btnPesquisar);
        btnPesquisar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (txtOsMaterialUtilizadoCodigo.getText().toString().length() >0)
                {
                    spiOsMaterialUtilizadoMaterial.setSelection(Util.getItemPositionFromId(spiOsMaterialUtilizadoMaterial,
                            MaterialDAO.COL_IDMATERIAL, Integer.parseInt(txtOsMaterialUtilizadoCodigo.getText().toString())));
                } else {
                    spiOsMaterialUtilizadoMaterial.setSelection(0);
                }
            }
        });

        doPovoaTela(root, materialUtilizado);
    }

    protected void doPovoaTela(View view, MaterialUtilizadoVO vo)
    {
        if (vo!=null)
        {
            //Código
            txtOsMaterialUtilizadoCodigo=(EditText) view.findViewById(R.id.txtOsMaterialUtilizadoCodigo);
            //Quantidade
            txtOsMaterialUtilizadoQuantidade=(EditText) view.findViewById(R.id.txtOsMaterialUtilizadoQuantidade);
        }
    }
}
