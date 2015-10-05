package wsilva.com.br.mobileos.interfaces;

import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.entity.os.ValaVO;

public interface IOrdemServicoDadosVala
{
    public void onSalvar(OrdemServicoVO ordemServico, ValaVO vala);
}
