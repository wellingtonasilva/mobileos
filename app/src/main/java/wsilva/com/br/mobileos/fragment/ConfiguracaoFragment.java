package wsilva.com.br.mobileos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.util.GenerateUUID;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.os.ConfiguracoesDAO;
import wsilva.com.br.mobileos.entity.os.ConfiguracoesVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.pageadapter.ConfiguracaoPagerAdapter;

public class ConfiguracaoFragment extends Fragment
{
    ConfiguracoesVO configuracoes;
    EditText txtServidorFTPIP;
    EditText txtServidorFTPUsuario;
    EditText txtServidorFTPSenha;
    EditText txtServidorFTPPorta;
    EditText txtConfigNomeDispositivo;
    EditText txtConfigIdDispositivo;
    EditText txtConfigPastDownload;
    EditText txtConfigPastaUpload;
    EditText txtConfigPastaImage;
    EditText txtConfigSerialDispositivo;
    EditText txtConfigCodigoColetor;
    EditText txtConfigCodigoEmpresa;
    EditText txtConfigCodigoEquipe;
    CheckBox chkConfigUtilizarFTPEnviar;
    CheckBox chkConfigUtilizarFTPReceber;
    CheckBox chkConfigEnviarAposExportacao;
    CheckBox chkConfigEnviarFotosFTP;
    //Web
    EditText txtConfigURL;
    CheckBox chkConfigUtilizarWEBEnviar;
    CheckBox chkConfigUtilizarWEBReceber;
    CheckBox chkConfigEnviarFotosWEB;
    //SMS
    EditText txtConfigSMSTelefone;
    CheckBox chkConfigSMSEnviarAposIniciar;
    CheckBox chkConfigSMSEnviarAposEncerrar;
    //Geral
    Spinner spiConfigLogomarca;
    CheckBox chkConfigExecucaoOSParalelo;
    CheckBox chkConfigExigeCheclist;
    CheckBox chkConfigExigeInicioAtividade;
    //Email
    EditText txtConfigEmailDestinatario;
    CheckBox chkConfigEmailEnviarAposExportar;
    CheckBox chkConfigEmailEnviarAposEncerrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_configuracao_manter, container, false);
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
            configuracoes = (ConfiguracoesVO) arguments.getSerializable(ConfiguracaoPagerAdapter.KEY_CONFIGURACAO);
        }

        //IP do servidor FTP
        txtServidorFTPIP=(EditText) root.findViewById(R.id.txtServidorFTPIP);
        //Usu�rio
        txtServidorFTPUsuario=(EditText) root.findViewById(R.id.txtServidorFTPUsuario);
        //Senha
        txtServidorFTPSenha=(EditText) root.findViewById(R.id.txtServidorFTPSenha);
        //Porta
        txtServidorFTPPorta=(EditText) root.findViewById(R.id.txtServidorFTPPorta);
        //Id do Dispositivo
        txtConfigIdDispositivo=(EditText) root.findViewById(R.id.txtConfigIdDispositivo);
        //Nome
        txtConfigNomeDispositivo=(EditText) root.findViewById(R.id.txtConfigNomeDispositivo);
        //C�digo
        txtConfigCodigoColetor=(EditText) root.findViewById(R.id.txtConfigCodigoColetor);
        //Empresa
        txtConfigCodigoEmpresa=(EditText) root.findViewById(R.id.txtConfigCodigoEmpresa);
        //Equipe
        txtConfigCodigoEquipe=(EditText) root.findViewById(R.id.txtConfigCodigoEquipe);
        //Serial
        txtConfigSerialDispositivo=(EditText) root.findViewById(R.id.txtConfigSerialDispositivo);
        //Pasta para Download
        txtConfigPastDownload=(EditText) root.findViewById(R.id.txtConfigPastDownload);
        //Para para Upload
        txtConfigPastaUpload=(EditText) root.findViewById(R.id.txtConfigPastaUpload);
        //Pasta para Image
        txtConfigPastaImage=(EditText) root.findViewById(R.id.txtConfigPastaImage);
        //Utilizar FTP para Enviar arquivos
        chkConfigUtilizarFTPEnviar=(CheckBox) root.findViewById(R.id.chkConfigUtilizarFTPEnviar);
        //Utilizar FTP para Receber arquivos
        chkConfigUtilizarFTPReceber=(CheckBox) root.findViewById(R.id.chkConfigUtilizarFTPReceber);
        //Enviar arquivos ap�s exporta��o
        chkConfigEnviarAposExportacao=(CheckBox) root.findViewById(R.id.chkConfigEnviarAposExportacao);
        //Enviar fotos via FTP
        chkConfigEnviarFotosFTP=(CheckBox) root.findViewById(R.id.chkConfigEnviarFotosFTP);
        //URL Web
        txtConfigURL=(EditText) root.findViewById(R.id.txtConfigURL);
        //Enviar Informa��es via WEB
        chkConfigUtilizarWEBEnviar=(CheckBox) root.findViewById(R.id.chkConfigUtilizarWEBEnviar);
        //Receber informa��es via WEB
        chkConfigUtilizarWEBReceber=(CheckBox) root.findViewById(R.id.chkConfigUtilizarWEBReceber);
        //Enviar foto via WEB
        chkConfigEnviarFotosWEB=(CheckBox) root.findViewById(R.id.chkConfigEnviarFotosWEB);
        //Telefone SMS
        txtConfigSMSTelefone=(EditText) root.findViewById(R.id.txtConfigSMSTelefone);
        //Enviar SMS ap�s iniciar
        chkConfigSMSEnviarAposIniciar=(CheckBox) root.findViewById(R.id.chkConfigSMSEnviarAposIniciar);
        //Enviar SMS ap�s encerrar
        chkConfigSMSEnviarAposEncerrar=(CheckBox) root.findViewById(R.id.chkConfigSMSEnviarAposEncerrar);
        //Geral
        chkConfigExecucaoOSParalelo=(CheckBox) root.findViewById(R.id.chkConfigExecucaoOSParalelo);
        chkConfigExigeCheclist=(CheckBox) root.findViewById(R.id.chkConfigExigeCheclist);
        chkConfigExigeInicioAtividade=(CheckBox) root.findViewById(R.id.chkConfigExigeInicioAtividade);
        txtConfigEmailDestinatario=(EditText) root.findViewById(R.id.txtConfigEmailDestinatario);
        chkConfigEmailEnviarAposExportar=(CheckBox) root.findViewById(R.id.chkConfigEmailEnviarAposExportar);
        chkConfigEmailEnviarAposEncerrar=(CheckBox) root.findViewById(R.id.chkConfigEmailEnviarAposEncerrar);
        spiConfigLogomarca=(Spinner) root.findViewById(R.id.spiConfigLogomarca);
        spiConfigLogomarca.setAdapter(Util.criarArrayAdapter(getActivity(), R.array.arrLogomarca,
                android.R.layout.simple_spinner_item));
        
        doPovoaTela(root, configuracoes);
    }

    protected void doPovoaTela(View view, ConfiguracoesVO vo)
    {
        ConfiguracoesDAO dao=new ConfiguracoesDAO(getActivity());
        if (dao.quantidadeRegistros() <=0)
        {
            GenerateUUID uuid=new GenerateUUID(getActivity());
            String key=uuid.getCombinedUniqueID();
            txtServidorFTPIP.setText(Util.FTP_IP);
            txtServidorFTPUsuario.setText(Util.FTP_USERNAME);
            txtServidorFTPSenha.setText(Util.FTP_PASSWORD);
            txtServidorFTPPorta.setText(Util.FTP_PORT);
            txtConfigURL.setText(Util.WEB_URL);
            txtConfigIdDispositivo.setText(GenerateUUID.formatCombinedUniqueID(key));
            txtConfigPastDownload.setText(Util.PATH_DOWNLOAD);
            txtConfigPastaUpload.setText(Util.PATH_UPLOAD);
            txtConfigPastaImage.setText(Util.PATH_IMAGE);
            chkConfigExecucaoOSParalelo.setChecked(false);

            vo=new ConfiguracoesVO();
        } else
        {
            List<ConfiguracoesVO> lista=dao.listar();
            vo=lista.get(0);
            txtServidorFTPIP.setText(vo.ftpIP);
            txtServidorFTPUsuario.setText(vo.ftpUsuario);
            txtServidorFTPSenha.setText(vo.ftpSenha);
            txtServidorFTPPorta.setText(String.valueOf(vo.ftpPorta));
            txtConfigNomeDispositivo.setText(vo.dispositivoNome);
            txtConfigIdDispositivo.setText(vo.dispositivoID);
            txtConfigPastDownload.setText(vo.dispositivoPastaDownload);
            txtConfigPastaUpload.setText(vo.dispositivoPastaUpload);
            txtConfigPastaUpload.setText(vo.dispositivoPastaImage);
            txtConfigSerialDispositivo.setText(vo.dispositivoSerial);
            chkConfigUtilizarFTPEnviar.setChecked((vo.utilizarFTPEnviarArquivos == 1 ? true : false));
            chkConfigUtilizarFTPReceber.setChecked((vo.utilizarFTPReceberArquivos == 1 ? true : false));
            chkConfigEnviarAposExportacao.setChecked((vo.enviarArquivosAposExportacao == 1 ? true : false));
            chkConfigEnviarFotosFTP.setChecked((vo.enviarFotosViaFTP == 1 ? true : false));
            txtConfigURL.setText(vo.webURL);
            chkConfigUtilizarWEBEnviar.setChecked((vo.utilizarWEBEnviarArquivos == 1 ? true : false));
            chkConfigUtilizarWEBReceber.setChecked((vo.utilizarWEBPReceberArquivos == 1 ? true : false));
            chkConfigEnviarFotosWEB.setChecked((vo.enviarFotosViaWEB == 1 ? true : false));
            txtConfigSMSTelefone.setText(String.valueOf(vo.smsTelefone));
            chkConfigSMSEnviarAposIniciar.setChecked((vo.enviarSmsAposIniciar == 1 ? true : false));
            chkConfigSMSEnviarAposEncerrar.setChecked((vo.enviarSmsAposEncerrar == 1 ? true : false));
            txtConfigCodigoColetor.setText(vo.coletorCodigo);
            txtConfigCodigoEmpresa.setText(vo.coletorEmpresa);
            txtConfigCodigoEquipe.setText(vo.coletorEquipe);
            chkConfigExecucaoOSParalelo.setChecked((vo.permiteExecucaoEmParaleloOS == 1 ? true : false));
            chkConfigExigeCheclist.setChecked((vo.exigeChecklist ==1 ? true:false));
            chkConfigExigeInicioAtividade.setChecked((vo.exigeInicioAtividade==1?true:false));
            txtConfigEmailDestinatario.setText(vo.emailDestinatario);
            chkConfigEmailEnviarAposExportar.setChecked((vo.enviarEmailAposEncerrar==1?true:false));
            chkConfigEmailEnviarAposEncerrar.setChecked((vo.enviarEmailAposExportar==1?true:false));
            spiConfigLogomarca.setSelection(vo.logomarca);
        }
    }
}
