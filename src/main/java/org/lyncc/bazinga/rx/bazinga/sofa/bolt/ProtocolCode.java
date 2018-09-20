package org.lyncc.bazinga.rx.bazinga.sofa.bolt;

import java.util.Arrays;

/**
 * @author liguolin
 * @create 2018-07-21 21:01
 **/
public class ProtocolCode {

    byte[] version;

    private ProtocolCode(byte... version) {
        this.version = version;
    }

    public static ProtocolCode fromBytes(byte... version) {
        return new ProtocolCode(version);
    }

    /**
     * get the first single byte if your protocol code is single code.
     * @return
     */
    public byte getFirstByte() {
        return this.version[0];
    }

    public int length() {
        return this.version.length;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ProtocolCode that = (ProtocolCode) o;
        return Arrays.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(version);
    }

    @Override
    public String toString() {
        return "ProtocolVersion{" + "version=" + Arrays.toString(version) + '}';
    }
}
