package wsilva.com.br.mobileos.constants;

/**
 * Created by wellingtonasilva on 28/11/15.
 */
public class MobileOSOperacao
{
    //Ordem de serviço Padráo
    public static int[] EFETUAR_OS_PADRAO = {0};

    //Reaterro de Vala
    public static int[] EFETUAR_OS_REATERRO_VALA = {0};

    //Recomposicao de calcada
    public static int[] EFETUAR_OS_RECOMPOSICAO_CALCADA = {0};

    //Recomposicao asfaltica (manutencao)
    public static int[] EFETUAR_OS_RECOMPOSICAO_ASFALTICA_MANUTENCAO = {0};

    //Recomposicao asfaltica (ligacao nova)
    public static int[] EFETUAR_OS_RECOMPOSICAO_ASFALTICA_LIGACAO_NOVA = {0};

    //Ligacao Nova
    public static int[] EFETUAR_OS_LIGACAO_NOVA = {0};

    //Vazamento na rede
    public static int[] EFETUAR_OS_VAZAMENTO_REDE= {0};

    //Vazamento no cavalete
    public static int[] EFETUAR_OS_VAZAMENTO_CAVALETE = {0};

    //Vazamento no ramal
    public static int[] EFETUAR_OS_VAZAMENTO_RAMAL = {0};

    //Retirada de Entulho
    public static int[] EFETUAR_OS_RETIRADA_ENTULHO = {0};

    //Corte
    public static int[] EFETUAR_OS_CORTE = {GSANOperacao.OPERACAO_CORTE_ADMINISTRATIVO_LIGACAO_AGUA_EFETUAR,
            GSANOperacao.OPERACAO_CORTE_LIGACAO_AGUA_EFETUAR, GSANOperacao.OPERACAO_CORTE_LIGACAO_AGUA_EFETUAR_INT,
            GSANOperacao.OPERACAO_CORTE_ADMINISTRATIVO_LIGACAO_AGUA_EFETUAR_INT};

    //Instalacao de Hidrometro
    public static int[] EFETUAR_OS_INSTALACAO_HIDROMETRO = {GSANOperacao.OPERACAO_INSTALACAO_HIDROMETRO_EFETUAR,
            GSANOperacao.OPERACAO_INSTALACAO_HIDROMETRO_EFETUAR_INT};

    //Supressao
    public static int[] EFETUAR_OS_SUPRESSAO = {GSANOperacao.OPERACAO_SUPRESSAO_LIGACAO_AGUA_EFETUAR,
            GSANOperacao.OPERACAO_SUPRESSAO_LIGACAO_AGUA_EFETUAR_INT};

    //Substituicao de Hidrometro
    public static int[] EFETUAR_OS_SUBSTITUICAO_HIDROMETRO = {GSANOperacao.OPERACAO_SUBSTITUICAO_HIDROMETRO_EFETUAR,
            GSANOperacao.OPERACAO_SUBSTITUICAO_HIDROMETRO_EFETUAR_INT};

    //Religacao
    public static int[] EFETUAR_OS_RELIGACAO = {GSANOperacao.OPERACAO_RELIGACAO_AGUA_EFETUAR,
            GSANOperacao.OPERACAO_RELIGACAO_AGUA_EFETUAR_INT, GSANOperacao.OPERACAO_EFETUAR_RELIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO,
            GSANOperacao.OPERACAO_EFETUAR_RELIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO_INT};

    //Remanejamento
    public static int[] EFETUAR_OS_REMANEJAMENTO = {GSANOperacao.OPERACAO_REMANEJAMENTO_HIDROMETRO_EFETUAR,
            GSANOperacao.OPERACAO_REMANEJAMENTO_HIDROMETRO_EFETUAR_INT};
}
