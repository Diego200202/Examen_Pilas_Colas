package Juego_Colores;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Juego {

    public static Queue<Integer>[] jugadores;
    // Los colores de las fichas se representan por enteros ,donde las fichas
    // negras vienen dadas por el 0, y el resto de jugadores tendrán el color
    // correspondiente a la posición del jugador (es decir, el jugador 1 tendrá
    // fichas de valor 1, ...)
    Stack<Integer> mesa;

    public Juego(int numJugadores, int numFichasJugador){
        jugadores = new Queue[numJugadores+1];

         for (int i = 0; i < numJugadores+1; i++) {
            jugadores[i] = new LinkedList<>(); // Puedes usar cualquier implementación de Queue, como LinkedList
            for(int j = 0; j<numFichasJugador; j++){
                jugadores[i].add(i);
            }
        }

        mesa = new Stack<>();
    }
    
    public int juego(int n, ArrayList<Tirada> tiradas) {
    // pre: n es el número de fichas inicial de cada jugador
    // “tiradas” tiene los valores de los dados durante una partida
    // post: el resultado es el número del jugador ganador
    int dur = tiradas.size();
    int i = 0;
    while (i<dur) {
        Tirada actual = tiradas.get(i);
        if(actual.dado1 == 6){
            return determinarGanador();
        }
        else if (actual.dado1 % 2 == 0) {
            Queue<Integer> colaJugador = jugadores[actual.dado2 - 1];
            if (!colaJugador.isEmpty()) {
                mesa.push(colaJugador.poll());
            }
        }else{
            if(!mesa.isEmpty()){
                jugadores[actual.dado2-1].add(mesa.pop());
            }
        }
        i++;
        System.out.println("Dado1: " + actual.dado1 + " Dado2: " + actual.dado2);
        System.out.println("Mesa:");
        imprimirMesa();
        System.out.println();
        System.out.println("Jugadores: ");
        imprimir();
    }
    return determinarGanador();
    }

    public int determinarGanador(){
        int maxFichas = 0;
        int ganador = -1;

        for(int i = 0; i<jugadores.length; i++){
            int fichasNegras = contarFichasJugador(jugadores[i]);   
            
            if(fichasNegras > maxFichas){
                maxFichas = fichasNegras;
                ganador = i;
            }else if(fichasNegras == maxFichas && ganador == -1){
                ganador = i;
            }
        }
        return ganador;
    }

    public ArrayList<Tirada> hacerTiradas(){
        ArrayList<Tirada> tiradas = new ArrayList<>();
        do{
            Tirada t = tirarDados();
            tiradas.add(t);
            if(t.dado1 == 6){
                break;
            }
        }while(true);
        return tiradas;
    }

    public int contarFichasJugador(Queue<Integer> jugador){
        int fichas = 0;
        for(Integer f : jugador){
            if (f == 0) {
                fichas++;
            }
        }
        return fichas;
    }

    public void imprimir(){
        for (Queue<Integer> q : jugadores) {
            for(Integer elem : q){
                System.out.print(elem + " ");
            }
            System.out.println();
        }
    }

    public void imprimirMesa(){
        for (Integer elem : mesa) {
            System.out.print(elem + " ");
        }
    }

    public Tirada tirarDados(){
        Random random = new Random();
        int num1 = random.nextInt(6)+1;
        int num2 = random.nextInt(6)+1;
        return new Tirada(num1, num2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cuantos jugadores? ");
        int numJug = sc.nextInt();
        sc.close();
        int numFichas = 6;
        Juego juego = new Juego(numJug+1, numFichas);
        ArrayList<Tirada> tiradas = juego.hacerTiradas();

        for (Tirada tirada : tiradas) {
            System.out.println("Dado1: " + tirada.dado1 + " Dado2: " + tirada.dado2);
        }
        System.out.println(juego.juego(numFichas, tiradas));
    }

    
}
