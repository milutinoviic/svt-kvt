INSERT into roles (name) VALUES ('USER'),('ADMIN'),('MANAGER');
INSERT INTO users (name, sur_name, address, phone_number, birthday, city, email, password, created_al, zip_code, type) VALUES
('John', 'Smith', 'Address 1', '1234567890', '1990-01-01', 'City1', 'john@example.com', 'pass123', '2023-01-01', '12345', 'User');

INSERT INTO users (name, sur_name, address, phone_number, birthday, city, email, password, created_al, zip_code, type) VALUES
    ('Alice', 'Johnson', 'Address 2', '0987654321', '1985-05-10', 'City2', 'alice@example.com', 'pass456', '2023-01-02', '54321', 'User');

INSERT INTO users (name, sur_name, address, phone_number, birthday, city, email, password, created_al, zip_code, type) VALUES
    ('Robert', 'Brown', 'Admin Street', '1122334455', '1980-07-20', 'City3', 'admin@example.com', 'admin', '2023-01-03', '67890', 'Admin');


INSERT INTO users (name, sur_name, address, phone_number, birthday, city, email, password, created_al, zip_code, type) VALUES
    ('Emma', 'Davis', 'Address 3', '2233445566', '1992-03-15', 'City4', 'emma@example.com', 'pass789', '2023-01-04', '13579', 'User');

INSERT INTO users (name, sur_name, address, phone_number, birthday, city, email, password, created_al, zip_code, type) VALUES
    ('Michael', 'Wilson', 'Address 4', '3344556677', '1988-08-25', 'City5', 'michael@example.com', 'pass101', '2023-01-05', '24680', 'User');

INSERT INTO users (name, sur_name, address, phone_number, birthday, city, email, password, created_al, zip_code, type) VALUES
    ('Sophia', 'Taylor', 'Address 5', '4455667788', '1995-11-30', 'City6', 'sophia@example.com', 'pass202', '2023-01-06', '13524', 'User');

INSERT INTO users (name, sur_name, address, phone_number, birthday, city, email, password, created_al, zip_code, type) VALUES
    ('James', 'Anderson', 'Address 6', '5566778899', '1975-12-12', 'City7', 'james@example.com', 'pass303', '2023-01-07', '78901', 'User');

INSERT INTO users (name, sur_name, address, phone_number, birthday, city, email, password, created_al, zip_code, type) VALUES
    ('Linda', 'Martinez', 'Admin Lane', '6677889900', '1983-04-22', 'City8', 'linda@example.com', 'adminPass2023', '2023-01-08', '32123', 'User');

INSERT INTO user_role(role_id, user_id) VALUES (1,1), (2,3),(1,2),(1,4),(1,5),(1,6),(1,7),(1,8);

INSERT INTO facility (name, description, created_at, address, city, total_rating, active) VALUES
                                                                                              ('Fitness Center Alpha', 'Modern fitness center with state-of-the-art equipment, a variety of fitness classes, and personal training options.', '2023-01-08', 'Main Street 1', 'City1', 10,false),
                                                                                              ('Wellness Hub Beta', 'A premier destination for relaxation, featuring massage therapy, spa treatments, and wellness workshops.', '2023-02-08', 'Second Avenue 23', 'City2', 10, false),
                                                                                              ('Gym Gamma', 'High-end gym offering a comprehensive range of fitness services, including professional training, group fitness classes, and nutritional advice.', '2023-03-08', 'Third Boulevard 5', 'City3', 10,false),
                                                                                              ('Yoga Studio Delta', 'Peaceful yoga studio with experienced instructors specializing in various styles of yoga and meditation for all skill levels.', '2023-04-08', 'Fourth Street 12', 'City4', 10,false),
                                                                                              ('CrossFit Box Epsilon', 'CrossFit facility designed for high-intensity workouts, featuring a range of functional fitness equipment and expert coaching.', '2023-05-08', 'Fifth Avenue 8', 'City5', 10,false),
                                                                                              ('Strength & Conditioning Zeta', 'Specialized gym focusing on strength training and conditioning with advanced equipment and personalized training programs.', '2023-06-08', 'Sixth Street 20', 'City6', 10,false);
