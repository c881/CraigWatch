public class AssetsWrapper {
    Asset ownAsset;
    Asset suspectAsset;
    double distance;

    public AssetsWrapper(Asset ownAsset, Asset suspectAsset, double distance){
        this.ownAsset = ownAsset;
        this.suspectAsset = suspectAsset;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "ownAsset=" + ownAsset +
                ", suspectAsset=" + suspectAsset +
                ", distance=" + distance;
    }
}

