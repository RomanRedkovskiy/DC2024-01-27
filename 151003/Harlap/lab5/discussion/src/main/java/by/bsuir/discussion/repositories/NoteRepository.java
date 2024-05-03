package by.bsuir.discussion.repositories;

import by.bsuir.discussion.domain.Note;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.MapIdCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends MapIdCassandraRepository<Note> {
    @AllowFiltering
    void deleteNoteBytweetIdAndId(Long id, Long uuid);
    @AllowFiltering //Bad solution, BUT generally we need to search all notes of certain tweet, so
                    //this search is redundant and that's why tweetId is a partition key
    Optional<Note> findNoteById(Long id);
    Optional<Note> findNoteBytweetIdAndId(Long id, Long uuid);
}