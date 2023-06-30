
package com.luv2code.hibernate.demo;

import java.util.List;

// import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentMaryWithoutDeletingCourseDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			
			int studentId = 2;
			Student tempStudent = session.get(Student.class, studentId);

			System.out.println("\nLoaded student: "+ tempStudent);
			
			System.out.println("Courses: " + tempStudent.getCourses());
			
			System.out.println("\nDeleting Student: " + tempStudent);
			session.delete(tempStudent);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally

		{

			// add clean up code
			session.close();
			factory.close();
		}
	}

}

/*
 * Output (MySQL WorkBench) :-
 * 
 * Student table :-
 * 
 * 1 John Doe john@luv2code@gmail.com 2 Mary Public mary@luv2code.com
 * 
 * course_student table :-
 * 
 * 10 1 10 2 11 2 12 2
 * 
 * Course table :-
 * 
 * 10 Pacman - How To Score One Million Points 11 Rubik's Cube - How to Speed
 * Cube 12 Atari 2600 - Game Development
 */