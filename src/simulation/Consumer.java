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

    /**
     *
     * @return Intoarce id-ul consumatorului
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     *
     * @return Intoarce id-ul consumatorului
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     *
     * @return Intoarce bugetul consumatorului
     */
    @Override
    public Integer getInitialBudget() {
        return initialBudget;
    }

    /**
     *
     * Seteaza bugetul consumatorului
     */
    public void setInitialBudget(final Integer initialBudget) {
        this.initialBudget = initialBudget;
    }

    /**
     *
     * @return Intoarce numarul de luni ramase in contract
     */
    public Integer getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     *
     * Seteaza numarul de luni ramase in contract
     */
    public void setMonthlyIncome(final Integer monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    /**
     *
     * @return Intoarce contractul actual
     */
    public Contract getContract() {
        return contract;
    }

    /**
     * Seteaza contractul actual
     */
    public void setContract(final Contract contract) {
        this.contract = contract;
    }

    /**
     *
     * @return Intoarce daca e falimentar sau nu
     */
    @Override
    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     *
     * Se seteaza daca e falimentar sau nu
     */
    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    /**
     *
     * @return Intoarce id-ul distribuitorului catre care
     * trebuie sa plateasca rata restanta
     */
    public Integer getIdOverdue() {
        return idOverdue;
    }

    /**
     *
     * Seteaza id-ul distribuitorului catre care
     * trebuie sa plateasca rata restanta
     */
    public void setIdOverdue(final Integer idOverdue) {
        this.idOverdue = idOverdue;
    }

    /**
     *
     * @return Intoarce pretul ratei restante
     * pe care trebuie sa o plateasca
     */
    public Integer getPriceOverdue() {
        return priceOverdue;
    }

    /**
     * Seteaza pretul ratei restante
     * pe care trebuie sa o plateasca
     */
    public void setPriceOverdue(final Integer priceOverdue) {
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
        // Nu are niciun contract initial
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

    @Override
    public String toString() {
        return "Consumer{" +
                "id=" + id +
                ", initialBudget=" + initialBudget +
                ", monthlyIncome=" + monthlyIncome +
                ", contract=" + contract +
                ", isBankrupt=" + isBankrupt +
                ", idOverdue=" + idOverdue +
                ", priceOverdue=" + priceOverdue +
                '}';
    }
}
