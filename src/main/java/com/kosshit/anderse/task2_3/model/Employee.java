package com.kosshit.anderse.task2_3.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString(of = {"employeeId", "lastName", "email"})
@EqualsAndHashCode(exclude = "employeeId")
public class Employee {

    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String shortName;
    private String email;
    private String phoneNumber;
    private LocalDate birthday;
    private LocalDate dateOfStart;
    private Project project;
    private EmployerLevel devLevel;
    private EnglishLevel englishLevel;
    private String skype;
    private Feedback feedBack;
    private Team team;

    public Employee() {
    }

    public Employee(Integer employeeId, String firstName, String lastName, String middleName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public Employee(Integer employeeId, String firstName, String lastName, String middleName, String email, String phoneNumber, LocalDate birthday, LocalDate dateOfStart, Project project, EmployerLevel devLevel, EnglishLevel englishLevel, String skype, Feedback feedBack, Team team) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.dateOfStart = dateOfStart;
        this.project = project;
        this.devLevel = devLevel;
        this.englishLevel = englishLevel;
        this.skype = skype;
        this.feedBack = feedBack;
        this.team = team;
    }

    public EmployerLevel getEmpLevel() {
        return devLevel;
    }

    public void setEmpLevel(EmployerLevel devLevel) {
        this.devLevel = devLevel;
    }

    public void setEmpLevel(int i){
        switch (i){
            case 0: this.devLevel = EmployerLevel.J1;
                break;

            case 1: this.devLevel = EmployerLevel.J2;
                break;

            case 2: this.devLevel = EmployerLevel.M1;
                break;

            case 3: this.devLevel = EmployerLevel.M2;
                break;

            case 4: this.devLevel = EmployerLevel.M3;
                break;

            case 5: this.devLevel = EmployerLevel.S1;
                break;

            case 6: this.devLevel = EmployerLevel.S2;
                break;

        }
    }


    public void setEnglishLevel(EnglishLevel englishLevel) {
        this.englishLevel = englishLevel;
    }

    public void setEnglishLevel(int i) {
        switch (i){
            case 0: this.englishLevel = EnglishLevel.A1;
                break;

            case 1: this.englishLevel = EnglishLevel.A2;
                break;

            case 2: this.englishLevel = EnglishLevel.B1;
                break;

            case 3: this.englishLevel = EnglishLevel.B2;
                break;

            case 4: this.englishLevel = EnglishLevel.C1;
                break;

            case 5: this.englishLevel = EnglishLevel.C2;
                break;
        }
    }

}

