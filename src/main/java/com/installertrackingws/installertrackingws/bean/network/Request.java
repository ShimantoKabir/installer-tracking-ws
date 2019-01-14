package com.installertrackingws.installertrackingws.bean.network;

import com.installertrackingws.installertrackingws.bean.material.TaskBn;
import com.installertrackingws.installertrackingws.bean.treasure.CostBreakDownBn;
import com.installertrackingws.installertrackingws.bean.workOrder.WorkOrderDetailBn;

import java.util.List;

public class Request {

    public WorkOrderDetailBn workOrderDetailBn;
    public List<TaskBn> taskBnList;
    public CostBreakDownBn costBreakDownBn;

    public Request() {}

    public List<TaskBn> getTaskBnList() {
        return taskBnList;
    }

    public CostBreakDownBn getCostBreakDownBn() {
        return costBreakDownBn;
    }

    public void setCostBreakDownBn(CostBreakDownBn costBreakDownBn) {
        this.costBreakDownBn = costBreakDownBn;
    }

    public void setTaskBnList(List<TaskBn> taskBnList) {
        this.taskBnList = taskBnList;
    }

    public WorkOrderDetailBn getWorkOrderDetailBn() {
        return workOrderDetailBn;
    }

    public void setWorkOrderDetailBn(WorkOrderDetailBn workOrderDetailBn) {
        this.workOrderDetailBn = workOrderDetailBn;
    }
}
