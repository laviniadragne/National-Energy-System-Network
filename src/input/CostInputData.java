package input;


/**
 * Clasa contine informatiile parsate din testele de input
 * corespunzatoare unui distributorChanges
 */

public class CostInputData {
    private Integer id;
    private final Integer infrastructureCost;

    public CostInputData() {
        this.infrastructureCost = 0;
    }

    /**
     *
     * @return Intoarce id-ul unui distribuitor
     */
    public Integer getId() {
        return id;
    }

    /**
     * Seteaza id-ul
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     *
     * @return Intoarce costul infrastructurii
     */
    public Integer getInfrastructureCost() {
        return infrastructureCost;
    }

}
