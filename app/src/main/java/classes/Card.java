package classes;

/**
 * Created by Ren on 06/04/2018.
 */

public class Card {
    private String card_id;
    private String card_name;
    private String card_desc;
    private String card_art;
    private String card_artist;
    private String card_rarity;
    private String card_type;
    private String card_set_num;
    private String set_id;
    private String subset_id;

    public Card(){}

    public Card(String card_id, String card_name, String card_desc, String card_art,
                String card_artist, String card_rarity, String card_type, String card_set_num,
                String set_id, String subset_id) {
        this.card_id = card_id;
        this.card_name = card_name;
        this.card_desc = card_desc;
        this.card_art = card_art;
        this.card_artist = card_artist;
        this.card_rarity = card_rarity;
        this.card_type = card_type;
        this.card_set_num = card_set_num;
        this.set_id = set_id;
        this.subset_id = subset_id;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getCard_desc() {
        return card_desc;
    }

    public void setCard_desc(String card_desc) {
        this.card_desc = card_desc;
    }

    public String getCard_art() {
        return card_art;
    }

    public void setCard_art(String card_art) {
        this.card_art = card_art;
    }

    public String getCard_artist() {
        return card_artist;
    }

    public void setCard_artist(String card_artist) {
        this.card_artist = card_artist;
    }

    public String getCard_rarity() {
        return card_rarity;
    }

    public void setCard_rarity(String card_rarity) {
        this.card_rarity = card_rarity;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getCard_set_num() {
        return card_set_num;
    }

    public void setCard_set_num(String card_set_num) {
        this.card_set_num = card_set_num;
    }

    public String getSet_id() {
        return set_id;
    }

    public void setSet_id(String set_id) {
        this.set_id = set_id;
    }

    public String getSubset_id() {
        return subset_id;
    }

    public void setSubset_id(String subset_id) {
        this.subset_id = subset_id;
    }

    @Override
    public String toString() {
        return "Card{" +
                "card_id='" + card_id + '\'' +
                ", card_name='" + card_name + '\'' +
                ", card_desc='" + card_desc + '\'' +
                ", card_art='" + card_art + '\'' +
                ", card_artist='" + card_artist + '\'' +
                ", card_rarity='" + card_rarity + '\'' +
                ", card_type='" + card_type + '\'' +
                ", card_set_num='" + card_set_num + '\'' +
                ", set_id='" + set_id + '\'' +
                ", subset_id='" + subset_id + '\'' +
                '}';
    }
}
