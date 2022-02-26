package uz.jl.ui.menu;

import uz.jl.enums.UserRole;
import uz.jl.services.session.SessionService;
import uz.jl.utils.Color;
import uz.jl.utils.Print;

public class Menu {
    public static void getMenu() {
        if (UserRole.ANONYMOUS.equals(SessionService.getSession().getRole())) {
            Print.println("LOGIN -> login");
            Print.println("REGISTER -> register");
            Print.println("EXIT -> exit");
        }

        if (UserRole.STUDENT.equals(SessionService.getSession().getRole())) {
            Print.println("HISTORY_QUIZ -> history quiz");
            Print.println("START_QUIZ -> start quiz");
            Print.println("LOGOUT -> logout");
            Print.println("EXIT -> exit");
        }

        if (UserRole.TEACHER.equals(SessionService.getSession().getRole())) {
            Print.println("SUBJECT_CREATE -> subject create");
            Print.println("SUBJECT_UPDATE -> subject update");
            Print.println("SUBJECT_DELETE -> subject delete");
            Print.println("SUBJECT_GET -> subject get");
            Print.println("SUBJECT_LIST -> subject list");

            Print.println("LEVEL_CREATE -> level create");
            Print.println("LEVEL_UPDATE -> level update");
            Print.println("LEVEL_DELETE -> level delete");
            Print.println("LEVEL_GET -> level get");
            Print.println("LEVEL_LIST -> level list");

            Print.println("QUESTION_CREATE -> question create");
            Print.println("QUESTION_UPDATE -> question update");
            Print.println("QUESTION_DELETE -> question delete");
            Print.println("QUESTION_GET -> question get");
            Print.println("QUESTION_LIST -> question list");

            Print.println("LOGOUT -> logout");
            Print.println("EXIT -> exit");
        }

        if (UserRole.ADMIN.equals(SessionService.getSession().getRole())) {
            Print.println("TEACHER_CREATE -> teacher create");
            Print.println("TEACHER_UPDATE -> teacher update");
            Print.println("TEACHER_DELETE -> teacher delete");
            Print.println("TEACHER_GET -> teacher get");
            Print.println("TEACHER_LIST -> teacher list");
            Print.println("LOGOUT -> logout");
            Print.println("EXIT -> exit");
        }
    }
}
