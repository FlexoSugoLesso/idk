package com.fratt.museum_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class EventsManager {

    private Context context;
    private LinearLayout container;
    private View view;
    private int NUMERO_DI_EVENTI;
    public EventsManager (LinearLayout container , Context context,View view)
    {
        this.container = container;
        this.context=context;
        this.view=view;
        NUMERO_DI_EVENTI = 3;
    }

    public void createEvents()
    {

    }




}
