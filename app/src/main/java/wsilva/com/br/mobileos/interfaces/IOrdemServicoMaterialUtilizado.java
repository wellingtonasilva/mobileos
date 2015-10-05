package wsilva.com.br.mobileos.interfaces;

import wsilva.com.br.mobileos.entity.os.MaterialUtilizadoVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;

public interface IOrdemServicoMaterialUtilizado
{
    public void onSalvar(OrdemServicoVO ordemServico, MaterialUtilizadoVO materialUtilizado);
}
