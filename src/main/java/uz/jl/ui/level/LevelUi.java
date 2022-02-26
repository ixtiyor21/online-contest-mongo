package uz.jl.ui.level;

import org.bson.types.ObjectId;
import uz.jl.container.UNIContainer;
import uz.jl.criteria.subject.SubjectCriteria;
import uz.jl.dto.level.LevelCreateDto;
import uz.jl.dto.level.LevelDto;
import uz.jl.dto.level.LevelUpdateDto;
import uz.jl.dto.subject.SubjectDto;
import uz.jl.entity.level.Level;
import uz.jl.mappers.level.LevelMapper;
import uz.jl.repository.subject.SubjectRepository;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.services.level.LevelService;
import uz.jl.ui.AbstractUi;
import uz.jl.ui.GenericUi;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.List;

public class LevelUi extends AbstractUi<LevelService> implements GenericUi {
    private static final SubjectRepository REPOSITORY = UNIContainer.getBean(SubjectRepository.class);
    private static final LevelMapper MAPPER = UNIContainer.getBean(LevelMapper.class);

    public LevelUi(LevelService service) {
        super(service);
    }

    @Override
    public void create() {
        getSubject();
        String subjectId = Input.getStr("subject code = ");
        try {
            SubjectDto subjectDto = REPOSITORY.get(subjectId);
            String title = Input.getStr("title = ");
            String code = Input.getStr("code = ");
            String description = Input.getStr("description = ");
            Level level = new Level(title, code, description);
            LevelCreateDto dto = MAPPER.toCreate(level);
            dto.setSubjectId(subjectDto.getId());
            ResponseEntity<Data<String>> response = service.create(dto);
            showResponse(response);
        } catch (Exception e) {
            Print.println(Color.RED, e.getMessage());
        }
    }

    @Override
    public void update() {
        getSubject();
        String subjectId = Input.getStr("subject id = ");
        try {
            SubjectDto subjectDto = REPOSITORY.get(subjectId);
            List<Level> levels = subjectDto.getLevels();
            for (Level level : levels) {
                Print.println(Color.YELLOW, level.getId() + " " + level.getCode());
            }
            String id = Input.getStr("id = ");
            String title = Input.getStr("title = ");
            String code = Input.getStr("code = ");
            String description = Input.getStr("description = ");
            Level level = new Level(title, code, description);
            level.setId(new ObjectId(id));
            LevelUpdateDto dto = MAPPER.toUpdate(level);
            dto.setSubjectId(subjectId);
            service.update(dto);
            Print.println("success");
        } catch (Exception e) {
            Print.println(Color.RED, e.getMessage());
        }
    }

    @Override
    public void delete() {
        getSubject();
        String subjectId = Input.getStr("subject id = ");
        try {
            SubjectDto subjectDto = REPOSITORY.get(subjectId);
            List<Level> levels = subjectDto.getLevels();
            for (Level level : levels) {
                Print.println(Color.YELLOW, level.getId() + " " + level.getCode());
            }
            String id = Input.getStr("id = ");
            service.deleteLevel(subjectId, id);
            Print.println("success");
        } catch (Exception e) {
            Print.println(Color.RED, e.getMessage());
        }
    }

    @Override
    public void get() {
        getSubject();
        String subjectId = Input.getStr("subject id = ");
        try {
            SubjectDto subjectDto = REPOSITORY.get(subjectId);
            List<Level> levels = subjectDto.getLevels();
            for (Level level : levels) {
                Print.println(Color.YELLOW, level.getId() + " " + level.getCode());
            }
            String id = Input.getStr("id = ");
            ResponseEntity<Data<LevelDto>> response = service.getLevel(subjectId, id);
            showResponse(response);
        } catch (Exception e) {
            Print.println(Color.RED, e.getMessage());
        }
    }

    @Override
    public void list() {
        getSubject();
        String subjectId = Input.getStr("subject id = ");
        try {
            SubjectDto subjectDto = REPOSITORY.get(subjectId);
            List<Level> levels = subjectDto.getLevels();
            for (Level level : levels) {
                Print.println(Color.YELLOW, level.getId() + " " + level.getCode());
            }
        } catch (Exception e) {
            Print.println(Color.RED, e.getMessage());
        }
    }

    public void getSubject() {
        List<SubjectDto> subjectDtoList = REPOSITORY.list(new SubjectCriteria());
        for (SubjectDto subjectDto : subjectDtoList) {
            Print.println(Color.YELLOW, subjectDto.getId() + " " + subjectDto.getCode());
        }
    }

    public String list2(String subjectCode) {
        try {
            SubjectDto subjectDto = REPOSITORY.get(subjectCode);
            List<Level> levels = subjectDto.getLevels();
            for (Level level : levels) {
                Print.println(Color.YELLOW, level.getCode());
            }
            String level1= Input.getStr("Enter level Code:");
            for (Level level : levels) {
                if(level.getCode().equalsIgnoreCase(level1)) {
                    return level.getCode();
                }
            }

        } catch (Exception e) {
            Print.println(Color.RED, e.getMessage());

        }
        return null;
    }
}
