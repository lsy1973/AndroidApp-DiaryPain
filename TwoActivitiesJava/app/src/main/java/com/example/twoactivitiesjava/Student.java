package com.example.twoactivitiesjava;


import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

    private String name = "";
    private String surname = "";
    private int age = 0;

    public Student(Parcel in) {
        this.name = in.readString();
        this.surname = in.readString();
        this.age = in.readInt();
    }

    public Student(String name, String surname, int age){
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeInt(age);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public static Creator<Student> getCREATOR() {
        return CREATOR;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getSurname()
    {
        return surname;
    }
    public void setAge(int age){
        this.age = age;
    }
    public int getAge()
    {
        return age;
    }
}

