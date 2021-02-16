package listpage;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EventsLab {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "DoneOrUndone")
    private boolean circumstance;

    public EventsLab(String title,int id,boolean circumstance){
        this.title = title;
        this.id = id;
        this.circumstance = circumstance;
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

    public boolean isCircumstance() {
        return circumstance;
    }

    public void setCircumstance(boolean circumstance) {
        this.circumstance = circumstance;
    }
}
