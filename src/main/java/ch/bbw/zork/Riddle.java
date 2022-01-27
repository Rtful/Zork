package ch.bbw.zork;

public class Riddle {

    private final Lock lock;
    private final String solution;
    private final String secret;

    public Riddle(String description, String solution, String secret) {
        this.solution = solution;
        this.secret = secret;
        this.lock = new Lock(solution, description);
    }

    public boolean unlock(String attempt) {
        return this.lock.unlock(attempt);
    }

    public String getDescription() {
        return this.lock.getDescription();
    }

    public String getSecret(){
        return this.lock.isUnlocked() ? this.secret : null;
    }
}
