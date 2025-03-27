package hu.szamalk.modell;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Szak implements Serializable {
    private static final String TANTARGYAK = "tantargyak.txt";
    private static final String STATISZTIKA = "statisztika.txt";
    private List<Tantargy> targyak;
    private String nev;
    private transient UUID id;

    public Szak(String nev) {
        this.nev = nev;
        genId();
        szakBeolvasasa();
    }

    public void genId(){
        this.id = UUID.randomUUID();
    }

    public List<Tantargy> getTargyak(){
        return new ArrayList<>(targyak);
    }


    public void szakKiirasa(){
        try(ObjectOutputStream objKi = new ObjectOutputStream(new FileOutputStream(TANTARGYAK))) {
            objKi.writeObject(targyak);
            System.out.println("Sikeres kiiras");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void szakBeolvasasa(){
        //Files.readAllLines(Path.of(Path.of("../resources/tantargyak.txt").toUri()));

            //this.tantargyak = (List<Tantargy>) objBe;

            System.out.println("Sikeres fájlbeolvasás");
    }

    public List<Tantargy> getTargyakNevSzerint(List<Tantargy> tantargyak){
        Collections.sort(tantargyak);
        return new ArrayList<Tantargy>(tantargyak);
    }

    public List<Tantargy> getTargyakKreditSzerint(List<Tantargy> targyak){
        //targyak.sort(kreditCompare);
        return new ArrayList<Tantargy>(this.targyak);
    }

    public void statisztika(){
        try(ObjectOutputStream objKi = new ObjectOutputStream(new FileOutputStream(STATISZTIKA))) {
            objKi.writeObject(targyak);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int kulonbozoTargyak(List<Tantargy> t){
        HashSet<Tantargy> kulonb = new HashSet<>(t);
        return kulonb.size();
    }

    public ArrayList<Tantargy> minMaxKredit(){
        ArrayList<Tantargy> list = new ArrayList<>();
        int min= targyak.get(0).getKredit();
        int max = 0;
        for (Tantargy tantargy : targyak) {
            if(tantargy.getKredit()>max){
                max=tantargy.getKredit();
            }

            if(tantargy.getKredit()<min){
                min=tantargy.getKredit();
            }
        }
        for (Tantargy t : targyak) {
            if(t.getKredit()==min){
                list.add(t);
            }
            if(t.getKredit()==max){
                list.add(t);
            }
        }
        return list;
    }

    public double kreditertek(){
        double sum = 0;

        for (Tantargy t : targyak) {
            sum+=t.getKredit();
        }
        return sum;
    }

    public List vizsgakentiTargyak(){
        class VizsgakentFelvehetoTargy{
            String nev;
            int kredit,felev;

            public VizsgakentFelvehetoTargy(String nev, int kredit, int felev) {
                this.nev = nev;
                this.kredit = kredit;
                this.felev = felev;
            }

            @Override
            public String toString() {
                return "VizsgakentFelvehetoTargy{" +
                        "nev='" + nev + '\'' +
                        ", kredit=" + kredit +
                        ", felev=" + felev +
                        '}';
            }
        }
        ArrayList<VizsgakentFelvehetoTargy> v = new ArrayList<>();
        for (Tantargy t : targyak) {
            if(!t.isCsak_vizsga()){
                v.add(new VizsgakentFelvehetoTargy(t.getNev(),t.getKredit(),t.getFelev()));
            }
        }
        return v;
    }
}
