<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Register - TuitionConnect</title>
    <link rel="stylesheet" href="styles.css"> <!-- Assuming external CSS, adjust path if needed -->
</head>
<body>
    <header>
        <nav>
            <div class="logo">
                <h1>TuitionConnect</h1>
            </div>
            <ul class="nav-links">
                <li><a href="home.jsp">Home</a></li>
                <li><a href="StudentRegister.jsp" class="active">Student Register</a></li>
                <li><a href="TutorRegister.jsp">Tutor Register</a></li>
                <li><a href="help.jsp">Help</a></li>
                <li><a href="contact.jsp">Contact</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <section class="register-section">
            <div class="register-container">
                <h2>Student Registration</h2>
                <form action="registerStudent" method="post">
                    <div class="form-group">
                        <label for="fullName">Full Name</label>
                        <input type="text" id="fullName" name="fullName" required>
                    </div>

                    <div class="form-group">
                        <label for="email">Email Address</label>
                        <input type="email" id="email" name="email" required>
                    </div>

                    <div class="form-group">
                        <label for="phone">Phone Number</label>
                        <input type="tel" id="phone" name="phone" required>
                    </div>

                    <div class="form-group">
                        <label for="subjects">Subjects Needed</label>
                        <input type="text" id="subjects" name="subjects" required>
                    </div>

                    <div class="form-group">
                        <label for="educationLevel">Education Level</label>
                        <select id="educationLevel" name="educationLevel" required>
                            <option value="" disabled selected>Select your education level</option>
                            <option value="school">School</option>
                            <option value="college">College/University</option>
                            <option value="professional">Professional Development</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="password">Create Password</label>
                        <input type="password" id="password" name="password" required>
                    </div>

                    <div class="form-group">
                        <label for="confirmPassword">Confirm Password</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" required>
                    </div>

                    <button type="submit" class="register-btn">Register as Student</button>

                    <p class="login-link">Already registered? <a href="login.jsp">Login here</a></p>
                </form>
            </div>
        </section>
    </main>

    <footer>
        <p>© 2025 TuitionConnect. All rights reserved.</p>
        <p>Server Time: <%= new java.util.Date() %></p>
    </footer>

</body>
</html>