--                                                                                               ('Pilates Studio Eta', 'Dedicated Pilates studio offering reformer and mat classes, designed to enhance flexibility, core strength, and overall fitness.', '2023-07-08', 'Seventh Avenue 15', 'City7', 10, true),
--                                                                                               ('Martial Arts Academy Theta', 'Comprehensive martial arts training center featuring classes in various disciplines, from traditional arts to modern combat sports.', '2023-08-08', 'Eighth Boulevard 30', 'City8', 10, true),
--                                                                                               ('Aquatic Fitness Center Iota', 'Facility with a focus on aquatic exercise, including lap swimming, water aerobics, and swim lessons in a state-of-the-art pool.', '2023-09-08', 'Ninth Street 25', 'City9', 10, true),
--                                                                                               ('Dance & Fitness Studio Kappa', 'Energetic studio offering dance classes, fitness boot camps, and workshops to promote physical activity and creativity.', '2023-10-08', 'Tenth Avenue 18', 'City10', 10, true),
--                                                                                               ('Climbing Wall Lambda', 'Indoor climbing gym with various wall heights and difficulty levels, catering to climbers of all experience levels.', '2023-11-08', 'Eleventh Street 40', 'City11', 10, true),
--                                                                                               ('Running Club Mu', 'Fitness facility focusing on running programs, including group runs, coaching, and track training sessions.', '2023-12-08', 'Twelfth Avenue 10', 'City12', 10, true),
--                                                                                               ('Kickboxing Studio Nu', 'Kickboxing gym with a focus on high-intensity workouts, including kickboxing classes and personal training sessions.', '2024-01-08', 'Thirteenth Boulevard 25', 'City13', 10, true),
--                                                                                               ('Bodyweight Training Center Xi', 'Specialized gym offering bodyweight training programs and calisthenics, featuring equipment for bodyweight exercises.', '2024-02-08', 'Fourteenth Street 35', 'City14', 10, true),
--                                                                                               ('Nutrition & Fitness Hub Omicron', 'Facility combining fitness training with nutrition counseling, offering personalized fitness plans and dietary advice.', '2024-03-08', 'Fifteenth Avenue 45', 'City15', 10, true),
--                                                                                               ('Rehabilitation Fitness Center Pi', 'Fitness center focused on rehabilitation and recovery, offering specialized equipment and programs for injury recovery and physical therapy.', '2024-04-08', 'Sixteenth Street 50', 'City16', 10, true);
INSERT INTO disciplines(name) VALUES ('sauna'), ('masaza'), ('fitness'),('aerobik'),('bazen');

