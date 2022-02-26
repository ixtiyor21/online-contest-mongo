package uz.jl.services.level;

import uz.jl.criteria.level.LevelCriteria;
import uz.jl.dto.level.LevelCreateDto;
import uz.jl.dto.level.LevelDto;
import uz.jl.dto.level.LevelUpdateDto;
import uz.jl.repository.level.LevelRepository;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.services.AbstractService;
import uz.jl.services.GenericCRUDService;
import uz.jl.services.GenericService;

import java.util.List;

public class LevelService extends AbstractService<LevelRepository> implements GenericCRUDService<LevelCreateDto, LevelUpdateDto, LevelDto, String>, GenericService<LevelDto, LevelCriteria> {
    public LevelService(LevelRepository repository) {
        super(repository);
    }

    @Override
    public ResponseEntity<Data<String>> create(LevelCreateDto dto) {
        String result = repository.create(dto);
        return new ResponseEntity<>(new Data<>(result));
    }

    @Override
    public ResponseEntity<Data<Void>> update(LevelUpdateDto dto) {
        Void result = repository.update(dto);
        return new ResponseEntity<>(new Data<>(result));
    }

    @Override
    public ResponseEntity<Data<Void>> delete(String key) {
        return null;
    }

    public void deleteLevel(String subjectId, String levelId) {
        Void result = repository.deleteLevel(subjectId, levelId);
        new ResponseEntity<>(new Data<>(result));
    }

    @Override
    public ResponseEntity<Data<LevelDto>> get(String key) {
        return null;
    }

    public ResponseEntity<Data<LevelDto>> getLevel(String subjectId, String levelId) {
        LevelDto result = repository.getLevel(subjectId, levelId);
        return new ResponseEntity<>(new Data<>(result));
    }

    @Override
    public ResponseEntity<Data<List<LevelDto>>> list(LevelCriteria criteria) {
        return null;
    }
}
