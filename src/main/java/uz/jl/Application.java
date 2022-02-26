package uz.jl;

import uz.jl.container.UNIContainer;
import uz.jl.ui.auth.AuthUi;
import uz.jl.ui.level.LevelUi;
import uz.jl.ui.menu.Menu;
import uz.jl.ui.question.QuestionUi;
import uz.jl.ui.quiz.QuizUi;
import uz.jl.ui.subject.SubjectUi;
import uz.jl.ui.user.UserUi;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author : Qozoqboyev Ixtiyor
 * Time : 28.01.2022 22:58
 * Project : mongo-contest-personal-version
 */
public class Application {
    private static final AuthUi AUTH_UI = UNIContainer.getBean(AuthUi.class);
    private static final UserUi USER_UI = UNIContainer.getBean(UserUi.class);
    private static final SubjectUi SUBJECT_UI = UNIContainer.getBean(SubjectUi.class);
    private static final LevelUi LEVEL_UI = UNIContainer.getBean(LevelUi.class);
    private static final QuizUi QUIZ_UI = UNIContainer.getBean(QuizUi.class);
    private static final QuestionUi QUESTION_UI = UNIContainer.getBean(QuestionUi.class);

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);
        Menu.getMenu();
        String choose = Input.getStr("choose = ").toUpperCase();
        switch (choose) {
            case "LOGIN" -> AUTH_UI.login();
            case "REGISTER" -> AUTH_UI.register();
            case "LOGOUT" -> AUTH_UI.logout();

            case "TEACHER_CREATE" -> USER_UI.create();
            case "TEACHER_UPDATE" -> USER_UI.update();
            case "TEACHER_DELETE" -> USER_UI.delete();
            case "TEACHER_GET" -> USER_UI.get();
            case "TEACHER_LIST" -> USER_UI.list();

            case "SUBJECT_CREATE" -> SUBJECT_UI.create();
            case "SUBJECT_UPDATE" -> SUBJECT_UI.update();
            case "SUBJECT_DELETE" -> SUBJECT_UI.delete();
            case "SUBJECT_GET" -> SUBJECT_UI.get();
            case "SUBJECT_LIST" -> SUBJECT_UI.list();

            case "LEVEL_CREATE" -> LEVEL_UI.create();
            case "LEVEL_UPDATE" -> LEVEL_UI.update();
            case "LEVEL_DELETE" -> LEVEL_UI.delete();
            case "LEVEL_GET" -> LEVEL_UI.get();
            case "LEVEL_LIST" -> LEVEL_UI.list();

            case "QUESTION_CREATE" -> QUESTION_UI.create();
            case "QUESTION_UPDATE" -> QUESTION_UI.update();
            case "QUESTION_DELETE" -> QUESTION_UI.delete();
            case "QUESTION_GET" -> QUESTION_UI.get();
            case "QUESTION_LIST" -> QUESTION_UI.list();

            case "HISTORY_QUIZ" -> QUIZ_UI.historyQuiz();
            case "START_QUIZ" -> QUIZ_UI.startQuiz();

            case "EXIT" -> {
                Print.println(Color.BLACK, "Goodbye...");
                return;
            }
            default -> Print.println(Color.RED, "Wrong menu !!!");
        }
        main(args);
    }
}

