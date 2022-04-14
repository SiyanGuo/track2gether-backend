INSERT INTO category
(categoryname, transtype_id)
VALUES
("salary", 1)
("investment", 1)
("other", 1)
("housing", 2)
("utilities", 2)
("food", 2)
("transportation", 2)
("clothing", 2);

INSERT INTO transaction_type
(type)
VALUES
("income")
("expenses");

INSERT INTO

INSERT INTO users
(email, firstname, lastname, password)
VALUES
('jshim@gmail.com', 'Jiwon', 'Shim', 'pass1234'),
('sjan@gmail.com', 'Soma', 'Jan', 'pass1234'),
('sguo@gmail.com', 'Serena', 'Guo', 'pass1234'),
('bmaewada@gmail.com', 'Buhari', 'Maewada', 'pass1234');

INSERT INTO transaction
(amount, date, description, shared, category_id, user_id)
VALUES
(1840.73, "03/09/2022", "Bi-weekly wage direct deposit", false, 1, 1)
(1840.73, "03/23/2022", "Bi-weekly wage direct deposit", false, 1, 1)
(278.67, "03/12/2022", "Expense for the spring clothing", false, 8, 1)
(213.87, "03/13/2022", "Grocery shopping", false, 6, 1);