package com.fratt.museum_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Slider extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public int[] Immagini = { //immagini - icone
            R.drawable.ic_bishop,
            R.drawable.ic_geography,
            R.drawable.ic_sculpture
    };
   /* public String[] descrizione = {};  descrizioni e sfondi
    public int[] Backgrounds ={};*/


    public Slider(Context context)
    {
        this.context=context;
    }


    @Override
    public int getCount() {
        return Immagini.length;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (ConstraintLayout) o;
    }



    @Override
    public Object instantiateItem(ViewGroup container , int position)
    {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider,container,false);
        ImageView imageView = view.findViewById(R.id.Imageview1);
        imageView.setImageResource(Immagini[position]);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container , int position , Object o)
    {
        container.removeView((ConstraintLayout)o);
    }
}
