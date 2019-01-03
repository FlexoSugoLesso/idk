package com.fratt.museum_app;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SliderHome extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    private final int NUMERO_DI_PAGE = 3;
    private FrameLayout frameLayout;


    public String[] titoli = {"Eventi","Casuale1","Casuale2"};  //descrizioni e sfondi
    public int Background =R.drawable.ic_gradient_homepage;// colori
    public int[] text_background = {R.color.OrangeTextBackground};

    public SliderHome(Context context, FrameLayout frameLayout)
    {
        this.context=context;
        this.frameLayout=frameLayout;
    }


    @Override
    public int getCount() {
        return NUMERO_DI_PAGE;//Immagini.length;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }



    @Override
    public Object instantiateItem(ViewGroup container , int position)
    {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.tabs,container,false);
        TextView textView = view.findViewById(R.id.tab_title);
        RelativeLayout relativeLayout = view.findViewById(R.id.sfondo);
        relativeLayout.setBackgroundResource(Background);
        textView.setText(titoli[position]);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container , int position , Object o)
    {
        container.removeView((RelativeLayout)o);
    }
}

