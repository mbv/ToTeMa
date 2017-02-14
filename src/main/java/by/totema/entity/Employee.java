package by.totema.entity;

public class Employee {

    private long id;
    private String name;
    private String title;
    private long yearSalary;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getYearSalary() {
        return yearSalary;
    }

    public void setYearSalary(long yearSalary) {
        this.yearSalary = yearSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (title != null ? !title.equals(employee.title) : employee.title != null) return false;
        return yearSalary != employee.yearSalary;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (int) (yearSalary ^ (yearSalary >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", yearSalary='" + yearSalary + '\'' +
                '}';
    }
}
