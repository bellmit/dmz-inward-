package dmztest;

/**
 * Created by dmz on 2016/8/17.
 */
class Department {
    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer id;
    private String name;

}

class Employee implements Cloneable {

    public static void testCloneable() {
        System.out.println("Cloneable...");
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    private Department department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private Integer age;

    public Employee(String name, Integer age, Department department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

public class CloneExample {
//    public static  T clone(T t) throws Exception {
//        //Check if T is instance of Serializeble other throw CloneNotSupportedException
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bos);
////        objectOutputStream.writeObject(t);
//
//        byte[] bytes = bos.toByteArray();
//        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
//
//        //Deserialize it and return the new instance
//        SerializationUtils.clone(t);
//        return (T)ois.readObject();
//    }

    public static void main(String args[]) throws CloneNotSupportedException {
        Department department = new Department(1, "IT");
        Employee employee = new Employee("DMZ", 1000, department);
//        employee.testCloneable();
        Employee emp = (Employee) employee.clone();
//        Cloneable c = (Cloneable) employee.clone();
//        System.out.println(c.);
        System.out.println(emp == employee);
        System.out.println(emp.getClass() == employee.getClass());
        System.out.println(emp.equals(employee));

        employee.setName("YM");
        employee.setAge(2000);
//        employee.getDepartment().setName("new IT");

        System.out.println(emp.getName());
        System.out.println(emp.getAge());
        System.out.println(emp.getDepartment().getName());
        emp.testCloneable();

    }

    public static void Test() {
        try {
            throw new NullPointerException();
//        } catch (NullPointerException | IndexOutOfBoundsException e) {
//            e.printStackTrace();
//            throw e;
        } catch (Throwable ex) {
            throw ex;
        }
    }
}
