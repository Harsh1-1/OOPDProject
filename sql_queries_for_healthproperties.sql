insert into smarthealthdb.property values(1,"Distance Run","How far the user has run"); 
insert into smarthealthdb.property values(2,"Calories Burned","Calories burned by user"); 
insert into smarthealthdb.property values(3,"Systolic Blood Pressure","This is Highest Level of Blood Pressure"); 
insert into smarthealthdb.property values(4,"Diastolic Blood Pressure","This is Lowest Level of Blood Pressure");

alter table smarthealthdb.datum modify column DatumID INT(11) AUTO_INCREMENT;
