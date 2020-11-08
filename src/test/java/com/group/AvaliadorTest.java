package com.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AvaliadorTest {

    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;

    @BeforeEach
    public void criaAvaliador(){
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("Joao");
        this.jose = new Usuario("Jose");
        this.maria = new Usuario("Maria");
    }
    
    @Test
    public void DeveEntenderLancesEmOrdemCrescente() {
        Leilao leilao = new Leilao("PS5 Novo");
        leilao.propoe(new Lance(joao, 250.0));
        leilao.propoe(new Lance(jose, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        leiloeiro.avalia(leilao);

        // comparando a saida com o esperado
        double maiorEsperado = 400;
        double menorEsperado = 250;

        // System.out.println(maiorEsperado == leiloeiro.getMaiorLance());
        // System.out.println(menorEsperado == leiloeiro.getMenorLance());

        // Assertions.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        // Assertions.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
        assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
        assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
    }

    @Test
    public void DeveEntenderLancesComApenasUmLance() {
        Leilao leilao = new Leilao("PS5 Novo");
        leilao.propoe(new Lance(joao, 1000.0));

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMenorLance(), equalTo(1000.0));
        assertThat(leiloeiro.getMaiorLance(), equalTo(1000.0));

        // assertEquals(1000, leiloeiro.getMaiorLance(), 0.0001);
        // assertEquals(1000, leiloeiro.getMenorLance(), 0.0001);
        
    }

    @Test
    public void DeveEncontrarOsTresMaioresLances(){
        Leilao leilao = new CriadorDeLeilao()
            .para("PS3")
            .lance(joao, 100.0)
            .lance(maria, 200.0)
            .lance(joao, 300.0)
            .lance(maria, 400.0)
            .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        // assertEquals(3, maiores.size());
        // assertEquals(400, maiores.get(0).getValor(), 0.00001);
        // assertEquals(300, maiores.get(1).getValor(), 0.00001);
        // assertEquals(200, maiores.get(2).getValor(), 0.00001);
        assertThat(3, equalTo(maiores.size()));
        assertThat(maiores, hasItems(
            new Lance(maria, 400.0),
            new Lance(joao, 300.0),
            new Lance(maria, 200.0)   
        ));

    }

    @Test
    public void naoDeveAvaliarLeiloesSemNenhumLanceDado(){
        try {
            Leilao leilao = new CriadorDeLeilao()
                .para("PS5 NOVO")
                .constroi();

            leiloeiro.avalia(leilao);
            Assertions.fail();
        } catch(RuntimeException e){
            // deu certo
        }

        // nao funcionou (revisar metodo de testar se o codigo rodou uma exceção - exception expected)
        // Leilao leilao = new CriadorDeLeilao()
        //     .para("PS5 NOVO")
        //     .constroi();
        
        // Throwable exception = assertThrows(RuntimeException.class, 
        // () -> leiloeiro.avalia(leilao));
        // assertEquals("Erro", exception.getMessage());
    }

}
