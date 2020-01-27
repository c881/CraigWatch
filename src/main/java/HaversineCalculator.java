public class HaversineCalculator implements DistanceCalculator{
    public static final double R = 6372.8; // In kilometers
    @Override
    public double calculate(Coordinate a, Coordinate b) {
        double lat1 = a.lat;
        double lat2 = b.lat;
        double lon1 = a.lon;
        double lon2 = b.lon;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double dist = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(dist));
        return R * c;

    }

    public static void main(String[] args) {
        Coordinate first = new Coordinate(31.9287517,34.805974);
        Coordinate second = new Coordinate(32.0334625,34.7602613);
        DistanceCalculator calculator = new HaversineCalculator();
        System.out.println(calculator.calculate(first, second));
    }
}
