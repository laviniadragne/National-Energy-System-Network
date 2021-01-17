package output;


/**
 * Clasa contine informatiile ce trebuie scrise in output
 * corespunzatoare unui consumator
 */
public class ConsumerOutputData {

    private Integer id;
    private final boolean isBankrupt;
    private Integer budget;

    public final Integer getId() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id = id;
    }

    public final boolean isBankrupt() {
        return isBankrupt;
    }

    public final Integer getBudget() {
        return budget;
    }

    public final void setBudget(final Integer budget) {
        this.budget = budget;
    }

    public ConsumerOutputData(final Integer id, final boolean isBankrupt,
                              final Integer buget) {
        this.id = id;
        this.isBankrupt = isBankrupt;
        this.budget = buget;
    }

}
