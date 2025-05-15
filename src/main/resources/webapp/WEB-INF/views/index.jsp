<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Home Tuition Booking System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary: #4361ee;
            --primary-light: #e6f0ff;
            --accent: #4cc9f0;
            --bg-light: #ffffff;
            --bg-dark: #121212;
            --text-light: rgba(255, 255, 255, 0.95);
            --text-dark: rgba(0, 0, 0, 0.95);
            --text-muted: rgba(0, 0, 0, 0.6);
            --text-muted-light: rgba(255, 255, 255, 0.7);
            --border-light: rgba(0, 0, 0, 0.1);
            --border-dark: rgba(255, 255, 255, 0.1);
            --shadow-sm: 0 1px 3px rgba(0, 0, 0, 0.05);
            --shadow-md: 0 4px 6px rgba(0, 0, 0, 0.1);
            --shadow-lg: 0 10px 15px rgba(0, 0, 0, 0.1);
            --transition-base: all 0.3s ease;
            --transition-slow: all 0.6s ease;
            --border-radius: 8px;
            --border-radius-lg: 12px;
            --spacing-unit: 1rem;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Inter', -apple-system, BlinkMacSystemFont, sans-serif;
            background: var(--bg-light);
            color: var(--text-dark);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            transition: var(--transition-slow);
            line-height: 1.6;
        }

        body.dark-mode {
            background: var(--bg-dark);
            color: var(--text-light);
        }

        /* Navigation */
        nav {
            background: var(--bg-light);
            padding: 1rem 5%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: sticky;
            top: 0;
            z-index: 100;
            box-shadow: var(--shadow-sm);
            border-bottom: 1px solid var(--border-light);
            transition: var(--transition-base);
        }

        body.dark-mode nav {
            background: var(--bg-dark);
            border-bottom: 1px solid var(--border-dark);
        }

        nav.scrolled {
            padding: 0.75rem 5%;
            box-shadow: var(--shadow-md);
        }

        nav h1 {
            font-size: 1.5rem;
            font-weight: 700;
            color: var(--primary);
        }

        nav ul {
            list-style: none;
            display: flex;
            gap: 1.5rem;
            align-items: center;
        }

        nav ul li a {
            color: var(--text-dark);
            text-decoration: none;
            font-weight: 500;
            padding: 0.5rem 0;
            position: relative;
            transition: var(--transition-base);
        }

        body.dark-mode nav ul li a {
            color: var(--text-light);
        }

        nav ul li a::after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 0;
            width: 0;
            height: 2px;
            background: var(--primary);
            transition: var(--transition-base);
        }

        nav ul li a:hover {
            color: var(--primary);
        }

        nav ul li a:hover::after {
            width: 100%;
        }

        .dark-toggle {
            background: transparent;
            color: var(--text-dark);
            border: none;
            width: 2.5rem;
            height: 2.5rem;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            cursor: pointer;
            font-size: 1.2rem;
            transition: var(--transition-base);
        }

        body.dark-mode .dark-toggle {
            color: var(--text-light);
        }

        .dark-toggle:hover {
            background: rgba(0, 0, 0, 0.05);
        }

        body.dark-mode .dark-toggle:hover {
            background: rgba(255, 255, 255, 0.05);
        }

        .auth-buttons {
            display: flex;
            gap: 1rem;
        }

        .auth-btn {
            padding: 0.5rem 1.25rem;
            border-radius: var(--border-radius-lg);
            font-weight: 500;
            text-decoration: none;
            transition: var(--transition-base);
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .login-btn {
            background: transparent;
            border: 1px solid var(--border-light);
            color: var(--text-dark);
        }

        body.dark-mode .login-btn {
            border: 1px solid var(--border-dark);
            color: var(--text-light);
        }

        .login-btn:hover {
            background: rgba(0, 0, 0, 0.02);
            border-color: var(--primary);
        }

        body.dark-mode .login-btn:hover {
            background: rgba(255, 255, 255, 0.02);
        }

        .signup-btn {
            background: var(--primary);
            border: 1px solid var(--primary);
            color: white;
        }

        .signup-btn:hover {
            background: #3a56d4;
            transform: translateY(-1px);
        }

        /* Hero Section */
        .hero {
            flex: 1;
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
            padding: 6rem 5% 8rem;
            position: relative;
            overflow: hidden;
            background: linear-gradient(to bottom, var(--primary-light) 0%, rgba(255,255,255,0) 100%);
        }

        body.dark-mode .hero {
            background: linear-gradient(to bottom, rgba(67, 97, 238, 0.1) 0%, rgba(0,0,0,0) 100%);
        }

        .hero-content {
            max-width: 800px;
            opacity: 0;
            transform: translateY(30px);
            animation: fadeInUp 1s ease-out forwards;
            animation-delay: 0.3s;
        }

        .hero h2 {
            font-size: 2.5rem;
            margin-bottom: 1.5rem;
            font-weight: 700;
            line-height: 1.2;
        }

        .hero p {
            font-size: 1.25rem;
            margin-bottom: 2.5rem;
            color: var(--text-muted);
        }

        body.dark-mode .hero p {
            color: var(--text-muted-light);
        }

        .hero .btn {
            padding: 1rem 2rem;
            background: var(--primary);
            color: white;
            font-weight: 500;
            border: none;
            border-radius: var(--border-radius-lg);
            text-decoration: none;
            font-size: 1rem;
            transition: var(--transition-base);
            display: inline-flex;
            align-items: center;
            gap: 0.5rem;
        }

        .hero .btn:hover {
            background: #3a56d4;
            transform: translateY(-2px);
            box-shadow: var(--shadow-md);
        }

        /* Subjects Section */
        .subjects {
            padding: 5rem 5%;
            text-align: center;
        }

        .subjects h3 {
            font-size: 2rem;
            margin-bottom: 3rem;
            font-weight: 600;
        }

        .subject-cards {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 1.5rem;
            max-width: 1200px;
            margin: 0 auto;
        }

        .card {
            background: var(--bg-light);
            border-radius: var(--border-radius);
            padding: 2rem 1.5rem;
            box-shadow: var(--shadow-sm);
            transition: var(--transition-base);
            border: 1px solid var(--border-light);
            text-align: left;
        }

        body.dark-mode .card {
            background: rgba(255, 255, 255, 0.05);
            border: 1px solid var(--border-dark);
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: var(--shadow-lg);
            border-color: var(--primary);
        }

        .card h4 {
            font-size: 1.25rem;
            margin-bottom: 0.75rem;
            color: var(--primary);
            font-weight: 600;
        }

        .card p {
            font-size: 0.95rem;
            color: var(--text-muted);
            margin-bottom: 1.5rem;
        }

        body.dark-mode .card p {
            color: var(--text-muted-light);
        }

        .card-icon {
            font-size: 1.5rem;
            margin-bottom: 1rem;
            color: var(--primary);
            transition: var(--transition-base);
        }

        .card-link {
            display: inline-flex;
            align-items: center;
            color: var(--primary);
            font-weight: 500;
            text-decoration: none;
            transition: var(--transition-base);
        }

        .card-link:hover {
            transform: translateX(5px);
        }

        .card-link i {
            margin-left: 0.5rem;
            transition: var(--transition-base);
        }

        .card-link:hover i {
            transform: translateX(3px);
        }

        /* Testimonials */
        .testimonials {
            padding: 5rem 5%;
            text-align: center;
            background: rgba(0, 0, 0, 0.02);
        }

        body.dark-mode .testimonials {
            background: rgba(255, 255, 255, 0.02);
        }

        .testimonial-cards {
            display: flex;
            gap: 1.5rem;
            overflow-x: auto;
            padding: 2rem 0;
            scroll-snap-type: x mandatory;
        }

        .testimonial-card {
            min-width: 300px;
            background: var(--bg-light);
            border-radius: var(--border-radius);
            padding: 2rem;
            box-shadow: var(--shadow-sm);
            scroll-snap-align: start;
            transition: var(--transition-base);
            border: 1px solid var(--border-light);
            text-align: left;
        }

        body.dark-mode .testimonial-card {
            background: rgba(255, 255, 255, 0.05);
            border: 1px solid var(--border-dark);
        }

        .testimonial-card:hover {
            transform: translateY(-5px);
            box-shadow: var(--shadow-md);
        }

        .testimonial-text {
            font-style: italic;
            margin-bottom: 1.5rem;
            color: var(--text-muted);
        }

        body.dark-mode .testimonial-text {
            color: var(--text-muted-light);
        }

        .testimonial-author {
            font-weight: 600;
            color: var(--primary);
        }

        /* Footer */
        footer {
            background: var(--bg-light);
            padding: 3rem 5% 1.5rem;
            border-top: 1px solid var(--border-light);
        }

        body.dark-mode footer {
            background: var(--bg-dark);
            border-top: 1px solid var(--border-dark);
        }

        .footer-content {
            max-width: 1200px;
            margin: 0 auto;
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 2rem;
            text-align: left;
        }

        .footer-column h4 {
            color: var(--primary);
            margin-bottom: 1.5rem;
            font-size: 1.1rem;
        }

        .footer-column ul {
            list-style: none;
        }

        .footer-column ul li {
            margin-bottom: 0.75rem;
        }

        .footer-column ul li a {
            color: var(--text-muted);
            text-decoration: none;
            transition: var(--transition-base);
        }

        body.dark-mode .footer-column ul li a {
            color: var(--text-muted-light);
        }

        .footer-column ul li a:hover {
            color: var(--primary);
        }

        .social-links {
            display: flex;
            gap: 1rem;
            margin-top: 1.5rem;
        }

        .social-links a {
            color: var(--text-muted);
            font-size: 1.2rem;
            transition: var(--transition-base);
        }

        body.dark-mode .social-links a {
            color: var(--text-muted-light);
        }

        .social-links a:hover {
            color: var(--primary);
        }

        .copyright {
            margin-top: 3rem;
            padding-top: 1.5rem;
            border-top: 1px solid var(--border-light);
            color: var(--text-muted);
            text-align: center;
            font-size: 0.9rem;
        }

        body.dark-mode .copyright {
            border-top: 1px solid var(--border-dark);
            color: var(--text-muted-light);
        }

        /* Animations */
        @keyframes fadeInUp {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        /* Scroll Reveal Animation */
        .reveal {
            opacity: 0;
            transform: translateY(30px);
            transition: all 0.6s ease;
        }

        .reveal.visible {
            opacity: 1;
            transform: translateY(0);
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            nav {
                flex-direction: column;
                gap: 1rem;
                padding: 1rem 5%;
            }

            nav ul {
                flex-direction: column;
                gap: 0.75rem;
                width: 100%;
            }

            .auth-buttons {
                width: 100%;
                justify-content: center;
                margin-top: 1rem;
            }

            .hero {
                padding: 4rem 5% 6rem;
            }

            .hero h2 {
                font-size: 2rem;
            }

            .hero p {
                font-size: 1.1rem;
            }

            .subject-cards {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>

<nav id="mainNav">
    <h1>TuitionConnect</h1>
    <ul>
        <li><a href="Search.html">Book a Tutor</a></li>
        <li><a href="Contact.html">Contact</a></li>
        <li><a href="View Profile.html">View Profile</a></li> <!-- Added this line -->
        <li><a href="Subjects.html">Subject and Tutors</a></li> <!-- Added this line -->
        <li><a href="Tutor Management.html">Tutor Management</a></li> <!-- Added this line -->
        <li><a href="View Booking.html">View booking</a></li> <!-- Added this line -->
        <div class="auth-buttons">
            <a href="Login.html" class="auth-btn login-btn"><i class="fas fa-sign-in-alt"></i> Login</a>
            <a href="Student Register.html" class="auth-btn signup-btn"><i class="fas fa-user-plus"></i> Sign Up</a>
        </div>
        <li><button class="dark-toggle" onclick="toggleDarkMode()"><i class="fas fa-moon"></i></button></li>
    </ul>
</nav>

<section class="hero">
    <div class="hero-content">
        <h2 class="reveal">Personalized Academic Success</h2>
        <p class="reveal">Connect with top-rated tutors in Mathematics, Science, English, and more.</p>
        <a href="Search.html" class="btn reveal">Book a Tutor Now <i class="fas fa-arrow-right"></i></a>
    </div>
</section>

<section class="subjects">
    <h3 class="reveal">Popular Subjects</h3>
    <div class="subject-cards">
        <div class="card reveal" style="transition-delay: 0.1s">
            <div class="card-icon"><i class="fas fa-laptop-code"></i></div>
            <h4>OOP</h4>
            <p>Master Object-Oriented Programming concepts with expert guidance.</p>
            <a href="Oop.html" class="card-link">Learn more <i class="fas fa-chevron-right"></i></a>
        </div>
        <div class="card reveal" style="transition-delay: 0.2s">
            <div class="card-icon"><i class="fas fa-project-diagram"></i></div>
            <h4>DSA</h4>
            <p>Learn Data Structures and Algorithms from industry professionals.</p>
            <a href="booking.html?subject=DSA" class="card-link">Learn more <i class="fas fa-chevron-right"></i></a>
        </div>
        <div class="card reveal" style="transition-delay: 0.3s">
            <div class="card-icon"><i class="fas fa-infinity"></i></div>
            <h4>DM</h4>
            <p>Understand Discrete Mathematics fundamentals with clear explanations.</p>
            <a href="booking.html?subject=DM" class="card-link">Learn more <i class="fas fa-chevron-right"></i></a>
        </div>
        <div class="card reveal" style="transition-delay: 0.4s">
            <div class="card-icon"><i class="fas fa-pen-fancy"></i></div>
            <h4>TW</h4>
            <p>Enhance your technical writing skills for professional success.</p>
            <a href="booking.html?subject=TW" class="card-link">Learn more <i class="fas fa-chevron-right"></i></a>
        </div>
    </div>
</section>

<section class="testimonials">
    <h3 class="reveal">What Our Students Say</h3>
    <div class="testimonial-cards">
        <div class="testimonial-card reveal" style="transition-delay: 0.1s">
            <div class="testimonial-text">"The OOP tutor helped me understand complex concepts through practical examples. My grades improved significantly!"</div>
            <div class="testimonial-author">- Fernando R., Computer Science</div>
        </div>
        <div class="testimonial-card reveal" style="transition-delay: 0.2s">
            <div class="testimonial-text">"I was struggling with algorithms, but my tutor broke them down into manageable parts. Highly recommend!"</div>
            <div class="testimonial-author">- Shehan R., Engineering</div>
        </div>
        <div class="testimonial-card reveal" style="transition-delay: 0.3s">
            <div class="testimonial-text">"The technical writing sessions were exactly what I needed to improve my documentation skills for work."</div>
            <div class="testimonial-author">- Peiris G., Software Developer</div>
        </div>
    </div>
</section>

<footer>
    <div class="footer-content">
        <div class="footer-column">
            <h4>TuitionConnect</h4>
            <p>Your personalized path to academic success through quality home tutoring.</p>
            <div class="social-links">
                <a href="#"><i class="fab fa-facebook-f"></i></a>
                <a href="#"><i class="fab fa-twitter"></i></a>
                <a href="#"><i class="fab fa-instagram"></i></a>
                <a href="#"><i class="fab fa-linkedin-in"></i></a>
            </div>
        </div>
        <div class="footer-column">
            <h4>Quick Links</h4>
            <ul>
                <li><a href="Search.html">Book a Tutor</a></li>
                <li><a href="Subjects.html">Subjects</a></li>
                <li><a href="Contact.html">Contact Us</a></li>
                <li><a href="Tutor Management.html">Tutor Management</a></li>
                <li><a href="Login.html">Login</a></li>
                <li><a href="Student Register.html">Register</a></li>
            </ul>
        </div>
        <div class="footer-column">
            <h4>Support</h4>
            <ul>
                <li><a href="Faq.html">FAQ</a></li>
                <li><a href="Terms of Service.html">Privacy Policy</a></li>
                <li><a href="Terms of Service.html">Terms of Service</a></li>
                <li><a href="Contact.html">Help Center</a></li>
            </ul>
        </div>
    </div>
    <div class="copyright">
        &copy; 2025 TuitionConnect. All rights reserved.
    </div>
</footer>

<script>
    // Dark mode toggle
    function toggleDarkMode() {
        document.body.classList.toggle('dark-mode');

        // Update icon
        const darkToggle = document.querySelector('.dark-toggle');
        if (document.body.classList.contains('dark-mode')) {
            darkToggle.innerHTML = '<i class="fas fa-sun"></i>';
            darkToggle.setAttribute('aria-label', 'Switch to light mode');
        } else {
            darkToggle.innerHTML = '<i class="fas fa-moon"></i>';
            darkToggle.setAttribute('aria-label', 'Switch to dark mode');
        }

        // Save preference to localStorage
        localStorage.setItem('darkMode', document.body.classList.contains('dark-mode'));
    }

    // Check for saved dark mode preference
    if (localStorage.getItem('darkMode') === 'true') {
        document.body.classList.add('dark-mode');
        document.querySelector('.dark-toggle').innerHTML = '<i class="fas fa-sun"></i>';
    }

    // Navbar scroll effect
    window.addEventListener('scroll', function() {
        const nav = document.getElementById('mainNav');
        if (window.scrollY > 50) {
            nav.classList.add('scrolled');
        } else {
            nav.classList.remove('scrolled');
        }
    });

    // Scroll reveal animation
    function checkReveal() {
        const reveals = document.querySelectorAll('.reveal');
        for (let i = 0; i < reveals.length; i++) {
            const windowHeight = window.innerHeight;
            const revealTop = reveals[i].getBoundingClientRect().top;
            const revealPoint = 100;

            if (revealTop < windowHeight - revealPoint) {
                reveals[i].classList.add('visible');
            }
        }
    }

    window.addEventListener('scroll', checkReveal);
    window.addEventListener('load', checkReveal);
</script>

<script>(function(){function c(){var b=a.contentDocument||a.contentWindow.document;if(b){var d=b.createElement('script');d.innerHTML="window.__CF$cv$params={r:'93ecc7891c5eb08e',t:'MTc0NzA4MjkzMi4wMDAwMDA='};var a=document.createElement('script');a.nonce='';a.src='/cdn-cgi/challenge-platform/scripts/jsd/main.js';document.getElementsByTagName('head')[0].appendChild(a);";b.getElementsByTagName('head')[0].appendChild(d)}}if(document.body){var a=document.createElement('iframe');a.height=1;a.width=1;a.style.position='absolute';a.style.top=0;a.style.left=0;a.style.border='none';a.style.visibility='hidden';document.body.appendChild(a);if('loading'!==document.readyState)c();else if(window.addEventListener)document.addEventListener('DOMContentLoaded',c);else{var e=document.onreadystatechange||function(){};document.onreadystatechange=function(b){e(b);'loading'!==document.readyState&&(document.onreadystatechange=e,c())}}}})();</script></body>
</html>