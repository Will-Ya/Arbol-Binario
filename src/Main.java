import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        // Comparador para enteros
        Comparator<Integer> comparator = Integer::compareTo;

        // Crear una cola de prioridad con algunos datos iniciales
        Coladeprioridad<Integer> pq = new Coladeprioridad<>(new Integer[]{5, 3, 8, 1, 4, 6, 2}, comparator);

        // Mostrar el estado inicial del heap
        System.out.println("Estado inicial del monto:");
        pq.printHeap();

        // Insertar un nuevo elemento
        pq.insert(7);
        
        // Retirar el elemento de mayor prioridad
        System.out.println("Elemento eliminado: " + pq.removeMax());
    }
}
