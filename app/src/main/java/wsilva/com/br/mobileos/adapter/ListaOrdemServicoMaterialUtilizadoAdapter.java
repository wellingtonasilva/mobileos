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
import wsilva.com.br.mobileos.entity.os.MaterialUtilizadoVO;

public class ListaOrdemServicoMaterialUtilizadoAdapter extends ArrayAdapter<MaterialUtilizadoVO>
{

	private List<MaterialUtilizadoVO> items;
	private Resources resources;
	int resource;

	public ListaOrdemServicoMaterialUtilizadoAdapter(Context context,
													 int resourceId,
													 List<MaterialUtilizadoVO> items)
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
			viewHolder.descricaoMaterial = (TextView) v.findViewById(R.id.lblSubtitulo1);
			viewHolder.quantidade  = (TextView) v.findViewById(R.id.lblSubtitulo2);

			v.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder) v.getTag();
		}

		MaterialUtilizadoVO vo=items.get(position);
		if (vo!=null)
		{
			viewHolder.numeroOS.setText(String.valueOf(vo.numeroOS));
			viewHolder.descricaoMaterial.setText(resources.getText(R.string.lbl_material_utilizado_descricao).toString()
					+ ": " +  String.valueOf(vo.descricaoMateriall));
			viewHolder.quantidade.setText(resources.getText(R.string.lbl_material_utilizado_quantidade).toString()
					+ ": " + String.valueOf(vo.quantidade));
		}
	
		return v;
	}


	static class ViewHolder
	{
		TextView numeroOS;
		TextView descricaoMaterial;
		TextView quantidade;
	}
}
