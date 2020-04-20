package config;

import org.springframework.web.filter.CharacterEncodingFilter;

public  class   WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings(){
        return  new String[]{"/"};
    }

    @Override
    protected  Class<?>[] getRootConfigClasses{
        return new Class<?>[] {ApplicationConfig.class};
    }

    protected Class<?>[] getServletConfigClasses(){
        return null;
    }

    protected  Filter[] getServletFilters(){
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncodig("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return  new Filter[] {characterEncodingFilter};
    }
}