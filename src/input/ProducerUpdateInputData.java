package input;

/**
 * Clasa contine informatiile parsate din testele de input
 * corespunzatoare update-urilor unui producator
 */
public class ProducerUpdateInputData {
    private Integer id;
    private Integer energyPerDistributor;

    public final Integer getId() {
        return id;
    }

    public final void setId(Integer id) {
        this.id = id;
    }

    public final Integer getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public final void setEnergyPerDistributor(Integer energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

}
