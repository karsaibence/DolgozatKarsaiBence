package hu.szamalk;

import hu.szamalk.modell.Szak;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Szak szak = new Szak("valami");

        System.out.println(szak);

    }
}