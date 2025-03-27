package hu.szamalk.modell;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TantargyTest {

    @Test
    void testAzonosNevuTanarok() {
        Tantargy t1 = new Tantargy("valami","Béla","Béla",3,1,true);
        t1.azonosNevuTanarok();

        Assertions.assertEquals('-',t1.getTanar2());
    }

    @Test
    void testKreditHelyesErteke() throws NemMegfeleloKreditException {
        Tantargy t1 = new Tantargy("valami","Béla","pista",0,1,true);
        Assertions.assertThrows(NemMegfeleloKreditException.class, () -> t1.kreditHelyesErteke());
    }

}