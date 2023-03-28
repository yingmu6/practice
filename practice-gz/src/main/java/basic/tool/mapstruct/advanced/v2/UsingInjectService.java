package basic.tool.mapstruct.advanced.v2;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author chenSy
 * @Date 2023/03/28 10:58
 * @Description
 */
@Mapper(componentModel = "spring")
public abstract class UsingInjectService {

    @Autowired
    protected AnimalService animalService;

    @Mapping(target = "name", expression = "java(animalService.handleName(pigBOV2.getPigName()))")
    public abstract AnimalBOV2 toAnimalBOV2(PigBOV2 pigBOV2);
}
