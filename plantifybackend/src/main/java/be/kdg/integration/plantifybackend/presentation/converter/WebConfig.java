package be.kdg.integration.plantifybackend.presentation.converter;

import be.kdg.integration.plantifybackend.presentation.viewModel.SignUpViewModel;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

/**
 * WebConfig for adding converters to the FormatterRegistry
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addConverter(new StringToPlantTypeConverter());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/createUser").setViewName("createUser");
        registry.addViewController("/login").setViewName("login");
    }
}
