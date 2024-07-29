package Week1.DesignPatternsAndPrinciples.SingletonPatternExample;

public class Logger {
    private static Logger logger = new Logger();
    private String log;

    private Logger() {

    }
    
    public static Logger getInstance() {
        return logger;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        logger1.setLog("Test case 1");
        System.out.println(logger1.getLog());

        Logger logger2 = Logger.getInstance();
        logger2.setLog("Test case 2");
        System.out.println(logger1.getLog());
        System.out.println(logger2.getLog());
    } 
}
