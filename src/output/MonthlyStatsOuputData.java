package output;

import java.util.List;

/**
 * Clasa contine informatiile ce trebuie scrise in output
 * corespunzatoare situatiei dintr-o luna a simularii
 */
public class MonthlyStatsOuputData {
    private Integer month;
    private List<Integer> distributorsIds;

    public final Integer getMonth() {
        return month;
    }

    public final void setMonth(Integer month) {
        this.month = month;
    }

    public final List<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public final void setDistributorsIds(List<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }

    public MonthlyStatsOuputData(Integer month, List<Integer> distributorsIds) {
        this.month = month;
        this.distributorsIds = distributorsIds;
    }

}
