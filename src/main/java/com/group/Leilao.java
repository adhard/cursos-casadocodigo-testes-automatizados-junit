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
        if(lances.isEmpty() || podeDarLance(lance.getUsuario())) {
            lances.add(lance);
        }
    }

    private Lance ultimoLanceDado() {
        return lances.get(lances.size()-1);
    }

    private int qtdDelancesDo(Usuario usuario) {
        int total = 0;
        for(Lance lance : lances) {
            if(lance.getUsuario().equals(usuario)) total++;
        }
        return total;
    }

    private boolean podeDarLance(Usuario usuario) {
        return !ultimoLanceDado().getUsuario().equals(usuario)
        && qtdDelancesDo(usuario) < 5;
    }

}
