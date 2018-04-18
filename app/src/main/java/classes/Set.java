package classes;

/**
 * Created by Ren on 06/04/2018.
 */

public class Set {
    private String set_id;
    private String set_name;
    private String set_year;
    private String set_size;
    private String set_desc;

    public Set(){}

    public Set(String set_id, String set_name, String set_year, String set_size, String set_desc) {
        this.set_id = set_id;
        this.set_name = set_name;
        this.set_year = set_year;
        this.set_size = set_size;
        this.set_desc = set_desc;
    }

    public String getSet_id() {
        return set_id;
    }

    public void setSet_id(String set_id) {
        this.set_id = set_id;
    }

    public String getSet_name() {
        return set_name;
    }

    public void setSet_name(String set_name) {
        this.set_name = set_name;
    }

    public String getSet_year() {
        return set_year;
    }

    public void setSet_year(String set_year) {
        this.set_year = set_year;
    }

    public String getSet_size() {
        return set_size;
    }

    public void setSet_size(String set_size) {
        this.set_size = set_size;
    }

    public String getSet_desc() {
        return set_desc;
    }

    public void setSet_desc(String set_desc) {
        this.set_desc = set_desc;
    }

    @Override
    public String toString() {
        return "Set{" +
                "set_id=" + set_id +
                ", set_name='" + set_name + '\'' +
                ", set_year=" + set_year +
                ", set_size=" + set_size +
                ", set_desc='" + set_desc + '\'' +
                '}';
    }
}
