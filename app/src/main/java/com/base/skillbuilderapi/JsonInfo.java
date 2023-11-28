package com.base.skillbuilderapi;

import java.util.List;

public class JsonInfo {
    private int status;
    private String message;
    private long last_updated;
    private Data data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getLastUpdated() {
        return last_updated;
    }

    public Data getData() {
        return data;
    }

    public static class Data {
        private List<ElementProgressEntity> element_progress;
        private List<ElementEntity> elements;
        private List<ChapterEntity> chapters;

        public List<ElementProgressEntity> getElementProgress() {
            return element_progress;
        }

        public List<ElementEntity> getElements() {
            return elements;
        }

        public List<ChapterEntity> getChapters() {
            return chapters;
        }
    }

    public static class ElementProgress {
        private int chapter_id;
        private int element_id;
        private int element_type;
        private String user_name;
        private int milestone_level;
        private long milestone_date;
        private int current_progress;
        private int max_star;
        private int certificate_earned;
        private long certificate_date;

        public int getChapter_id() {
            return chapter_id;
        }

        public int getElement_id() {
            return element_id;
        }

        public int getElement_type() {
            return element_type;
        }

        public String getUser_name() {
            return user_name;
        }

        public int getMilestone_level() {
            return milestone_level;
        }

        public long getMilestone_date() {
            return milestone_date;
        }

        public int getCurrent_progress() {
            return current_progress;
        }

        public int getMax_star() {
            return max_star;
        }

        public int getCertificate_earned() {
            return certificate_earned;
        }

        public long getCertificate_date() {
            return certificate_date;
        }
    }

    public static class Element {
        private int element_id;
        private int chapter_id;
        private int display_id;
        private int element_type;
        private int sb_type;
        private String element_name;
        private int element_is_deleted;

        public int getElement_id() {
            return element_id;
        }

        public int getChapter_id() {
            return chapter_id;
        }

        public int getDisplay_id() {
            return display_id;
        }

        public int getElement_type() {
            return element_type;
        }

        public int getSb_type() {
            return sb_type;
        }

        public String getElement_name() {
            return element_name;
        }

        public int getElement_is_deleted() {
            return element_is_deleted;
        }
    }

    public static class Chapter {
        private int chapter_id;
        private String chapter_name;
        private int display_id;
        private int deleted;

        public int getChapter_id() {
            return chapter_id;
        }

        public String getChapter_name() {
            return chapter_name;
        }

        public int getDisplay_id() {
            return display_id;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}
