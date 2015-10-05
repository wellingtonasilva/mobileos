package wsilva.com.br.mobileos.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.entity.os.FotoVO;

public class ListaOrdemServicoFotosAdapter extends ArrayAdapter<FotoVO> {

	private ArrayList<FotoVO> items;
	private Context context;
	private Resources resources;

	public ListaOrdemServicoFotosAdapter(Context context,
			int resourceId,
			List<FotoVO> items)
	{
		super(context, resourceId, items);
		this.items= (ArrayList<FotoVO>) items;
		this.context = context;
		resources = context.getResources();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View v = convertView;
		if (v==null)
		{
			LayoutInflater vi=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v=vi.inflate(R.layout.lay_ordem_servico_foto_list_row, null);
		}

		FotoVO vo =items.get(position);
		if (vo !=null)
		{
			//Titulo
			TextView lblTitulo = (TextView) v.findViewById(R.id.lblTitulo);
			lblTitulo.setText(Util.dateToString("dd/MM/yyyy", vo.dataFoto));
			//Subtitulo
			TextView lblSubtitulo1 = (TextView) v.findViewById(R.id.lblSubtitulo1);
			lblSubtitulo1.setText(resources.getText(R.string.app_nome_foto)
					+ ": " + (vo.nomeFoto == null ? "" : vo.nomeFoto));

			//Subtitulo
			TextView lblSubtitulo2 = (TextView) v.findViewById(R.id.lblSubtitulo2);
			lblSubtitulo2.setText(resources.getText(R.string.app_descricao)
					+ ": " + (vo.descricaoFoto == null ? "" : vo.descricaoFoto));

			ImageView imgFoto = (ImageView) v.findViewById(R.id.imgFoto);
			Bitmap bitmap = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(vo.filename.replace("file:", "")),
					120, 130);


			//ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(vo.image);
			//Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);

			if (bitmap!=null)
			{
				imgFoto.setImageBitmap(bitmap);
			}
		}
	
		return v;
	}
}
