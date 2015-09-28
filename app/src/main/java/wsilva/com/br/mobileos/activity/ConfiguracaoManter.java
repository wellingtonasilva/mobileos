package wsilva.com.br.mobileos.activity;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.viewpagerindicator.LinePageIndicator;
import com.viewpagerindicator.PageIndicator;

import wsilva.com.br.mobileos.R;
import wsilva.com.br.mobileos.core.activity.CoreAppCompactActivity;
import wsilva.com.br.mobileos.entity.os.ConfiguracoesVO;
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.pageadapter.ConfiguracaoPagerAdapter;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;
import wsilva.com.br.mobileos.services.ImportacaoService;

public class ConfiguracaoManter extends CoreAppCompactActivity
{

    ConfiguracoesVO configuracoes;
    ConfiguracaoPagerAdapter pagerAdapter;
    ViewPager viewPager;
    PageIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.lay_template_fragment);

        init(savedInstanceState);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    public void onCoreAcaoInicio()
    {
        super.onCoreAcaoInicio();
        if (progressDialog!=null) progressDialog.show();
    }

    @Override
    public void onCoreAcaoFim()
    {
        super.onCoreAcaoFim();
        if (progressDialog!=null) progressDialog.dismiss();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
    {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
    {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
    {

    }

    protected void init(Bundle savedInstanceState)
    {
        if (savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if (extras != null)
            {
                configuracoes = (ConfiguracoesVO) extras.getSerializable(ConfiguracaoPagerAdapter.KEY_CONFIGURACAO);
            }
        } else
        {
            configuracoes = (ConfiguracoesVO) savedInstanceState.getSerializable(ConfiguracaoPagerAdapter.KEY_CONFIGURACAO);
        }

        //Cria lista de fragmentos
        pagerAdapter = new ConfiguracaoPagerAdapter(getSupportFragmentManager(), getResources(),
                configuracoes);

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
                PopupMenu popupMenu = new PopupMenu(ConfiguracaoManter.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup_ordem_servico, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        doTabChange(item);
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
        alterarSubtitulo(ConfiguracaoPagerAdapter.TAB_CONFIGURACAO);

        Button btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doVoltar();
            }
        });

        Button btnRefresh = (Button) findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = ImportacaoService.newInstance(ConfiguracaoManter.this, "wb020130508_010_002_000001_0001.csv");
                getBaseContext().startService(intent);
            }
        });
    }

    protected void doTabChange(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.mnuOrdemServico:
                viewPager.setCurrentItem(ConfiguracaoPagerAdapter.TAB_CONFIGURACAO);
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
        lblTitulo.setText(getResources().getText(R.string.app_name));
    }

    protected void doVoltar()
    {
        finish();
    }

}
