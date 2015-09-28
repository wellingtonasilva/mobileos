package wsilva.com.br.mobileos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.os.AgenteExternoDAO;
import wsilva.com.br.mobileos.dao.os.RedeCausaVazamentoDAO;
import wsilva.com.br.mobileos.dao.os.RedeDiametroDAO;
import wsilva.com.br.mobileos.dao.os.RedeMaterialDAO;
import wsilva.com.br.mobileos.dao.os.RedeTipoDAO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoDadosRedeFragment extends Fragment
{
    OrdemServicoVO ordemServico;
    Spinner spiDadosRedeCausa=null;
    Spinner spiDadosRedeRedeRamal=null;
    Spinner spiDadosRedeDiametroRede=null;
    Spinner spiDadosRedeMaterialRede=null;
    Spinner spiDadosRedeAgenteExterno=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico_dados_rede, container, false);
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
        }
        spiDadosRedeCausa=(Spinner) root.findViewById(R.id.spiDadosRedeCausa);
        spiDadosRedeRedeRamal=(Spinner) root.findViewById(R.id.spiDadosRedeRedeRamal);
        spiDadosRedeDiametroRede=(Spinner) root.findViewById(R.id.spiDadosRedeDiametroRede);
        spiDadosRedeMaterialRede=(Spinner) root.findViewById(R.id.spiDadosRedeMaterialRede);
        spiDadosRedeAgenteExterno=(Spinner) root.findViewById(R.id.spiDadosRedeAgenteExterno);

        //Causa do Vazamento
        spiDadosRedeCausa.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new RedeCausaVazamentoDAO(root.getContext()),
                new String[]{RedeCausaVazamentoDAO.COL_DESCRICAOCAUSAVAZAMENTO},
                new int[]{android.R.id.text1}));

        //Rede/Ramal
        spiDadosRedeRedeRamal.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new RedeTipoDAO(root.getContext()),
                new String[]{RedeTipoDAO.COL_DESCRICAOREDETIPO},
                new int[]{android.R.id.text1}));

        //Diametro da Rede
        spiDadosRedeDiametroRede.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new RedeDiametroDAO(root.getContext()),
                new String[]{RedeDiametroDAO.COL_DESRICAOREDEDIAMETRO},
                new int[]{android.R.id.text1}));

        //Material da Rede
        spiDadosRedeMaterialRede.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new RedeMaterialDAO(root.getContext()),
                new String[]{RedeMaterialDAO.COL_DESCRICAOREDEMATERIAL},
                new int[]{android.R.id.text1}));

        //Agente Externo
        spiDadosRedeAgenteExterno.setAdapter(Util.criarSimpleCursorAdapter(root.getContext(),
                new AgenteExternoDAO(root.getContext()),
                new String[]{AgenteExternoDAO.COL_AGENTEEXTERNO},
                new int[]{android.R.id.text1}));

        doPovoaTela(root, ordemServico);
    }

    protected void doPovoaTela(View view, OrdemServicoVO vo)
    {
        if (vo!=null)
        {
            //Causa do Vazamento
            if (ordemServico.idCausaRede!=0) {
                spiDadosRedeCausa.setSelection(Util.getItemPositionFromId(spiDadosRedeCausa,
                        RedeCausaVazamentoDAO.COL_IDCAUSAVAZAMENTO, ordemServico.idCausaRede));
            }

            //Rede/Ramal
            if (ordemServico.tipoRede!=0) {
                spiDadosRedeRedeRamal.setSelection(Util.getItemPositionFromId(spiDadosRedeRedeRamal,
                        RedeTipoDAO.COL_IDREDETIPO, ordemServico.tipoRede));
            }

            //Diametro da Rede
            if (ordemServico.idDiametroRede!=0) {
                spiDadosRedeDiametroRede.setSelection(Util.getItemPositionFromId(spiDadosRedeDiametroRede,
                        RedeDiametroDAO.COL_IDREDEDIAMETRO, ordemServico.idDiametroRede));
            }

            //Material da Rede
            if (ordemServico.idMaterialRede!=0) {
                spiDadosRedeMaterialRede.setSelection(Util.getItemPositionFromId(spiDadosRedeMaterialRede,
                        RedeMaterialDAO.COL_IDREDEMATERIAL, ordemServico.idMaterialRede));
            }

            //Agente Externo
            if (ordemServico.idAgenteExterno!=0) {
                spiDadosRedeAgenteExterno.setSelection(Util.getItemPositionFromId(spiDadosRedeAgenteExterno,
                        AgenteExternoDAO.COL_IDAGENTEEXTERNO, ordemServico.idAgenteExterno));
            }

            EditText txtDadosRedeProfundidade=(EditText) view.findViewById(R.id.txtDadosRedeProfundidade);
            EditText txtDadosRedePressao=(EditText) view.findViewById(R.id.txtDadosRedePressao);
            EditText txtDadosRedeLacreAnterior=(EditText) view.findViewById(R.id.txtDadosRedeLacreAnterior);
            EditText txtDadosRedeLacreNovo=(EditText) view.findViewById(R.id.txtDadosRedeLacreNovo);
            EditText txtDadosRedeNumeroHidrometro=(EditText) view.findViewById(R.id.txtDadosRedeNumeroHidrometro);
            EditText txtDadosRedeLeitura=(EditText) view.findViewById(R.id.txtDadosRedeLeitura);

            //Profundidade
            txtDadosRedeProfundidade.setText(ordemServico.profundidadeRede);
            //Pressão
            txtDadosRedePressao.setText(ordemServico.pressaoRede);
            //Lacre Anterior
            txtDadosRedeLacreAnterior.setText(String.valueOf(ordemServico.numeroLacreAnterior));
            //Lacre Novo
            txtDadosRedeLacreNovo.setText(String.valueOf(ordemServico.numeroLocreNovo));
            //Numero do Hidrometro
            txtDadosRedeNumeroHidrometro.setText(ordemServico.numeroHidrometro);
            //Leitura
            txtDadosRedeLeitura.setText(String.valueOf(ordemServico.leitura));
        }
    }
}
