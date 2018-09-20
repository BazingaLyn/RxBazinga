package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

/**
 * @author liguolin
 * @create 2018-07-18 16:46
 **/
public interface RemotingAddressParser {


    Url parse(String url);


    String parseUniqueKey(String url);


    String parseProperty(String url, String propKey);


    void initUrlArgs(Url url);


    /** symbol : */
    public static final char COLON = ':';

    /** symbol = */
    public static final char EQUAL = '=';

    /** symbol & */
    public static final char AND   = '&';

    /** symbol ? */
    public static final char QUES  = '?';

}
