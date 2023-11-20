import java.util.*;

public class Cafeteria {
    Queue<Queue<Integer>> cola = null; // una cola de colas

    public Cafeteria(){
        cola = new LinkedList<>();
        
    }
    
    public void insertar(Integer elem){
        // Postcondición: se ha añadido “elem” en la cola de la cafetería
        boolean agregado = false;

        for(Queue<Integer> grupo : cola){
            if(mismoGrupo(elem, grupo.peek())){
                grupo.add(elem);
                agregado = true;
            }
        }

        if(!agregado){
            Queue<Integer> nuevoGrupo = new LinkedList<>();
            nuevoGrupo.add(elem);
            cola.add(nuevoGrupo);
        }
    }

    public Integer borrar(){
        // Precondición: se tiene al menos un elemento en la cola
        // Postcondición: se ha eliminado el primer elemento de la cola y
        // se devuelve su valor
        Queue<Integer> primerGrupo = cola.peek();

        Integer valorEliminado = primerGrupo.poll();

        if (primerGrupo.isEmpty()) {
            cola.poll();
        }

        return valorEliminado;
    }

    public boolean mismoGrupo(int p1, int p2){
        // Postcondición: true si p1 y p2 pertenecen al mismo grupo y false si no
        // NO HAY QUE IMPLEMENTAR ESTE MÉTODO
        if ((p1 == 1 || p1 == 5 || p1 == 7) && (p2 == 1 || p2 == 5 || p2 == 7)) {
            return true;
        } else if ((p1 == 2 || p1 == 4 || p1 == 8 || p1 == 10) && (p2 == 2 || p2 == 4 || p2 == 8 || p2 == 10)) {
            return true;
        } else if ((p1 == 3 || p1 == 6 || p1 == 11) && (p2 == 3 || p2 == 6 || p2 == 11)) {
            return true;
        } else{
            return false;
        }
    }

    public void imprimirCola() {
        System.out.print("Estado de la cola: ");
        for (Queue<Integer> grupo : cola) {
            for (Integer elem : grupo) {
                System.out.print(elem + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Cafeteria cafeteria = new Cafeteria();
        
        cafeteria.insertar(2);
        cafeteria.insertar(6);
        cafeteria.insertar(8);
        cafeteria.insertar(5);
        cafeteria.insertar(4);
        cafeteria.insertar(1);
        cafeteria.insertar(3);
        
        System.out.println("Salida: " + cafeteria.borrar());
        cafeteria.imprimirCola();
        System.out.println("Salida: " + cafeteria.borrar());
        cafeteria.imprimirCola();
        System.out.println("Salida: " + cafeteria.borrar());
        cafeteria.imprimirCola();

        cafeteria.insertar(11);
        cafeteria.imprimirCola();
    }
} // Cafeteria