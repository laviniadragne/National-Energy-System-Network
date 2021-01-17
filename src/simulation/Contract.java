package simulation;

/**
 * Clasa contine informatiile despre un contract
 * folosita in simularea jocului
 */
public class Contract {

    private Integer consumerId;
    private Integer price;
    private Integer remainedContractMonths;
    private Integer distributorId;

    public final Integer getDistributorId() {
        return distributorId;
    }

    public final void setDistributorId(final Integer distributorId) {
        this.distributorId = distributorId;
    }

    public final Integer getConsumerId() {
        return consumerId;
    }

    public final void setConsumerId(final Integer consumerId) {
        this.consumerId = consumerId;
    }

    public final Integer getPrice() {
        return price;
    }

    public final void setPrice(final Integer price) {
        this.price = price;
    }

    public final Integer getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public final void setRemainedContractMonths(final Integer remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

    public Contract(final Integer consumerId, final Integer price,
                    final Integer remainedContractMonths) {
        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
        // Initial un consumator nu este abonat nicaieri
        this.distributorId = -1;
    }

}
