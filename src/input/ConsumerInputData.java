package input;


/**
 * Clasa contine informatiile parsate din testele de input
 * corespunzatoare unui consumator
 */
public class ConsumerInputData {
    private Integer id;
    private Integer initialBudget;
    private Integer monthlyIncome;

    public final Integer getId() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id = id;
    }

    public final Integer getInitialBudget() {
        return initialBudget;
    }

    public final void setInitialBudget(final Integer initialBudget) {
        this.initialBudget = initialBudget;
    }

    public final Integer getMonthlyIncome() {
        return monthlyIncome;
    }

    public final void setMonthlyIncome(final Integer monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}
