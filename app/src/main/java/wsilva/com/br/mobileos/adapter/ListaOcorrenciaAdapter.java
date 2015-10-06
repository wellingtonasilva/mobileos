package wsilva.com.br.mobileos.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.entity.ocorrencia.InterrupcaoVO;

public class ListaOcorrenciaAdapter extends ArrayAdapter<InterrupcaoVO>
{

	private List<InterrupcaoVO> items;
	private Resources resources;
	int resource;

	public ListaOcorrenciaAdapter(Context context,
								  int resourceId,
								  List<InterrupcaoVO> items)
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
			v=vi.inflate(R.layout.lay_ocorrencia_list_row, null);

			viewHolder = new ViewHolder();
			viewHolder.linha1 = (TextView) v.findViewById(R.id.lblLinha1);
			viewHolder.linha2 = (TextView) v.findViewById(R.id.lblLinha2);
			viewHolder.linha3  = (TextView) v.findViewById(R.id.lblLinha3);
			viewHolder.linha4  = (TextView) v.findViewById(R.id.lblLinha4);

			v.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder) v.getTag();
		}

		InterrupcaoVO vo=items.get(position);
		if (vo!=null)
		{
			if (vo.dataFim==null || vo.dataFim.length()==0)
			{
				v.setBackgroundResource(R.color.holo_orange_dark);
			} else {
				v.setBackgroundResource(R.color.holo_green_light);
			}

			//Motivo
			if (viewHolder.linha1 != null) {
				viewHolder.linha1.setText(vo.descricaoInterrupcaoMotivo);
			}
			//Data/Hora Inicio
			if (viewHolder.linha2 != null) {
				viewHolder.linha2.setText(this.resources.getString(R.string.lbl_msg_data_hora_inicio)
						+ ": " + vo.dataInicio + " " + vo.horaInicio);
			}
			//Data/Hora Fim
			if (viewHolder.linha3 != null) {
				viewHolder.linha3.setText(this.resources.getString(R.string.lbl_msg_data_hora_fim)
						+ ": " + (vo.dataFim==null ? "" : vo.dataFim + " " + vo.horaFim));
			}
			//Observação
			if (viewHolder.linha4 != null) {
				viewHolder.linha4.setText(this.resources.getString(R.string.lbl_msg_observacao_inicio)
						+ ": " + (vo.observacaoInicio==null ? "" :vo.observacaoInicio));
			}
		}
	
		return v;
	}


	static class ViewHolder
	{
		TextView linha1;
		TextView linha2;
		TextView linha3;
		TextView linha4;
	}
}
