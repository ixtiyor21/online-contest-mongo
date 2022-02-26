package uz.jl.mappers.subject;

import org.bson.types.ObjectId;
import uz.jl.dto.subject.SubjectCreateDto;
import uz.jl.dto.subject.SubjectDto;
import uz.jl.dto.subject.SubjectUpdateDto;
import uz.jl.entity.subject.Subject;
import uz.jl.mappers.GenericCRUDMapper;
import uz.jl.mappers.GenericMapper;

public class SubjectMapper implements GenericCRUDMapper<SubjectCreateDto, SubjectUpdateDto, SubjectDto, Subject>, GenericMapper<Subject, SubjectDto> {
    @Override
    public SubjectCreateDto toCreate(Subject model) {
        return new SubjectCreateDto(model.getTitle(), model.getCode(), model.getDescription());
    }

    @Override
    public SubjectUpdateDto toUpdate(Subject model) {
        return new SubjectUpdateDto(model.getId().toString(), model.getTitle(), model.getCode(), model.getDescription());
    }

    @Override
    public SubjectDto to(Subject model) {
        return new SubjectDto(model.getId().toString(), model.getTitle(), model.getCode(), model.getDescription(), model.getLevels());
    }

    @Override
    public Subject fromCreate(SubjectCreateDto dto) {
        return new Subject(dto.getTitle(), dto.getCode(), dto.getDescription());
    }

    @Override
    public Subject fromUpdate(SubjectUpdateDto dto) {
        Subject subject = new Subject(dto.getTitle(), dto.getCode(), dto.getDescription());
        subject.setId(new ObjectId(dto.getId()));
        return subject;
    }

    @Override
    public Subject from(SubjectDto dto) {
        Subject subject = new Subject(dto.getTitle(), dto.getCode(), dto.getDescription());
        subject.setId(new ObjectId(dto.getId()));
        subject.setLevels(dto.getLevels());
        return subject;
    }

    @Override
    public SubjectDto toList(Subject model) {
        return new SubjectDto(model.getId().toString(), model.getTitle(), model.getCode(), model.getDescription(), model.getLevels());
    }

    @Override
    public Subject fromList(SubjectDto dto) {
        Subject subject = new Subject(dto.getTitle(), dto.getCode(), dto.getDescription());
        subject.setId(new ObjectId(dto.getId()));
        subject.setLevels(dto.getLevels());
        return subject;
    }
}
