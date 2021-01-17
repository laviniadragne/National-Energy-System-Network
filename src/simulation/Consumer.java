package simulation;

/**
 * Clasa contine informatiile despre un consumator
 * folosit in simularea jocului
 */
public class Consumer implements Entity {

    private Integer id;
    private Integer initialBudget;
    private Integer monthlyIncome;
    private Contract contract;
    private boolean isBankrupt;
    private Integer idOverdue;
    private Integer priceOverdue;

    @Override
    public final Integer getId() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id = id;
    }

    @Override
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

    public final Contract getContract() {
        return contract;
    }

    public final void setContract(final Contract contract) {
        this.contract = contract;
    }

    @Override
    public final boolean isBankrupt() {
        return isBankrupt;
    }

    public final void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public final Integer getIdOverdue() {
        return idOverdue;
    }

    public final void setIdOverdue(final Integer idOverdue) {
        this.idOverdue = idOverdue;
    }

    public final Integer getPriceOverdue() {
        return priceOverdue;
    }

    public final void setPriceOverdue(final Integer priceOverdue) {
        this.priceOverdue = priceOverdue;
    }

    public Consumer(final Integer id, final Integer initialBudget,
                    final Integer monthlyIncome) {
        this.id = id;
        this.initialBudget = initialBudget;
        this.monthlyIncome = monthlyIncome;
        // Initial un contract are pretul 0 si lunile ramase de plata 0
        this.contract = new Contract(this.id, 0, 0);
        this.isBankrupt = false;
        // Nu e restant nimanui initial
        this.idOverdue = -1;
        this.priceOverdue = -1;
    }

    /**
     * Adauga salariu in luna respectiva
     */
    public void updateSalary() {
        Integer actualSalary = getMonthlyIncome();
        Integer actualBuget = getInitialBudget();

        actualBuget += actualSalary;
        setInitialBudget(actualBuget);
    }

    /**
     * Scade numarul de luni ramase in contract
     */
    public void updateMonths() {
        // Scad numarul de luni ramase in contract
        Integer actualMonths = getContract().getRemainedContractMonths();
        actualMonths--;
        getContract().setRemainedContractMonths(actualMonths);
    }
}
