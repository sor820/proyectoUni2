import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que encapsula toda la simulación del sistema de transporte autónomo.
 * <br/>
 * <b>
 * Este sistema modela un entorno logístico con diferentes tipos de vehículos autónomos
 * (terrestres, aéreos y acuáticos) que realizan misiones de transporte en un entorno
 * con obstáculos y condiciones variables, utilizando exclusivamente clases anidadas estáticas.
 * </b>
 *
 * @author [Cristofer A. Hernandez y David Gustavo Lara]
 * @version 1.0
 */
public class Simulador {
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

    public interface Electrico {
        void recargarBateria();
    }

    public interface Combustion {
        void repostarCombustible();
    }

    public interface Autonomo {
        void activarModoAutonomo();
    }


    /**
     * Clase abstracta que representa un vehículo autónomo genérico.
     */
    public static abstract class Vehiculo {
        private String id;
        private double capacidad;
        private String ubicacion;

        // Métodos abstractos
        public abstract void moverse();

        public abstract void cargar(Object carga);

        public abstract void descargar();

        // Getters y setters encapsulados
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getCapacidad() {
            return capacidad;
        }

        public void setCapacidad(double capacidad) {
            this.capacidad = capacidad;
        }

        public String getUbicacion() {
            return ubicacion;
        }

        public void setUbicacion(String ubicacion) {
            this.ubicacion = ubicacion;
        }


        // Clases concretas de vehículos

        /**
         * Vehículo terrestre que implementa capacidades de rodar y usar combustible.
         */
        public static class Auto extends Vehiculo implements Rodante, Combustion {

            public Auto(String id) {
                this.setId(id);
            }

            @Override
            public void moverse() {
                System.out.println("Auto moviéndose por carretera.");
            }


            @Override
            public void cargar(Object carga) {
                System.out.println("Auto cargando: " + carga.toString());
            }


            @Override
            public void descargar() {
                System.out.println("Auto descargando en punto de entrega.");
            }

            @Override
            public void conducir() {
                System.out.println("Auto en modo conducción manual.");
            }

            @Override
            public void repostarCombustible() {
                System.out.println("Auto repostando gasolina.");
            }
        }


        /**
         * Vehículo aéreo autónomo con capacidades eléctricas.
         */
        public static class Dron extends Vehiculo implements Volador, Electrico, Autonomo {
            public Dron(String id) {
                this.setId(id);
            }

            @Override
            public void moverse() {
                System.out.println("Dron desplazándose en el aire.");
            }

            @Override
            public void cargar(Object carga) {
                System.out.println("Dron cargando paquete ligero.");
            }

            @Override
            public void descargar() {
                System.out.println("Dron descargando mediante sistema de cables.");
            }

            @Override
            public void volar() {
                System.out.println("Dron ascendiendo a 100 metros.");
            }

            @Override
            public void recargarBateria() {
                System.out.println("Batería de ion-litio recargándose.");
            }

            @Override
            public void activarModoAutonomo() {
                System.out.println("Dron activando navegación por GPS.");
            }
        }

        /**
         * Vehículo anfibio con capacidades terrestres y acuáticas.
         */
        public static class Anfibio extends Vehiculo implements Rodante, Nadador {
            public Anfibio(String id) {
                this.setId(id);
            }

            @Override
            public void moverse() {
                System.out.println("Anfibio cambiando entre tierra y agua.");
            }

            @Override
            public void cargar(Object carga) {
                System.out.println("Anfibio cargando carga sellada.");
            }

            @Override
            public void descargar() {
                System.out.println("Anfibio usando rampa hidráulica.");
            }

            @Override
            public void conducir() {
                System.out.println("Anfibio en modo 4x4.");
            }

            @Override
            public void navegar() {
                System.out.println("Anfibio navegando a 5 nudos.");
            }
        }

        /**
         * Vehículo submarino especializado.
         */
        public static class Submarino extends Vehiculo implements Nadador {
            public Submarino(String id) {
                this.setId(id);
            }

            @Override
            public void moverse() {
                System.out.println("Submarino sumergiéndose a 200 metros.");
            }

            @Override
            public void cargar(Object carga) {
                System.out.println("Submarino cargando equipo submarino.");
            }

            @Override
            public void descargar() {
                System.out.println("Submarino liberando carga con grúa.");
            }

            @Override
            public void navegar() {
                System.out.println("Submarino usando sonar para navegar.");
            }
        }


    }


    /**
     * Clase abstracta que representa una carga genérica.
     */
    public static abstract class Carga {
        private double peso;

        public abstract void verificarSeguridad();

        public double getPeso() {
            return peso;
        }

        public void setPeso(double peso) {
            this.peso = peso;
        }
    }

    /**
     * Carga especializada para materiales frágiles.
     */
    public static class CargaFragil extends Carga {
        @Override
        public void verificarSeguridad() {
            System.out.println("Aplicando protocolos anti-vibración.");
        }

        @Override
        public String toString() {
            return "Carga Frágil";
        }
    }

    /**
     * Clase abstracta que representa una misión logística.
     */
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

    /**
     * Misión especializada para entregas urgentes.
     */
    public static class EntregaUrgente extends Mision {
        private double tiempoLimite;

        public EntregaUrgente(String origen, String destino, Vehiculo vehiculo, double tiempoLimite) {
            super(origen, destino, vehiculo);
            this.tiempoLimite = tiempoLimite;
        }

