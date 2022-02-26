package uz.jl.repository.level;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.types.ObjectId;
import uz.jl.container.UNIContainer;
import uz.jl.dto.level.LevelCreateDto;
import uz.jl.dto.level.LevelDto;
import uz.jl.dto.level.LevelUpdateDto;
import uz.jl.entity.level.Level;
import uz.jl.entity.subject.Subject;
import uz.jl.mappers.level.LevelMapper;
import uz.jl.repository.AbstractRepository;
import uz.jl.repository.GenericCRUDRepository;

import java.util.List;
import java.util.Objects;

public class LevelRepository extends AbstractRepository implements GenericCRUDRepository<LevelCreateDto, LevelUpdateDto, LevelDto, String> {
    private static final LevelMapper MAPPER = UNIContainer.getBean(LevelMapper.class);

    @Override
    public String create(LevelCreateDto dto) {
        MongoCollection<Subject> collection = getDatabase().getCollection(property.get("db.collection.subject"), Subject.class);
        Level level = MAPPER.fromCreate(dto);
        level.setId(new ObjectId());
        collection.updateOne(Filters.eq("_id", new ObjectId(dto.getSubjectId())), Updates.addToSet("levels", level));
        return "Successfully created";
    }

    @Override
    public Void update(LevelUpdateDto dto) {
        MongoCollection<Subject> collection = getDatabase().getCollection(property.get("db.collection.subject"), Subject.class);
        FindIterable<Subject> subjects = collection.find(Filters.eq("_id", new ObjectId(dto.getSubjectId())));
        List<Level> levels = Objects.requireNonNull(subjects.first()).getLevels();
        for (Level level : levels) {
            if (level.getId().toString().equals(dto.getId())) {
                level.setTitle(dto.getTitle());
                level.setCode(dto.getCode());
                level.setDescription(dto.getDescription());
                break;
            }
        }
        collection.updateOne(Filters.eq("_id", new ObjectId(dto.getSubjectId())), Updates.set("levels", levels));
        return null;
    }

    @Override
    public Void delete(String key) {
        return null;
    }

    public Void deleteLevel(String subjectId, String levelId) {
        MongoCollection<Subject> collection = getDatabase().getCollection(property.get("db.collection.subject"), Subject.class);
        FindIterable<Subject> subjects = collection.find(Filters.eq("_id", new ObjectId(subjectId)));
        List<Level> levels = Objects.requireNonNull(subjects.first()).getLevels();
        for (Level level : levels) {
            if (level.getId().toString().equals(levelId)) {
                levels.remove(level);
                break;
            }
        }
        collection.updateOne(Filters.eq("_id", new ObjectId(subjectId)), Updates.set("levels", levels));
        return null;
    }

    @Override
    public LevelDto get(String key) {
        return null;
    }

    public LevelDto getLevel(String subjectId, String levelId) {
        MongoCollection<Subject> collection = getDatabase().getCollection(property.get("db.collection.subject"), Subject.class);
        FindIterable<Subject> subjects = collection.find(Filters.eq("_id", new ObjectId(subjectId)));
        for (Level level : Objects.requireNonNull(subjects.first()).getLevels()) {
            if (level.getId().toString().equals(levelId)) {
                return MAPPER.to(level);
            }
        }
        return new LevelDto();
    }
}
