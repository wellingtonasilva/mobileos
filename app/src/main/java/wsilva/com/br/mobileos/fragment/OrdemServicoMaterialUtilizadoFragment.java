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
import android.widget.Toast;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.os.MaterialDAO;
import wsilva.com.br.mobileos.entity.os.MaterialUtilizadoVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.interfaces.IOrdemServicoMaterialUtilizado;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoMaterialUtilizadoFragment extends Fragment
{
    IOrdemServicoMaterialUtilizado listener;
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

    public void setListener(IOrdemServicoMaterialUtilizado listener)
    {
        this.listener = listener;
    }

    protected void init(View root, Bundle arguments)
    {
        if (arguments!=null)
        {
            ordemServico = (OrdemServicoVO) arguments.getSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM);
            //materialUtilizado = (MaterialUtilizadoVO) arguments.getSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM);
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
                                       int position, long id)
            {
                txtOsMaterialUtilizadoCodigo = (EditText) getView().findViewById(R.id.txtOsMaterialUtilizadoCodigo);
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

        doPovoaTela(root, ordemServico, materialUtilizado);
    }

    protected void doPovoaTela(View view, OrdemServicoVO ordemServico, MaterialUtilizadoVO vo)
    {
        EditText txtOsMaterialUtilizadoOS=(EditText) view.findViewById(R.id.txtOsMaterialUtilizadoOS);

        if (vo!=null)
        {
            //Código
            txtOsMaterialUtilizadoCodigo=(EditText) view.findViewById(R.id.txtOsMaterialUtilizadoCodigo);
            //Quantidade
            txtOsMaterialUtilizadoQuantidade=(EditText) view.findViewById(R.id.txtOsMaterialUtilizadoQuantidade);
        }

        if (ordemServico!=null)
        {

            txtOsMaterialUtilizadoOS.setText(String.valueOf(ordemServico.numeroOS));
        }
    }

    public void salvar()
    {
        if (listener != null)
        {
            View view = getView();
            //
            EditText txtOsMaterialUtilizadoQuantidade=(EditText) view.findViewById(R.id.txtOsMaterialUtilizadoQuantidade);

            //Verifica se foi informado a quantidade
            if (txtOsMaterialUtilizadoQuantidade.getText().toString().length() ==0) {
                Toast.makeText(getActivity(), "Informe a quantidade utilizada.", Toast.LENGTH_SHORT).show();
                return ;
            }

            MaterialUtilizadoVO vo= (materialUtilizado!=null ? materialUtilizado : new MaterialUtilizadoVO());
            vo.numeroOS = ordemServico.numeroOS;
            //Descrição do Material
            vo.descricaoMateriall = Util.getItemDescricao(spiOsMaterialUtilizadoMaterial,
                    MaterialDAO.COL_DESCRICAOMATERIAL);
            //Código do Material
            vo.idMaterial = Util.getItemId(spiOsMaterialUtilizadoMaterial,
                    MaterialDAO.COL_IDMATERIAL);
            //Quantidade
            vo.quantidade = Double.parseDouble(txtOsMaterialUtilizadoQuantidade.getText().toString());
            //Id. da Equipe
            vo.idEquipeExecucao = ordemServico.idEquipeExecucao;
            //Descrição da Equipe
            vo.descricaoEquipeExecucao = ordemServico.descricaoEquipeExecucao;

            listener.onSalvar(ordemServico, vo);
        }
    }
}
