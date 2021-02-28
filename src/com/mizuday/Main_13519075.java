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

            List<Graph<String>> graph = Graph.fileToGraph("../test/" + namafile + ".txt");
            List<String> solusi = new ArrayList<>();

            Graph.solver(graph, solusi);

            System.out.print("solve lagi? y/n >> ");
            
            String in = sc.nextLine().toLowerCase();
            if (in.equals("n")|| in.equals("no")){
                break;
            }
        }
    }
}

