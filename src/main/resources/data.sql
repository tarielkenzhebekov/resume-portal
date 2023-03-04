INSERT INTO public."user" (ID, USER_NAME, PASSWORD, ACTIVE, ROLES) VALUES
(1, 'tariel', '123', 'true', 'USER'),
(2, 'shumkar', '123', 'true', 'USER'),
(3, 'zhyrgal', '123', 'true', 'USER');

INSERT INTO public."user_profile" (ID, USER_NAME, THEME, SUMMARY, FIRST_NAME, LAST_NAME, EMAIL, PHONE, DESIGNATION) VALUES
(1, 'tariel', 1, 'Seeking a Backend Developer position with an outstanding career opportunity that will offer a rewarding work environment along with a winning team that will fully utilize management skills.', 'Tariel', 'Kenzhebekov', 'tariel@example.com', '0999-112-233', 'Back-End Developer'),
(2, 'shumkar', 2, ' Junior Mobile Developer with 2 plus years of experience in the Telecommunications domain. Extensive mobile (IOS, Android &Hybrid) application development experience with strong knowledge of various programming languages.', 'Shumkarbek', 'Barpyev', 'shumkarbek@example.com', '0555-445-566', 'Mobile Developer'),
(3, 'zhyrgal', 3, 'Front end developers use programming languages like HTML, JavaScript, and CSS, or frameworks like Tailwind, to determine and develop the visual concept and functionality of visual elements in a website.', 'Zhyrgalbek', 'Sharshenov', 'zhyrgalbek@example.com', '0777-889-977', 'Front-End Developer');

