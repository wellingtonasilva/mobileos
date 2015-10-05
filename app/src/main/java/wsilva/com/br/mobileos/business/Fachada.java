package wsilva.com.br.mobileos.business;

import android.content.Context;

import wsilva.com.br.mobileos.dao.os.FotoDAO;
import wsilva.com.br.mobileos.dao.os.MaterialUtilizadoDAO;
import wsilva.com.br.mobileos.dao.os.OrdemServicoDAO;
import wsilva.com.br.mobileos.dao.os.ValaDAO;
import wsilva.com.br.mobileos.entity.os.FotoVO;
import wsilva.com.br.mobileos.entity.os.MaterialUtilizadoVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;
import wsilva.com.br.mobileos.fragment.OrdemServicoDadosRedeFragment;

/**
 * Created by wellingtonasilva on 27/09/15.
 */
public class Fachada {

    public static boolean iniciarOrdemServico(Context context, OrdemServicoVO servicoVO)
    {
        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO(context);
        return ordemServicoDAO.atualizar(servicoVO);
    }

    public static boolean encerrarOrdemServico(Context context, OrdemServicoVO servicoVO)
    {
        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO(context);
        return ordemServicoDAO.atualizar(servicoVO);
    }

    public static boolean salvarOrdemServicoDadosRede(Context context, OrdemServicoVO servicoVO)
    {
        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO(context);
        return ordemServicoDAO.atualizar(servicoVO);
    }

    public static boolean inserirVala(Context context, OrdemServicoVO ordemServico,
                                      ValaVO vala)
    {
        ValaDAO valaDAO = new ValaDAO(context);
        return valaDAO.inserir(vala) > 0;
    }

    public static boolean inserirMaterialUtilizado(Context context, OrdemServicoVO ordemServico,
                                      MaterialUtilizadoVO materialUtilizado)
    {
        MaterialUtilizadoDAO materialUtilizadoDAO = new MaterialUtilizadoDAO(context);
        return materialUtilizadoDAO.inserir(materialUtilizado) > 0;
    }

    public static boolean inserirFoto(Context context, OrdemServicoVO servicoVO, FotoVO foto)
    {
        FotoDAO fotoDAO = new FotoDAO(context);
        return fotoDAO.inserir(foto) > 0;
    }
}
