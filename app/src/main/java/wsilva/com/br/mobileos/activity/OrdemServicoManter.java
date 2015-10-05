package wsilva.com.br.mobileos.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import com.viewpagerindicator.LinePageIndicator;
import com.viewpagerindicator.PageIndicator;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.business.Fachada;
import wsilva.com.br.mobileos.core.util.Util;
import wsilva.com.br.mobileos.dao.os.FotoDAO;
import wsilva.com.br.mobileos.entity.os.FotoVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.fragment.OrdemServicoDadosRedeFragment;
import wsilva.com.br.mobileos.fragment.OrdemServicoDadosValaFragment;
import wsilva.com.br.mobileos.interfaces.IOrdemServicoDadosRede;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoManter extends ActionBarActivity implements ActionBar.TabListener
{
    static final int REQUEST_TAKE_PHOTO = 1;
    OrdemServicoVO ordemServico;
    OrdemServicoPagerAdapter pagerAdapter;
    ViewPager viewPager;
    PageIndicator mIndicator;

    //Save a file: path for use with ACTION_VIEW intents
    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.lay_template_viewpager_indicator);

        init(savedInstanceState);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");
            //mImageView.setImageBitmap(imageBitmap);
            //Util.galleryAddPic(OrdemServicoManter.this, mCurrentPhotoPath);
            doSalvarFoto(mCurrentPhotoPath);
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
    {
        viewPager.setCurrentItem(tab.getPosition());
        if (tab.getPosition() == OrdemServicoPagerAdapter.TAB_DADOS_REDE)
        {
            OrdemServicoDadosRedeFragment fragment =  (OrdemServicoDadosRedeFragment)
                    getSupportFragmentManager().findFragmentById(R.id.pager);
            if (fragment!=null)
            {
                fragment.setListener(ordemServicoDadosRede);
            }
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
    {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
    {

    }

    IOrdemServicoDadosRede ordemServicoDadosRede = new IOrdemServicoDadosRede()
    {
        @Override
        public void onSalvar(OrdemServicoVO ordemServico)
        {
            Log.w("$$$", "OrdemServicoManter>ordemServicoDadosRede>onSalvar");
            if (Fachada.salvarOrdemServicoDadosRede(OrdemServicoManter.this, ordemServico))
            {
                Util.createAlertDialog(OrdemServicoManter.this,
                        getResources().getText(R.string.app_processo_realizado_sucesso).toString(),
                        R.string.app_name,
                        R.string.app_ok, R.string.app_cancelar,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        }, null);
            }
        }
    };

    protected void init(Bundle savedInstanceState)
    {
        if (savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if (extras != null)
            {
                ordemServico = (OrdemServicoVO) extras.getSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM);
            }
        } else
        {
            ordemServico = (OrdemServicoVO) savedInstanceState.getSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM);
        }

        //Cria lista de fragmentos
        pagerAdapter = new OrdemServicoPagerAdapter(getSupportFragmentManager(), getResources(),
                ordemServico);

        //Configura ActionBar
        //final ActionBar actionBar = getSupportActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        //actionBar.setHomeButtonEnabled(false);
        //actionBar.setDisplayShowTitleEnabled(false);

        // Specify that we will be displaying tabs in the action bar.
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
        {
            @Override
            public void onPageSelected(int position)
            {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                //actionBar.setSelectedNavigationItem(position);
            }
        });

        mIndicator = (LinePageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(viewPager);
        mIndicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
        {
            @Override
            public void onPageSelected(int position)
            {
                alterarSubtitulo(position);
                Button btnNovo = (Button) findViewById(R.id.btnNovo);
                Button btnSalvar = (Button) findViewById(R.id.btnSalvar);

                switch (position)
                {
                    case OrdemServicoPagerAdapter.TAB_DADOS_VALA:
                        habilitarBotoes(new int[]{R.id.btnSalvar}, new int[]{R.id.btnNovo});
                        btnNovo.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                showDadosVala();
                            }
                        });
                        break;
                    case OrdemServicoPagerAdapter.TAB_MATERIAL_UTILIZADO:
                        habilitarBotoes(new int[]{R.id.btnSalvar}, new int[]{R.id.btnNovo});
                        btnNovo.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                showMaterialUtilizado();
                            }
                        });
                        break;
                    case OrdemServicoPagerAdapter.TAB_DADOS_REDE:
                        habilitarBotoes(new int[]{R.id.btnNovo}, new int[]{R.id.btnSalvar});
                        btnSalvar.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                doSalvarDadosRede();
                            }
                        });
                        break;
                    case OrdemServicoPagerAdapter.TAB_FOTOS:
                        habilitarBotoes(new int[]{R.id.btnSalvar}, new int[]{R.id.btnNovo});
                        btnNovo.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                dispatchTakePictureIntent();
                            }
                        });
                        break;
                    default:
                        habilitarBotoes(new int[]{R.id.btnNovo},new int[]{R.id.btnSalvar});
                }
            }
        });

        //Titulo das Tabs
        /*
        for (int i = 0; i < pagerAdapter.getCount(); i++)
        {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab
            	(	actionBar.newTab()
                            .setText(pagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        */

        ImageView btnPopupMenu = (ImageView) findViewById(R.id.btnPopupMenu);
        btnPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(OrdemServicoManter.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup_ordem_servico, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        if (item.getItemId() == R.id.mnuOrdemServico)
                        {
                            Intent intent = new Intent(OrdemServicoManter.this, OrdemServicoExecucaoInicio.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM, ordemServico);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else if (item.getItemId() == R.id.mnuOrdemServicoEncerramento)
                        {
                            Intent intent = new Intent(OrdemServicoManter.this, OrdemServicoExecucaoEncerramento.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM, ordemServico);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else if (item.getItemId() == R.id.mnuOrdemServicoVala)
                        {
                            Intent intent = new Intent(OrdemServicoManter.this, OrdemServicoExecucaoEncerramento.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM, ordemServico);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else if (item.getItemId() == R.id.mnuOrdemServicoMaterialUtilizado)
                        {
                            Intent intent = new Intent(OrdemServicoManter.this, OrdemServicoMaterialUtilizado.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM, ordemServico);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else if (item.getItemId() == R.id.mnuOrdemServicoCorte)
                        {
                            Intent intent = new Intent(OrdemServicoManter.this, OrdemServicoCorte.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM, ordemServico);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else if (item.getItemId() == R.id.mnuOrdemServicoInstalacaoHM)
                        {
                            Intent intent = new Intent(OrdemServicoManter.this, OrdemServicoInstalacaoHM.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM, ordemServico);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else if (item.getItemId() == R.id.mnuOrdemServicoSubstituicaoHM)
                        {
                            Intent intent = new Intent(OrdemServicoManter.this, OrdemServicoSubstituicaoHM.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM, ordemServico);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else if (item.getItemId() == R.id.mnuOrdemServicoLigacaoNova)
                        {
                            Intent intent = new Intent(OrdemServicoManter.this, OrdemServicoLigacaoNova.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM, ordemServico);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else if (item.getItemId() == R.id.mnuOrdemServicoReligacao)
                        {
                            Intent intent = new Intent(OrdemServicoManter.this, OrdemServicoReligacao.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM, ordemServico);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else if (item.getItemId() == R.id.mnuOrdemServicoRemanejamento)
                        {
                            Intent intent = new Intent(OrdemServicoManter.this, OrdemServicoRemanejamento.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM, ordemServico);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }



                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        btnPopupMenu.setVisibility(View.VISIBLE);

        //Alerar titulo
        alterarTitulo();

        //Alterar subtitulo
        alterarSubtitulo(OrdemServicoPagerAdapter.TAB_ORDEM_SERVICO);

        Button btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                doVoltar();
            }
        });
    }

    protected void doTabChange(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.mnuOrdemServico:
                viewPager.setCurrentItem(OrdemServicoPagerAdapter.TAB_ORDEM_SERVICO);
        }
    }

    protected void alterarSubtitulo(int position)
    {
        TextView lblSubtitulo = (TextView) findViewById(R.id.lblSubtitulo);
        lblSubtitulo.setText(pagerAdapter.getPageTitle(position));
    }

    protected void alterarTitulo()
    {
        TextView lblTitulo = (TextView) findViewById(R.id.lblTitulo);
        lblTitulo.setText(getResources().getText(R.string.app_ordem_servico));
    }

    protected void doVoltar() {
        finish();
    }

    protected void habilitarBotoes(int[] desabilitar, int[] habilitar)
    {
        //Servir como base para layout
        Button btnVoltar = (Button) findViewById(R.id.btnVoltar);

        if (desabilitar!=null)
        {
            for (int i=0; i<desabilitar.length; i++)
            {
                Button button = (Button) findViewById(desabilitar[i]);
                button.setVisibility(View.GONE);
            }
        }

        if (habilitar!=null)
        {
            for (int i=0; i<habilitar.length; i++)
            {
                Button button = (Button) findViewById(habilitar[i]);
                button.setVisibility(View.VISIBLE);
                button.setLayoutParams(btnVoltar.getLayoutParams());
            }
        }
    }

    protected void showDadosVala()
    {
        Intent intent = new Intent(OrdemServicoManter.this, OrdemServicoDadosVala.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM, ordemServico);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void showMaterialUtilizado()
    {
        Intent intent = new Intent(OrdemServicoManter.this, OrdemServicoMaterialUtilizado.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(OrdemServicoPagerAdapter.TEMPLATE_SELECTED_ITEM, ordemServico);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void doSalvarDadosRede()
    {
        OrdemServicoDadosRedeFragment fragment =  (OrdemServicoDadosRedeFragment)
                getSupportFragmentManager().getFragments().get(OrdemServicoPagerAdapter.TAB_DADOS_REDE);
        fragment.setListener(ordemServicoDadosRede);
        if (fragment!=null)
        {
            fragment.salvar();
        }
    }

    protected void doSalvarFoto(String filename)
    {
        FotoVO vo = new FotoVO();
        FotoDAO dao = new FotoDAO(OrdemServicoManter.this);
        vo.numeroOS = ordemServico.numeroOS;
        vo.nomeFoto = "Foto #" + String.valueOf(dao.obterNumeroFoto(ordemServico.numeroOS));
        vo.descricaoFoto = vo.nomeFoto;
        vo.dataFoto = new Date();
        vo.horaFoto = Util.dateToString("HH:mm", new Date());
        vo.filename = filename;

        Fachada.inserirFoto(OrdemServicoManter.this, ordemServico, vo);
    }

    private void dispatchTakePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            // Create the File where the photo should go
            File photoFile = null;
            try
            {
                photoFile = Util.createImageFile();
                mCurrentPhotoPath = "file:" + photoFile.getAbsolutePath();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null)
            {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

}
