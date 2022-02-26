package uz.jl.ui.question;

import uz.jl.container.UNIContainer;
import uz.jl.criteria.subject.SubjectCriteria;
import uz.jl.dto.question.QuestionCreateDto;
import uz.jl.dto.subject.SubjectDto;
import uz.jl.entity.question.Question;
import uz.jl.entity.variant.Variant;
import uz.jl.mappers.question.QuestionMapper;
import uz.jl.repository.subject.SubjectRepository;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.services.question.QuestionService;
import uz.jl.ui.AbstractUi;
import uz.jl.ui.GenericUi;
import uz.jl.ui.level.LevelUi;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.ArrayList;
import java.util.List;

public class QuestionUi extends AbstractUi<QuestionService> implements GenericUi {
    LevelUi levelUi= UNIContainer.getBean(LevelUi.class);
    private static final SubjectRepository REPOSITORY = UNIContainer.getBean(SubjectRepository.class);
    private static final QuestionMapper MAPPER = UNIContainer.getBean(QuestionMapper.class);

    public QuestionUi(QuestionService service) {
        super(service);
    }

    @Override
    public void create() {

        getSubject();
        String subjectCode = Input.getStr("subject Code = ");
       String levelCode= levelUi.list2(subjectCode);
        try {
            Integer ball = Input.getNum("ball = ");
            String title = Input.getStr("title = ");
            String context = Input.getStr("context = ");
            Question question = new Question(ball, title, context);
            Integer count = Input.getNum("Variant count = ");
            question.setVariants(createVariant(count));
            question.setSubjectCode(subjectCode);
            question.setLevelCode(levelCode);
            QuestionCreateDto dto = MAPPER.toCreate(question);
            ResponseEntity<Data<String>> response = service.create(dto);
            showResponse(response);
        } catch (Exception e) {
            Print.println(Color.RED, e.getMessage());
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void get() {

    }

    @Override
    public void list() {

    }

    public void getSubject() {
        List<SubjectDto> subjectDtoList = REPOSITORY.list(new SubjectCriteria());
        for (SubjectDto subjectDto : subjectDtoList) {
            Print.println(Color.YELLOW, subjectDto.getCode());
        }
    }





     private List<Variant> createVariant(Integer count) {
        List<Variant> variants=new ArrayList<>();
         for (int i = 0; i < count; i++) {
             boolean istrue=false;
             String code=Input.getStr("Code:");
             String context=Input.getStr("Context:");
             String isTrue=Input.getStr("1=>isTrue 2=>false");
             if(isTrue.equals("1")) {
                 istrue=true;
             }
             Variant variant=new Variant(context,code,istrue);
              variants.add(variant);
         }
         return variants;
     }
}
