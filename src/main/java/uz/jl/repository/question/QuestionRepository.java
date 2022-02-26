package uz.jl.repository.question;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import uz.jl.container.UNIContainer;
import uz.jl.dto.question.QuestionCreateDto;
import uz.jl.dto.question.QuestionDto;
import uz.jl.dto.question.QuestionUpdateDto;
import uz.jl.entity.level.Level;
import uz.jl.entity.question.Question;
import uz.jl.entity.subject.Subject;
import uz.jl.mappers.question.QuestionMapper;
import uz.jl.repository.AbstractRepository;
import uz.jl.repository.GenericCRUDRepository;

public class QuestionRepository extends AbstractRepository implements GenericCRUDRepository<QuestionCreateDto, QuestionUpdateDto, QuestionDto, String> {
    private static final QuestionMapper MAPPER = UNIContainer.getBean(QuestionMapper.class);

    @Override
    public String create(QuestionCreateDto dto) {
        MongoCollection<Subject> collection = getDatabase().getCollection(property.get("db.collection.subject"), Subject.class);
        Question question = MAPPER.fromCreate(dto);
        question.setId(new ObjectId());
        Subject subjects=collection.find(Filters.eq("code",dto.getSubjectCode())).first();
        if(subjects!=null) {
            for (Level level : subjects.getLevels()) {
                if (level.getCode().equalsIgnoreCase(dto.getLevelCode())) {
                    level.getQuestions().add(question);
                    break;
                }
            }
            UpdateResult result = collection.updateOne(Filters.eq("code", question.getSubjectCode()), Updates.set("levels", subjects.getLevels()));
            System.out.println(result);
            return "Successfully created";
        }
        return "Can't created!";
    }

    @Override
    public Void update(QuestionUpdateDto dto) {
        MongoCollection<Subject> collection = getDatabase().getCollection(property.get("db.collection.subject"), Subject.class);
        FindIterable<Subject> subjects = collection.find(Filters.eq("_id", new ObjectId(dto.getSubjectId())));
       // List<Question> questions = Objects.requireNonNull(subjects.first().getLevels());
        return null;
    }

    @Override
    public Void delete(String key) {
        return null;
    }

    @Override
    public QuestionDto get(String key) {
        return null;
    }
}
