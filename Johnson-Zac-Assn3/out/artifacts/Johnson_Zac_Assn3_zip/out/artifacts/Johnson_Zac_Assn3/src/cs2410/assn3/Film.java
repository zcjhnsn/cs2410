package cs2410.assn3;

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

    /**
     * Function to get film rating
     * @return String
     */
    public String getFilmRating() {
        return this.filmRating;
    }

    /**
     * Returns year film was released
     * @return String
     */
    public String getYearReleased() {
        return this.yearReleased;
    }

    /**
     * Returns number of stars
     * @return String
     */
    public String getNumberOfStars() {
        return this.numberOfStars;
    }

    /**
     * Returns month last watched
     * @return String
     */
    public String getMonth() {
        return this.month;
    }

    /**
     * Returns day last watched
     * @return String
     */
    public String getDay() {
        return this.day;
    }

    /**
     * Returns year last watched
     * @return String
     */
    public String getYear() {
        return this.year;
    }

    /**
     * Sets film title
     * @param filmTitle film title
     */
    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    /**
     * Sets film rating
     * @param filmRating film rating
     */
    public void setFilmRating(String filmRating) {
        this.filmRating = filmRating;
    }

    /**
     * Sets year film was released
     * @param yearReleased year film was released
     */
    public void setYearReleased(String yearReleased) {
        this.yearReleased = yearReleased;
    }

    /**
     * Sets number of stars
     * @param numberOfStars number of stars out of 5
     */
    public void setNumberOfStars(String numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    /**
     * sets month last watched
     * @param month month last watched
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * Sets day of month last watched
     * @param day day last watched
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * Sets year of month last watched
     * @param year year last watched
     */
    public void setYear(String year) {
        this.year = year;
    }
}
