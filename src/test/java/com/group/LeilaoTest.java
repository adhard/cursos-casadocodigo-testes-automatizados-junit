package com.group;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LeilaoTest {

    @Test
    public void deveReceberUmLance(){
        Leilao leilao = new Leilao("Macbook pro 15");
        assertEquals(0, leilao.getLances().size());
        leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000.00));
        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.00, leilao.getLances().get(0).getValor(), 0.00001);

    }

    @Test
    public void deveReceberVariosLances(){
        Leilao leilao = new Leilao("Macbook pro 15");
        leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000.00));
        leilao.propoe(new Lance(new Usuario("Steve Wozniak"), 3000.00));

        assertEquals(2, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(3000, leilao.getLances().get(1).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario(){
        Leilao leilao = new Leilao("Macbook pro 15");
        Usuario stevejobs = new Usuario("Steve jobs");

        leilao.propoe(new Lance(stevejobs, 2000.0));
        leilao.propoe(new Lance(stevejobs, 3000.0));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

}
