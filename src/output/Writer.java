package output;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;

/**
 * Clasa scrie pe baza unei clase de output datele
 * intr-un fisier de la o cale primita ca si parametru
 */
public final class Writer {

    private final String outputPath;

    /**
     *
     * @param outputPath calea catre fisierul de output
     */
    public Writer(final String outputPath) {
        this.outputPath = outputPath;
    }

    /**
     *
     * @return intoarce calea catre fisierul de output
     */
    public String getInputPath() {
        return outputPath;
    }

    /**
     * Scrie in format JSON datele
     * @param output clasa cu datele ce trebuie scrise
     */
    public void writeData(final Output output) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        objectWriter.writeValue(new File(outputPath), output);
    }
}
