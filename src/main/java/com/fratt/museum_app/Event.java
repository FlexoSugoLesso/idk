package com.fratt.museum_app;

import android.media.Image;

public class Event {


    private String data;
    private String titolo;
    private String citta;
    private String via;
    private String descrizione;
    private Image sfondo;


    public Event(String data , String titolo , String citta , String via , String descrizione , Image sfondo)
    {
        this.citta = citta;
        this.data = data;
        this.descrizione = descrizione;
        this.titolo = titolo;
        this.via = via;
        this.sfondo = sfondo;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getCitta() {
        return citta;
    }

    public String getVia() {
        return via;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Image getSfondo() {
        return sfondo;
    }

    public String getData() {

        return data;
    }
}
