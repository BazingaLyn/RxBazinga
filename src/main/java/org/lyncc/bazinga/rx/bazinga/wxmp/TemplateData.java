package org.lyncc.bazinga.rx.bazinga.wxmp;

/**
 * @author liguolin
 * @create 2018-07-13 10:54
 **/
public class TemplateData {

    private String value;
    private String color;
    public TemplateData(String value,String color){
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
