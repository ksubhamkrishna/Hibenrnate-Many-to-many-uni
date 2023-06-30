
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

public class DeleteCoursePacmanWithoutDeletingStudentDemo {

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

			/*
			 * // get the student mary from the database int theId = 1;
			 * 
			 * Student tempStudent = session.get(Student.class, theId);
			 * 
			 * System.out.println("Loaded Student is : " + tempStudent);
			 * 
			 * // for deleting the entire course session.delete(tempStudent.getCourses());
			 */
			// for deleting a particular course
			// System.out.println("The Courses Related to Particular Student are : "+
			// tempStudent.getCourses());

			// get the pacman course from db
			int courseId = 13;
			Course tempCourse = session.get(Course.class, courseId);

			System.out.println("Deleting course: " + tempCourse);
			session.delete(tempCourse);

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