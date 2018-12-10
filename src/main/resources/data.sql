/*initialize default users to be used on sign in, the password value is `testpassword`*/
INSERT INTO
  Users (username,password)
  VALUES ('testuser','$2a$10$i2R5GVCMo6my8.jqkaEMa.k90aJHMNHOlGY00XHR.gSN0lbpSm/WG'),
  ('testadmin','$2a$10$i2R5GVCMo6my8.jqkaEMa.k90aJHMNHOlGY00XHR.gSN0lbpSm/WG');

/* for role based access */
INSERT  INTO
  role (name) VALUES ('ROLE_ADMIN'),('ROLE_USER');
INSERT INTO
  userroles (user_id,role_id) VALUES (1,2),(2,1);

/* initialize client apps to be used for authorization, the client_secret is `demosecret` */
INSERT INTO
  oauth_client_details (client_id,resource_ids,client_secret,scope,
    authorized_grant_types,authorities,access_token_validity,
    refresh_token_validity,autoapprove)
  values ('democlient','securitydemoservice','$2a$10$18h4zM9cP.fffd7DvnYss.ao/6P7j/Yg/lpNMjeM08FfNaZU5JfPu','read,write,trust',
    'password','admin',300,
    300,'true'),('client','securitydemoservice','$2a$10$18h4zM9cP.fffd7DvnYss.ao/6P7j/Yg/lpNMjeM08FfNaZU5JfPu','read,write,trust',
    'authorization_code','admin',300,
    300,'true');