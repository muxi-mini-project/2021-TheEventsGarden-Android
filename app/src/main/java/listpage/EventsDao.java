package listpage;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventsDao {
    @Insert
    void InsertEvents(EventsLab... eventsLabs);

    @Query("SELECT * FROM EventsLab ORDER BY ID")
    List<EventsLab> getAllEvents();
}
