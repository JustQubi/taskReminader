package pl.justqubi.taskReminader.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.justqubi.taskReminader.models.enteties.NoteEntity;
import pl.justqubi.taskReminader.models.enteties.UserEntity;

import java.util.List;

@Repository
public interface NotesRepository extends CrudRepository {

    @Query(nativeQuery = true, value = "SELECT * FROM notes")
    List<NoteEntity> findAllNotes();

    List<NoteEntity> findByUser(UserEntity userEntity);

    boolean existsByTitle(String title);
}
