import java.util.ArrayList;
import java.util.List;

// Clase principal
public class Simulador3 {

    // Clase abstracta Vehiculo
    public static abstract class Vehiculo {
        private String id;
        private double capacidad;
        private String ubicacion;

        // Getters y Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            if (id != null && !id.isEmpty()) {
                this.id = id;
            } else {
                throw new IllegalArgumentException("ID no puede ser nulo o vacío.");
            }
        }

        public double getCapacidad() {
            return capacidad;
        }

        public void setCapacidad(double capacidad) {
            if (capacidad > 0) {
                this.capacidad = capacidad;
            } else {
                throw new IllegalArgumentException("Capacidad debe ser mayor que cero.");
            }
        }

        public String getUbicacion() {
            return ubicacion;
        }

        public void setUbicacion(String ubicacion) {
            if (ubicacion != null && !ubicacion.isEmpty()) {
                this.ubicacion = ubicacion;
            } else {
                throw new IllegalArgumentException("Ubicación no puede ser nula o vacía.");
            }
        }

        // Métodos abstractos
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
        private double peso;

        // Getters y Setters
        public double getPeso() {
            return peso;
        }

        public void setPeso(double peso) {
            if (peso > 0) {
                this.peso = peso;
            } else {
                throw new IllegalArgumentException("Peso debe ser mayor que cero.");
            }
        }

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

    // Clase Mision
    public static class Mision {
        private String origen;
        private String destino;
        private Vehiculo vehiculoAsignado;

        // Getters y Setters
        public String getOrigen() {
            return origen;
        }

        public void setOrigen(String origen) {
            if (origen != null && !origen.isEmpty()) {
                this.origen = origen;
            } else {
                throw new IllegalArgumentException("Origen no puede ser nulo o vacío.");
            }
        }

        public String getDestino() {
            return destino;
        }

        public void setDestino(String destino) {
            if (destino != null && !destino.isEmpty()) {
                this.destino = destino;
            } else {
                throw new IllegalArgumentException("Destino no puede ser nulo o vacío.");
            }
        }

        public Vehiculo getVehiculoAsignado() {
            return vehiculoAsignado;
        }

        public void setVehiculoAsignado(Vehiculo vehiculoAsignado) {
            if (vehiculoAsignado != null) {
                this.vehiculoAsignado = vehiculoAsignado;
            } else {
                throw new IllegalArgumentException("El vehículo asignado no puede ser nulo.");
            }
        }

        // Métodos de la misión
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

        // Métodos controlados para interactuar con datos internos
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