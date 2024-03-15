# Att göra

- Register Admin: POST http://localhost:8080/api/v1/auth/adminRegister
- Authenticate (Login again and update token): POST http://localhost:8080/api/v1/auth/authenticate
- Update User by admin: UPDATE http://localhost:8080/api/v1/users/update/{userEmail}
- Delete User by admin: DELETE http://localhost:8080/api/v1/users/delete/{userEmail}

### UserApi
- Register User: POST http://localhost:8080/api/v1/auth/userRegister
- Change Password: PATCH http://localhost:8080/api/v1/users
- Login: POST http://localhost:8080/api/v1/auth/authenticate

### StudentApi
- Add Student: POST http://localhost:8080/student/save
- Assign Course to Student: PUT http://localhost:8080/student/{stuId}/course/{courseId}
- Get Student Details: GET http://localhost:8080/student/getStudent/{stuId}
- Get All Students: GET http://localhost:8080/student/getStudents
- Update Student: PUT http://localhost:8080/student/update/{stuId}
- Delete Student: DELETE http://localhost:8080/student/delete/{stuId}

### CourseApi
- Add Course: POST http://localhost:8080/course/save
- Get Course by ID: GET http://localhost:8080/course/getCourse/{courseId}
- Get All Courses: GET http://localhost:8080/course/getCourses
- Update Course: PUT http://localhost:8080/course/update/{courseId}
- Delete Course: DELETE http://localhost:8080/course/delete/{courseId}
