package ru.itpark;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;
import ru.itpark.config.Config;
import ru.itpark.config.Mixed;
import ru.itpark.repository.FilmRepository;

/**
 * Created by worker on 12/15/17.
 */
public class Main {
    public static void main(String[] args) {
        {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
            FilmRepository filmRepository = context.getBean(FilmRepository.class);
            System.out.println(filmRepository.findById(1));
            System.out.println(filmRepository.create("Anonymous", "Anonymous"));
        }
        {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            FilmRepository filmRepository = context.getBean(FilmRepository.class);
            System.out.println(filmRepository.findById(1));
        }
        {
            GenericGroovyApplicationContext context = new GenericGroovyApplicationContext("beans.groovy");
            FilmRepository filmRepository = context.getBean(FilmRepository.class);
            System.out.println(filmRepository.findById(1));
        }

        /* mixed */
        {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Mixed.class);
            FilmRepository filmRepository = context.getBean(FilmRepository.class);
            System.out.println(filmRepository.findById(1));
        }
        {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("mixed.xml");
            FilmRepository filmRepository = context.getBean(FilmRepository.class);
            System.out.println(filmRepository.findById(1));
        }
        {
            GenericGroovyApplicationContext context = new GenericGroovyApplicationContext("mixed.groovy");
            FilmRepository filmRepository = context.getBean(FilmRepository.class);
            System.out.println(filmRepository.findById(1));
        }
    }
}
