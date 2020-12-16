package test.java.stream;

import java.util.List;

public class Article {
 
    private final String       title;
    private final String       author;
    private final List<String> tags;
    private final String       countryCode;
    private final String       province;
 
    public Article(String title, String author, List<String> tags, String countryCode, String province){
        this.title = title;
        this.author = author;
        this.tags = tags;
        this.countryCode = countryCode;
        this.province = province;
    }
 
    public String getTitle() {
        return title;
    }
 
    public String getAuthor() {
        return author;
    }
 
    public List<String> getTags() {
        return tags;
    }
 
    public String getCountryCode() {
        return countryCode;
    }
 
    public String getProvince() {
        return province;
    }
 
}