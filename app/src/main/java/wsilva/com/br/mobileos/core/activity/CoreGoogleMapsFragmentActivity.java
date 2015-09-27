package wsilva.com.br.mobileos.core.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import wsilva.com.br.mobileos.R;

public class CoreGoogleMapsFragmentActivity extends CoreFragmentActivity implements OnMapReadyCallback
{
    // Might be null if Google Play services APK is not available.
    protected GoogleMap mMap;

    protected final void init()
    {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null)
        {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null)
            {
                setUpMap();
            }
        }
    }

    protected void setUpMap()
    {
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
    }

    protected void addMarkAndZoom(String title, LatLng latLng, float zoom)
    {
        if (mMap!=null)
        {
            LatLng latLngSamsung = new LatLng(-3.101612, -59.944217);
            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(title));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)
                    .zoom(zoom)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    protected void addMark(String title, LatLng position, int imageResource, String snippet)
    {
        View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.lay_template_maps_custom_marker, null);
        TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
        ImageView imgIcon = (ImageView) marker.findViewById(R.id.imgIcon);
        numTxt.setText(title);

        //Image
        imgIcon.setImageResource(imageResource);

        //Marker customMarker
        mMap.addMarker(new MarkerOptions()
                .position(position)
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker))));
    }

    protected Bitmap createDrawableFromView(Context context, View view)
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);

        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels,
                displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
                view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }


    protected void addIcon(IconGenerator iconFactory, String text, LatLng position)
    {
        MarkerOptions markerOptions = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(text))).position(position)
                .title(text)
                .anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());

        mMap.addMarker(markerOptions);
    }

}
