package com.mizuday;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println();
            Scanner sc= new Scanner(System.in);
            System.out.print("nama file (tanpa txt): ");
            String namafile = sc.nextLine();

            List<Matkul> graph = Matkul.fileToListDAG("../test/" + namafile + ".txt");
            List<Matkul> graphCopy = Matkul.copyListMatkul(graph);
            List<String> solusi = new ArrayList<>();
            Matkul.solver(graph, solusi);

            Matkul.printSolusi(graphCopy,solusi);

            System.out.print("solve lagi? y/n >> ");
            String in = sc.nextLine().toLowerCase();
            if (in.equals("n")|| in.equals("no")){
                break;
            }
        }
    }
}

