import java.util.concurrent.Callable;

public class MyCallable implements Callable<CraigAsset> {
    private String link;

    @Override
    public CraigAsset call() throws Exception {
        return Parser.getCraigAsset(link);
    }

    public void setLink(String link) {
        this.link = link;
    }
}
