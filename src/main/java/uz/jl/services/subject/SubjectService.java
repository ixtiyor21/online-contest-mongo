package uz.jl.services.subject;

import uz.jl.criteria.subject.SubjectCriteria;
import uz.jl.dto.subject.SubjectCreateDto;
import uz.jl.dto.subject.SubjectDto;
import uz.jl.dto.subject.SubjectUpdateDto;
import uz.jl.repository.subject.SubjectRepository;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.services.AbstractService;
import uz.jl.services.GenericCRUDService;
import uz.jl.services.GenericService;

import java.util.List;

public class SubjectService extends AbstractService<SubjectRepository> implements GenericCRUDService<SubjectCreateDto, SubjectUpdateDto, SubjectDto, String>, GenericService<SubjectDto, SubjectCriteria> {
    public SubjectService(SubjectRepository repository) {
        super(repository);
    }

    @Override
    public ResponseEntity<Data<String>> create(SubjectCreateDto dto) {
        String result = repository.create(dto);
        return new ResponseEntity<>(new Data<>(result));
    }

    @Override
    public ResponseEntity<Data<Void>> update(SubjectUpdateDto dto) {
        Void result = repository.update(dto);
        return new ResponseEntity<>(new Data<>(result));
    }

    @Override
    public ResponseEntity<Data<Void>> delete(String key) {
        Void result = repository.delete(key);
        return new ResponseEntity<>(new Data<>(result));
    }

    @Override
    public ResponseEntity<Data<SubjectDto>> get(String key) {
        try {
            SubjectDto result = repository.get(key);
            return new ResponseEntity<>(new Data<>(result));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Data<List<SubjectDto>>> list(SubjectCriteria criteria) {
        List<SubjectDto> list = repository.list(criteria);
        return new ResponseEntity<>(new Data<>(list));
    }
}
