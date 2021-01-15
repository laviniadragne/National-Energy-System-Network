package input;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


/**
 * Clasa citeste si parseaza datele din teste
 */
public final class InputLoader {

    /**
     * Calea catre input
     */
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     *
     * @return calea catre fisierul de input
     */
    public String getInputPath() {
        return inputPath;
    }


    /**
     * Metoda citeste baza de date
     * @return un obiect Input
     */
    public Input readData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(inputPath), Input.class);
    }
}
