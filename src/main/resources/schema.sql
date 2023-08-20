CREATE MEMORY TABLE "PUBLIC"."DEPARTMENTS"(
    "DEPARTMENT_ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "DEPARTMENT_NAME" CHARACTER VARYING(255)
);            
ALTER TABLE "PUBLIC"."DEPARTMENTS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_9" PRIMARY KEY("DEPARTMENT_ID");       

CREATE MEMORY TABLE "PUBLIC"."JOBS"(
    "SALARY" FLOAT(24) NOT NULL,
    "JOB_ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "JOB_TITLE" CHARACTER VARYING(255)
);              
ALTER TABLE "PUBLIC"."JOBS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2" PRIMARY KEY("JOB_ID");     
      
CREATE MEMORY TABLE "PUBLIC"."LOCATIONS"(
    "LOCATION_ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "CITY" CHARACTER VARYING(255),
    "COUNTRY" CHARACTER VARYING(255)
);    
ALTER TABLE "PUBLIC"."LOCATIONS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_5" PRIMARY KEY("LOCATION_ID");   
CREATE MEMORY TABLE "PUBLIC"."EMPLOYEES"(
    "AGE" INTEGER NOT NULL,
    "DEPARTMENT_ID" BIGINT,
    "EMPLOYEE_ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "JOB_ID" BIGINT,
    "LOCATION_ID" BIGINT,
    "EMPLOYEE_NAME" CHARACTER VARYING(255),
    "SEX" CHARACTER VARYING(255)
);    
ALTER TABLE "PUBLIC"."EMPLOYEES" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" PRIMARY KEY("EMPLOYEE_ID");           
        
ALTER TABLE "PUBLIC"."EMPLOYEES" ADD CONSTRAINT "PUBLIC"."FKNPQYU6U0FDH2RMQTOUE23GXB4" FOREIGN KEY("JOB_ID") REFERENCES "PUBLIC"."JOBS"("JOB_ID") NOCHECK;    
ALTER TABLE "PUBLIC"."EMPLOYEES" ADD CONSTRAINT "PUBLIC"."FKGY4QE3DNQRM3KTD76SXP7N4C2" FOREIGN KEY("DEPARTMENT_ID") REFERENCES "PUBLIC"."DEPARTMENTS"("DEPARTMENT_ID") NOCHECK;               
ALTER TABLE "PUBLIC"."EMPLOYEES" ADD CONSTRAINT "PUBLIC"."FKIRH0A5Q2EN4GKBAYQHGFIWHS" FOREIGN KEY("LOCATION_ID") REFERENCES "PUBLIC"."LOCATIONS"("LOCATION_ID") NOCHECK; 