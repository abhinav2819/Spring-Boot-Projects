INSERT INTO patient_tbl(name, email, date_of_birth, gender, blood_group)
VALUES
  ('Raj Kumar','raj@gmail.com','1999-03-01','male','O_Positive'),
  ('Kamlesh Kumar','kamlesh@gmail.com','1989-05-08','male','O_Negative'),
  ('Kajal Kumari','kajal@gmail.com','2000-12-29','female','O_Positive'),
  ('Kalavati devi','kalavati@gmail.com','2005-07-21','female','A_Positive'),
  ('Rani Kumari','rani@gmail.com','2008-09-11','female','B_Positive'),
  ('Shekhar Kumar','shekhar@gmail.com','2001-05-01','male','AB_Positive');

INSERT INTO doctor (name, specialization, email)
VALUES
    ('Dr. Rakesh Mehta', 'Cardiology', 'rakesh.mehta@gmail.com'),
    ('Dr. Sneha Kapoor', 'Dermatology', 'sneha.kapoor@gmail.com'),
    ('Dr. Navin Raman', 'ENT', 'navin.raman@gmail.com'),
    ('Dr. Gukesh Singh', 'Orthology', 'gukesh.singh@gmail.com');

INSERT INTO appointment (appointment_time, reason, doctor_id, patient_id)
VALUES
    ('2025-12-05 11:30:00', 'General Checkup', '1', '2'),
    ('2025-12-05 10:30:00', 'Blood pressure Checkup', '2', '4'),
    ('2025-12-05 12:30:00', 'Knee pain', '3', '5'),
    ('2025-12-12 10:30:00', 'Skin Rash', '4', '2'),
    ('2025-12-05 10:30:00', 'Follow-on Checkup', '2', '4'),
    ('2025-12-05 13:00:00', 'Migraine', '1', '1'),
    ('2025-12-19 10:30:00', 'Routine Checkup', '2', '4');