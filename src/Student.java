public class Student {
    private int id;
    private String name;
    private int duration;
    private double rating;

    public Student(int id, String name, int duration, double rating) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.rating = rating;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
