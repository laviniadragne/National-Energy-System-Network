import input.Input;
import input.InputLoader;
import output.Output;
import output.Writer;
import simulation.Simulation;
//import simulation.Simulation;

/**
 * Entry point to the simulation
 */
public final class Main {

    private Main() { }

    /**
     * Main function which reads the input file and starts simulation
     *
     * @param args input and output files
     * @throws Exception might error when reading/writing/opening files, parsing JSON
     */
    public static void main(final String[] args) throws Exception {
        // Creez inputul
//        InputLoader inputLoader = new InputLoader("/home/lavinia/Desktop/POO/Proiect/proiect-etapa2/proiect-etapa2-energy-system/checker/resources/in/basic_15.json");
        InputLoader inputLoader = new InputLoader(args[0]);
        Input input = inputLoader.readData();

//        System.out.println(input.toString());


        // Fac simularea
        Output output =  Simulation.getSimulation(input);

        // Scriu output-ul
//        Writer writer = new Writer("/home/lavinia/Desktop/POO/Proiect/proiect-etapa2/proiect-etapa2-energy-system/checker/resources/out/basic_15.json");
        Writer writer = new Writer(args[1]);
        writer.writeData(output);
    }
}
