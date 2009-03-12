package tests

import org.junit.Test
import static org.junit.Assert.assertEquals

class IzPackStudioTest {
     @Test
    void methode()
     {
         assertEquals 4,2+2
     }
}

class JUnitExemple
{
    private tauxAlcoolemie
    private tva

    JUnitExemple(alcool, taxe)
    {
        tauxAlcoolemie = alcool
        tva = taxe
    }

    def getTauxAlcoolemie()
    {
        return tauxAlcoolemie
    }

    def getTva()
    {
        return tva
    }
}

class JUnitExempleTest
{
    @Test
    void testTauxAlcoolemie()
    {
        def myJUnitExemple = new JUnitExemple(0.5,19.6)
        assertEquals(myJUnitExemple.getTauxAlcoolemie(),0.5)
    }

    @Test
    void testTva()
    {
        def myJUnitExemple = new JUnitExemple(0.5,19.6)
        assertEquals(myJUnitExemple.getTva(),19.6)
    }
}