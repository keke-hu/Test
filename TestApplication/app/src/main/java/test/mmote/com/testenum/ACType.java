package test.mmote.com.testenum;

/**
 * Created by KeKe on 2017/8/16.
 */

public enum ACType {
    USER_AGREE_SERVICE("1"),
    USER_AGREE_COMPLETE("2");

    private String type;

    ACType(String type) {
        this.type = type;
    }

    private String getType() {
        return type;
    }

    public static ACType getAcType(String type) {
        for (ACType acType : values()) {
            if (acType.getType().equals(type)) {
                return acType;
            }
        }
        return null;
    }
}
