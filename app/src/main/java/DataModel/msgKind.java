package DataModel;

public enum msgKind {
    none(0),
    SimpleText(1),
    Image(2),
    Audio(3),
    Video(4);

    msgKind(int i) {
    }

    public static  int toInt(msgKind kind){
        int value;
        switch (kind) {
            case SimpleText:
                value = 1;
                break;
            case Image:
                value = 2;
                break;
            case Audio:
                value = 3;
                break;
            case Video:
                value = 4;
                break;
            default: value=0;
        }
        return value;
    }

    public static msgKind valueOf(int value){
        msgKind kind;
        switch (value) {
            case 1:
                kind = SimpleText;
                break;
            case 2:
                kind = Image;
                break;
            case 3:
                kind = Audio;
                break;
            case 4:
                kind = Video;
                break;
            default: kind=none;
        }
        return kind;
    }
        }
