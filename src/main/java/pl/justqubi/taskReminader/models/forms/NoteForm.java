package pl.justqubi.taskReminader.models.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class NoteForm {
        private String title;
        @Pattern(regexp = "[0-9][0-9]")
        private String priority;
        private String date;
        private String content;

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getPriority() {
                return priority;
        }

        public void setPriority(String priority) {
                this.priority = priority;
        }

        public String getDate() {
                return date;
        }

        public void setDate(String date) {
                this.date = date;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }
}
