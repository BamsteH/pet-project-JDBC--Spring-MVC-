CREATE TABLE Departments(
    dpID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    dpName TEXT
);

CREATE TABLE Employees(
    empId BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    empName TEXT,
    empActive boolean,
    emp_dpID BIGINT,
    FOREIGN KEY (emp_dpID) REFERENCES Departments(dpID)
);

CREATE TABLE Notifications(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    message VARCHAR(255) NOT NULL,
    payload JSON
)