package uz.jl.repository.subject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.types.ObjectId;
import uz.jl.container.UNIContainer;
import uz.jl.criteria.subject.SubjectCriteria;
import uz.jl.dto.subject.SubjectCreateDto;
import uz.jl.dto.subject.SubjectDto;
import uz.jl.dto.subject.SubjectUpdateDto;
import uz.jl.entity.subject.Subject;
import uz.jl.mappers.subject.SubjectMapper;
import uz.jl.repository.AbstractRepository;
import uz.jl.repository.GenericCRUDRepository;
import uz.jl.repository.GenericRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SubjectRepository extends AbstractRepository implements GenericCRUDRepository<SubjectCreateDto, SubjectUpdateDto, SubjectDto, String>, GenericRepository<SubjectDto, SubjectCriteria> {
    private static final SubjectMapper MAPPER = UNIContainer.getBean(SubjectMapper.class);

    @Override
    public String create(SubjectCreateDto dto) {
        MongoCollection<Subject> collection = getDatabase().getCollection(property.get("db.collection.subject"), Subject.class);
        Subject subject = MAPPER.fromCreate(dto);
        collection.insertOne(subject);
        return "Successfully created";
    }

    @Override
    public Void update(SubjectUpdateDto dto) {
        MongoCollection<Subject> collection = getDatabase().getCollection(property.get("db.collection.subject"), Subject.class);
        Subject subject = MAPPER.fromUpdate(dto);
        collection.updateOne(Filters.eq("_id", subject.getId()), Updates.combine(
                Updates.set("title", subject.getTitle()),
                Updates.set("code", subject.getCode()),
                Updates.set("description", subject.getDescription())
        ));
        return null;
    }

    @Override
    public Void delete(String key) {
        MongoCollection<Subject> collection = getDatabase().getCollection(property.get("db.collection.subject"), Subject.class);
        collection.deleteOne(Filters.eq("_id", new ObjectId(key)));
        return null;
    }

    @Override
    public SubjectDto get(String key) {
        MongoCollection<Subject> collection = getDatabase().getCollection(property.get("db.collection.subject"), Subject.class);
        FindIterable<Subject> subjects = collection.find(Filters.eq("code", key));
        if (Objects.isNull(subjects.first()))
            throw new RuntimeException("subject not found");
        return MAPPER.to(Objects.requireNonNull(subjects.first()));
    }

    @Override
    public List<SubjectDto> list(SubjectCriteria criteria) {
        List<SubjectDto> list = new ArrayList<>();
        MongoCollection<Subject> collection = getDatabase().getCollection(property.get("db.collection.subject"), Subject.class);
        FindIterable<Subject> subjects = collection.find();
        for (Subject subject : subjects) {
            list.add(MAPPER.to(subject));
        }
        return list;
    }
}
