package Regalos;

import java.util.LinkedList;
import java.util.Queue;

public class Colegio {
    Queue<Queue<String>> alumnos; // cola de colas
    
    public void repartirRegalos(Queue<String> regalos){
        // post: los regalos se han repartido uno a uno, entregando
        // el primer regalo a los alumnos del primer curso de la cola
        // de alumnos, y así sucesivamente
        // Se han escrito en la pantalla las asignaciones de regalos en forma
        // de pares (alumno, regalo)
        while (!regalos.isEmpty()) {
            String regalo = regalos.poll();
            Queue<String> cola = alumnos.poll();
            for(String alumno : cola){
                System.out.println(alumno + " " + regalo);
            }
            alumnos.add(cola);
        }
    }

    public Colegio(){
        alumnos = new LinkedList<>();
        Queue<String> primero = new LinkedList<>();
        primero.add("a");
        primero.add("b");
        primero.add("c");
        primero.add("d");
        Queue<String> segundo = new LinkedList<>();
        segundo.add("e");
        segundo.add("f");
        segundo.add("g");
        segundo.add("h");
        Queue<String> tercero = new LinkedList<>();
        tercero.add("i");
        tercero.add("j");
        tercero.add("k");
        tercero.add("l");
        Queue<String> cuarto = new LinkedList<>();
        cuarto.add("m");
        cuarto.add("n");
        cuarto.add("o");
        cuarto.add("p");
        Queue<String> quinto = new LinkedList<>();
        quinto.add("q");
        quinto.add("r");
        quinto.add("s");
        quinto.add("t");
        Queue<String> sexto = new LinkedList<>();
        sexto.add("u");
        sexto.add("v");
        sexto.add("w");
        sexto.add("x");

        alumnos.add(primero);
        alumnos.add(segundo);
        alumnos.add(tercero);
        alumnos.add(cuarto);
        alumnos.add(quinto);
        alumnos.add(sexto);
    }

    public static void main(String[] args) {
        Colegio colegio = new Colegio();

        Queue<String> regalos = new LinkedList<>();
        int i = 0;
        while (i < 10) {
            String reg = "r" + i;
            regalos.add(reg);
            i++;
        }

        colegio.repartirRegalos(regalos);
    }
}
