package org.EduRegisterationSystem.Data;

import org.EduRegisterationSystem.Entity.Doctor;
import org.EduRegisterationSystem.Entity.Student;
import org.EduRegisterationSystem.Helper.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.function.Predicate;

public class DoctorRepository implements Repository<Doctor> {

    private List<Doctor> doctors;
    private volatile static Repository doctorRepository = null;
    private DoctorRepository() {
        doctors = new ArrayList<>();
        generateDummyData();
    }

    public static Repository getInstance() {
        if (doctorRepository == null) {
            synchronized (DoctorRepository.class) {
                if (doctorRepository == null) {
                    doctorRepository = new DoctorRepository();
                }
            }
        }
        return doctorRepository;
    }

    @Override
    public List<Doctor> getAll() {
        return doctors;
    }

    @Override
    public void setAll(List<Doctor> entities) {
        doctors = entities;
    }

    @Override
    public void add(Doctor entity) {
        doctors.add(entity);
    }

    @Override
    public Doctor remove(int index) {
        return doctors.remove(index);
    }

    @Override
    public Doctor get(int index) {
        return doctors.get(index);
    }

    @Override
    public Doctor findBy(Predicate<Doctor> filter) throws Exception {
        for (var doctor : doctors) {
            if (filter.test(doctor)) return doctor;
        }
        throw new Exception("Doctor not found");
    }

    @Override
    public boolean contains(Predicate<Doctor> filter) {
        for (var doctor : doctors) {
            if (filter.test(doctor))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void set(int index, Doctor entity) {
        doctors.set(index,entity);
    }

    public void generateDummyData() {
        Vector<String> names = new Vector<>(Arrays.asList("Ali", "Mostafa", "Hani", "Mohamed", "Ashraf", "Samy", "Morad", "Sayed", "Hussien"));
        for (int i = 1; i <= 9; ++i) {
            Doctor doctor = new Doctor();
            doctor.setUserName(Helper.generateID(i, 3, "d"));
            doctor.setPassword(Helper.generateID(i, 3, "d"));
            doctor.setName(names.get((int) (Math.random() * 9)) + " " + names.get((int) (Math.random() * 9)));
            doctor.setId(Helper.generateID(i, 3, "d") + (int) (Math.random() * 12345));
            doctor.setEmail(Helper.generateID(i, 3, "d") + "@gmail.com");
            doctors.add(doctor);
        }
    }
}
