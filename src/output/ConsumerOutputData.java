package output;


/**
 * Clasa contine informatiile ce trebuie scrise in output
 * corespunzatoare unui consumator
 */
public class ConsumerOutputData {

    private Integer id;
    private boolean isBankrupt;
    private Integer budget;

    /**
     *
     * @return Intoarce id-ul unui consumator
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * Seteaza id-ul unui consumator
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     *
     * @return Intoarce starea unui consumator
     */
    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     *
     * Seteaza starea unui consumator
     */
    public void setBankrupt(final boolean bankrupt) {
        this.isBankrupt = bankrupt;
    }

    /**
     *
     * @return Intoarce bugetul unui consumator
     */
    public Integer getBudget() {
        return budget;
    }

    /**
     *
     * Seteaza bugetul unui consumator
     */
    public void setBudget(final Integer budget) {
        this.budget = budget;
    }

    public ConsumerOutputData(final Integer id, final boolean isBankrupt,
                              final Integer buget) {
        this.id = id;
        this.isBankrupt = isBankrupt;
        this.budget = buget;
    }

}
