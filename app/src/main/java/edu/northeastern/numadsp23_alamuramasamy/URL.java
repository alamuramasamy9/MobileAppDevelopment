package edu.northeastern.numadsp23_alamuramasamy;

public class URL {
    private String name;
    private String url;

    public URL(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }
//    public URL getUrlObject(){
//        return this;
//    }



    public void setobjName(String name) {
        this.name=name;
    }
    public void setUrl(String url ) {
        this.url=url;
    }
}