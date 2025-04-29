package  com.github.zylczu.api.model.exception;


import com.github.zylczu.api.model.vo.constants.StatusEnum;

/**
 * @author YiHui
 * @date 2022/9/2
 */
public class ExceptionUtil {

    public static ForumException of(StatusEnum status, Object... args) {
        return new ForumException(status, args);
    }

}
