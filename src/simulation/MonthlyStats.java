package simulation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Clasa contine informatiile despre update-urile
 * dintr-o luna folosite in simularea jocului
 */
public class MonthlyStats {
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

    public MonthlyStats(Integer month) {
        this.month = month;
        this.distributorsIds = new ArrayList<>();
    }

    /**
     * Sorteaza lista de id-uri ale distribuitorilor
     */
    public void sortId() {
        distributorsIds.sort(Comparator.comparingInt(m -> m));
    }

}
