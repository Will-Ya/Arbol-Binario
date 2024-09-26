
import java.util.Arrays;
import java.util.Comparator;

public class Coladeprioridad<T> {
    private T[] heap;
    private int size;
    private Comparator<? super T> comparator;

    // Constructor vacío
    @SuppressWarnings("unchecked")
    public Coladeprioridad() {
        this.heap = (T[]) new Object[10];
        this.size = 0;
    }

    // Constructor con un arreglo de datos
    public Coladeprioridad(T[] data, Comparator<? super T> comparator) {
        this.comparator = comparator;
        this.size = data.length;
        this.heap = Arrays.copyOf(data, size);
        buildHeap();
    }

    // Inserta un elemento en la cola de prioridad
    public void insert(T element) {
        if (size == heap.length) {
            resize(2 * heap.length);
        }
        heap[size] = element;
        size++;
        System.out.println("Insertando: " + element);
        swim(size - 1);
        printHeap();
    }

    // Retorna y elimina el elemento con mayor prioridad
    public T removeMax() {
        if (size == 0) throw new IllegalStateException("La cola está vacía");
        T max = heap[0];
        System.out.println("Eliminando máximo: " + max);
        exch(0, size - 1);
        size--;
        sink(0);
        heap[size] = null; // Evitar fuga de memoria
        printHeap();
        return max;
    }

    // Verifica si un nodo es menor que otro
    private boolean less(int i, int j) {
        return comparator.compare(heap[i], heap[j]) < 0;
    }

    // Intercambia dos elementos
    private void exch(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Corrige la regla de contenido hacia arriba
    private void swim(int k) {
        while (k > 0 && less((k - 1) / 2, k)) {
            System.out.println("Swim: " + heap[k] + " hacia arriba");
            exch(k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }

    // Corrige la regla de contenido hacia abajo
    private void sink(int k) {
        int child;
        while (2 * k + 1 < size) {
            child = 2 * k + 1; // Hijo izquierdo
            if (child + 1 < size && less(child, child + 1)) {
                child++; // Hijo derecho
            }
            if (!less(k, child)) break;
            System.out.println("Sink: " + heap[k] + " hacia abajo");
            exch(k, child);
            k = child;
        }
    }

    // Redimensiona el arreglo
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] newHeap = (T[]) new Object[capacity];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
    }

    // Construye el heap desde un arreglo
    private void buildHeap() {
        for (int k = (size / 2) - 1; k >= 0; k--) {
            sink(k);
        }
    }

    // Método para mostrar el contenido del heap
    public void printHeap() {
        System.out.println("Estado actual del heap: " + Arrays.toString(Arrays.copyOf(heap, size)));
    }
}
