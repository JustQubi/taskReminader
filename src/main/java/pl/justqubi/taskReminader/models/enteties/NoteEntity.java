package pl.justqubi.taskReminader.models.enteties;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "notes")
@Data
public class NoteEntity {
    private @Id
    @GeneratedValue
    int id;
    private String title;
    private String priority;
    private String date;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_login_id")
    private UserEntity user;

    @Override
    public String toString() {
        return "NoteEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", priority='" + priority + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
