package com.mizuday;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Matkul{
    private String nama;
    private List<String> prereq;

    public Matkul(String nama){
        this.nama = nama;
        prereq = new ArrayList<>();
    }

    public Matkul(Matkul nama){
        this.nama = nama.getNama();
        this.prereq = new ArrayList<>();
        this.prereq.addAll(nama.getPrereq());
    }

    public String getNama() {
        return nama;
    }
    public List<String> getPrereq(){
        return prereq;
    }

    public void tambahPrereq(String nama){
        prereq.add(nama);
    }

    public void kurangPrereq(String nama){
        int counter = 0;
        for(String pre : prereq ){
            if (pre.equals(nama)){
                prereq.remove(counter);
                break;
            }
            counter++;
        }
    }

    public boolean hasPrereq(){
        return prereq.size() > 0;
    }

    public static List<Matkul> copyListMatkul(List<Matkul> src){
        List<Matkul> copy = new ArrayList<>();
        for (Matkul matkul : src){
            Matkul temp = new Matkul(matkul);
            copy.add(temp);
        }
        return copy;
    }

    public static void menghapusPrereq(List<Matkul> dag, String nama){
        for (Matkul matkul : dag){
            matkul.kurangPrereq(nama);
        }
    }

    public static boolean punyaPreqSama(List<Matkul> listDAG, String namaMatkul, List<String> matkul) {
        for (Matkul dag : listDAG){
            if (dag.getNama().equals(namaMatkul)){
                for (String str : matkul){
                    for (String lagi: dag.getPrereq()){
                        if (str.equals(lagi))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public static List<Matkul> fileToListDAG(String namafile){
        List<Matkul> listDAG = new ArrayList<>();
        List<String[]> data = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(namafile));
            String line = reader.readLine();
            while (line != null) {
                line = line.replace(".", "");
                String[] temp = line.split(", ");
                data.add(temp);
                line = reader.readLine();
            }
            reader.close();


            for (int i = 0; i < data.size(); i++) {
                listDAG.add(new Matkul(data.get(i)[0]));
                for (String string : data.get(i)){
                    if (!string.equals(data.get(i)[0]))
                    listDAG.get(i).tambahPrereq(string);
                }
            }

        } catch (IOException e) {
            System.out.println("Nama file salah");
        }
        return listDAG;
    }

    public static void solver(List<Matkul> listDAG, List<String> solusi){
        if (!listDAG.isEmpty()){
            try {
                // Jika punya prereq tambahkan ke paling akhir
                if (listDAG.get(0).hasPrereq()) {
                    listDAG.add(listDAG.get(0));
                }
                // Jika tidak punya prereq, tambahkan ke solusi dan hapus prereq
                else {
                    solusi.add(listDAG.get(0).getNama());
                    menghapusPrereq(listDAG, listDAG.get(0).getNama());
                }
                // Buang paling awal rekursif lagi
                listDAG.remove(0);
                solver(listDAG, solusi);
            } catch (NullPointerException e) {
                System.out.println("NullPointerException Caught");
            }
        }
    }

    public static void printSolusi(List<Matkul> listDAGCUI, List<String> solusi){
        int smt = 1;
        for (int i = 0; i < solusi.size(); i++) {
            System.out.print("Semester "+ (smt) + ": " + solusi.get(i));
            List<String> printSebelumnya = new ArrayList<>();
            printSebelumnya.add(solusi.get(i));

            while (i < solusi.size()-1 && !punyaPreqSama(listDAGCUI,solusi.get(i+1), printSebelumnya)){
                System.out.print(", "+solusi.get(i+1));
                i++;
            }
            smt++;
            System.out.println();
        }
    }

}