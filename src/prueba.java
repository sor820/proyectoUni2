import java.util.ArrayList;
import java.util.List;

public class prueba {

    public class Simulador {

        // Clase abstracta Vehiculo
        public static abstract class Vehiculo {
            protected String id;
            protected double capacidad;
            protected String ubicacion;

            // Métodos abstractos
            public abstract void moverse();
            public abstract void cargar(Object carga);
            public abstract void descargar();
        }

        // Interfaces funcionales
        public interface Rodante {
            void conducir();
        }

        public interface Volador {
            void volar();
        }

        public interface Nadador {
            void navegar();
        }

        // Clases concretas
        public static class Auto extends Vehiculo implements Rodante {
            @Override
            public void moverse() {
                System.out.println("Auto moviéndose.");
            }

            @Override
            public void cargar(Object carga) {
                System.out.println("Auto cargando: " + carga.toString());
            }

            @Override
            public void descargar() {
                System.out.println("Auto descargando.");
            }

            @Override
            public void conducir() {
                System.out.println("Auto conduciendo.");
            }
        }

        public static class Dron extends Vehiculo implements Volador {
            @Override
            public void moverse() {
                System.out.println("Dron moviéndose.");
            }

            @Override
            public void cargar(Object carga) {
                System.out.println("Dron cargando: " + carga.toString());
            }

            @Override
            public void descargar() {
                System.out.println("Dron descargando.");
            }

            @Override
            public void volar() {
                System.out.println("Dron volando.");
            }
        }

        public static class Submarino extends Vehiculo implements Nadador {
            @Override
            public void moverse() {
                System.out.println("Submarino moviéndose.");
            }

            @Override
            public void cargar(Object carga) {
                System.out.println("Submarino cargando: " + carga.toString());
            }

            @Override
            public void descargar() {
                System.out.println("Submarino descargando.");
            }

            @Override
            public void navegar() {
                System.out.println("Submarino navegando.");
            }
        }

        public static class Anfibio extends Vehiculo implements Rodante, Nadador {
            @Override
            public void moverse() {
                System.out.println("Anfibio moviéndose.");
            }

            @Override
            public void cargar(Object carga) {
                System.out.println("Anfibio cargando: " + carga.toString());
            }

            @Override
            public void descargar() {
                System.out.println("Anfibio descargando.");
            }

            @Override
            public void conducir() {
                System.out.println("Anfibio conduciendo.");
            }

            @Override
            public void navegar() {
                System.out.println("Anfibio navegando.");
            }
        }

        // Clase Mision
        public static class Mision {
            private String origen;
            private String destino;
            private Vehiculo vehiculoAsignado;

            public Mision(String origen, String destino, Vehiculo vehiculoAsignado) {
                this.origen = origen;
                this.destino = destino;
                this.vehiculoAsignado = vehiculoAsignado;
            }

            public void iniciar() {
                System.out.println("Misión iniciada desde " + origen + " hacia " + destino);
                vehiculoAsignado.moverse();
            }

            public void completar() {
                System.out.println("Misión completada en " + destino);
            }
        }

        // Clase Entorno
        public static class Entorno {
            private List<Vehiculo> vehiculos = new ArrayList<>();
            private List<Mision> misionesActivas = new ArrayList<>();

            public void agregarVehiculo(Vehiculo vehiculo) {
                vehiculos.add(vehiculo);
            }

            public void agregarMision(Mision mision) {
                misionesActivas.add(mision);
            }

            public void simularCiclo() {
                System.out.println("Simulando ciclo...");
                for (Mision mision : misionesActivas) {
                    mision.iniciar();
                    mision.completar();
                }
            }
        }
    }
}
