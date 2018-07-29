package com.leon.testspringbatch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("studentToPeopleItemProcessor")
public class StudentToPeopleItemProcessor implements ItemProcessor<Student, People> {

    @Override
    public People process(Student student) {
        People people = new People(student.getId(), student.getName(), student.getAge(), "student");
        return people;
    }
}
