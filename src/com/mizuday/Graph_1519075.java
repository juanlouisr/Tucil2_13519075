package com.mizuday;

class Graph<T> {
    private T prec;
    private T succ;

    public Graph(T preccc, T succc){
        prec = preccc;
        succ = succc;
    }

    public T getPrec(){
        return prec;
    }

    public T getSucc(){
        return succ;
    }

    public void cetakIsi(){
        System.out.println("{" + prec + ", "+ succ + "}");
    }


}
