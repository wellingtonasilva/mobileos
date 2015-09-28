package wsilva.com.br.mobileos.adapter;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;

public class ListaOrdemServicoAdapter extends ArrayAdapter<OrdemServicoVO>
{

	private List<OrdemServicoVO> items;
	private Resources resources;
	int resource;
	
	public ListaOrdemServicoAdapter(Context context,
			int resourceId,
			List<OrdemServicoVO> items)
	{
		super(context, resourceId, items);
		this.items=items;
		resources = context.getResources();
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View v = convertView;
		ViewHolder viewHolder;

		if (v==null)
		{
			LayoutInflater vi=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v=vi.inflate(R.layout.lay_ordem_servico_list_row, null);

			viewHolder = new ViewHolder();
			viewHolder.descricaoServico = (TextView) v.findViewById(R.id.lblServico);
			viewHolder.dataGeracaoOS = (TextView) v.findViewById(R.id.lblLinha1);
			viewHolder.logradouro  = (TextView) v.findViewById(R.id.lblLinha2);
			viewHolder.bairro  = (TextView) v.findViewById(R.id.lblLinha3);
			viewHolder.numeroImovel  = (TextView) v.findViewById(R.id.lblLinha3);
			viewHolder.observacaoRA = (TextView) v.findViewById(R.id.lblLinha4);
			viewHolder.numeroHidrometro  = (TextView) v.findViewById(R.id.lblLinha5);
			viewHolder.numeroZona = (TextView) v.findViewById(R.id.lblLinha6);
			viewHolder.statusOS = (TextView) v.findViewById(R.id.lblStatusOS);
			viewHolder.numeroRA = (TextView) v.findViewById(R.id.lblNumeroRA);
			viewHolder.numeroOS = (TextView) v.findViewById(R.id.lblNumeroOS);

			v.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder) v.getTag();
		}

		OrdemServicoVO os=items.get(position);
		if (os!=null)
		{
			if (os.situacaoOS == Util.OS_ID_STATUS_PENDENTE)					//Pendente
			{
				v.setBackgroundResource(R.color.background_holo_light);
				viewHolder.statusOS.setText(os.descricaoSituacaoOS);
				
			} else if (os.situacaoOS==Util.OS_ID_STATUS_ENCERRADA) {			//Encerrada
				v.setBackgroundResource(R.color.holo_green_light);
				viewHolder.statusOS.setText(os.descricaoSituacaoOS);
				
			} else if (os.situacaoOS ==Util.OS_ID_STATUS_CANCELADA) {			//Cancelada
				v.setBackgroundResource(R.color.holo_red_light);
				viewHolder.statusOS.setText(os.descricaoSituacaoOS);
				
			} else if (os.situacaoOS==Util.OS_ID_STATUS_EM_EXECUCAO) {			//Em Execução
				v.setBackgroundResource(R.color.holo_orange_dark);
				viewHolder.statusOS.setText(os.descricaoSituacaoOS);
			} else {
				v.setBackgroundResource(R.color.background_holo_light);
				viewHolder.statusOS.setText(os.descricaoSituacaoOS);
			}

			//Número da RA
			viewHolder.numeroRA.setText(resources.getText(R.string.app_ra).toString()
					+ " " + String.valueOf(os.numeroRA));
			//Número da OS
			viewHolder.numeroOS.setText(resources.getText(R.string.app_os).toString()
					+ " " + String.valueOf(os.numeroOS));
			//Descrição do Serviço
			viewHolder.descricaoServico.setText(os.descricaoTipoServico);
			//Data/Hora da Geração da OS
			viewHolder.dataGeracaoOS.setText(resources.getText(R.string.app_data_geracao_os).toString()
					+ ": " + (os.dataGeracaoOS != null ? Util.dateToString("dd/MM/yyyy", os.dataGeracaoOS) : ""));
			//Endereço
			viewHolder.logradouro.setText(resources.getText(R.string.app_logradouro).toString()
					+ ": " + (os.logradouro == null ? "" : os.logradouro));
			//Bairro
			viewHolder.bairro.setText(resources.getText(R.string.app_bairro).toString()
					+ ": " + os.bairro);
			//Número do Imóvel
			viewHolder.numeroImovel.setText(resources.getText(R.string.app_numero_imovel).toString()
					+ ": " + (os.numeroImovel == null ? "" : os.numeroImovel));
			//Observação RA
			viewHolder.observacaoRA.setText(resources.getText(R.string.app_numero_imovel).toString()
					+ ": " + (os.observacaoRA ==null ? "" : os.observacaoRA));
			//Número do Hidrômetro ou Data do Encerramento
			if (os.situacaoOS != Util.OS_ID_STATUS_ENCERRADA)
			{
				viewHolder.numeroHidrometro.setText(resources.getText(R.string.app_numero_hidrometro).toString()
						+ ": " +  (os.numeroHidrometro == null ? "" : os.numeroHidrometro));
			} else
			{
				viewHolder.numeroHidrometro.setText(resources.getText(R.string.app_data_encerramento).toString()
						+ ": " + (os.dataEncerramentoOS!=null ? Util.dateToString("dd/MM/yyyy",os.dataEncerramentoOS) : "")
						+ " " + resources.getText(R.string.app_hora).toString()
						+ ": " + (os.horaEncerramentoOS!=null ? os.horaEncerramentoOS : ""));
			}
			//Zona, Quadra e Lote
			viewHolder.numeroZona.setText(
					resources.getText(R.string.app_data_encerramento).toString() + ": " + String.valueOf(os.idSetorComercial)
						+ resources.getText(R.string.app_quadra).toString() + ": " + (os.numeroQuadra == null ? "" : os.numeroQuadra)
						+ resources.getText(R.string.app_quadra).toString() + ": " + String.valueOf(os.numeroLote));
		}
	
		return v;
	}


	static class ViewHolder
	{
		TextView descricaoServico;
		TextView dataGeracaoOS;
		TextView logradouro;
		TextView bairro;
		TextView numeroImovel;
		TextView observacaoRA;
		TextView numeroHidrometro;
		TextView numeroZona;
		TextView statusOS;
		TextView numeroRA;
		TextView numeroOS;
	}
}
