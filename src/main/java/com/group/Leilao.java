package com.group;

import java.util.ArrayList;
import java.util.List;

public class Leilao {
    
    private String title;
    private List<Lance> lances = new ArrayList<>();

    public Leilao(String title){
        this.title = title;
    }

    public List<Lance> getLances(){
        return lances;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void propoe(Lance lance){
        this.lances.add(lance);
    }
}
