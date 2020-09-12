package demo;

public class Film {
    /**
     * String value for film title
     */
    private String filmTitle;

    /**
     * String value for film rating (G, PG, PG-13, R)
     */
    private String filmRating;

    /**
     * String value for year the film was released
     */
    private String yearReleased;

    /**
     * String value for month the film was last watched
     */
    private String month;

    /**
     * String value for day the film was last watched
     */
    private String day;

    /**
     * String value for year the film was last watched
     */
    private String year;

    /**
     * Double value for number of stars out of 5
     */
    private String numberOfStars;

    /**
     * Default constructor
     */
    public Film() {
        filmTitle = "";
        filmRating = "";
        yearReleased = "";
        month = "";
        day = "";
        year = "";
    }

    /**
     * Function to get film title
     * @return String
     */
    public String getFilmTitle() {
        return this.filmTitle;
    }

    public String getFilmRating() {
        return this.filmRating;
    }

    public String getYearReleased() {
        return this.yearReleased;
    }

    public String getNumberOfStars() {
        return this.numberOfStars;
    }

    public String getMonth() {
        return this.month;
    }

    public String getDay() {
        return this.day;
    }

    public String getYear() {
        return this.year;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public void setFilmRating(String filmRating) {
        this.filmRating = filmRating;
    }

    public void setYearReleased(String yearReleased) {
        this.yearReleased = yearReleased;
    }

    public void setNumberOfStars(String numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
