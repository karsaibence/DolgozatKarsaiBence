package hu.szamalk.modell;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Tantargy implements Comparable<Tantargy>, Serializable {
    private String nev, tanar1, tanar2;
    private int kredit, felev;
    private boolean csak_vizsga;

    public Tantargy(String nev, String tanar1, String tanar2, int kredit, int felev, boolean csak_vizsga) {
        this.nev = nev;
        this.tanar1 = tanar1;
        this.tanar2 = tanar2;
        this.kredit = kredit;
        this.felev = felev;
        this.csak_vizsga = csak_vizsga;
    }

    public Tantargy() {

    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getTanar1() {
        return tanar1;
    }

    public void setTanar1(String tanar1) {
        this.tanar1 = tanar1;
    }

    public String getTanar2() {
        return tanar2;
    }

    public void setTanar2(String tanar2) {
        this.tanar2 = tanar2;
    }

    public int getKredit() {
        return kredit;
    }

    public void setKredit(int kredit) {
        this.kredit = kredit;
    }

    public int getFelev() {
        return felev;
    }

    public void setFelev(int felev) {
        this.felev = felev;
    }

    public boolean isCsak_vizsga() {
        return csak_vizsga;
    }

    public void setCsak_vizsga(boolean csak_vizsga) {
        this.csak_vizsga = csak_vizsga;
    }

    @Override
    public String toString() {
        return "Tantargy{" +
                "nev='" + nev + '\'' +
                ", tanar1='" + tanar1 + '\'' +
                ", tanar2='" + tanar2 + '\'' +
                ", kredit=" + kredit +
                ", felev=" + felev +
                ", csak_vizsga=" + csak_vizsga +
                '}';
    }

    @Override
    public int compareTo(Tantargy t) {
        return this.nev.compareTo(t.nev);
    }

    public static KreditCompare kreditCompare() {
        return new KreditCompare();
    }

    private static class KreditCompare implements Comparator<Tantargy> {

        @Override
        public int compare(Tantargy t1, Tantargy t2) {
            return t1.kredit - t2.kredit;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tantargy tantargy = (Tantargy) o;
        return kredit == tantargy.kredit && felev == tantargy.felev && Objects.equals(nev, tantargy.nev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nev, kredit, felev);
    }

    public void azonosNevuTanarok() {
        if(this.tanar1.equals(this.tanar2)) {
            tanar2="-" ;
        }
    }

    public void kreditHelyesErteke() throws NemMegfeleloKreditException {
        if(getKredit() > 5 || this.getKredit() < 1){
            throw new NemMegfeleloKreditException();
        }
    }
}
