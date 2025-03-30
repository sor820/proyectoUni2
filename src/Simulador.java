
import java.util.ArrayList;
import java.util.List;

// Clase principal
public class Simulador {

    // Clase abstracta Vehiculo
    public static abstract class Vehiculo {
        protected String id;
        protected double capacidad;
        protected String ubicacion;

        public abstract void moverse();

        public abstract void cargar(Object carga);

        public abstract void descargar();
    }

    // Interfaces
    public interface Rodante {
        void conducir();
    }

    public interface Volador {
        void volar();
    }

    public interface Nadador {
        void navegar();
    }

    public interface Electrico {
        void recargarBateria();
    }

    public interface Combustion {
        void repostarCombustible();
    }

    public interface Autonomo {
        void activarModoAutonomo();
    }

    // Clases concretas de vehículos
    public static class Auto extends Vehiculo implements Rodante, Combustion {
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

        @Override
        public void repostarCombustible() {
            System.out.println("Auto repostando combustible.");
        }
    }

    public static class Dron extends Vehiculo implements Volador, Electrico {
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

        @Override
        public void recargarBateria() {
            System.out.println("Dron recargando batería.");
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

    // Clase abstracta Carga
    public static abstract class Carga {
        protected double peso;

        public abstract void verificarSeguridad();
    }

    public static class CargaFragil extends Carga {
        @Override
        public void verificarSeguridad() {
            System.out.println("Verificando protección adicional para carga frágil.");
        }
    }

    public static class CargaMilitar extends Carga {
        @Override
        public void verificarSeguridad() {
            System.out.println("Verificando restricciones para carga militar.");
        }
    }

    // Clase abstracta Ruta
    public static abstract class Ruta {
        protected String origen;
        protected String destino;

        public abstract double calcularDistancia();
    }

    // Tipos de misión
    public static class EntregaUrgente extends Mision {
        private double tiempoLimite;

        public EntregaUrgente(String origen, String destino, Vehiculo vehiculoAsignado, double tiempoLimite) {
            super(origen, destino, vehiculoAsignado);
            this.tiempoLimite = tiempoLimite;
        }

        @Override
        public void iniciar() {
            System.out.println("Entrega urgente iniciada con tiempo límite de " + tiempoLimite + " horas.");
            vehiculoAsignado.moverse();
        }
    }

    // Clase Mision
    public static class Mision {
        protected String origen;
        protected String destino;
        protected Vehiculo vehiculoAsignado;

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

