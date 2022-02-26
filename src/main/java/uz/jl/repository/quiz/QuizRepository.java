package uz.jl.repository.quiz;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import uz.jl.entity.level.Level;
import uz.jl.entity.question.Question;
import uz.jl.entity.quiz.Quiz;
import uz.jl.entity.subject.Subject;
import uz.jl.entity.user.User;
import uz.jl.repository.AbstractRepository;
import uz.jl.services.session.SessionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class QuizRepository extends AbstractRepository {
    public List<Quiz> historyQuiz() {
        MongoCollection<User> collection = getDatabase().getCollection(property.get("db.collection.user"), User.class);
        FindIterable<User> users = collection.find(Filters.eq("username", SessionService.getSession().getUsername()));
        if (Objects.isNull(users.first())) {
            throw new RuntimeException("user not found");
        }
        try {
            return Objects.requireNonNull(users.first()).getQuizzes();
        }catch (NullPointerException e){
            throw new RuntimeException("quiz not found");
        }
    }

    public void saveQuiz(Quiz quiz) {
        MongoCollection<User> collection = getDatabase().getCollection(property.get("db.collection.user"), User.class);
        Bson bson1 = new Document("username", SessionService.getSession().getUsername());
        User user = collection.find(bson1).first();
        if(user != null)
        user.getQuizzes().add(quiz);
        collection.updateOne(bson1, Updates.combine(Updates.addEachToSet("quizzes", user.getQuizzes()), Updates.inc("score", user.getScore() + quiz.getScore())));
    }

    public List<Subject> getSubject() {
        List<Subject> list = new ArrayList<>();
        MongoCollection<Subject> collection = getDatabase().getCollection(property.get("db.collection.subject"), Subject.class);
        FindIterable<Subject> subjects = collection.find();
        for (Subject subject : subjects) {
            list.add(subject);
        }
        return list;
    }

    public List<Level> getLevel(String subjectCode) {
        MongoCollection<Subject> collection = getDatabase().getCollection(property.get("db.collection.subject"), Subject.class);
        FindIterable<Subject> subjects = collection.find(Filters.eq("code", subjectCode));
        return Objects.requireNonNull(subjects.first()).getLevels();
    }

    public Integer getCount(String subjectCode, String levelCode) {
        List<Level> level = getLevel(subjectCode);
        for (Level level1 : level) {
            if (level1.getCode().equals(levelCode)) {
                return level1.getQuestions().size();
            }
        }
        return 0;
    }

    public List<Question> getQuestion(String subjectCode, String levelCode, Integer count) {
        List<Question> questions=new ArrayList<>();
        List<Question> questions2=new ArrayList<>();
        List<Level> level = getLevel(subjectCode);
        for (Level level1 : level) {
            if (level1.getCode().equals(levelCode)) {
                questions=level1.getQuestions();
            }
        }
        if (questions.size()==0)
        return new ArrayList<>();
        Random random=new Random();
        for (int i = 1; i <= count; i++) {
            int k=random.nextInt(questions.size());
            questions2.add(questions.get(k));
        }
        return questions2;
    }
}
