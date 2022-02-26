package uz.jl.mappers.question;

import org.bson.types.ObjectId;
import uz.jl.dto.question.QuestionCreateDto;
import uz.jl.dto.question.QuestionDto;
import uz.jl.dto.question.QuestionUpdateDto;
import uz.jl.entity.question.Question;
import uz.jl.mappers.GenericCRUDMapper;
import uz.jl.mappers.GenericMapper;

public class QuestionMapper implements GenericCRUDMapper<QuestionCreateDto, QuestionUpdateDto, QuestionDto, Question>, GenericMapper<Question, QuestionDto> {
    @Override
    public QuestionCreateDto toCreate(Question model) {
        return new QuestionCreateDto(model.getSubjectCode(),model.getLevelCode(),
                model.getBall(), model.getTitle(), model.getContext(),model.getVariants());
    }

    @Override
    public QuestionUpdateDto toUpdate(Question model) {
        QuestionUpdateDto dto = new QuestionUpdateDto("", model.getBall(), model.getContext(), model.getTitle());
        dto.setId(model.getId().toString());
        return dto;
    }

    @Override
    public QuestionDto to(Question model) {
        QuestionDto dto = new QuestionDto("", model.getBall(), model.getContext(), model.getTitle(), model.getVariants());
        dto.setId(model.getId().toString());
        return dto;
    }

    @Override
    public Question fromCreate(QuestionCreateDto dto) {
        return new Question(dto.getSubjectCode(),dto.getLevelCode(),dto.getBall(), dto.getContext(),
                dto.getTitle(),dto.getVariants());

    }

    @Override
    public Question fromUpdate(QuestionUpdateDto dto) {
        Question question = new Question(dto.getBall(), dto.getContext(), dto.getTitle());
        question.setId(new ObjectId(dto.getId()));
        return question;
    }

    @Override
    public Question from(QuestionDto dto) {
        Question question = new Question(dto.getBall(), dto.getContext(), dto.getTitle());
        question.setId(new ObjectId(dto.getId()));
        question.setVariants(dto.getVariants());
        return question;
    }

    @Override
    public QuestionDto toList(Question model) {
        return new QuestionDto("", model.getBall(), model.getContext(), model.getTitle(), model.getVariants());
    }

    @Override
    public Question fromList(QuestionDto dto) {
        Question question = new Question(dto.getBall(), dto.getContext(), dto.getTitle());
        question.setId(new ObjectId(dto.getId()));
        return question;
    }
}