        @Override
        public void iniciar() {
            System.out.printf("INICIO URGENTE: %s a %s (%s)\n", origen, destino, vehiculoAsignado.getId());
            vehiculoAsignado.moverse();
        }

        @Override
        public void completar() {
            System.out.println("Entrega completada en " + tiempoLimite + " horas.");
        }
    }


    /**
     * Entorno que gestiona vehículos y misiones activas.
     */
    public static class Entorno {
        private List<Vehiculo> vehiculos = new ArrayList<>();
        private List<Mision> misiones = new ArrayList<>();

        public void agregarVehiculo(Vehiculo v) {
            vehiculos.add(v);
        }

        public void agregarMision(Mision m) {
            misiones.add(m);
        }

        public void simularCiclo() {
            System.out.println("\n--- INICIO DE CICLO DE SIMULACIÓN ---");
            misiones.forEach(m -> {
                m.iniciar();
                m.completar();
            });
            System.out.println("--- FIN DE CICLO ---\n");
        }
    }

    public static void main(String[] args) {
        // Creación de vehículos concretos (Tipificación)
        Vehiculo.Auto auto1 = new Vehiculo.Auto("A1");
        Vehiculo.Dron dron1 = new Vehiculo.Dron("D1");
        Vehiculo.Anfibio anfibio1 = new Vehiculo.Anfibio("AN1");
        Vehiculo.Submarino submarino1 = new Vehiculo.Submarino("S1");

        System.out.println("--- Demostración de Tipificación ---");
        System.out.println("Tipo de auto1: " + auto1.getClass().getSimpleName());
        System.out.println("Tipo de dron1: " + dron1.getClass().getSimpleName());

        // Upcasting (Polimorfismo)
        Vehiculo vehiculoGenerico1 = auto1; // Un Auto es un Vehiculo
        Vehiculo vehiculoGenerico2 = dron1; // Un Dron es un Vehiculo
        Rodante vehiculoRodante = anfibio1; // Un Anfibio es Rodante
        Nadador vehiculoNadador = submarino1; // Un Submarino es Nadador

        System.out.println("\n--- Demostración de Upcasting y Polimorfismo ---");
        vehiculoGenerico1.moverse(); // Llama a la implementación de moverse de Auto
        vehiculoGenerico2.moverse(); // Llama a la implementación de moverse de Dron
        vehiculoRodante.conducir(); // Llama a la implementación de conducir de Anfibio
        vehiculoNadador.navegar(); // Llama a la implementación de navegar de Submarino

        // Polimorfismo a través de una lista
        List<Vehiculo> flota = new ArrayList<>();
        flota.add(auto1);
        flota.add(dron1);
        flota.add(anfibio1);
        flota.add(submarino1);

        System.out.println("\n--- Polimorfismo en una lista ---");
        for (Vehiculo vehiculo : flota) {
            System.out.print(vehiculo.getId() + ": ");
            vehiculo.moverse(); // Comportamiento polimórfico: cada vehículo se mueve de forma diferente
        }

        // Downcasting (requiere precaución y verificación con instanceof)
        System.out.println("\n--- Demostración de Downcasting ---");
        if (vehiculoGenerico1 instanceof Vehiculo.Auto) {
            Vehiculo.Auto autoRecuperado = (Vehiculo.Auto) vehiculoGenerico1;
            autoRecuperado.repostarCombustible();
        }

        if (vehiculoGenerico2 instanceof Vehiculo.Dron) {
            Vehiculo.Dron dronRecuperado = (Vehiculo.Dron) vehiculoGenerico2;
            dronRecuperado.volar();
            dronRecuperado.activarModoAutonomo();
        }

        // Uso de interfaces (Polimorfismo)
        System.out.println("\n--- Polimorfismo con Interfaces ---");
        List<Rodante> vehiculosTerrestres = new ArrayList<>();
        vehiculosTerrestres.add(auto1);
        vehiculosTerrestres.add(anfibio1);
        for (Rodante rodante : vehiculosTerrestres) {
            rodante.conducir();
        }

        List<Volador> vehiculosAereos = new ArrayList<>();
        vehiculosAereos.add(dron1);
        for (Volador volador : vehiculosAereos) {
            volador.volar();
        }

        List<Nadador> vehiculosAcuaticos = new ArrayList<>();
        vehiculosAcuaticos.add(submarino1);
        vehiculosAcuaticos.add(anfibio1);
        for (Nadador nadador : vehiculosAcuaticos) {
            nadador.navegar();
        }

        // Demostración de la clase Entorno y Misiones
        System.out.println("\n--- Demostración de Entorno y Misiones ---");
        Entorno entornoSimulacion = new Entorno();
        entornoSimulacion.agregarVehiculo(auto1);
        entornoSimulacion.agregarVehiculo(dron1);
        entornoSimulacion.agregarVehiculo(anfibio1);
        entornoSimulacion.agregarVehiculo(submarino1);

        CargaFragil carga1 = new CargaFragil();
        carga1.setPeso(2.5);
        carga1.verificarSeguridad();

        Mision mision1 = new Mision("Ciudad A", "Ciudad B", auto1);
        EntregaUrgente mision2 = new EntregaUrgente("Puerto 1", "Isla 2", dron1, 3.0);

        entornoSimulacion.agregarMision(mision1);
        entornoSimulacion.agregarMision(mision2);

        entornoSimulacion.simularCiclo();
    }
}