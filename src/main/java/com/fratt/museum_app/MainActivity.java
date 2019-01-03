
package com.fratt.museum_app;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.google.android.gms.vision.CameraSource;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;


import github.chenupt.springindicator.SpringIndicator;


public class MainActivity extends AppCompatActivity{


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private LinearLayout Container;
    private LinearLayout mainbutton;
    int windowwidth;
    int windowheight;
    private Boolean enabled ;
    private ImageView semicerchio;
    private RelativeLayout containermain;
    private ViewPager viewPager;
    private  SliderHome slider;
    private FrameLayout framelayout_homepage;
    public int Backgrounds[] ;
    private final int NUMERO_DI_PAGE = 3;
    private DotsIndicator dotsDotsIndicators;


    private LinearLayout linearLayout;

    private int posizione ;
    private View view;
    private LayoutInflater layoutInflater;
    private ImageView SearchButton;

    private int backgrounds [] = { R.drawable.foresta, R.drawable.mountains,R.drawable.museo};
    private Drawable background_drawable[] ;
    private ImageView roundimage;
    private EditText search_bar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        Container = findViewById(R.id.container);
        mainbutton = findViewById(R.id.MainButton);
        windowwidth = getWindowManager().getDefaultDisplay().getWidth();
        windowheight = getWindowManager().getDefaultDisplay().getHeight();
        semicerchio = findViewById(R.id.semicerchio);
        containermain = findViewById(R.id.containermain);
        enabled = true;
        SearchButton = findViewById(R.id.search_icon);
        search_bar = findViewById(R.id.search_bar);
        search_bar.setVisibility(View.INVISIBLE);


        background_drawable = new Drawable[NUMERO_DI_PAGE];




        for(int i = 0 ; i < 3 ; i++)
        {

            background_drawable[i] = resizeImage(backgrounds[i]);
        }
        MettiIcona();

          layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);

        view = layoutInflater.inflate(R.layout.homepage,Container,false);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.l2)));
        Container.addView(view);
        roundimage= view.findViewById(R.id.roundedImageView);

        viewPager = view.findViewById(R.id.viewtab);
        framelayout_homepage=view.findViewById(R.id.sfondo_homepage);
        slider = new SliderHome(getApplicationContext(), framelayout_homepage);
        posizione=1;
        viewPager.setCurrentItem(1);
        viewPager.setAdapter(slider);

        OpenHomePage();
        posizione=0;

        viewPager.setCurrentItem(0);



        navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);


        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(search_bar.getVisibility()==View.VISIBLE)
                {
                    search_bar.setVisibility(View.INVISIBLE);
                }
                if(search_bar.getVisibility()==View.INVISIBLE)
                {
                    search_bar.setVisibility(View.VISIBLE);
                }
                System.out.println("ciao");

            }
        };
        SearchButton.setOnClickListener(clickListener);




        //coloraItem();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {


                Container.removeAllViews();
                enabled = true;

                switch (item.getItemId()) {
                    case R.id.menu1:
                        view = layoutInflater.inflate(R.layout.drawer_tab_1,Container,false);

                        // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.l1)));
                        Container.addView(view);

                        //  MettiIcona();
                        break;
                    case R.id.menu2:
                        view = layoutInflater.inflate(R.layout.drawer_tab_2,Container,false);
                        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.l2)));
                        Container.addView(view);
                        //  MettiIcona();
                        break;
                    case R.id.menu3:
                        view = layoutInflater.inflate(R.layout.homepage,Container,false);
                        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.l2)));
                       OpenHomePage();
                        Container.addView(view);

                        break;
                    default:
                        break;
                }

                return true;
            }

        });


        mainbutton.setOnTouchListener(new ImageView.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(enabled) {
                    LayoutParams layoutParams = (LayoutParams) mainbutton.getLayoutParams();
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            break;
                        case MotionEvent.ACTION_MOVE:

                            System.out.println("p>" + layoutParams.bottomMargin);
                            if (layoutParams.bottomMargin < -68)
                                layoutParams.bottomMargin += 2;
                            else
                            {
                                layoutParams.bottomMargin = -80;
                               // apriSchede();
                            }



                            mainbutton.setLayoutParams(layoutParams);
                            break;
                        default:
                            break;
                    }
                }
                return true;
            }
        });
    }

    private void apriSchede() {
        enabled = false;
        Activity activity = (Activity) this;
         QR_manager qr_manager = new QR_manager(Container,getApplicationContext(),this);
         qr_manager.displayCamera();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);

        /* */
    }
    public void MettiIcona()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);




        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24px);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerToggle.syncState();
        //setSupportActionBar(toolbar);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.getDrawerArrowDrawable().setSpinEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
    }
    // Handle item selection

    ViewPager.OnPageChangeListener viewlistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
            framelayout_homepage.setBackground(background_drawable[i]);
            roundimage.setImageDrawable(background_drawable[i]);
          /*  Picasso.with(getApplicationContext()).load(backgrounds[i]).into(new Target(){

                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    framelayout_homepage.setBackground(new BitmapDrawable(getApplicationContext().getResources(), bitmap));
                }

                @Override
                public void onBitmapFailed(final Drawable errorDrawable) {
                    Log.d("TAG", "FAILED");
                }

                @Override
                public void onPrepareLoad(final Drawable placeHolderDrawable) {
                    Log.d("TAG", "Prepare Load");
                }
            });*/
            posizione = i;

        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }
    };
    public Drawable resizeImage(int image) {

        Display display = getWindowManager().getDefaultDisplay();
        double deviceWidth = display.getWidth();

        BitmapDrawable bd = (BitmapDrawable) getApplicationContext().getResources().getDrawable(R.drawable.foresta);
        double imageHeight = bd.getBitmap().getHeight();
        double imageWidth = bd.getBitmap().getWidth();

        double ratio = deviceWidth / imageWidth;
        int newImageHeight = (int) (imageHeight * ratio);

        Bitmap bMap = BitmapFactory.decodeResource(this.getResources(), image);
        Drawable drawable = new BitmapDrawable(this.getResources(),
                getResizedBitmap(bMap, newImageHeight, (int) deviceWidth));

        return drawable;
    }

    /************************ Resize Bitmap *********************************/
    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();
        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);

        return resizedBitmap;
    }



    public void OpenHomePage()
    {
        viewPager = view.findViewById(R.id.viewtab);
        framelayout_homepage=view.findViewById(R.id.sfondo_homepage);
        slider = new SliderHome(getApplicationContext(), framelayout_homepage);
        viewPager.setAdapter(slider);
        viewPager.addOnPageChangeListener(viewlistener);
        dotsDotsIndicators = view.findViewById(R.id.dots_indicator);
        dotsDotsIndicators.setViewPager(viewPager);
        framelayout_homepage.setBackground(background_drawable[posizione]);
        roundimage.setImageDrawable(background_drawable[posizione]);


    }
}
