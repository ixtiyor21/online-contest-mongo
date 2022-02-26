package uz.jl.services.quiz;

import uz.jl.entity.level.Level;
import uz.jl.entity.question.Question;
import uz.jl.entity.quiz.Quiz;
import uz.jl.entity.subject.Subject;
import uz.jl.repository.quiz.QuizRepository;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.services.AbstractService;

import java.util.ArrayList;
import java.util.List;

import static uz.jl.utils.Color.RED;

public class QuizService extends AbstractService<QuizRepository> {
    public QuizService(QuizRepository repository) {
        super(repository);
    }

    public void saveQuiz(Quiz quiz) {
        repository.saveQuiz(quiz);
    }

    public ResponseEntity<Data<Quiz>> historyQuiz() {
        try {
            List<Quiz> quizzes = repository.historyQuiz();
            int k=quizzes.size();
            return new ResponseEntity<>(new Data<>(quizzes.get(k-1)));
        } catch (Exception e) {
            throw new RuntimeException(RED+"Quiz not found");
        }
    }

    public ResponseEntity<Data<List<Subject>>> getSubject() {
        List<Subject> subject = repository.getSubject();
        return new ResponseEntity<>(new Data<>(subject));
    }

    public ResponseEntity<Data<List<Level>>> getLevel(String subjectCode) {
        List<Level> level = repository.getLevel(subjectCode);
        return new ResponseEntity<>(new Data<>(level));
    }

    public ResponseEntity<Data<Integer>> getCount(String subjectCode, String levelCode) {
        Integer count = repository.getCount(subjectCode, levelCode);
        return new ResponseEntity<>(new Data<>(count));
    }

    public ResponseEntity<Data<List<Question>>> getQuestion(String subjectCode, String levelCode, Integer count) {
        List<Question> question = repository.getQuestion(subjectCode, levelCode, count);
        return new ResponseEntity<>(new Data<>(question));
    }
}
