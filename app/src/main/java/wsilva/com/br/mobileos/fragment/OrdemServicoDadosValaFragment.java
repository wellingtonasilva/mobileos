package wsilva.com.br.mobileos.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.os.LocalOcorrenciaDAO;
import wsilva.com.br.mobileos.dao.os.PavimentoTipoDAO;
import wsilva.com.br.mobileos.dao.os.ServicoTipoDAO;
import wsilva.com.br.mobileos.dao.os.ValaDAO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;
import wsilva.com.br.mobileos.interfaces.IOrdemServicoDadosVala;
import wsilva.com.br.mobileos.interfaces.IOrdemServicoExecucaoInicio;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoDadosValaFragment extends Fragment
{
    IOrdemServicoDadosVala listener;
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
            ordemServico = (OrdemServicoVO) arguments.getSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM);
            //vala = (ValaVO) arguments.getSerializable(OrdemServicoPagerAdapter.KEY_ORDEM_SERVICO);
        }

        spiDadosValaLocalOcorrencia = (Spinner) root.findViewById(R.id.spiDadosValaLocalOcorrencia);
        spiDadosValaLocalOcorrencia.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new LocalOcorrenciaDAO(root.getContext()),
                new String[]{LocalOcorrenciaDAO.COL_DESCRICAOLOCALOCORRENCIA},
                new int[]{android.R.id.text1}));

        spiDadosValaPavimento = (Spinner) root.findViewById(R.id.spiDadosValaPavimento);
        spiDadosValaPavimento.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new PavimentoTipoDAO(root.getContext()),
                new String[]{PavimentoTipoDAO.COL_DESCRICAOPAVIMENTOTIPO},
                new int[]{android.R.id.text1}));

        doPovoaTela(root, vala);
    }

    public void setListener(IOrdemServicoDadosVala listener)
    {
        this.listener = listener;
    }


    protected void doPovoaTela(View view, ValaVO vo)
    {
        EditText txtDadosValaNumeroOs=(EditText) view.findViewById(R.id.txtDadosValaNumeroOs);
        EditText txtDadosValaNumero=(EditText) view.findViewById(R.id.txtDadosValaNumero);

        if (vo!=null)
        {
            EditText txtDadosValaComprimento=(EditText) view.findViewById(R.id.txtDadosValaComprimento);
            EditText txtDadosValaLargura=(EditText) view.findViewById(R.id.txtDadosValaLargura);
            EditText txtDadosValaProfundidade=(EditText) view.findViewById(R.id.txtDadosValaProfundidade);
            CheckBox chkDadosValaIndicadorEntulho=(CheckBox) view.findViewById(R.id.chkDadosValaIndicadorEntulho);
            //
            txtDadosValaNumeroOs.setText(String.valueOf(ordemServico.numeroOS));
            txtDadosValaNumero.setText(String.valueOf(vala.numeroVala));
            txtDadosValaComprimento.setText(String.valueOf(vala.comprimento));
            txtDadosValaLargura.setText(String.valueOf(vala.largura));
            txtDadosValaProfundidade.setText(String.valueOf(vala.profundidade));
            chkDadosValaIndicadorAterro.setChecked(vala.indicadorAterro==1 ? true:false);
            chkDadosValaIndicadorEntulho.setChecked(vala.indicadorEntulho==1 ? true:false);
            chkDadosValaIndicadorAterroPelaEquipe.setChecked(vala.indicadorAterradoPelaEquipe==1 ?true:false);
        }
        else
        {
            ValaDAO dao = new ValaDAO(getActivity());
            txtDadosValaNumeroOs.setText(String.valueOf(ordemServico.numeroOS));
            txtDadosValaNumero.setText(String.valueOf(dao.obterUltimoRegistro(ordemServico.numeroOS)));
        }
    }

    public void salvar()
    {
        ValaVO vo=null;

        if (vala==null){
            vo=new ValaVO();
            vo.indicadorFotoValaAberta = 0;
            vo.indicadorFotoValaFechada = 0;
        } else {
            vo=vala;
        }

        if (listener != null)
        {
            View view = getView();
            //Valores
            EditText txtDadosValaComprimento=(EditText) view.findViewById(R.id.txtDadosValaComprimento);
            EditText txtDadosValaLargura=(EditText) view.findViewById(R.id.txtDadosValaLargura);
            EditText txtDadosValaProfundidade=(EditText) view.findViewById(R.id.txtDadosValaProfundidade);
            CheckBox chkDadosValaIndicadorAterro=(CheckBox) view.findViewById(R.id.chkDadosValaIndicadorAterro);
            CheckBox chkDadosValaIndicadorEntulho=(CheckBox) view.findViewById(R.id.chkDadosValaIndicadorEntulho);
            CheckBox chkDadosValaIndicadorAterroPelaEquipe=(CheckBox) view.findViewById(R.id.chkDadosValaIndicadorAterroPelaEquipe);
            EditText txtDadosValaQuantidadeBag=(EditText) view.findViewById(R.id.txtDadosValaQuantidadeBag);
            EditText txtDadosValaNumero=(EditText) view.findViewById(R.id.txtDadosValaNumero);

            //Valida Comprimento
            if (txtDadosValaComprimento.getText().length() ==0) {
                Toast.makeText(getActivity(), "Informe o comprimento da vala", Toast.LENGTH_SHORT).show();
                return ;
            }

            //Valida Largura
            if (txtDadosValaLargura.getText().length()==0) {
                Toast.makeText(getActivity(), "Informe a largura da vala", Toast.LENGTH_SHORT).show();
                return ;
            }
            //Profundidade
            if (txtDadosValaProfundidade.getText().length()==0){
                txtDadosValaProfundidade.setText("0");
            }

            //Numero da Os
            vo.numeroOS = ordemServico.numeroOS;
            vo.numeroVala = Integer.parseInt(txtDadosValaNumero.getText().toString());
            //Comprimento
            vo.comprimento = Double.parseDouble(txtDadosValaComprimento.getText().toString());
            //Largura
            vo.largura = Double.parseDouble(txtDadosValaLargura.getText().toString());
            //Profunidade
            vo.profundidade = Double.parseDouble(txtDadosValaProfundidade.getText().toString());
            //Indicador de Aterro
            vo.indicadorAterro = (chkDadosValaIndicadorAterro.isChecked() ? 1:2);
            //Inciador de Entulho
            vo.indicadorEntulho = (chkDadosValaIndicadorEntulho.isChecked() ? 1:2);
            //Indicador de Aterrado pela Equipe
            vo.indicadorAterradoPelaEquipe = (chkDadosValaIndicadorAterroPelaEquipe.isChecked() ? 1:2);
            //Quantidade de Bag's
            vo.quantidadeBags = (txtDadosValaQuantidadeBag.getText().toString().length() > 0 ?
                    Integer.parseInt(txtDadosValaQuantidadeBag.getText().toString()) : 0);
            //Local Ocorrência
            vo.idLocalOcorrencia = Util.getItemId(spiDadosValaLocalOcorrencia, LocalOcorrenciaDAO.COL_ID);
            vo.descricaoLocalOcorrencia = Util.getItemDescricao(spiDadosValaLocalOcorrencia, LocalOcorrenciaDAO.COL_DESCRICAOLOCALOCORRENCIA);
            //Pavimento
            vo.idPavimento = Util.getItemId(spiDadosValaPavimento, PavimentoTipoDAO.COL_ID);
            vo.descricaoPavimento = Util.getItemDescricao(spiDadosValaPavimento, PavimentoTipoDAO.COL_DESCRICAOPAVIMENTOTIPO);
            //Id. Equipe Execução
            vo.idEquipeExecucao = ordemServico.idEquipeExecucao;
            vo.descricaoEquipeExecucao = ordemServico.descricaoEquipeExecucao;

            this.listener.onSalvar(ordemServico, vo);
        }
    }

}
