public class House {
    private int id;
    private int duration;
    private double rating;

    public House(int id, int duration, double rating) {
        this.id = id;
        this.duration = duration;
        this.rating = rating;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public double getRating() {
        return rating;
    }
    public void updateDuration(){
        this.duration--;
    }
}
