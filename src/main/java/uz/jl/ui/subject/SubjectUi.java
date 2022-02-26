package uz.jl.ui.subject;

import org.bson.types.ObjectId;
import uz.jl.container.UNIContainer;
import uz.jl.criteria.subject.SubjectCriteria;
import uz.jl.dto.subject.SubjectCreateDto;
import uz.jl.dto.subject.SubjectDto;
import uz.jl.dto.subject.SubjectUpdateDto;
import uz.jl.entity.subject.Subject;
import uz.jl.mappers.subject.SubjectMapper;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.services.subject.SubjectService;
import uz.jl.ui.AbstractUi;
import uz.jl.ui.GenericUi;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.List;

public class SubjectUi extends AbstractUi<SubjectService> implements GenericUi {
    private static final SubjectMapper MAPPER = UNIContainer.getBean(SubjectMapper.class);

    public SubjectUi(SubjectService service) {
        super(service);
    }

    @Override
    public void create() {
        String title = Input.getStr("title = ");
        String code = Input.getStr("code = ");
        String description = Input.getStr("description = ");
        Subject subject = new Subject(title, code, description);
        SubjectCreateDto dto = MAPPER.toCreate(subject);
        ResponseEntity<Data<String>> response = service.create(dto);
        showResponse(response);
    }

    @Override
    public void update() {
        String id = Input.getStr("id = ");
        String title = Input.getStr("title = ");
        String code = Input.getStr("code = ");
        String description = Input.getStr("description = ");
        Subject subject = new Subject(title, code, description);
        subject.setId(new ObjectId(id));
        SubjectUpdateDto dto = MAPPER.toUpdate(subject);
        service.update(dto);
        Print.println(Color.BLUE, "success");
    }

    @Override
    public void delete() {
        String id = Input.getStr("id = ");
        service.delete(id);
        Print.println(Color.BLUE, "success");
    }

    @Override
    public void get() {
        String id = Input.getStr("id = ");
        try {
            ResponseEntity<Data<SubjectDto>> response = service.get(id);
            showResponse(response);
        } catch (Exception e) {
            Print.println(e.getMessage());
        }
    }

    @Override
    public void list() {
        ResponseEntity<Data<List<SubjectDto>>> response = service.list(new SubjectCriteria());
        showResponse(response);
    }
}
