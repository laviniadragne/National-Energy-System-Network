package simulation;

import java.util.ArrayList;
import java.util.List;

public class MonthlyStats {
    private Integer month;
    private List<Integer> distributorsIds;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public List<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public void setDistributorsIds(List<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }

    public MonthlyStats(Integer month) {
        this.month = month;
        this.distributorsIds = new ArrayList<>();
    }
}
