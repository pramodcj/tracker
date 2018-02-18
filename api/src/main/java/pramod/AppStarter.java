package pramod;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import pramod.config.JPAConfig;

public class AppStarter extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{Application.class, JPAConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
