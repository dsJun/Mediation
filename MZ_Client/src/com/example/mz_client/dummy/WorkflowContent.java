package com.example.mz_client.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class WorkflowContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<WorkflowA> ITEMS = new ArrayList<WorkflowA>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, WorkflowA> ITEM_MAP = new HashMap<String, WorkflowA>();

//    static {
//        // Add 3 sample items.
//        addItem(new WorkflowA("1", "dd 1"));
//        addItem(new WorkflowA("2", "Workflow 2"));
//        addItem(new WorkflowA("3", "Workflow 3"));
//        addItem(new WorkflowA("4", "Workflow 4"));
//        addItem(new WorkflowA("5", "Workflow 5"));
//        addItem(new WorkflowA("6", "Workflow 6"));
//        addItem(new WorkflowA("7", "Workflow 7"));
//        addItem(new WorkflowA("8", "Workflow 8"));
//        addItem(new WorkflowA("9", "Workflow 9"));
//    }

    public static void addItem(WorkflowA item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class WorkflowA {
        public String id;
        public String content;
        public boolean status;

        public WorkflowA(String id, String content, boolean status) {
            this.id = id;
            this.content = content;
            this.status = status;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
