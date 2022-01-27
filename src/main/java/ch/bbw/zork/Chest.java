package ch.bbw.zork;

public class Chest {
    private final Lock lock;

    public Chest(Lock lock) {
        this.lock = lock;
    }

    public boolean isOpen() {
        return this.lock == null || this.lock.isUnlocked();
    }

    public boolean unlock(String code) {
        return this.lock.unlock(code);
    }

    public boolean unlock(Item key) {
        return this.lock.unlock(key);
    }

    public String getDescription() {
        return this.lock.getDescription();
    }

    public String getLockType() {
        return this.lock.getType();
    }
}

