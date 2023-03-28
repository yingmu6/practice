package basic.tool.mapstruct.advanced.v2;

import org.springframework.stereotype.Service;

/**
 * @Author chenSy
 * @Date 2023/03/28 10:48
 * @Description
 */
@Service
public class AnimalService {

    public String handleName(String name) {
        return name + "：通过AnimalService处理";
    }
}
