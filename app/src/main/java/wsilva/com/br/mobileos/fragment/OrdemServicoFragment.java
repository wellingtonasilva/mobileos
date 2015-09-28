package wsilva.com.br.mobileos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoFragment extends Fragment
{
    OrdemServicoVO ordemServico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_ordem_servico, container, false);
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

        doPovoaTela(root, ordemServico);
    }

    protected void doPovoaTela(View view, OrdemServicoVO vo)
    {
        if (vo!=null)
        {
            EditText txtNumeroRA = (EditText) view.findViewById(R.id.txtNumeroRA);
            EditText txtNumeroOS = (EditText) view.findViewById(R.id.txtDadosOSNumeroOs);
            EditText txtServico  = (EditText) view.findViewById(R.id.txtSevico);
            EditText txtDataGeracaoOS= (EditText) view.findViewById(R.id.txtDataGeracao);
            EditText txtLogradouro = (EditText) view.findViewById(R.id.txtLogradouro);
            EditText txtBairro = (EditText) view.findViewById(R.id.txtBairro);
            EditText txtNumeroImovel = (EditText) view.findViewById(R.id.txtNumeroImovel);
            EditText txtIdCliente = (EditText) view.findViewById(R.id.txtCodigoCliente);
            EditText txtIdImovel = (EditText) view.findViewById(R.id.txtNumeroLigacao);
            EditText txtNomeCliente = (EditText) view.findViewById(R.id.txtNomeCliente);
            EditText txtSevicoExecutado=(EditText) view.findViewById(R.id.txtSevicoExecutado);
            EditText txtObservacaoRA=(EditText) view.findViewById(R.id.txtObservacaoRA);
            EditText txtDadosOSObservacaoOS=(EditText) view.findViewById(R.id.txtDadosOSObservacaoOS);
            EditText txtDadosOSObservacaoCampo=(EditText) view.findViewById(R.id.txtDadosOSObservacaoCampo);
            EditText txtNumeroZona=(EditText) view.findViewById(R.id.txtNumeroZona);
            EditText txtNumeroQuadra=(EditText) view.findViewById(R.id.txtNumeroQuadra);
            EditText txtNumeroLote=(EditText) view.findViewById(R.id.txtNumeroLote);
            EditText txtNumeroHidrometro=(EditText) view.findViewById(R.id.txtNumeroHidrometro);
            //Povoa
            txtNumeroOS.setText(String.valueOf(vo.numeroOS));
            txtNumeroRA.setText(String.valueOf(vo.numeroRA));
            txtServico.setText(String.valueOf(vo.descricaoTipoServico));
            txtDataGeracaoOS.setText(Util.dateToString("dd/MM/yyyy", vo.dataGeracaoRA));
            txtLogradouro.setText(vo.logradouro);
            txtNumeroImovel.setText(String.valueOf(vo.numeroImovel));
            txtBairro.setText(vo.bairro);
            txtIdCliente.setText(String.valueOf(vo.idCliente));
            txtIdImovel.setText(String.valueOf(vo.idImovel));
            txtNomeCliente.setText(vo.nomeCliente);
            txtSevicoExecutado.setText(vo.descricaoTipoServicoExecutado);
            txtObservacaoRA.setText(vo.observacaoRA);
            txtDadosOSObservacaoOS.setText(vo.observacaoOS);
            txtDadosOSObservacaoCampo.setText(vo.observacaoCampo);
            txtNumeroZona.setText(String.valueOf(vo.idSetorComercial));
            txtNumeroQuadra.setText(vo.numeroQuadra);
            txtNumeroLote.setText(String.valueOf(vo.numeroLote));
            txtNumeroHidrometro.setText(vo.numeroHidrometro);
        }
    }
}
