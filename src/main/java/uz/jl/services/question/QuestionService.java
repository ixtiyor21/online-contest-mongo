package uz.jl.services.question;

import uz.jl.criteria.question.QuestionCriteria;
import uz.jl.dto.question.QuestionCreateDto;
import uz.jl.dto.question.QuestionDto;
import uz.jl.dto.question.QuestionUpdateDto;
import uz.jl.repository.question.QuestionRepository;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.services.AbstractService;
import uz.jl.services.GenericCRUDService;
import uz.jl.services.GenericService;

import java.util.List;

public class QuestionService extends AbstractService<QuestionRepository> implements
        GenericCRUDService<QuestionCreateDto, QuestionUpdateDto, QuestionDto, String>, GenericService<QuestionDto, QuestionCriteria> {
    public QuestionService(QuestionRepository repository) {
        super(repository);
    }

    @Override
    public ResponseEntity<Data<String>> create(QuestionCreateDto dto) {
        String result = repository.create(dto);
        return new ResponseEntity<>(new Data<>(result));
    }

    @Override
    public ResponseEntity<Data<Void>> update(QuestionUpdateDto dto) {
        Void result = repository.update(dto);
        return new ResponseEntity<>(new Data<>(result));
    }

    @Override
    public ResponseEntity<Data<Void>> delete(String key) {
        return null;
    }

    @Override
    public ResponseEntity<Data<QuestionDto>> get(String key) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<QuestionDto>>> list(QuestionCriteria criteria) {
        return null;
    }
}
