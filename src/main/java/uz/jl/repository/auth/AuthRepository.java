package uz.jl.repository.auth;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import uz.jl.container.UNIContainer;
import uz.jl.dto.auth.AuthCreateDto;
import uz.jl.entity.user.User;
import uz.jl.mappers.auth.AuthMapper;
import uz.jl.repository.AbstractRepository;
import uz.jl.services.session.SessionService;

import java.util.Objects;

import static uz.jl.utils.Color.RED;

public class AuthRepository extends AbstractRepository {
    private static final AuthMapper MAPPER = UNIContainer.getBean(AuthMapper.class);

    public String login(String username, String password) {
        MongoCollection<User> collection = getDatabase().getCollection(property.get("db.collection.user"), User.class);
        FindIterable<User> users = collection.find(Filters.and(Filters.eq("username", username), Filters.eq("password", password)));
        if (Objects.isNull(users.first())) {
            throw new RuntimeException(RED+"User not found");
        }
        SessionService.setSession(users.first());
        return "Successfully login!";
    }

    public String register(AuthCreateDto dto) {
        MongoCollection<User> collection = getDatabase().getCollection(property.get("db.collection.user"), User.class);
        User user = MAPPER.fromCreate(dto);
        collection.insertOne(user);
        return "Successfully registered";
    }

    public String logout() {
        SessionService.setSession(new User());

        return "bye !";
    }
}
