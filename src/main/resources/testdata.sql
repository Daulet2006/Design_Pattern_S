-- Тестовые отделы
INSERT INTO department (id, name) VALUES
  (1, 'IT'),
  (2, 'HR'),
  (3, 'Finance');

-- Тестовые сотрудники
INSERT INTO employee (id, name, position, base_salary, type, date, department_id) VALUES
  (1, 'Алихан', 'Разработчик', 350000, 'Permanent', '2023-01-15', 1),
  (2, 'Жанна', 'HR-менеджер', 250000, 'Permanent', '2022-11-01', 2),
  (3, 'Данияр', 'Бухгалтер', 300000, 'Contract', '2023-03-10', 3),
  (4, 'Салтанат', 'Тестировщик', 200000, 'Remote', '2023-02-20', 1);