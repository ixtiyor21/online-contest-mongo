package uz.jl.repository.user;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.types.ObjectId;
import uz.jl.container.UNIContainer;
import uz.jl.criteria.user.UserCriteria;
import uz.jl.dto.user.UserCreateDto;
import uz.jl.dto.user.UserDto;
import uz.jl.dto.user.UserUpdateDto;
import uz.jl.entity.user.User;
import uz.jl.enums.UserRole;
import uz.jl.mappers.user.UserMapper;
import uz.jl.repository.AbstractRepository;
import uz.jl.repository.GenericCRUDRepository;
import uz.jl.repository.GenericRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepository extends AbstractRepository implements GenericCRUDRepository<UserCreateDto, UserUpdateDto, UserDto, String>, GenericRepository<UserDto, UserCriteria> {
    private static final UserMapper MAPPER = UNIContainer.getBean(UserMapper.class);

    @Override
    public String create(UserCreateDto dto) {
        MongoCollection<User> collection = getDatabase().getCollection(property.get("db.collection.user"), User.class);
        User user = MAPPER.fromCreate(dto);
        user.setRole(UserRole.TEACHER);
        collection.insertOne(user);
        return "Successfully created";
    }

    @Override
    public Void update(UserUpdateDto dto) {
        MongoCollection<User> collection = getDatabase().getCollection(property.get("db.collection.user"), User.class);
        collection.updateOne(Filters.and(Filters.eq("_id", new ObjectId(dto.getId())), Filters.eq("role", "TEACHER")), Updates.combine(
                Updates.set("username", dto.getUsername()),
                Updates.set("password", dto.getPassword()),
                Updates.set("email", dto.getEmail())
        ));
        return null;
    }

    @Override
    public Void delete(String key) {
        MongoCollection<User> collection = getDatabase().getCollection(property.get("db.collection.user"), User.class);
        collection.deleteOne(Filters.and(Filters.eq("_id", new ObjectId(key)), Filters.eq("role", "TEACHER")));
        return null;
    }

    @Override
    public UserDto get(String key) {
        MongoCollection<User> collection = getDatabase().getCollection(property.get("db.collection.user"), User.class);
        FindIterable<User> users = collection.find(Filters.and(Filters.eq("_id", new ObjectId(key)), Filters.eq("role", "TEACHER")));
        if (Objects.isNull(users.first()))
            throw new RuntimeException("user not found");
        return MAPPER.to(Objects.requireNonNull(users.first()));
    }

    @Override
    public List<UserDto> list(UserCriteria criteria) {
        List<UserDto> list = new ArrayList<>();
        MongoCollection<User> collection = getDatabase().getCollection(property.get("db.collection.user"), User.class);
        for (User user : collection.find()) {
            if (UserRole.TEACHER.equals(user.getRole()))
                list.add(MAPPER.to(user));
        }
        return list;
    }
}
