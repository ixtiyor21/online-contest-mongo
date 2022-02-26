package uz.jl.container;

import com.google.gson.Gson;
import uz.jl.mappers.auth.AuthMapper;
import uz.jl.mappers.level.LevelMapper;
import uz.jl.mappers.question.QuestionMapper;
import uz.jl.mappers.subject.SubjectMapper;
import uz.jl.mappers.user.UserMapper;
import uz.jl.properties.DbProperty;
import uz.jl.repository.auth.AuthRepository;
import uz.jl.repository.level.LevelRepository;
import uz.jl.repository.question.QuestionRepository;
import uz.jl.repository.quiz.QuizRepository;
import uz.jl.repository.subject.SubjectRepository;
import uz.jl.repository.user.UserRepository;
import uz.jl.services.auth.AuthService;
import uz.jl.services.level.LevelService;
import uz.jl.services.question.QuestionService;
import uz.jl.services.quiz.QuizService;
import uz.jl.services.subject.SubjectService;
import uz.jl.services.user.UserService;
import uz.jl.ui.auth.AuthUi;
import uz.jl.ui.level.LevelUi;
import uz.jl.ui.question.QuestionUi;
import uz.jl.ui.quiz.QuizUi;
import uz.jl.ui.subject.SubjectUi;
import uz.jl.ui.user.UserUi;

public class UNIContainer {
        private static final DbProperty DB_PROPERTY;
        private static final AuthMapper AUTH_MAPPER;
        private static final UserMapper USER_MAPPER;
        private static final SubjectMapper SUBJECT_MAPPER;
        private static final LevelMapper LEVEL_MAPPER;
        private static final QuestionMapper QUESTION_MAPPER;

        private static final AuthRepository AUTH_REPOSITORY;
        private static final AuthService AUTH_SERVICE;
        private static final AuthUi AUTH_UI;

        private static final UserRepository USER_REPOSITORY;
        private static final UserService USER_SERVICE;
        private static final UserUi USER_UI;

        private static final SubjectRepository SUBJECT_REPOSITORY;
        private static final SubjectService SUBJECT_SERVICE;
        private static final SubjectUi SUBJECT_UI;

        private static final LevelRepository LEVEL_REPOSITORY;
        private static final LevelService LEVEL_SERVICE;
        private static final LevelUi LEVEL_UI;

        private static final QuestionRepository QUESTION_REPOSITORY;
        private static final QuestionService QUESTION_SERVICE;
        private static final QuestionUi QUESTION_UI;

        private static final QuizRepository QUIZ_REPOSITORY;
        private static final QuizService QUIZ_SERVICE;
        private static final QuizUi QUIZ_UI;

        private static final Gson GSON;

    static {
        DB_PROPERTY = new DbProperty();
        AUTH_MAPPER = new AuthMapper();
        USER_MAPPER = new UserMapper();
        SUBJECT_MAPPER = new SubjectMapper();
        LEVEL_MAPPER = new LevelMapper();
        QUESTION_MAPPER = new QuestionMapper();

        AUTH_REPOSITORY = new AuthRepository();
        AUTH_SERVICE = new AuthService(AUTH_REPOSITORY);
        AUTH_UI = new AuthUi(AUTH_SERVICE);

        USER_REPOSITORY = new UserRepository();
        USER_SERVICE = new UserService(USER_REPOSITORY);
        USER_UI = new UserUi(USER_SERVICE);

        SUBJECT_REPOSITORY = new SubjectRepository();
        SUBJECT_SERVICE = new SubjectService(SUBJECT_REPOSITORY);
        SUBJECT_UI = new SubjectUi(SUBJECT_SERVICE);

        LEVEL_REPOSITORY = new LevelRepository();
        LEVEL_SERVICE = new LevelService(LEVEL_REPOSITORY);
        LEVEL_UI = new LevelUi(LEVEL_SERVICE);

        QUIZ_REPOSITORY = new QuizRepository();
        QUIZ_SERVICE = new QuizService(QUIZ_REPOSITORY);
        QUIZ_UI = new QuizUi(QUIZ_SERVICE);

        QUESTION_REPOSITORY = new QuestionRepository();
        QUESTION_SERVICE = new QuestionService(QUESTION_REPOSITORY);
        QUESTION_UI = new QuestionUi(QUESTION_SERVICE);

        GSON = new Gson();
    }

    public static <T> T getBean(Class<T> bean) {
        return getBean(bean.getSimpleName().toUpperCase());
    }

    public static <T> T getBean(String bean) {
        return switch (bean) {
            case "DBPROPERTY" -> (T) DB_PROPERTY;
            case "AUTHMAPPER" -> (T) AUTH_MAPPER;
            case "USERMAPPER" -> (T) USER_MAPPER;
            case "SUBJECTMAPPER" -> (T) SUBJECT_MAPPER;
            case "LEVELMAPPER" -> (T) LEVEL_MAPPER;
            case "QUESTIONMAPPER" -> (T) QUESTION_MAPPER;

            case "AUTHUI" -> (T) AUTH_UI;
            case "USERUI" -> (T) USER_UI;
            case "SUBJECTREPOSITORY" -> (T) SUBJECT_REPOSITORY;
            case "SUBJECTUI" -> (T) SUBJECT_UI;
            case "LEVELUI" -> (T) LEVEL_UI;
            case "QUESTIONUI" -> (T) QUESTION_UI;
            case "QUIZUI" -> (T) QUIZ_UI;

            case "GSON" -> (T) GSON;
            default -> throw new RuntimeException("Bean Not Found");
        };
    }
}
