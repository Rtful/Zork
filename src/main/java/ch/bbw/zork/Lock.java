package ch.bbw.zork;

import java.util.Objects;

public class Lock {
    private final Item key;
    private final String code;
    private final String description;
    private boolean unlocked = false;
    private final String type;

    public Lock(String code, String description){
        this.key = null;
        this.code = code;
        this.description = description;
        this.type = "code";
    }

    public Lock(Item key, String description){
        this.key = key;
        this.code = null;
        this.description = description;
        this.type = "key";
    }

    public boolean isUnlocked(){
        return this.unlocked;
    }

    public String getDescription() {
        return description;
    }

    public boolean unlock(String code){
        return this.unlocked = Objects.equals(this.code, code);
    }

    public boolean unlock(Item key){
        return this.unlocked = Objects.equals(this.key, key);
    }

    public String getType(){
        return this.type;
    }
}
