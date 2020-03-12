package br.com.alura.argentum.indicadores;

import org.junit.Assert;
import org.junit.Test;

import br.com.alura.argentum.modelo.SerieTemporal;

public class MediaMovelSimplesTest {
    @Test
    public void sequenciaSimplesDeCandles() {
        SerieTemporal serie = GeradorDeSerie.criaSerie(1 ,2 ,3 ,4 ,3 ,4, 5);

        MediaMovelSimples mms = new MediaMovelSimples(new IndicadorFechamento());

        Assert.assertEquals(2.0, mms.calcula(2, serie),0.000001);
        Assert.assertEquals(3.0, mms.calcula(3, serie),0.000001);
        Assert.assertEquals(10.0/3, mms.calcula(4, serie),0.000001);
        Assert.assertEquals(11.0/3, mms.calcula(5, serie),0.000001);
        Assert.assertEquals(4.0, mms.calcula(6, serie),0.000001);
    }
}