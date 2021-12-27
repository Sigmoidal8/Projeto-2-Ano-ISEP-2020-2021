
insert into adress values('2074400-032', 'R. Dr. António Granjo', 207,'4400-032','Porto',41.13522572, -8.62001961);
insert into adress values('2104150-417', 'R. Dom João de Castro', 210,'4150-417','Porto',41.15947751,-8.65950997);
insert into adress values('2004050-573', 'Terreiro da Sé', 200,'4050-573','Porto',41.14384141, -8.61106636);
insert into adress values('764050-556', 'R. do Comércio do Porto', 76,'4050-556','Porto', 41.14197032, -8.61645204);
insert into adress values('14200-072', 'Instituto Superior de Engenharia do Porto', 1,'4200-072','Porto', 41.177565, -8.607298);

insert into pharmacy values('1', 'Bayer', 'lapr3g029@gmail.com', 'Lapr32021', '2074400-032');

insert into parkinglot values('1',20,1, 'Scooter', '1');

insert into product values('Moderna7', 'Moderna', 30 ,4);
insert into product values('Pfizer6', 'Pfizer', 25, 3);

insert into pharmacy_product values('1', 'Moderna7', 3);
insert into pharmacy_product values('1', 'Pfizer6', 10);

insert into client values('N921321544', 'Nuno Figueiredo', 'nunoF', '1190986@isep.ipp.pt', '921321544',0,'2104150-417');
insert into client values('S252145214', 'Sandro Moura', 'sandroM', '1191051@isep.ipp.pt', '252145214',30.6,'2004050-573');
insert into client values('J453123456', 'João Forno', 'joaoF', '1190778@isep.ipp.pt', '453123456',0,'764050-556');
insert into client values('M123456789', 'Miguel Soares', 'miguelS', '1190780@isep.ipp.pt', '123456789',0,'14200-072');

insert into courier values('422145321','Luís Silva', '1191091@isep.ipp.pt', '3412213', 'luisS');

insert into scooter values(1,300,300,1,350,'1'); 
insert into scooter values(2,300,300,1,350,'1');
insert into scooter values(3,300,300,0,350, NULL); 

insert into invoice values('INV/14/11/2020/13/44/12',to_date('2020-11-14', 'yyyy-mm-dd'), 30.6, 'S252145214');

insert into invoice_product values('INV/14/11/2020/13/44/12', 'Moderna7',1);

insert into delivery values('1', to_date('2020-11-16', 'yyyy-mm-dd'), 4, 'INV/14/11/2020/13/44/12', '1');
insert into delivery values('2', to_date('2020-11-16', 'yyyy-mm-dd'), 4, 'INV/14/11/2020/13/44/12', '1');

insert into drone values(4,300,300,250,'1');