INSERT INTO work_day (valid_from, day, start_time, end_time)
VALUES
    ('2024-08-25', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-08-26', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-08-27', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-08-28', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-08-29', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-08-30', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-08-31', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-01', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-09-02', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-09-03', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-09-04', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-09-05', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-09-06', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-09-07', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-08', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-09-09', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-09-10', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-09-11', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-09-12', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-09-13', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-09-14', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-15', 6, '09:00:00', '17:00:00'); -- Nedelja


INSERT INTO work_day (valid_from, day, start_time, end_time)
VALUES
    ('2024-08-25', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-08-26', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-08-27', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-08-28', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-08-29', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-08-30', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-08-31', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-01', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-09-02', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-09-03', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-09-04', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-09-05', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-09-06', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-09-07', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-08', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-09-09', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-09-10', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-09-11', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-09-12', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-09-13', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-09-14', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-15', 6, '09:00:00', '17:00:00'); -- Nedelja


INSERT INTO work_day (valid_from, day, start_time, end_time)
VALUES
    ('2024-08-25', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-08-26', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-08-27', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-08-28', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-08-29', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-08-30', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-08-31', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-01', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-09-02', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-09-03', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-09-04', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-09-05', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-09-06', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-09-07', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-08', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-09-09', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-09-10', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-09-11', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-09-12', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-09-13', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-09-14', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-15', 6, '09:00:00', '17:00:00'); -- Nedelja


INSERT INTO work_day (valid_from, day, start_time, end_time)
VALUES
    ('2024-08-25', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-08-26', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-08-27', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-08-28', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-08-29', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-08-30', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-08-31', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-01', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-09-02', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-09-03', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-09-04', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-09-05', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-09-06', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-09-07', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-08', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-09-09', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-09-10', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-09-11', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-09-12', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-09-13', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-09-14', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-15', 6, '09:00:00', '17:00:00'); -- Nedelja



INSERT INTO work_day (valid_from, day, start_time, end_time)
VALUES
    ('2024-08-25', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-08-26', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-08-27', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-08-28', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-08-29', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-08-30', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-08-31', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-01', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-09-02', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-09-03', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-09-04', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-09-05', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-09-06', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-09-07', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-08', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-09-09', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-09-10', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-09-11', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-09-12', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-09-13', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-09-14', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-15', 6, '09:00:00', '17:00:00'); -- Nedelja


INSERT INTO work_day (valid_from, day, start_time, end_time)
VALUES
    ('2024-08-25', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-08-26', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-08-27', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-08-28', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-08-29', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-08-30', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-08-31', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-01', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-09-02', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-09-03', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-09-04', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-09-05', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-09-06', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-09-07', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-08', 6, '09:00:00', '17:00:00'), -- Nedelja
    ('2024-09-09', 0, '08:00:00', '16:00:00'), -- Ponedeljak
    ('2024-09-10', 1, '08:00:00', '16:00:00'), -- Utorak
    ('2024-09-11', 2, '08:00:00', '16:00:00'), -- Sreda
    ('2024-09-12', 3, '08:00:00', '16:00:00'), -- Četvrtak
    ('2024-09-13', 4, '08:00:00', '16:00:00'), -- Petak
    ('2024-09-14', 5, '09:00:00', '13:00:00'), -- Subota
    ('2024-09-15', 6, '09:00:00', '17:00:00'); -- Nedelja


INSERT INTO facility_work_days(facility_id, work_days_id) VALUES
                                                              (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11), (1, 12), (1, 13), (1, 14), (1, 15), (1, 16), (1, 17), (1, 18), (1, 19), (1, 20),
                                                              (2, 21), (2, 22), (2,23),(2, 24), (2, 25), (2, 26), (2, 27), (2, 28), (2, 29),(2, 30),(2, 31), (2, 32), (2, 33), (2, 34), (2, 35), (2, 36), (2, 37), (2, 38), (2, 39), (2, 40),
                                                              (3, 41), (3, 42), (3, 43), (3, 44), (3, 45), (3, 46), (3, 47), (3, 48), (3, 49), (3, 50), (3, 51), (3, 52), (3, 53), (3, 54), (3, 55), (3, 56), (3, 57), (3, 58), (3, 59), (3, 60),
                                                              (4, 61), (4, 62), (4, 63), (4, 64), (4, 65), (4, 66), (4, 67), (4, 68), (4, 69), (4, 70), (4, 71), (4, 72), (4, 73), (4, 74), (4, 75), (4, 76), (4, 77), (4, 78), (4, 79), (4, 80),
                                                              (5, 81), (5, 82), (5, 83), (5, 84), (5, 85), (5, 86), (5, 87), (5, 88), (5, 89), (5, 90), (5, 91), (5, 92), (5, 93), (5, 94), (5, 95), (5, 96), (5, 97), (5, 98), (5, 99), (5, 100),
                                                              (6, 101), (6, 102), (6, 103), (6, 104), (6, 105), (6, 106), (6, 107), (6, 108), (6, 109), (6, 110), (6, 111), (6, 112), (6, 113), (6, 114), (6, 115), (6, 116), (6, 117), (6, 118), (6, 119), (6, 120);


INSERT INTO disciplines_facilities(facilities_id, disciplines_id) VALUES (1,1),(1,2),(1,3);
INSERT INTO disciplines_facilities(facilities_id, disciplines_id) VALUES (2,1),(2,3);
INSERT INTO disciplines_facilities(facilities_id, disciplines_id) VALUES (3,1),(3,2),(3,4);
INSERT INTO disciplines_facilities(facilities_id, disciplines_id) VALUES (4,1),(4,2),(4,3),(4,4);
INSERT INTO disciplines_facilities(facilities_id, disciplines_id) VALUES (5,5),(5,3),(5,4);
