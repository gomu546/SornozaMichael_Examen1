import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import smbomba.smBBA;
import smUtilidades.smLoading;
import smHormiga.smHSoldado;

public class smAntCiberDron implements smIA {

    private String smTipoArsenal = "";

    private smBBA automata;
    private Scanner scanner;
    private smHSoldado hormigaSoldado;

    private static final String RUTA_ARCHIVO = "SornozaMichael.cvs";
    private static final String SEPARADOR_CSV = ";";

    public smAntCiberDron() {
        this.automata = new smBBA();
        this.scanner = new Scanner(System.in);
        this.hormigaSoldado = new smHSoldado();
        System.out.println(smLoading.COLOR_BLUE + "Sistema Anti-Ciber Dron Inicializado." + smLoading.COLOR_RESET);
    }

    public void mostrarMenuPrincipal() {
        this.pedirCadenaArsenal();

        int opcion;
        do {
            System.out.println(smLoading.COLOR_PURPLE + "\n--- MENÚ PRINCIPAL ---" + smLoading.COLOR_RESET);
            System.out.println("1. Mostrar Información del Usuario");
            System.out.println("2. Mostrar Horarios de Procesamiento (Carga por Coordenada)");
            System.out.println("3. Mostrar Estado Vital de la Hormiga Soldado");
            System.out.println("4. Verificar si la Bomba Explotó");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1 -> smmostrarInformacion();
                    case 2 -> smmostrarHorarios();
                    case 3 -> smmostrarEstadoHormiga();
                    case 4 -> smverificarBombaExplota();
                    case 5 ->
                        System.out.println(smLoading.COLOR_RED + "Apagando el Sistema..." + smLoading.COLOR_RESET);
                    default -> System.out.println(
                            smLoading.COLOR_RED + "Opción inválida. Intente de nuevo." + smLoading.COLOR_RESET);
                }
            } else {
                System.out.println(
                        smLoading.COLOR_RED + "Entrada inválida. Debe ingresar un número." + smLoading.COLOR_RESET);
                scanner.nextLine();
                opcion = 0;
            }
        } while (opcion != 5);
    }

    private void pedirCadenaArsenal() {
        System.out.println(smLoading.COLOR_YELLOW + "\n--- INGRESO DE DATOS ---" + smLoading.COLOR_RESET);
        System.out.print("Ingrese la cadena de Tipo Arsenal para las verificaciones: ");
        this.smTipoArsenal = scanner.nextLine().trim();
        System.out.println(
                smLoading.COLOR_GREEN + "Cadena ingresada: [" + this.smTipoArsenal + "]" + smLoading.COLOR_RESET);
    }

    public void smmostrarInformacion() {
        System.out.println(smLoading.COLOR_CYAN + "\n--- INFORMACIÓN DEL USUARIO ---" + smLoading.COLOR_RESET);
        System.out.println("Nombre: Michael Sornoza");
        System.out.println("Cedula: 1751268655");
    }

    private void smmostrarEstadoHormiga() {
        System.out.println(smLoading.COLOR_YELLOW + "\n--- ESTADO VITAL DE LA HORMIGA ---" + smLoading.COLOR_RESET);
        this.hormigaSoldado.smcomer();
    }

    @Override
    public boolean smbuscar(boolean resultadoAutomata) {
        return resultadoAutomata;
    }

    private void smverificarBombaExplota() {
        System.out.println(
                smLoading.COLOR_YELLOW + "\n--- VERIFICACIÓN DE EXPLOSIÓN / ACCIÓN ---" + smLoading.COLOR_RESET);

        boolean esArsenalValido = automata.smautomataBBA(this.smTipoArsenal);
        boolean accionExitosa = this.smbuscar(esArsenalValido);

        mostrarTablaAccion(accionExitosa, this.smTipoArsenal);
    }

    private void mostrarTablaAccion(boolean resultadoAccion, String cadenaArsenal) {
        String accionStr = resultadoAccion
                ? smLoading.COLOR_GREEN + "true" + smLoading.COLOR_RESET
                : smLoading.COLOR_RED + "false" + smLoading.COLOR_RESET;

        String titulo = resultadoAccion
                ? smLoading.COLOR_RED + "[+] COORDENADAS UCRANIANAS A DESTRUIR:" + smLoading.COLOR_RESET
                : smLoading.COLOR_GREEN + "[+] COORDENADAS VERIFICADAS (SEGURO):" + smLoading.COLOR_RESET;

        System.out.println("\n" + titulo);

        System.out.printf("| %-12s | %-15s | %-10s |%n", "Geoposición", "Tipo Arsenal", "Acción");
        System.out.println("-----------------------------------------------------");

        System.out.printf("| %-12s | %-15s | %-10s |%n",
                "Coord-05", cadenaArsenal, accionStr);

        System.out.println("-----------------------------------------------------");

        if (resultadoAccion) {
            System.out.println(
                    smLoading.COLOR_RED + "¡ALERTA! SE ACTIVÓ LA ACCIÓN DE DESTRUCCIÓN." + smLoading.COLOR_RESET);
        } else {
            System.out.println(smLoading.COLOR_GREEN + "Patrón de arsenal rechazado. NO se activa la acción."
                    + smLoading.COLOR_RESET);
        }
    }

    
    private String safeCampo(String[] campos, int index) {
        if (index < campos.length && !campos[index].trim().isEmpty()) {
            return campos[index].trim();
        }
        return " ";
    }

   
    public void smmostrarHorarios() {
        System.out.println(smLoading.COLOR_YELLOW + "\n--- INICIANDO CARGA DE HORARIOS ---" + smLoading.COLOR_RESET);
        System.out
                .println(smLoading.COLOR_CYAN + "Simulando carga por coordenada (0% a 100%):" + smLoading.COLOR_RESET);

        try (Scanner fileScanner = new Scanner(new File(RUTA_ARCHIVO))) {
            if (fileScanner.hasNextLine())
                fileScanner.nextLine();

            while (fileScanner.hasNextLine()) {
                String linea = fileScanner.nextLine();
                String[] campos = linea.split(SEPARADOR_CSV, -1);

                String coordenada = safeCampo(campos, 0);
                simularCargaAl100PorCiento(coordenada);
            }

        } catch (FileNotFoundException e) {
            System.err
                    .println(smLoading.COLOR_RED + "ERROR: Archivo de horarios no encontrado." + smLoading.COLOR_RESET);
            return;
        }

        System.out.println(smLoading.COLOR_CYAN + "\n[+] COORDENADAS UCRANIANAS:" + smLoading.COLOR_RESET);

        System.out.printf("| %-8s | %-12s | %-6s | %-6s | %-8s | %-6s | %-6s | %-15s |%n",
                "Loading", "Geoposición", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Tipo Arsenal");
        System.out.println(
                "|----------|--------------|--------|--------|----------|--------|--------|-----------------|");

        try (Scanner printScanner = new Scanner(new File(RUTA_ARCHIVO))) {

            if (printScanner.hasNextLine())
                printScanner.nextLine();

            while (printScanner.hasNextLine()) {
                String linea = printScanner.nextLine();
                String[] campos = linea.split(SEPARADOR_CSV, -1);

                String geoposicion = safeCampo(campos, 0);
                String lunes = safeCampo(campos, 1);
                String martes = safeCampo(campos, 2);
                String miercoles = safeCampo(campos, 3);
                String jueves = safeCampo(campos, 4);
                String viernes = safeCampo(campos, 5);
                String arsenal = safeCampo(campos, 6);

                System.out.printf("| %-8s | %-12s | %-6s | %-6s | %-8s | %-6s | %-6s | %-15s |%n",
                        smLoading.COLOR_GREEN + "100%" + smLoading.COLOR_RESET,
                        geoposicion, lunes, martes, miercoles, jueves, viernes, arsenal);
            }

            System.out.println(
                    "-----------------------------------------------------------------------------------------");

        } catch (FileNotFoundException ignored) {
        }
    }

    private void simularCargaAl100PorCiento(String coordenada) {
        final int TOTAL_ITERACIONES = 30;

        for (int i = 0; i <= TOTAL_ITERACIONES; i++) {
            int porcentaje = (i * 100) / TOTAL_ITERACIONES;
            String spinnerFrame = smLoading.printSpinnerFrame();

            String progressBar = String.format("[%s%s]",
                    smLoading.COLOR_GREEN + "#".repeat(i * 30 / TOTAL_ITERACIONES),
                    smLoading.COLOR_WHITE + "-".repeat(30 - (i * 30 / TOTAL_ITERACIONES)));

            System.out.print(String.format("\r%s %s Procesando: %s %3d%%",
                    spinnerFrame, coordenada, progressBar, porcentaje));

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.print("\r" + " ".repeat(100) + "\r");
        System.out.println(smLoading.COLOR_GREEN + " [LISTO]" + smLoading.COLOR_RESET);
    }
}
