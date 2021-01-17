package output;

/**
 * Clasa contine informatiile ce trebuie scrise in output
 * corespunzatoare unui contract
 */
public class ContractOutputData {

    private Integer consumerId;
    private Integer price;
    private Integer remainedContractMonths;

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

    public ContractOutputData(final Integer consumerId, final Integer price,
                              final Integer remainedContractMonths) {
        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

}
