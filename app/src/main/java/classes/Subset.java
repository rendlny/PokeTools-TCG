package classes;

/**
 * Created by Ren on 06/04/2018.
 */

public class Subset {
    private String subset_id;
    private String subset_name;
    private String subset_desc;
    private String subset_size;
    private String subset_year;
    private String set_id;

    public Subset(){}

    public Subset(String subset_id, String subset_name, String subset_desc, String subset_size,
                  String subset_year, String set_id) {
        this.subset_id = subset_id;
        this.subset_name = subset_name;
        this.subset_desc = subset_desc;
        this.subset_size = subset_size;
        this.subset_year = subset_year;
        this.set_id = set_id;
    }

    public String getSubset_id() {
        return subset_id;
    }

    public void setSubset_id(String subset_id) {
        this.subset_id = subset_id;
    }

    public String getSubset_name() {
        return subset_name;
    }

    public void setSubset_name(String subset_name) {
        this.subset_name = subset_name;
    }

    public String getSubset_desc() {
        return subset_desc;
    }

    public void setSubset_desc(String subset_desc) {
        this.subset_desc = subset_desc;
    }

    public String getSubset_size() {
        return subset_size;
    }

    public void setSubset_size(String subset_size) {
        this.subset_size = subset_size;
    }

    public String getSubset_year() {
        return subset_year;
    }

    public void setSubset_year(String subset_year) {
        this.subset_year = subset_year;
    }

    public String getSet_id() {
        return set_id;
    }

    public void setSet_id(String set_id) {
        this.set_id = set_id;
    }

    @Override
    public String toString() {
        return "Subset{" +
                "subset_id='" + subset_id + '\'' +
                ", subset_name='" + subset_name + '\'' +
                ", subset_desc='" + subset_desc + '\'' +
                ", subset_size='" + subset_size + '\'' +
                ", subset_year='" + subset_year + '\'' +
                ", set_id='" + set_id + '\'' +
                '}';
    }
}

