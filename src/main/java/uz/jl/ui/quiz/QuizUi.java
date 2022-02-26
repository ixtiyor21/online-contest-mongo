package uz.jl.ui.quiz;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uz.jl.entity.answers.Answers;
import uz.jl.entity.level.Level;
import uz.jl.entity.question.Question;
import uz.jl.entity.quiz.Quiz;
import uz.jl.entity.subject.Subject;
import uz.jl.entity.variant.Variant;
import uz.jl.enums.QuizStatus;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.services.quiz.QuizService;
import uz.jl.services.session.SessionService;
import uz.jl.ui.AbstractUi;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.awt.Color.PINK;
import static uz.jl.utils.Color.PURPLE;
import static uz.jl.utils.Color.RED;

public class QuizUi extends AbstractUi<QuizService> {
    private static final Quiz QUIZ = new Quiz();
    private static List<Answers> ANSWERS = new ArrayList<>();

    public QuizUi(QuizService service) {
        super(service);
    }

    public void historyQuiz() {
        try {
            ResponseEntity<Data<Quiz>> response = service.historyQuiz();
            Quiz quiz = response.getData().getBody();

            Print.println(PURPLE + "Username : " + quiz.getUser().getUsername());
            Print.println(PURPLE + "Score : " + quiz.getScore()+"/"+quiz.getMaxScore()+ " ball");
            Print.println(PURPLE + "Questions number : " + quiz.getAnswers().size() + "\n");

            List<Answers> answers = quiz.getAnswers();
            for (int i = 1; i <= answers.size(); i++) {
                Answers answer = answers.get(i - 1);
                Print.println(PURPLE + "" + (i) + "." + "Question : " + answer.getQuestion().getContext());
                Print.println(PURPLE + "Question ball : " + answer.getQuestion().getBall());
                Print.println(PURPLE + "Your answer : " + answer.getAnswer() + ")" + answer.getValue());
                Print.println(PURPLE + "Is correct : " + answer.getIsTrue());
            }
        }catch (Exception e){
            Print.println(e.getMessage());
        }
    }

    public void questionProcess(Question question) {
        Print.println(Color.YELLOW, question.getTitle() + " = ball = " + question.getBall() + "\n" + question.getContext());
        QUIZ.setMaxScore(QUIZ.getMaxScore()+question.getBall());
        for (Variant variant : question.getVariants()) {
            Print.println(Color.CYAN,variant.getCode()+") "+ variant.getContext());
        }
        String answer = Input.getStr("you answer = ");
        for (Variant variant : question.getVariants()) {
            if (variant.getCode().equals(answer)) {
                Answers answers = new Answers(answer,variant.getContext(),question, variant.getIsTrue());
                if (variant.getIsTrue()) {
                    QUIZ.setScore(QUIZ.getScore() + question.getBall());
                }
                ANSWERS.add(answers);
                break;
            }
        }
    }

    public void startQuiz() {
        ResponseEntity<Data<List<Subject>>> response = service.getSubject();
        for (Subject subject : response.getData().getBody()) {
            Print.println(Color.WHITE, subject.getCode());
        }
        String subject = Input.getStr("subject = ");
        ResponseEntity<Data<List<Level>>> response1 = service.getLevel(subject);
        for (Level level : response1.getData().getBody()) {
            Print.println(Color.WHITE, level.getCode());
        }
        String level = Input.getStr("level = ");
        Integer count = Input.getNum("count = ");
        ResponseEntity<Data<List<Question>>> question = service.getQuestion(subject, level, count);
        QUIZ.setUser(SessionService.getSession());
        for (Question question1 : question.getData().getBody()) {
            questionProcess(question1);
        }
        QUIZ.setAnswers(ANSWERS);
        QUIZ.setStatus(QuizStatus.DONE);
        service.saveQuiz(QUIZ);
        ANSWERS=new ArrayList<>();
        Print.println(Color.GREEN, "Quiz finished !");
        Print.println(Color.GREEN, "Your score is :"+QUIZ.getScore()+"/"+QUIZ.getMaxScore()+" ball");
        SessionService.getSession().getQuizzes().add(QUIZ);
        SessionService.getSession().setScore(SessionService.getSession().getScore() + QUIZ.getScore());
    }
}
