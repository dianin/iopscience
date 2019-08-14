package Utils;

import ru.yandex.qatools.allure.annotations.Step;

public class LogUtil
{
    private LogUtil() {
    }

    @Step("{0}")
    public static void log(final String message){
        //intentionally empty
    }

}
