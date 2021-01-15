package input;


/**
 * Clasa contine informatiile parsate din testele de input
 * corespunzatoare unui consumator
 */
public class ConsumerInputData {
    private Integer id;
    private Integer initialBudget;
    private Integer monthlyIncome;

    /**
     *
     * @return Intoarce id-ul unui consumator
     */
    public Integer getId() {
        return id;
    }

    /**
     * Seteaza id-ul unui consumator
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     *
     * @return Intoarce bugetul unui consumat
     */
    public Integer getInitialBudget() {
        return initialBudget;
    }

    /**
     * Seteaza bugetul unui consumator
     */
    public void setInitialBudget(final Integer initialBudget) {
        this.initialBudget = initialBudget;
    }

    /**
     *
     * @return Intoarce numarul de luni din contract ale unui consumator
     */
    public Integer getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     * Intoarce numarul de luni din contract ale unui consumator
     */
    public void setMonthlyIncome(final Integer monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    @Override
    public String toString() {
        return "ConsumerInputData{" +
                "id=" + id +
                ", initialBudget=" + initialBudget +
                ", monthlyIncome=" + monthlyIncome +
                '}';
    }
}
