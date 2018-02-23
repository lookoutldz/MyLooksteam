package looko.looksteam.demo.tool;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*用于非controller类调用service
 * 
 */
@Configuration
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext appCtx;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appCtx = applicationContext;
    }

    public static Object getBean(String beanName) {
        return appCtx.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clz) {
        return (T)appCtx.getBean(clz);
    }
}
