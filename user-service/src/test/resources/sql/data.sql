INSERT INTO app.role
VALUES ('ADMIN'),('USER');

INSERT INTO app.status
VALUES ('ACTIVATED'),('WAITING_ACTIVATION'),('DEACTIVATED');


INSERT INTO app.users
     VALUES ('66c83144-c4ca-4e88-a5b1-4c94c0b91449','admim.admin@admin.com',
			 'Admin Admin',now(),now(),
			 '$2a$10$if.MoZ5HQHMgUjLDP1gIDOgNTFy4txpW2.WUBhWYltqEht3dlbBEu');

INSERT INTO app.user_role
	 VALUES ('66c83144-c4ca-4e88-a5b1-4c94c0b91449', 'ADMIN');

INSERT INTO app.user_status
	 VALUES ('66c83144-c4ca-4e88-a5b1-4c94c0b91449', 'ACTIVATED');