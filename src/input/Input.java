package input;

import java.util.List;


/**
 * Clasa contine informatiile parsate din testele de input
 */

public final class Input {

    private int numberOfTurns;
    private InitialData initialData;
    private List<MonthlyUpdates> monthlyUpdates;

    /**
     *
     * @return Intoarce numarul de runde
     */
    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    /**
     *
     * Seteaza numarul de runde
     */
    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    /**
     *
     * @return Intoarce un obiect de tip InitialData
     */
    public InitialData getInitialData() {
        return initialData;
    }

    /**
     *
     * Seteaza un obiect de tip InitialData
     */
    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    /**
     *
     * @return Intoarce o lista cu update-uri lunare
     */
    public List<MonthlyUpdates> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    /**
     *
     * Seteaza o lista de update-uri lunare
     */
    public void setMonthlyUpdates(final List<MonthlyUpdates> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }

    @Override
    public String toString() {
        return "Input{" +
                "numberOfTurns=" + numberOfTurns +
                ", initialData=" + initialData +
                ", monthlyUpdates=" + monthlyUpdates +
                '}';
    }
}
