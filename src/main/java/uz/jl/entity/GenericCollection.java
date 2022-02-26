package uz.jl.entity;


import lombok.*;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GenericCollection {
    @BsonProperty(value = "_id")
    private ObjectId id;
    private Boolean isDeleted;
}
