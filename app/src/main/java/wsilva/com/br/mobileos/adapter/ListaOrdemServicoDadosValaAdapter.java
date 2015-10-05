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
import wsilva.com.br.mobileos.entity.os.ValaVO;

public class ListaOrdemServicoDadosValaAdapter extends ArrayAdapter<ValaVO>
{

	private List<ValaVO> items;
	private Resources resources;
	int resource;

	public ListaOrdemServicoDadosValaAdapter(Context context,
											 int resourceId,
											 List<ValaVO> items)
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
			v=vi.inflate(R.layout.lay_ordem_servico_dados_vala_list_row, null);

			viewHolder = new ViewHolder();
			viewHolder.numeroOS = (TextView) v.findViewById(R.id.lblTitulo);
			viewHolder.numeroVala = (TextView) v.findViewById(R.id.lblSubtitulo1);
			viewHolder.largura  = (TextView) v.findViewById(R.id.lblSubtitulo2);
			viewHolder.comprimento  = (TextView) v.findViewById(R.id.lblSubtitulo3);
			viewHolder.profundidade  = (TextView) v.findViewById(R.id.lblSubtitulo4);

			v.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder) v.getTag();
		}

		ValaVO vo=items.get(position);
		if (vo!=null)
		{
			viewHolder.numeroOS.setText(resources.getText(R.string.lbl_interrupcao_numero_os).toString()
					+ " " + String.valueOf(vo.numeroOS));
			viewHolder.numeroVala.setText(resources.getText(R.string.lbl_dados_vala_numero_vala).toString()
					+ ": " +  String.valueOf(vo.numeroVala));
			viewHolder.largura.setText(resources.getText(R.string.lbl_dados_vala_numero_vala).toString()
					+ ": " + String.valueOf(vo.largura));
			viewHolder.comprimento.setText(resources.getText(R.string.lbl_dados_vala_numero_vala).toString()
					+ ": " + String.valueOf(vo.largura));
			viewHolder.profundidade.setText(resources.getText(R.string.lbl_dados_vala_numero_vala).toString()
					+ ": " + String.valueOf(vo.profundidade));
		}
	
		return v;
	}


	static class ViewHolder
	{
		TextView numeroOS;
		TextView numeroVala;
		TextView largura;
		TextView comprimento;
		TextView profundidade;
	}
}
