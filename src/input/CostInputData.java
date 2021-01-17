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

    public final Integer getId() {
        return id;
    }

    public final void setId(final Integer id) {
        this.id = id;
    }

    public final Integer getInfrastructureCost() {
        return infrastructureCost;
    }

}
