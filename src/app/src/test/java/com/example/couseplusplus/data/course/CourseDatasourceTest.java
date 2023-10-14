package com.example.couseplusplus.data.course;

import static org.junit.Assert.*;


import org.junit.Test;

import java.util.List;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CourseDatasourceTest {
    @Test
    public void testGetInstance() {
        CourseDatasource uut = CourseDatasource.getInstance();
        assertEquals(CourseDatasource.getInstance(), uut);
    }

    @Test
    public void testGetAll() {
        CourseDatasource uut = new CourseDatasource();
        assertEquals(List.of(), uut.getAll());
        uut.cache = new CourseCache(List.of());
        assertEquals(List.of(), uut.getAll());
    }

    @Test
    public void testFindByName() {
        CourseDatasource uut = new CourseDatasource();
        assertEquals(List.of(), uut.findByCourseName(""));
        uut.cache = new CourseCache(List.of());
        uut.courseFinder = new CourseFinder(uut.cache);
        assertEquals(List.of(), uut.findByCourseName(""));
    }

    @Test
    public void testFindByCode() {
        CourseDatasource uut = new CourseDatasource();
        assertEquals(List.of(), uut.findByCourseCode(""));
        uut.cache = new CourseCache(List.of());
        uut.courseFinder = new CourseFinder(uut.cache);
        assertEquals(List.of(), uut.findByCourseCode(""));
    }
}