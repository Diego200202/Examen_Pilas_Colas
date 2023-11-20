package Juego_Bloques;

import java.util.*;

public class Juego {
    Stack<Bloque>[] tablero; // array de pilas
    public static int NUMCOLUMNAS = 7;

    public Juego() { // constructora
        tablero = (Stack<Bloque>[]) new Stack[NUMCOLUMNAS];
        for (int i = 0; i < NUMCOLUMNAS; i++) {
            tablero[i] = new Stack<>();
            llenarPilaAleatoriamente(tablero[i]);
        }
    }

    private void llenarPilaAleatoriamente(Stack<Bloque> pila) {
        // Código para llenar aleatoriamente las pilas de bloques
    }

    public int jugar() {
        int columnaBola = NUMCOLUMNAS / 2; // Bola empieza en la columna del medio
        int puntosTotales = 0;

        while (columnaBola >= 0 && columnaBola < NUMCOLUMNAS && !tablero[columnaBola].isEmpty()) {
            Bloque bloqueActual = tablero[columnaBola].pop();
            puntosTotales += bloqueActual.puntos;

            // Mover la bola a la siguiente columna según las reglas
            if ("i".equals(bloqueActual.direccion)) {
                columnaBola = Math.max(0, columnaBola - bloqueActual.salto);
            } else if ("d".equals(bloqueActual.direccion)) {
                columnaBola = Math.min(NUMCOLUMNAS - 1, columnaBola + bloqueActual.salto);
            }

            // Verificar si la bola se sale de las columnas
            if (columnaBola < 0 || columnaBola >= NUMCOLUMNAS) {
                // Se ha perdido el juego
                return -1;
            }
        }

        // Verificar si la bola cayó en una columna sin bloques
        if (tablero[columnaBola].isEmpty()) {
            // Se ha superado el juego
            return puntosTotales;
        }

        return -1; // Esto no debería ocurrir, es un caso de seguridad
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        
        int resultado = juego.jugar();

        if (resultado == -1) {
            System.out.println("¡Has perdido!");
        } else {
            System.out.println("¡Has superado el juego con " + resultado + " puntos!");
        }
    }
}
