package uz.jl.services.session;

import uz.jl.entity.user.User;

public class SessionService {
    private static User session = new User();

    public static User getSession() {
        return session;
    }

    public static void setSession(User session) {
        SessionService.session = session;
    }
}
