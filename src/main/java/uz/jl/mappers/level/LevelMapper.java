package uz.jl.mappers.level;

import org.bson.types.ObjectId;
import uz.jl.dto.level.LevelCreateDto;
import uz.jl.dto.level.LevelDto;
import uz.jl.dto.level.LevelUpdateDto;
import uz.jl.entity.level.Level;
import uz.jl.mappers.GenericCRUDMapper;
import uz.jl.mappers.GenericMapper;

public class LevelMapper implements GenericCRUDMapper<LevelCreateDto, LevelUpdateDto, LevelDto, Level>, GenericMapper<Level, LevelDto> {
    @Override
    public LevelCreateDto toCreate(Level model) {
        return new LevelCreateDto("", model.getTitle(), model.getCode(), model.getDescription());
    }

    @Override
    public LevelUpdateDto toUpdate(Level model) {
        LevelUpdateDto dto = new LevelUpdateDto("", model.getTitle(), model.getCode(), model.getDescription());
        dto.setId(model.getId().toString());
        return dto;
    }

    @Override
    public LevelDto to(Level model) {
        LevelDto dto = new LevelDto("", model.getTitle(), model.getCode(), model.getDescription(), model.getQuestions());
        dto.setId(model.getId().toString());
        return dto;
    }

    @Override
    public Level fromCreate(LevelCreateDto dto) {
        return new Level(dto.getTitle(), dto.getCode(), dto.getDescription());
    }

    @Override
    public Level fromUpdate(LevelUpdateDto dto) {
        Level level = new Level(dto.getTitle(), dto.getCode(), dto.getDescription());
        level.setId(new ObjectId(dto.getId()));
        return level;
    }

    @Override
    public Level from(LevelDto dto) {
        Level level = new Level(dto.getTitle(), dto.getCode(), dto.getDescription());
        level.setId(new ObjectId(dto.getId()));
        level.setQuestions(dto.getQuestions());
        return level;
    }

    @Override
    public LevelDto toList(Level model) {
        return new LevelDto("", model.getTitle(), model.getCode(), model.getDescription(), model.getQuestions());
    }

    @Override
    public Level fromList(LevelDto dto) {
        Level level = new Level(dto.getTitle(), dto.getCode(), dto.getDescription(), dto.getQuestions());
        level.setId(new ObjectId(dto.getId()));
        return level;
    }
}
