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
import wsilva.com.br.mobileos.entity.os.OrdemServicoVO;
import wsilva.com.br.mobileos.pageadapter.OrdemServicoPagerAdapter;

public class OrdemServicoManter extends ActionBarActivity implements ActionBar.TabListener
{

    OrdemServicoVO ordemServico;
    OrdemServicoPagerAdapter pagerAdapter;
    ViewPager viewPager;
    PageIndicator mIndicator;

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
                ordemServico = (OrdemServicoVO) extras.getSerializable(OrdemServicoLista.TEMPLATE_SELECTED_ITEM);
            }
        } else
        {
            ordemServico = (OrdemServicoVO) savedInstanceState.getSerializable(OrdemServicoLista.TEMPLATE_SELECTED_ITEM);
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
                            startActivity(intent);
                        }
                        else if (item.getItemId() == R.id.mnuOrdemServicoEncerramento)
                        {
                            Intent intent = new Intent(OrdemServicoManter.this, OrdemServicoExecucaoEncerramento.class);
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

    protected void doVoltar()
    {
        finish();
    }

}
