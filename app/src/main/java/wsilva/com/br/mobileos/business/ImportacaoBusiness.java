package wsilva.com.br.mobileos.business;

import android.content.Context;

import java.util.Date;
import java.util.List;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.checklist.ChecklistGrupoDAO;
import wsilva.com.br.mobileos.dao.checklist.ChecklistItemDAO;
import wsilva.com.br.mobileos.dao.checklist.ChecklistOpcaoDAO;
import wsilva.com.br.mobileos.dao.checklist.FuncionariosDAO;
import wsilva.com.br.mobileos.dao.checklist.VeiculosDAO;
import wsilva.com.br.mobileos.dao.ocorrencia.InterrupcaoMotivoDAO;
import wsilva.com.br.mobileos.dao.os.AgenteExternoDAO;
import wsilva.com.br.mobileos.dao.os.CorteTipoDAO;
import wsilva.com.br.mobileos.dao.os.EquipeDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroLocalArmazenagemDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroLocalInstalacaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroProtecaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroSituacaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroTipoInstalacaoDAO;
import wsilva.com.br.mobileos.dao.os.HidrometroTipoSubstituicaoDAO;
import wsilva.com.br.mobileos.dao.os.LigacaoAguaSituacaoDAO;
import wsilva.com.br.mobileos.dao.os.LigacaoEsgotoSituacaoDAO;
import wsilva.com.br.mobileos.dao.os.LocalOcorrenciaDAO;
import wsilva.com.br.mobileos.dao.os.LogradouroTipoDAO;
import wsilva.com.br.mobileos.dao.os.MaterialDAO;
import wsilva.com.br.mobileos.dao.os.MotivoCorteDAO;
import wsilva.com.br.mobileos.dao.os.MotivoEncerramentoDAO;
import wsilva.com.br.mobileos.dao.os.OrdemServicoDAO;
import wsilva.com.br.mobileos.dao.os.PavimentoTipoDAO;
import wsilva.com.br.mobileos.dao.os.RedeCausaVazamentoDAO;
import wsilva.com.br.mobileos.dao.os.RedeDiametroDAO;
import wsilva.com.br.mobileos.dao.os.RedeMaterialDAO;
import wsilva.com.br.mobileos.dao.os.RedeTipoDAO;
import wsilva.com.br.mobileos.dao.os.ReligacaoTipoDAO;
import wsilva.com.br.mobileos.dao.os.ServicoTipoDAO;
import wsilva.com.br.mobileos.dao.os.UsuarioDAO;
import wsilva.com.br.mobileos.entity.checklist.ChecklistGrupoVO;
import wsilva.com.br.mobileos.entity.checklist.ChecklistItemVO;
import wsilva.com.br.mobileos.entity.checklist.ChecklistOpcaoVO;
import wsilva.com.br.mobileos.entity.checklist.FuncionariosVO;
import wsilva.com.br.mobileos.entity.checklist.VeiculosVO;
import wsilva.com.br.mobileos.entity.ocorrencia.InterrupcaoMotivoVO;
import wsilva.com.br.mobileos.entity.os.AgenteExternoVO;
import wsilva.com.br.mobileos.entity.os.CorteTipoVO;
import wsilva.com.br.mobileos.entity.os.EquipeVO;
import wsilva.com.br.mobileos.entity.os.HidrometroLocalArmazenagemVO;
import wsilva.com.br.mobileos.entity.os.HidrometroLocalInstalacaoVO;
import wsilva.com.br.mobileos.entity.os.HidrometroProtecaoVO;
import wsilva.com.br.mobileos.entity.os.HidrometroSituacaoVO;
import wsilva.com.br.mobileos.entity.os.HidrometroTipoInstalacaoVO;
import wsilva.com.br.mobileos.entity.os.HidrometroTipoSubstituicaoVO;
import wsilva.com.br.mobileos.entity.os.LigacaoAguaSituacaoVO;
import wsilva.com.br.mobileos.entity.os.LigacaoEsgotoSituacaoVO;
import wsilva.com.br.mobileos.entity.os.LocalOcorrenciaVO;
import wsilva.com.br.mobileos.entity.os.LogradouroTipoVO;
import wsilva.com.br.mobileos.entity.os.MaterialVO;
import wsilva.com.br.mobileos.entity.os.MotivoCorteVO;
import wsilva.com.br.mobileos.entity.os.MotivoEncerramentoVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.entity.os.PavimentoTipoVO;
import wsilva.com.br.mobileos.entity.os.RedeCausaVazamentoVO;
import wsilva.com.br.mobileos.entity.os.RedeDiametroVO;
import wsilva.com.br.mobileos.entity.os.RedeMaterialVO;
import wsilva.com.br.mobileos.entity.os.RedeTipoVO;
import wsilva.com.br.mobileos.entity.os.ReligacaoTipoVO;
import wsilva.com.br.mobileos.entity.os.ServicoTipoVO;
import wsilva.com.br.mobileos.entity.os.UsuarioVO;

