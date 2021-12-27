package eapli.base.workflowmanagement.domain;

public enum WorkflowStatus {

    /**
     * This workflow is currently active
     */
    ACTIVE,

    /**
     * This workflow is scheduled to be active in the future
     */
    SCHEDULED,

    /**
     * This workflow in not currently active
     */
    INACTIVE,
}
