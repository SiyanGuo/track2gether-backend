INSERT INTO transaction_type
(type)
VALUES
('income'),
('expenses');

INSERT INTO category
(categoryname, transtype_id)
VALUES
('salary', 1),
('investment', 1),
('other', 1),
('housing', 2),
('utilities', 2),
('food', 2),
('transportation', 2),
('clothing', 2);


INSERT INTO users
(email, firstname, lastname, password)
VALUES
('jshim@gmail.com', 'Jiwon', 'Shim', 'pass1234'),
('sjan@gmail.com', 'Soma', 'Jan', 'pass1234'),
('sguo@gmail.com', 'Serena', 'Guo', 'pass1234'),
('bmaewada@gmail.com', 'Buhari', 'Maewada', 'pass1234');


INSERT INTO transaction
(amount, category_id, description, shared, user_id)
VALUES
(1840.73, 1, 'Bi-weekly wage direct deposit', false, 1),
(213.87, 6, 'Grocery shopping', false, 1);