/**
 * Created by wellingtonasilva on 28/09/15.
 */
public class ImportacaoBusiness {


    public static void importarArquivo(Context context, String filename)
    {
        boolean excluiUsuarios=false;
        boolean excluiRedeTipo=false;
        boolean excluiPavimentoTipo=false;
        boolean excluiServitoTipo=false;
        boolean excluiRedeMaterial=false;
        boolean excluiMaterial=false;
        boolean excluiMotivoEncerramento=false;
        boolean excluiLocalOcorrencia=false;
        boolean excluiRedeDiametro=false;
        boolean excluiRedeCausaVazamento=false;
        boolean excluiAgeneExterno=false;
        boolean excluiOrdemServico=false;
        boolean excluiCorteTipo=false;
        boolean excluiHidrometroLocalArmazenagem=false;
        boolean excluiHidrometroLocalInstalacao=false;
        boolean excluiHidrometroProtecao=false;
        boolean excluiHidrometroSituacao=false;
        boolean excluiHidrometroTipoInstalacao=false;
        boolean excluiHidrometroTipoSubstituicao=false;
        boolean excluiLigacaoAguaSituacao=false;
        boolean excluiLigacaoEsgotoSituacao=false;
        boolean excluiMotivoCorte=false;
        boolean excluiReligacaoTipo=false;
        boolean excluiEquipe=false;
        boolean excluirChecklistGrupo=false;
        boolean excluirChecklistItem=false;
        boolean excluirChecklistOpcao=false;
        boolean excluirInterrupcaoMotivo=false;
        boolean excluirFuncionario=false;
        boolean excluirVeiculos=false;
        boolean excluirLogradouroTipo=false;

        //Ler conteúdo do arquivo
        List<String> lines= Util.lerDadosFromFile(filename, Util.PATH_DOWNLOAD);
        String line;
        int iLinhas=lines.size();

        for (int i=0; i<iLinhas; i++)
        {
            line=lines.get(i);
            if (line.length() >0)
            {
                if (line.contains("wa1"))			//Usuarios
                {
                    UsuarioDAO dao=new UsuarioDAO(context);
                    UsuarioVO vo=dao.obterObject(line.substring(4));

                    if (excluiUsuarios==false)
                    {
                        excluiUsuarios= dao.removerTodos();
                        //Util.iniciarUsuario(context);
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wa3"))		//Rede - Tipo
                {
                    RedeTipoDAO dao=new RedeTipoDAO(context);
                    RedeTipoVO vo=dao.obterObject(line.substring(4));

                    if (excluiRedeTipo==false) {
                        excluiRedeTipo= dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wb8"))		//Pavimento Tipo
                {
                    PavimentoTipoDAO dao=new PavimentoTipoDAO(context);
                    PavimentoTipoVO vo=dao.obterObject(line.substring(4));

                    if (excluiPavimentoTipo==false) {
                        excluiPavimentoTipo= dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wa2"))		//Serviço Tipo
                {
                    ServicoTipoDAO dao=new ServicoTipoDAO(context);
                    ServicoTipoVO vo=dao.obterObject(line.substring(4));

                    if (excluiServitoTipo==false) {
                        excluiServitoTipo= dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wa4"))		//Rede - Material
                {
                    RedeMaterialDAO dao=new RedeMaterialDAO(context);
                    RedeMaterialVO vo=dao.obterObject(line.substring(4));

                    if (excluiRedeMaterial==false) {
                        excluiRedeMaterial= dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wa7"))		//Material
                {
                    MaterialDAO dao=new MaterialDAO(context);
                    MaterialVO vo=dao.obterObject(line.substring(4));

                    if (excluiMaterial==false) {
                        excluiMaterial= dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wa6"))		//Motivo Encerramento
                {
                    MotivoEncerramentoDAO dao=new MotivoEncerramentoDAO(context);
                    MotivoEncerramentoVO vo=dao.obterObject(line.substring(4));

                    if (excluiMotivoEncerramento==false) {
                        excluiMotivoEncerramento= dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wa8"))		//Local Ocorrência
                {
                    LocalOcorrenciaDAO dao=new LocalOcorrenciaDAO(context);
                    LocalOcorrenciaVO vo=dao.obterObject(line.substring(4));

                    if (excluiLocalOcorrencia==false) {
                        excluiLocalOcorrencia= dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wa5"))		//Rede - Diâmetro
                {
                    RedeDiametroDAO dao=new RedeDiametroDAO(context);
                    RedeDiametroVO vo=dao.obterObject(line.substring(4));

                    if (excluiRedeDiametro==false) {
                        excluiRedeDiametro= dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wb4"))		//Rede - Causa Vazamento
                {
                    RedeCausaVazamentoDAO dao=new RedeCausaVazamentoDAO(context);
                    RedeCausaVazamentoVO vo=dao.obterObject(line.substring(4));

                    if (excluiRedeCausaVazamento==false) {
                        excluiRedeCausaVazamento= dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wb2"))		//Agente Externo
                {
                    AgenteExternoDAO dao=new AgenteExternoDAO(context);
                    AgenteExternoVO vo=dao.obterObject(line.substring(4));

                    if (excluiAgeneExterno==false) {
                        excluiAgeneExterno= dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wb3"))		//Ordem de Serviço
                {
                    OrdemServicoDAO dao=new OrdemServicoDAO(context);
                    OrdemServicoVO vo=dao.obterObject(line.substring(4));

                    if (excluiOrdemServico==false) {
                        excluiOrdemServico= dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wd1"))		//Corte Tipo
                {
                    CorteTipoDAO dao=new CorteTipoDAO(context);
                    CorteTipoVO vo=dao.obterObject(line.substring(4));

                    if (excluiCorteTipo==false){
                        excluiCorteTipo=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wd2"))		//HidrometroLocalArmazenagem
                {
                    HidrometroLocalArmazenagemDAO dao=new HidrometroLocalArmazenagemDAO(context);
                    HidrometroLocalArmazenagemVO vo=dao.obterObject(line.substring(4));

                    if (excluiHidrometroLocalArmazenagem==false){
                        excluiHidrometroLocalArmazenagem=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }

                else if (line.contains("wd3"))		//excluiHidrometroLocalInstalacao
                {
                    HidrometroLocalInstalacaoDAO dao=new HidrometroLocalInstalacaoDAO(context);
                    HidrometroLocalInstalacaoVO vo=dao.obterObject(line.substring(4));

                    if (excluiHidrometroLocalInstalacao==false){
                        excluiHidrometroLocalInstalacao=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wd4"))		//HidrometroProtecao
                {
                    HidrometroProtecaoDAO dao=new HidrometroProtecaoDAO(context);
                    HidrometroProtecaoVO vo=dao.obterObject(line.substring(4));

                    if (excluiHidrometroProtecao==false){
                        excluiHidrometroProtecao=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wd5"))		//HidrometroSituacao
                {
                    HidrometroSituacaoDAO dao=new HidrometroSituacaoDAO(context);
                    HidrometroSituacaoVO vo=dao.obterObject(line.substring(4));

                    if (excluiHidrometroSituacao==false){
                        excluiHidrometroSituacao=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wd6"))		//HidrometroTipoInstalacao
                {
                    HidrometroTipoInstalacaoDAO dao=new HidrometroTipoInstalacaoDAO(context);
                    HidrometroTipoInstalacaoVO vo=dao.obterObject(line.substring(4));

                    if (excluiHidrometroTipoInstalacao==false){
                        excluiHidrometroTipoInstalacao=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wd7"))		//HidrometroTipoSubstituicao
                {
                    HidrometroTipoSubstituicaoDAO dao=new HidrometroTipoSubstituicaoDAO(context);
                    HidrometroTipoSubstituicaoVO vo=dao.obterObject(line.substring(4));

                    if (excluiHidrometroTipoSubstituicao==false){
                        excluiHidrometroTipoSubstituicao=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wd8"))		//LigacaoAguaSituacao
                {
                    LigacaoAguaSituacaoDAO dao=new LigacaoAguaSituacaoDAO(context);
                    LigacaoAguaSituacaoVO vo=dao.obterObject(line.substring(4));

                    if (excluiLigacaoAguaSituacao==false){
                        excluiLigacaoAguaSituacao=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wd9"))		//LigacaoEsgotoSituacao
                {
                    LigacaoEsgotoSituacaoDAO dao=new LigacaoEsgotoSituacaoDAO(context);
                    LigacaoEsgotoSituacaoVO vo=dao.obterObject(line.substring(4));

                    if (excluiLigacaoEsgotoSituacao==false){
                        excluiLigacaoEsgotoSituacao=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("we1"))		//MotivoCorte
                {
                    MotivoCorteDAO dao=new MotivoCorteDAO(context);
                    MotivoCorteVO vo=dao.obterObject(line.substring(4));

                    if (excluiMotivoCorte==false){
                        excluiMotivoCorte=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("we2"))		//ReligacaoTipo
                {
                    ReligacaoTipoDAO dao=new ReligacaoTipoDAO(context);
                    ReligacaoTipoVO vo=dao.obterObject(line.substring(4));

                    if (excluiReligacaoTipo==false){
                        excluiReligacaoTipo=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wa9"))		//Equipe
                {
                    EquipeDAO dao=new EquipeDAO(context);
                    EquipeVO vo=dao.obterObject(line.substring(4));

                    if (excluiEquipe==false){
                        excluiEquipe=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wy1"))		//Checklist - Grupo
                {
                    ChecklistGrupoDAO dao=new ChecklistGrupoDAO(context);
                    ChecklistGrupoVO vo=dao.obterObject(line.substring(4));

                    if (excluirChecklistGrupo==false){
                        excluirChecklistGrupo=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wy2"))		//Checklist - Item
                {
                    ChecklistItemDAO dao=new ChecklistItemDAO(context);
                    ChecklistItemVO vo=dao.obterObject(line.substring(4));

                    if (excluirChecklistItem==false){
                        excluirChecklistItem=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wy3"))		//Checklist - Op��o
                {
                    ChecklistOpcaoDAO dao=new ChecklistOpcaoDAO(context);
                    ChecklistOpcaoVO vo=dao.obterObject(line.substring(4));

                    if (excluirChecklistOpcao==false){
                        excluirChecklistOpcao=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wy4"))		//Interrupcao Motivo
                {
                    InterrupcaoMotivoDAO dao=new InterrupcaoMotivoDAO(context);
                    InterrupcaoMotivoVO vo=dao.obterObject(line.substring(4));

                    if (excluirInterrupcaoMotivo==false){
                        excluirInterrupcaoMotivo=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wy5"))		//Funcionario
                {
                    FuncionariosDAO dao=new FuncionariosDAO(context);
                    FuncionariosVO vo=dao.obterObject(line.substring(4));

                    if (excluirFuncionario==false){
                        excluirFuncionario=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wy6"))		//Ve�culos
                {
                    VeiculosDAO dao=new VeiculosDAO(context);
                    VeiculosVO vo=dao.obterObject(line.substring(4));

                    if (excluirVeiculos==false){
                        excluirVeiculos=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
                else if (line.contains("wy7"))		//Logradouro Tipp
                {
                    LogradouroTipoDAO dao=new LogradouroTipoDAO(context);
                    LogradouroTipoVO vo=dao.obterObject(line.substring(4));

                    if (excluirLogradouroTipo==false){
                        excluirLogradouroTipo=dao.removerTodos();
                    }
                    dao.inserir(vo);
                }
            }
        }
    }
}
